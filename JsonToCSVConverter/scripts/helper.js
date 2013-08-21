function reset()
{
    $('#text').text("");
    $('#jsonResponse').text("");
}
function callGithub (url){
    $.getJSON(url +"?callback=?",
        function(json)
        {

            if(json.meta.status==200)
            {
                //alert("response taken, json.meta.status = " + json.meta.status);
                $('#jsonResponse').text("response taken, json.meta.status = " + json.meta.status);
                //$("#txtaResults").html(json.data.repos_url);
                var csv = ConvertToCSV(json.data)
                $('#text').text(csv);
            }
            else
            {
                $('#jsonResponse').text("Error happened");
                //console.log(json);
            }
        })
}
function ConvertToCSV(objArray) {
    //var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;

    var array;

    if (typeof objArray == 'object')
    {
        array = objArray
    }
    else
    {
        array = JSON.parse(objArray)
    }
    var str = '';

    for (var i = 0; i < array.length; i++)
    {
        var line = '';

        for (var index in array[i])

        {
            if ( typeof array[i][index] != 'object')
            {
                if (line != '')
                    line += ',';

                line += array[i][index];
            }
        }

        str += line + '\r\n';
    }

    return str;

}

function save(csv)
{
    //var csv = $('#text').val();
    if (csv == null || csv=='')
        {
            alert("Nothing to save");
            return
        }
    var bb = new BlobBuilder();
    bb.append(csv);
    var blob = bb.getBlob("text/plain");
    var fileName = "Stats_" + GetDateTime() + ".csv";
    saveAs(blob, fileName);
}

function GetDateTime()
{
    var myDate = new Date();
    var MonthHolder = myDate.getMonth()+ 1;
    var dateHolder = addZero(myDate.getFullYear().toString()) + addZero(MonthHolder) + addZero(myDate.getDate().toString());
    var timeHolder = addZero(myDate.getHours().toString())+ addZero(myDate.getMinutes().toString()) + addZero(myDate.getSeconds().toString())
    return dateHolder + timeHolder
}
function addZero(dateElement)
{
    var tempHolder;
    tempHolder = dateElement;
    if(tempHolder<10)
    {
        tempHolder = '0' + tempHolder;
        return tempHolder;
    }
    else
    {
        return tempHolder;
    }
}


