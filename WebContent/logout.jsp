<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="loginDAO" class="max.login.LoginDAO"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <%
    String ud=(String) session.getAttribute("uid");
          if(loginDAO.logout(ud))
          {
        	  out.println("Successfull Logout");
      request.getRequestDispatcher("Home.jsp").include(request, response);
          }
          else
          {
        	  out.println("DB REEERRR");
              request.getRequestDispatcher("Home.jsp").include(request, response);
               
          }
  %>
</body>
</html>