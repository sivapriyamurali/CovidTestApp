document.onload = GenerateTable();

function GenerateTable() {
    //Build an array containing Customer records.
    var testCenters = new Array();
    testCenters.push([1, "Loc1", "48326"]);
    testCenters.push([2, "Loc2", "48327"]);
    testCenters.push([3, "Loc3", "48328"]);
    testCenters.push([4, "Loc4", "48329"]);

    //Create a HTML Table element.
    var table = document.getElementById("tbodyid")
    var row = document.createElement("td");

    //Get the count of columns.
    var columnCount = testCenters[0].length;
    //Add the data rows.
    for (var i = 0; i < testCenters.length; i++) {
        row = table.insertRow(-1);
        for (var j = 0; j < columnCount; j++) {

            var cell = row.insertCell(-1);
            cell.innerHTML = testCenters[i][j];

        }
        var r1 = document.createElement('input');
        r1.type = "radio";
        row.appendChild(r1);
        r1.setAttribute("name", "test");
        r1.setAttribute("id", "radiobtn-"+ i);


    }

}



var button = document.getElementById("getRadio");
var radios = document.getElementsByTagName("input");

button.onclick = getRadiobtnId;
function getRadiobtnId(){
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            // get value, set checked flag or do whatever you need to
            var value = radios[i].id;
            alert(value);
        }
    }
}


