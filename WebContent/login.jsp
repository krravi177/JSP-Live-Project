<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="loginDAO" class="max.login.LoginDAO"/>
    <jsp:useBean id="loginBean" class="max.login.LoginBean"/>
    <jsp:setProperty name="loginBean" property="*"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
   String st=loginDAO.login(loginBean) ; 
if(st.equalsIgnoreCase(loginBean.getUid()))
{
	 session.setAttribute("uid", st);
	 request.getRequestDispatcher("wel.jsp").forward(request, response);
}
else if(st.equalsIgnoreCase("AR"))
{
	out.println("User Already Login ");
	request.getRequestDispatcher("Home.jsp").include(request, response);
}
else
{
	out.println("Invalid User ");
	request.getRequestDispatcher("Home.jsp").include(request, response);
}

%>


</body>
</html>