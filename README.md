# TaskDevartLab
using MVVM &amp; retrofit &amp; dataBinding &amp;recycler view &amp;card view &amp; hdodenhof lib. &amp;picasso&amp;Coroutines&amp;sneaker&amp;nav graph &amp; google sheet DB &amp; API google script <br/>
1-create google sheets like : https://docs.google.com/spreadsheets/d/15H-hd0z2h6OIf_g5e-33fEY6jMxpYJPGs_SYVSoZEa4/edit?usp=sharing <br/>
![1](https://user-images.githubusercontent.com/19371586/136418932-62b83b1e-b12d-489f-9de9-78ecfc4e3368.PNG)
<br/>
2- create google script to create api to google sheets like:https://script.google.com/d/13EGmpLksxWYfErTy6p59LiDJLVP6liJGH1Vk82qIl82vZADGagjEsQN_/edit?usp=sharing <br/>
![2](https://user-images.githubusercontent.com/19371586/136419150-a879e0af-04a8-4eee-a2b1-d044dc53cde8.PNG)
<br/>
Copy this is code to create api at google script 👇👇👇👇👇👇👇:<br/>
var id = '15H-hd0z2h6OIf_g5e-33fEY6jMxpYJPGs_SYVSoZEa4';
var name = 'Sheet1';

function doGet(e) {
  var url = id;
  var sheetName = name;

  var myData = SpreadsheetApp.openById(id);
  var sheet = myData.getSheetByName(name);

  var json = convertSheet2Json(sheet);
  return ContentService.createTextOutput(JSON.stringify(json))
    .setMimeType(ContentService.MimeType.JSON);
}
function convertSheet2Json(sheet) {
  // first line(title)
  var firstRange = sheet.getRange(1, 1, 1, sheet.getLastColumn());
  var firstRowValues = firstRange.getValues();
  var titleColumns = firstRowValues[0];

  // after the second line(data)
  var lastRow = sheet.getLastRow();
  var rowValues = [];
  for(var rowIndex=2; rowIndex<=lastRow; rowIndex++) {
    var colStartIndex = 1;
    var rowNum = 1;
    var range = sheet.getRange(rowIndex, colStartIndex, rowNum, sheet.getLastColumn());
    var values = range.getValues();
    rowValues.push(values[0]);
  }

  // create json
  var jsonArray = [];
  for(var i=0; i<rowValues.length; i++) {
    var line = rowValues[i];
    var json = new Object();
    for(var j=0; j<titleColumns.length; j++) {
      json[titleColumns[j]] = line[j];
    }
    jsonArray.push(json);
  }
  return jsonArray;
}
<br/>
<br/>
<br/>
<br/>
