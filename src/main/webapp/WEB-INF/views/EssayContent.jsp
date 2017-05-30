<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/img";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			margin-top:10px;
		}
		.personal_Essay{
			width: 100%;
			height: 100%;
			margin-top: 10px;

		}
		.personal_body{
			width: 30%;
			height: 215px;
			background-color: #fff;
			float: left;
		    overflow: hidden;
		}
		.Essay_body{
			width: 69%;
			float: right;
			background-color: #fff;
			overflow:hidden;
			height: 100%;
		    padding: 7px 0;
		    border: solid 1px #ddd;
		    border-radius: 4px;

		}
		.Essay_title{
			width: 100%;
			margin-bottom: 100px;
			background-color: #fff;
			text-align: center;
		}
		.Essay_label{
			width: 100%;
			height: 70px;
			background-color: #fff;
		}
		.Essay_container{
			width: 100%;
			background-color: #fff;
			padding:5%;
		}
		.comment_body{
			width: 69%;
			margin-top: 10px;
			background-color: #fff;
			float: right;
			overflow:hidden;
			padding-top:2%;
			padding-left:2%;
			padding-bottom:2%;
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
			text-align:center;
		}
		.comment_person_text{
			padding:10px;
			height: 100%;
			width: 90%;
			float: right;
			padding: 1%;
			background-color: #fff;
			overflow: hidden;
			float: right;
		}
		.comment_body_title{
			border-bottom: 0.5px solid;
			border-color:#DEDEDE;
		}
		dt{
			float: left;
			padding-right:20px;
			padding-left:20px;
			width: 18%;
			height: 60%;
			border-right: 0.5px solid;
			border-color:#DEDEDE;
		}
		dd{
			padding-left: 20px;
			padding-right: 20px;
			width:82%;
			height:100%;
			float: left;
			overflow: hidden;
			border-color:#DEDEDE ;
			
		}
		dl{
			overflow: hidden;
			padding-top: 20px;
			padding-bottom: 20px;
			position:relative;

		}
		.blog_list_dl{
			border-bottom: 0.5px solid;
			border-color:#DEDEDE; 
		}
		.comment_person_title{
			border-bottom: 0.5px solid; 
			border-color:#DEDEDE; 
			padding-left:2%;
			padding-top:2%;
			padding-bottom:2%;
			margin-bottom:2%;
		}

		.dl_dd_abstracts{
			
			 
		}
		.dl_dd_time{
			position:absolute;
			font-color:#999999;
			right:5%;
			bottom:5%;
		}
		.dl_dd_label{
			padding-top: 20px;
			padding-left:20px;
			overflow: hidden;
			float:left;
			
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
			
			margin:5% auto;
			width: 50px;
			height:50px;
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

		.dl_dd_secondcomment{
			float: right;
			overflow: hidden;
			width: 90%;
		}

		.dl_dd_firstcomment{
			margin-bottom: 10px;
			overflow: hidden;
			border-bottom: 0.5px solid;
			border-color:#FFF;
		}

		.blog_person_head{
			width: 100%;
			height: 50%;
			padding-top: 10%;
			text-align: center;
		}

		.blog_person_headdiv{
			margin:0px auto;
			text-align: center;
		}

		.blog_person_headimg{
			margin-top: 35px;
			width: 120px;
			height:120px;
			text-align: center;
			vertical-align: center;
		}

		.blog_person_headname{
			padding-top: 20px;
			margin-bottom: 20px;
			display:block;
			float:left;
			overflow: hidden;
			text-align: center;
			width: 100%;

		}

		.Essay_title_manager{
			overflow: hidden;
			float: right;
			padding-right: 5%;
		}
		.comment_person_button{
			margin:10px;
		}


	</style>
	 
		<script type="text/javascript">
		
		window.onload=function(){  
			  var div1=document.getElementById("Essay_manager");   

			  var usersession='${sessionuser.username}';

			  var sbtitle ='${essayauthor.username}';

			  if(sbtitle==usersession){
			  	div1.style.display='block';
			  }else{
			  	div1.style.display='none';
			  }
		};
	</script>
	
	

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
			<!-- 博客页面个人信息 -->
			<div class="personal_Essay">
				<!-- 个人信息 -->
				<div class="personal_body">
					<div class="blog_person_headdiv">
						<a href="<%=path %>/blog/${essayauthor.id}" class="blog_person_head">
							<img src="<%=imgPath %>${essayauthor.relativeHeadPortraitPath}" class="blog_person_headimg">	
						</a>
					</div>	
					<div style="padding:0px;">
					<a href="<%=path %>/blog/${essayauthor.id}" class="blog_person_headname">
						<!-- 作者名 -->		 
						${ essayauthor.username }
					</a>
					</div>
				</div>
			</div>

			<!-- 文章页面 -->
			<div class="Essay_body">
			<!-- 标题 -->
				<div class="Essay_title">
					<h1>${essay.title}</h1>
					<div id="Essay_manager" name="Essay_manager" class="Essay_title_manager">
						<a class="btn btn-danger btn-sm" href="<%=path %>/blog/${userid}/${essayid}/edit" >编辑文章</button>
						<a class="btn btn-danger btn-sm" href="<%=path %>/blog/${userid}/${essayid}/delete" >删除文章</a>
					</div>
				</div>
				<div class="xian" style="width:100%;margin:0 auto;padding:0 200px; border-top:1px solid #ddd" ></div>
				<!-- 标签 -->
				<div class="Essay_label">
					<c:forEach items="${essayLabels}" var="label" varStatus="loop">
					<div class="dl_dd_label">
						<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
						<font class="dl_font_label">${ label.content }</font>
					</div>		
					</c:forEach>
				</div>
				<div class="xian" style="width:100%;margin:0 auto;padding:0 200px; border-top:1px solid #ddd" ></div>
				<!-- 内容 -->
				<div class="Essay_container"> 
					<%-- <c:import url="${essayContent}"/>   --%>
						${essayContent}
						<%--  <jsp:include page="${essayContent}" flush="true" />  --%>  
				</div>
			</div>
			<!-- 评论 -->
			<div class="comment_body">
				<div class="comment_body_title">
					评论
				</div>
				<div class="dl_dd_firstcomment">
					<c:forEach items="${essayComment}" var="comment" varStatus="loop"> 
						<dl class="blog_list_dl"> 
							<dt>
								<div class="blog_list_headdiv">
									<a href="" class="blog_list_head">
										<img src="<%=imgPath %>${essayCommentUser[loop.count-1].relativeHeadPortraitPath}" class="blog_list_headimg">	
									</a>
								</div>	
								<a href="" class="blog_list_headname">
									${essayCommentUser[loop.count-1].username}
								</a>
							</dt>
							<dd>
								
									<div class="dl_dd_abstracts">
										<font class="dl_font">${comment.content}</font>
									</div>
							</dd>	
							<div class="dl_dd_time">
										${comment.time}	
									</div>					
						</dl>
					</c:forEach> 
					<!-- 
					
					<div class="dl_dd_secondcomment">
						<dl class="blog_list_dl">
							<dt>
								<a href="" class="blog_list_headname">
									用户名		
								</a>
							</dt>
							<dd>
								<div class="dl_dd_abstracts">
									<font class="dl_font">我是二级评论</font>
								</div>			
							</dd>
						</dl>
					</div> 
					
					-->
				</div>
   
			</div>
			<!-- 个人评论区域 -->
			<div class="comment_person">
				<div class="comment_person_title">
					用户评论 
				</div>
				<div class="comment_person_name">
					<p>${sessionuser.username}</p>
				</div>  
				<div class="comment_person_text"> 
					<form action="<%=path %>/blog/${userid}/${essayid}?method=addComment" method="post">
						<textarea class="form-control" name="comment" rows="3"></textarea>
						<div class="comment_person_button">
						<button class="btn btn-info" aligin="right">提交</button>
						</div>
						
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