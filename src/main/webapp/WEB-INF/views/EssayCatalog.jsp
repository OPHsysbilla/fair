<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  import="bean.Blog,bean.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <%=path %>/ --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Homepage</title>

    <!-- Bootstrap -->

    <link rel="stylesheet" href="<%=path %>/dist/css/bootstrap.min.css">

	
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="<%=path %>/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		window.onload=function(){  
			  var div1=document.getElementById("nav_log1");  
			  var div2=document.getElementById("nav_log2");  
			  var div3=document.getElementById("nav_unlog1");  
			  var div4=document.getElementById("nav_unlog2");  

			  var sbtitle ='<%=session.getAttribute("sessionuser")%>';
			  
			  alert(sbtitle);
			  
			  if(sbtitle){
				  	div1.style.display='block';
				  	div2.style.display='block';
				  	div3.style.display='none';
				  	div4.style.display='none';
				  	
				  }
				if(!sbtitle){
					div3.style.display='block';
				  	div4.style.display='block';
				  	div1.style.display='none';
				  	div2.style.display='none';
				  }
		};
	</script>
	<style type="text/css">
		body{
			padding-top:50px;
			background-color: #e4e4e4;
		}
		.carousel{
			height:500px;
			background-color: #000;
		}

		.carousel .item{
			height:500px;
			background-color: #000;
		}

		.carousel .border-image-repeat: {
			width:100%;
			background-color: #000;
		}
		.container-toolbar { 
			position:fixed; 
			right: 100px; 
			bottom: 190px; 
			width:40px; 
			height:150px; 
		} 
		.blog_write{
			width:40px; 
			height:30px;
			background: white; 
			margin-bottom:20px;
		}
		.main_blog_body{
			width:65%;

			padding-top:10px;
			margin-top:10px; 
			margin-left: 15%;
			
		}
		.footer_bottm{
			height:50px;
			background-color: #fff;
			bottom: 0;
			width: 100%;
		    overflow: hidden;
		    padding-left: 10%;
		    padding-right: 10%;
		    border-top: 1px solid #f0f0f0;

		}
		.form-group_regist{
			width: 30px;
			margin-right:10px;
			right:10px; 
		}
		.form-group_login{
			width: 30px;
			margin-right:10px; 
		}
		.user_head{
			height: 200px;
			width: 100%;
			background-color: #fff; 
			position:relative;	
		}
		.user_head_name{
			bottom: 0px;
			position:absolute;
			left:10%;
			bottom:10%;
		}
		.user_nav{
			width: 20%;
			height: 1000px;
			margin-top:10px; 
			background-color: #fff; 
			float: left;
			overflow: hidden;
			margin-bottom:10px; 
		}
		.user_bloglist{
			width: 100%;
			margin-top:10px; 
			background-color: #fff; 
			float: left;
			border-left:1px solid #ddd;
			margin-bottom: 10px;
		} 

		dt{
			float: left;
			padding-right:20px;
			padding-left:20px;
			width: 12%;
			height: 60%;
		}
		dd{
			padding-left: 20px;
			padding-right: 20px;
			float: left;
			overflow: hidden;
		}
		dl{
			overflow: hidden;
			padding-top: 20px;
			padding-bottom: 20px;

		}
		.blog_list_dl{

			border-bottom: 0.5px solid;
			border-color:#DEDEDE; 
		}

		.dl_dd_abstracts{
			 
		}
		.dl_dd_label{
			padding-top: 20px;
		}
		h3{
			margin-top:0px;
			font-weight:bold;
		}
		.dl_font{
			color:gray;
		}
		.dl_font_label{
			color: black;
		}
		.blog_list_head{
			width: 100%;
			height: 50%;
			padding-bottom: 10%;
			text-align: center;
		}
		.blog_list_headdiv{
			margin:0px auto;
			text-align: center;
		}
		.blog_list_headimg{
			
			margin:10% auto;
			width: 70px;
			height:70px;
			padding-bottom: 10%;
			text-align: center;
			vertical-align: center;


		}
		.blog_list_headname{
			display:block;
			float:left;
			overflow: hidden;
			text-align: center;
			width: 100%;
			padding-bottom: 20%;
		}
		a:link {   
			color:#999999;   
			text-decoration: none;   
			font-weight: normal;
		}   
		a:visited {   
			color:#999999;   
			text-decoration: none;   
			font-weight: normal;
		}   
		a:hover {   
			color:#34b0cc;   
			text-decoration: none;   
		} 
	</style>

  </head>
  <body>
    <div class="head-nav">
    	<div class="logo">
    		<img src="">
    	</div>

    	<div class="toolbar">
    		<nav class="navbar navbar-inverse navbar-fixed-top">
			  <div class="container-fluid">
			    <!-- Brand and toggle get grouped for better mobile display -->

 				<!-- 商标-->

			    <div class="navbar-header">
			      <!-- 响应式布局 -->
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			      <!-- 商标 首页链接 -->
			      <a class="navbar-brand" href="#">Brand</a>
			    </div>

			    <!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			    <!--  首页链接  -->
			      <ul class="nav navbar-nav">
			        <li><a href="#">MiniBlog</a></li>
			      </ul>

			      <!-- 工具栏 -->
			      <ul class="nav navbar-nav navbar-right">
			      	<!-- 跳转到编写页面 -->
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">写博客 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="#">写博客</a></li>
			            <li role="separator" class="divider"></li>    
			            <li>
			            <li><a href="#">上传资源</a></li>
			            </li>
			          </ul>
			        </li>
			        <!-- 如果登录 个人主页 未登录 显示登录和注册 -->
			        <li class="nav_unlog" id="nav_unlog1" name="nav_unlog1"><a href="#" data-toggle="modal" >注册</a></li>
			        <li class="nav_unlog" id="nav_unlog2" name="nav_unlog2"><a href="<%=path %>/account/login" >登录</a></li>
			     	<li class="nav_log" id="nav_log1" name="nav_log1"><a href="#">个人主页</a></li>
					<li class="nav_log" id="nav_log2" name="nav_log2"><a href="<%=path %>/account/logout">登出</a></li>
			      </ul>

			      <!-- 搜索 表单 -->
			      <form class="navbar-form navbar-right" action="#">
			        <div class="form-group">
			          <input type="text" class="form-control" placeholder="Search">
			        </div>
			        <button type="submit" class="btn btn-info">搜索</button>
			      </form>

			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
    	</div>
    </div>

    <div class="mainbody">
    	
    	<!--  个人界面  -->
		<div class="main_blog_body">
			<!-- 用户名头部 -->
			<div class="user_head">
				
				<div class="user_head_name">
					<h1>${alluserblogname.username}</h1>
				</div>
			</div>


			<!-- 功能区 -->
			<div class="user_bloglist"> 

			<c:forEach items="${alluserblog}" var="blog" varStatus="loop"> 
	 			<a  href="<%=path %>/blog/${blog.userId}/${blog.id}" >
					<dl class="blog_list_dl">
						<dd>
							<h3>${blog.title}</h3>
							<div class="dl_dd_abstracts">
								<font class="dl_font">${blog.abstracts}</font>
							</div>
							<div class="dl_dd_label">

								<span class="glyphicon glyphicon-star" aria-hidden="true"></span><font class="dl_font_label">标签</font>
							</div>
						</dd>
					</dl>
				</a>
			</c:forEach> 
			</div>
		</div>

		<div class="container-toolbar">
			<div class="blog_write">
				<button class="btn btn-default">
					专家约谈
				</button>
			</div>
			<div class="blog_write">
				<button class="btn btn-default">
					编写博客
				</button>
			</div>
			<div class="blog_write">
				<button class="btn btn-default">
					返回顶部
				</button>
			</div>
		</div>


    </div>

    <!-- 页面底部信息 -->
    <div class="footer_bottm">
    <p class="pull-right"><a href="#top">回到顶部</a></p>
    <p> Copyright © 2017</p>
    </div>

    <!-- Modal 弹出注册框 -->
	<div class="modal fade" id="myModal_regist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title modal-title-info" id="myModalLabel">注册</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal">
			  <fieldset>
			    <div class="form-group">
			      <label for="inputEmail" class="col-lg-2 control-label">用户名</label>
			      <div class="col-lg-10">
			        <input type="text" class="form-control" id="inputUsername" placeholder="username">
			      </div>
			    </div>
			    <div class="form-group">
			      <label for="inputPassword" class="col-lg-2 control-label">密码</label>
			      <div class="col-lg-10">
			        <input type="text" class="form-control" id="inputPassword" placeholder="password">
			      </div>
			    </div>
			    <div class="form-group_regist">
			    	<button class="btn btn-info">注册</button>
			    </div>
			  </fieldset>
			</form>
	      </div>
	  </div>
	</div>
	</div>

	    <!-- Modal 弹出登录框 --> 
<div class="modal fade" id="myModal_login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title modal-title-info" id="myModalLabel">登录</h4>
      </div>
      <div class="modal-body">
        <form  method="POST"class="form-horizontal">
		  <fieldset>
		    <div class="form-group">
		      <label for="inputEmail" class="col-lg-2 control-label">用户名</label>
		      <div class="col-lg-10">
		        <input type="text" class="form-control" id="inputUsername" placeholder="username">
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="inputPassword" class="col-lg-2 control-label">密码</label>
		      <div class="col-lg-10">
		        <input type="text" class="form-control" id="inputPassword" placeholder="password">
		      </div>
		    </div>
		    <div class="form-group_login">
		    	<button class="btn btn-info">登录</button>
		    </div>
		  </fieldset>
		</form>
      </div>
    </div>
  </div>
</div>  

  </body>
</html>