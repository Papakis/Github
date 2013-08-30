getWebserviceURL=function(){
    var URL="https://localhost:8443/webservice/";     //webserivceURL
    return URL;
}

// knockouts ViewModels

var masterViewModel = {
	repoName : ko.observable("Github"),
	userName : ko.observable("Papakis"),
    error: ko.observable(""),
    user: ko.observable(),
    password: ko.observable(),
    token: ko.observable(),
    signedIn: ko.observable(false),
	totalCommits: ko.observable(0),
	totalContributors: ko.observable(0),
    contributorsReady: ko.observable(false)
}

var userViewModel = {
	users: ko.observableArray()
}

var repoViewModel = {
	repository : ko.observable(""),
	repoErrorMessage : ko.observable(""),
	avgCommits: ko.observable(0)
}

//Functions

//Creating CSV String
getUsersCSV = function(){
	console.log("getUsersCSV| ");
	ConvertToCSV(userViewModel.users());
}

getRepoCSV = function(){
	console.log("getRepoCSV| ");
	ConvertToCSV(repoViewModel.repository());
}

getCommitsCSV = function(){
    console.log("getCommitsCSV| ");
    function Commit(username,date,time,messageLength,message){
        this.username=username;
        this.date=date;
        this.time=time;
        this.messageLength=messageLength;
        this.message=message;
    }
    var commits=[];         //array with commits to generate CSV
    $.each(userViewModel.users(),function(i,user){
        $.each(user.commitMessages,function(j,commit){
            var message='"'+commit[3].replace(/"/g,"'").replace(/\n/g, '\t ')+'"'; //formatting commit messages - changing
                                                                                    // double quotes to single quotes
                                                                                    // and new lines to tabs
            commits.push(new Commit(user.login,commit[0],commit[1],commit[2],message));
        })
    });
    ConvertToCSV(commits);
}

getRepoInfo=function() {            // get Repo Info from webservice
    console.log('getRepoInfo');
    $.ajax({
        url: getWebserviceURL()+'repos/'+ masterViewModel.userName() + '/' + masterViewModel.repoName(),
        type: 'POST',
        data: {token:masterViewModel.token()},
        success: function(data) {
            if(!checkError(data)){
                console.log('getRepoInfo| Success| CheckPassed');
                repoViewModel.repository(data);
                $("#notFound").hide();
                $("#content").show();
                getContributors();
            }
        },
        error: function(data){
            unknownErrorDisplay();
            console.log('getRepoInfo| Fail');
            console.log(data.message);
        }

    });
}

getContributors=function(){        // get Contributors of current repository
	console.log("getContributors");
    userViewModel.users([]);
    var url=getWebserviceURL()+"repos/"+masterViewModel.userName()+"/"+masterViewModel.repoName()+"/contributors";
    $.ajax({
        url: url,
        type: 'POST',
        data: {token:masterViewModel.token()},
        success: function( data ) {
			masterViewModel.totalCommits(0);
			masterViewModel.totalContributors(0);
            if(!checkError(data)){
				console.log("getContributors| Success")
				
                $.each(data, function(index, item){
					//Updating values to calculate average
					masterViewModel.totalCommits(masterViewModel.totalCommits()+item.total);
					masterViewModel.totalContributors(masterViewModel.totalContributors()+1);

                    getUser(item.user.login,item.total);    // get detailed info about each contributor
                });
				masterViewModel.contributorsReady(true);
				repoViewModel.avgCommits(masterViewModel.totalCommits()/masterViewModel.totalContributors());
            }
        },
        error: function( data ) {
			console.log("getContributors| Error");
            console.log( 'Error while getting contributors info: ', data );
        }
    });
}

getUser=function(login,total){     //get detailed info about any Github user, not neccessarily connected with current repository
    console.log("getUser");
    var url=getWebserviceURL()+"user/"+login;
    $.ajax({
        url: url,
        type: 'POST',
        data: {token:masterViewModel.token()},
        success: function( data ) {
            if(!checkError(data)){
                console.log("getUser|Sucess| CheckPass");

                //Adding no of commits to user
                data.commitsNum = total;
                getUserCommits(data);     // get commits of this user - commits only from current repository
            }
        },
        error: function( data ) {
            console.log( 'Error while getting user info for: '+login, data );
        }
    });
}

getUserCommits=function(user){     //get list of a user commits to current repository
    console.log("getUserCommits");
    var url=getWebserviceURL()+"user/"+user.login+"/commits";
    $.ajax({
        url: url,
        type: 'POST',
        data: {
            repoName:masterViewModel.repoName(),
            owner:masterViewModel.userName(),
            token:masterViewModel.token()
        },
        success: function( data ) {
            if(!checkError(data)){
                console.log("getUserCommits|Success| CheckPass");
                user.commitMessages=data.commitMessages;
                user.averageCommitMessageLength=data.averageCommitMessageLength;

                userViewModel.users.push(user);   //adding full detailed user to list of users/contributors

                if(masterViewModel.contributorsReady()==true && masterViewModel.totalContributors()==userViewModel.users().length){
                    drawRepoStats();
                    masterViewModel.contributorsReady(false);
                }
            }
        },
        error: function( data ) {
            console.log( 'Error while getting user commits for: '+user.login, data );
        }
    });
}

signIn=function(){
    $("#signInButton").button('loading');
    $("#signInButton").attr('disabled','disabled');

    $.ajax({
        url: getWebserviceURL()+'user/authorization',
        type: 'POST',
        data: {username:masterViewModel.user(),
            password:masterViewModel.password()},
        success: function(data) {
            if(data.error==null){
                masterViewModel.token(data)
                masterViewModel.signedIn(true);
                $("#authenticationError").hide();
                $("#signInButton").button('reset');
                $("#signInButton").removeAttr('disabled');
                $("#signInModal").modal('hide');
                console.log("SUCCES "+ data)
            }
            else{
                $("#authenticationError").show();
                $("#signInButton").button('reset');
                $("#signInButton").removeAttr('disabled');
                console.log( 'Error while authenticating: '+ data );
            }
        },
        error: function( data ) {
            $("#authenticationError").show();
            $("#signInButton").button('reset');
            $("#signInButton").removeAttr('disabled');
            console.log( 'Error while authenticating: '+ data );
        }
    })
}

signOut=function(){                 // signs out by deleting current user token, so it's not send with requests
    masterViewModel.signedIn(false);
    masterViewModel.token(null);
    console.log("signedOut");
}

average=function(data){       //calculating average number of commits
    var sum=0;
    $.each(data, function(i, value){
        sum+=value[1];
    })
    return sum/data.length;
}

drawRepoStats=function(){             //drawing bar graph using flot.js
    console.log("DrawStats");
    var data1=[];

    $.each(userViewModel.users(), function(i, user){
        data1.push([user.login,user.commitsNum]);
    });

    var avg=average(data1);

    var bar_array=[];
    console.log("average "+avg);
    $.each(data1, function(i, bar) {
        if(bar[1]<avg) barColor="#FF2B00"
        else  barColor="#62B849"
        bar_array.push({
            data: [bar],
            bars: {
                show: true,
                fill:0.9,
                align: "center",
                barWidth: 0.6
            },
            color: barColor
        });
    });

    var markings = [
        { color: "#00F", lineWidth: 2, yaxis: { from: avg, to: avg } }
    ];

    $.plot("#graph", bar_array, {
        xaxis: {
            mode: "categories",
            tickLength: 1
        },
        valueLabels: {
            show: true
        },
        grid: { markings: markings }
    });
}

showUserCommitsModal=function(login){
	console.log("showUserModal");
    $("#modal"+login).modal({
        keyboard:true
    });
    $("#modal"+login).modal('show');
}

showSignInWindow=function() {
    $("#signInModal").modal({
        keyboard:true
    });
    $("#signInModal").modal('show');

}

acceptCertificate=function(){       // opens new tab with webservice website, just to accept certificate
    window.open(getWebserviceURL()+"certificate");
    $("#unknownError").hide();
}

dismissAlert=function(){
    $("#notFound").hide();
}

unknownErrorDisplay=function(){
    $('#content').hide();
    $('#notFound').hide();
    $("#unknownError").show();
}

dismissUnknownErrorAlert=function(){
    $("#unknownError").hide();
}

checkError=function(data){
    if(data.error!=null){
        masterViewModel.error(data);
        $("#notFound").show();
        $("#content").hide();
        return true;
    }else{
        return false;
    }
}

$(document).ready( function(){
				$('#content').hide();
				$('#notFound').hide();
                $("#authenticationError").hide();
                $("#unknownError").hide();
				ko.applyBindings(masterViewModel,document.getElementById("notFound"));
				ko.applyBindings(masterViewModel,document.getElementById("search"));
				ko.applyBindings(masterViewModel,document.getElementById("signIn"));
				ko.applyBindings(masterViewModel,document.getElementById("signInModal"));
				ko.applyBindings(masterViewModel,document.getElementById("unknownError"));
				ko.applyBindings(repoViewModel,document.getElementById("repositoryTab"));
				ko.applyBindings(userViewModel,document.getElementById("usersTab"));
	});