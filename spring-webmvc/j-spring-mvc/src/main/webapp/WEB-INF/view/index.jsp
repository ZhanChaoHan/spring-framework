<!DOCTYPE html>
<html>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<h2>Hello World!</h2>
<c:out value="${name}"/>
<%
out.println("Your IP address is " + request.getRemoteAddr());
%>

<%request.getParameter("name");%>

${name}
</body>
</html>
