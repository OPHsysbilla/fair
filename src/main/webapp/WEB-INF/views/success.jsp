<%@page import="java.io.File"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传文件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body> 
  	<c:forEach items="${paths}" var="path" varStatus="loop"> 
    	 <br>
   		<a href= "<%=path%>/download?filePath=${path}" >${pathnames[loop.count-1]}</a>
     </c:forEach> 
     <br>
     <a href= "<%=path%>/uploadfile/${userid}" >上传文件</a>
  </body>
</html>
<%--      <c:forEach items="${paths}" var="path" varStatus="loop"> 
     	 <br>
   		<a href= "/fair/download?filePath=${path}"  >${fn:substringAfter(path,'/')}</a>
     </c:forEach> --%>
 
	<%-- <%
  	String[] paths = (String[])request.getAttribute("paths");
  	for(int i = 0; i < paths.length; i++)
  	{
  		File file = new File(paths[i]);
   %>
   	<br>
   	<a href=<%="/fair/download?filePath=" + paths[i]%>><%=file.getName() %></a>
  <% } %>  --%>
 
