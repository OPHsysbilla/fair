<%@page import="java.io.File"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/img";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
  <title>My JSP 'allblog.jsp' starting page</title>  
  <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <link rel="stylesheet" href="<%=path %>/dist/css/bootstrap.min.css">
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="<%=path %>/dist/js/bootstrap.min.js"></script>

  <style type="text/css">
    .upload_file{
      text-align: center;
    }
    .content{
      width: 60%;
      margin:0px auto;
      padding-top: 10%;
      overflow: hidden;
    }
    .content_1{
      overflow: hidden;
      width: 15%;
      height: 150px;
      float: left;
      border: 0.5px solid;
      border-color:#DEDEDE;
      text-align: center;
      vertical-align: center;
      margin-left: 5%;
      margin-right: 5%;
    }

    
  </style>


  </head>
  
  <body>

    <div class="content">
  		<c:forEach items="${paths}" var="path" varStatus="loop">  
			<div class="content_1">
			         <img src="<%=imgPath %>/default_jpg1.png" style="margin-top:5%;margin-bottom:5%;" width="70%" height="70%" class="img-rounded"><br>
			         <a href= "/fair/download?filePath=${path}" >${pathnames[loop.count-1]}</a>

	      	</div>
			 
      	
     	</c:forEach> 

    </div>
    <br>
    <div style="margin: 0px auto;text-align: center;padding-top:10% ">
      <button class="btn btn-info" data-toggle="modal" data-target="#myModal_login">上传文件</button>
    </div>


  <div class="modal fade" id="myModal_login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title modal-title-info" id="myModalLabel">上传文件</h4>
        </div>
        <div class="modal-body">
          <form class="form-horizontal" method="post" enctype="multipart/form-data" action="<%=path %>/filepost/${userid} ">

          <div class="upload_file">

            <div class="form-group">
              <h1>File to Upload:</h1>
            </div>
            <div class="form-group">
              <input style="padding-left: 20%;" type="file" name="file" id="exampleInputFile">
            </div>
            <div class="form-group">
              <input style="padding-left: 20%;" type="file" name="file" id="exampleInputFile">
            </div>
            <div class="form-group">
              <input style="padding-left: 20%;" type="file" name="file" id="exampleInputFile">
            </div>

            <button type="submit" class="btn btn-info">上传</button>
          </div>

      </form>
        </div>
      </div>
    </div>
  </div>





  </body>
</html>