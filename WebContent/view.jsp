<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="view.SHGDTO"%>
<%@ page import="view.MemberDTO"%>
<jsp:useBean id="sHGMemberDAO" class="max.regist.SHGMemberDAO"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
   String ud= (String) session.getAttribute("uid");
     Map<String, List<Object>> m= sHGMemberDAO.getSHGDeatils(ud.substring(0, 2).toLowerCase());
     List<Object> shgDetail= (List<Object>)m.get("shgDetail");
     List<Object> memDetail= (List<Object>)m.get("memDetail");
     out.println(shgDetail.size()+" shg "+memDetail.size());
      for( Object  shg:shgDetail)
      {
    	  SHGDTO q=(SHGDTO)shg;
      
    	  %>
	<table>
		<tr>
			<td>State Name</td>
			<td>District Name</td>
			<td></td>
			<td>4</td>
			<td>5</td>
			<td>6</td>
		</tr>
		<tr>
			<td><%=q.getStateName()%></td>
			<td><%=q.getDistName()%></td>
			<td><%=q.getGroupName()%></td>
			<td><%=q.getIfsc()%></td>
			<td><%=q.getStateName()%></td>
			<td><%=q.getStateName()%></td>
		</tr>


		<%
     for( Object   w: memDetail)
     {
    	 MemberDTO mm= (MemberDTO)w;
    	 if(q.getShgcode()==mm.getShgCode())
    	 {
    	 %>

		<tr>
			<td>1</td>
			<td>11</td>
			<td>111</td>
			<td>1111</td>
		</tr>
		<tr>
			<td><%=mm.getMemberCode()%></td>
			<td><%=mm.getAdhar()%></td>
			<td><%=mm.getName()%></td>
			<td><%=mm.getShgCode()%></td>
		</tr>



		<%	
    	 }
     }
      } %>

	</table>



</body>
</html>