<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="master.StateBean" %>
    <%@ page import="master.DistBean" %>
    <%@ page import="java.util.List" %>
    <jsp:useBean id="locationMasterDAO" class="master.LocationMasterDAO"/>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="javascript">
function getBankDetail() {
var ifsc=document.getElementById("ifsc").value;

  var xmlhttp;


if (window.XMLHttpRequest)
{
// code for IE7+, Firefox, Chrome, Opera, Safari
     xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
 }
 
 xmlhttp.onreadystatechange=function()
 {
 if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
//document.getElementById("fname").value
 
     document.getElementById("bd").innerHTML=xmlhttp.responseText;
     
}
  
 }
  xmlhttp.open("GET","BankServlet?ifsc="+ifsc,true);
      xmlhttp.send();
}


function getDistrictName() {
var sCode=document.getElementById("stateCode").value;
  var xmlhttp;


if (window.XMLHttpRequest)
{
// code for IE7+, Firefox, Chrome, Opera, Safari
     xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
 }
 xmlhttp.onreadystatechange=function()
 {
 if (xmlhttp.readyState==4 && xmlhttp.status==200)
{

document.getElementById("print").value=xmlhttp.responseText;
}
  
 }
  xmlhttp.open("GET","StateMasterServlet?sCode="+sCode,true);
      xmlhttp.send();
}

   function addRow()
   {
   var table=document.getElementById("child");
   var rowCount=table.rows.length;
   var row=table.insertRow(rowCount);
   var cell1=row.insertCell(0);
   cell1.innerHTML=rowCount;
   var cell2=row.insertCell(1);
   
   var element2=document.createElement("input");
   element2.type="checkbox";
   element2.name="chk";
   cell2.appendChild(element2);
   
   var cell3=row.insertCell(2);
   
   var element3=document.createElement("input");
   element3.type="text";
   element3.name="cname";
   cell3.appendChild(element3);
   
   var cell4=row.insertCell(3);
   
   var element4=document.createElement("input");
   element4.type="text";
   element4.name="age";
   cell4.appendChild(element4);
   var cell5=row.insertCell(4);
   
   var element5=document.createElement("input");
   element5.type="text";
   element5.name="cadhar";
   cell5.appendChild(element5);
   }
   function deleteRow()
   {
     var table=document.getElementById("child");
	 var i=1;
	 if(chkbox==cheked)
	 {
	   table.deleteRow(i);
	   i++;
	 }
	
   }
   
</script>
</head>
<body>

<%
String uid= (String)session.getAttribute("uid");
StateBean sb=locationMasterDAO.getStateNameBystateShortName(uid);
 List<DistBean> list=locationMasterDAO.getDistListByStateCode(sb.getStCode());
%>
<form action="SHGMasterServlet">
<table id="parent" bgcolor="red" border ="3">
<tr>
   <td colspan="4" align="center">SHGs Details</td>
</tr>
<tr>    
<td>Group Name</td><td><input type="text" name="gname" id="t1"></td></tr>
<tr><td>Account No </td><td><input type="text" name="dob" id="t2"></td>
<td>IFSC</td><td><input type="text" name="ifsc" id="ifsc" onblur="getBankDetail()">
<p id="bd">
</td></tr>

<tr><td>State Name</td><td><input type="hidden" name="stCode" value="<%=sb.getStCode()%>"><%=sb.getStName() %>

</td>


<td>District Name</td>
                      <td><select name="districtCode">
                      
                                   <option value="0">select District</option>
                               
                               <%
                               for(DistBean p:list)
                               {
                               
                               %>
                               
                         <option value="<%=p.getDistCode()%>"><%=p.getDistName() %> </option>
								   
								   <%} %>
								    
								  </select></td>
</tr>


</table>
<table id="child" bgcolor="yellow" border ="3">
  <tr>
   <td colspan="4" align="center">SHG Member's Detail </td><td align="right"> <input type="button"  value ="ADD Member" id="b1"  onclick="addRow();"></td>
</tr>
  <tr>
     <td>S.No.</td><td>Select</td><td>Member Name</td><td>Age</td><td>Adhar</td>
  </tr>
  <tr>
     <td>1</td><td><input type="checkbox" name="chk"></td>
     <td><input type="text" name="cname"></td>
     <td><input type="text" name="age"></td>
     <td><input type="text" name="cadhar"></td>
  </tr>
  
</table>
<table>
<tr><td><input type="submit" value="Save"></td>
<td><a href=view.html>View</a></td>
</tr>
</table>

</form>
</body>
</html>