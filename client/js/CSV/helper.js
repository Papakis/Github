function ConvertToCSV(objArray) {
    var array = [];
    var str = '';

    if (objArray.length > 0) {
        array = objArray;
        for (var i in objArray[0]){                // creating first line with labels
            if (typeof objArray[0][i]!= 'object') {
                str+= i+',';
            }
        }
        str=str.slice(0,str.length);
    }
    else                      //input object to array
    {
        array[0]=objArray;
        for (var i in objArray){                  // creating first line with labels
            if (typeof objArray[i]!= 'object') {
                str+= i+',';
            }
        }
        str=str.slice(0,str.length);
    }

    str +='\r\n';

    for (var i = 0; i < array.length; i++) {      // reading data from array
        var line = '';

        for (var index in array[i]) {
            if (typeof array[i][index] != 'object') {
                if (line != '')
                    line += ',';

                line += array[i][index];
            }
        }
        str += line + '\r\n';
    }
    save(str);
}

function save(csv) {                     //creating file
    if (csv == null || csv == '') {
        alert("Nothing to save");
        return;
    }
    var bb = new BlobBuilder();
    bb.append(csv);
    var blob = bb.getBlob("text/plain");
    var fileName = "Stats_" + GetDateTime() + ".csv";
    saveAs(blob, fileName);
}

function GetDateTime() {
    var myDate = new Date();
    var MonthHolder = myDate.getMonth() + 1;
    var dateHolder = addZero(myDate.getFullYear().toString()) + addZero(MonthHolder) + addZero(myDate.getDate().toString());
    var timeHolder = addZero(myDate.getHours().toString()) + addZero(myDate.getMinutes().toString()) + addZero(myDate.getSeconds().toString())
    return dateHolder + timeHolder
}
function addZero(dateElement) {         // formatting date
    var tempHolder;
    tempHolder = dateElement;
    if (tempHolder < 10) {
        tempHolder = '0' + tempHolder;
        return tempHolder;
    }
    else {
        return tempHolder;
    }
}
