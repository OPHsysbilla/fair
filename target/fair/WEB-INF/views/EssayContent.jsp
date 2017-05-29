<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>EssayContent</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<%=path %>/dist/css/bootstrap.min.css">
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="<%=path %>/dist/js/bootstrap.min.js"></script>
	<style type="text/css">
		body{
			padding-top:50px;
			background-color: #e4e4e4;
		}
		div{
			display: block;
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
		.container_body{
			width:70%;
			margin: 0px auto;
		}
		.body_header{
			width: 100%;
			height: 50px;
			margin-top:200px; 
			background-color: black;

		}
		.footer_bottm{
			height:50px;
			background-color: #fff;
			bottom: 0;
			width: 100%;
		    margin-top: 20px;
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
		.personal_Essay{
			width: 100%;
			height: 100%;
			margin-top: 0px;
		}
		.personal_body{
			width: 30%;
			height: 200px;
			background-color: #fff;
			float: left;
		    overflow: hidden;
		}
		.Essay_body{
			width: 69%;
			float: right;
			margin-top:0px; 
			background-color: #fff;
			overflow:hidden;
			height: 100%;
		    padding: 7px 0;
		    border: solid 1px #ddd;
		    border-radius: 4px;

		}
		.Essay_title{
			width: 100%;
			height: 100px;
			background-color: #fff;
		}
		.Essay_label{
			width: 100%;
			height: 100px;
			background-color: #fff;
		}
		.Essay_container{
			width: 100%;
			background-color: #fff;
		}
		.comment_body{
			width: 69%;
			margin-top: 10px;
			background-color: #fff;
			float: right;
			overflow:hidden;

		}
		.comment_person{
			width: 69%;
			margin-top: 10px;
			background-color: #fff;
			float: right;
			margin-bottom: 10px;
		}
		.comment_person_name{
			height: 100%;
			width: 10%;
			background-color: #fff;
			overflow: hidden;
			float: left;
		}
		.comment_person_text{
			height: 100%;
			width: 90%;
			float: right;
			padding: 1%;
			background-color: #fff;
			overflow: hidden;
			float: right;
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
			        <!-- 如果登陆 个人主页 未登陆 显示登陆和注册 -->
			        <li><a href="#" data-toggle="modal" data-target="#myModal_regist">注册</a></li>
			        <li><a href="#"  data-toggle="modal" data-target="#myModal_login">登陆</a></li>
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
    	

		<div class="container_body">
			<!-- 黑条 -->
			<div class="body_header">
			</div>
			<div class="personal_Essay">
				<!-- 个人信息 -->
				<div class="personal_body">
					<p>信息</p>
				</div>
			</div>
				<!-- 文章页面 -->
				<div class="Essay_body">
					<!-- 标题 -->
					<div class="Essay_title">
						<h1>标题</h1>
					</div>
					<div class="xian" style="width:100%;margin:0 auto;padding:0 200px; border-top:1px solid #ddd" ></div>
					<!-- 标签 -->
					<div class="Essay_label">
						<h6>标签</h6>
					</div>
					<div class="xian" style="width:100%;margin:0 auto;padding:0 200px; border-top:1px solid #ddd" ></div>
					<!-- 内容 -->
					<div class="Essay_container">
						<h6>内容</h6>
						<h6>内容</h6>
						<h6>内容</h6>
						<h6>内容</h6>


						<h6>内容</h6>
						<h6>内容</h6>
						v
						v

						<h6>内容</h6>
						<h6>内容</h6>
						<h6>内容</h6>
						<h6>内容</h6>
						v
						v<h6>内容</h6>
						<h6>内容</h6>
						<h6>内容</h6>
					</div>
				</div>
				<!-- 评论 -->
				<div class="comment_body">
					<h4>评论区</h4>
				</div>
				<!-- 个人评论区域 -->
				<div class="comment_person">
					<div class="comment_person_name">
						<h4>用户名：</h4>
					</div>
					<div class="comment_person_text">
						<form>
						<textarea class="form-control" rows="3"></textarea>
						<button class="btn btn-info" aligin="right">提交</button>
						</form>
					</div>
				</div>
			
		</div>



		<!-- 工具条 -->
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

	    <!-- Modal 弹出登陆框 -->
	<div class="modal fade" id="myModal_login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title modal-title-info" id="myModalLabel">登陆</h4>
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
			    <div class="form-group_login">
			    	<button class="btn btn-info">登陆</button>
			    </div>
			  </fieldset>
			</form>
	      </div>
	    </div>
	  </div>
	</div>

  </body>
</html>