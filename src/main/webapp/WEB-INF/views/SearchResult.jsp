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
    <title>SearchResult</title>

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
			width:50%;
			height:100%;
			padding-top:10px;
			margin-top:10px; 
			margin-left: 15%;
			background-color: #fff; 
		}
		.searchblock{
			width:50%;
			margin-left: 15%;
			padding-top:20px;
		}
		.footer_bottm{
			margin-top:20px;
			height:50px;
			background-color: #fff;
			padding-left: 10%;
			padding-right: 10%;

		}
		.form-control-search{
			width:80%;
			display: inline;
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
		.search_input{
			width: 90%;
			float: left;
			overflow: hidden;
		}
		.search_btn{
			width: 10%;
			float: right;

		}
		.btn-form-search{
			width: 10%;
		}
		.page_divide{
			margin-left: 50%; 
		}
		dt{
			padding-right:20px;
			padding-left:20px;
		}
		dd{
			padding-left: 20px;
			padding-right: 20px;
			padding-bottom: 5px;
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
		h3{
			margin-top:0px;
			font-weight:bold;
		}
		.dl_font{
			color:gray;
		}
		.dl_dd_label{
			padding-top: 20px;
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
			      <form class="navbar-form navbar-right" action="<%=path%>/searchget?curpage=${curpage}" method="GET">
			        <div class="form-group">
			          <input type="text" class="form-control" placeholder="请输入搜索内容" value="${ searchstr }" name="searchstr">
			        </div>
			        <button type="submit" class="btn btn-info btn-search">搜索</button>
			      </form>

			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
    	</div>
    </div>

    <div class="mainbody">
    	
 
			          
    	<div class="searchblock">
    		<form class="form" action="<%=path%>/searchget?curpage=${curpage}" method="GET">
    			<div class="search_input">
	    			<input type="text" class="form-control input-lg" id="Search" placeholder="请输入搜索内容" value="${ searchstr }" name="searchstr">
	    		</div>	 
	    		<div class="Search_btn">
	    			<button type="submit" class="btn btn-danger btn-lg btn-form-search">搜索</button> 
	    		</div>	
    		</form>
    	</div>


		<!-- 文章目录 一页显示10条内容 -->
		<div class="container_body">
			<h6> 标签为 <strong> ${ searchstr } </strong>的文章</h6>
			${blogByTitle[0].title}
			<c:forEach items="${blogsByTitle}" var="blog" varStatus="loop"> 
				<dl class="blog_list_dl">
					<dt>
						<h3>${blog.title}</h3>
					</dt>
					<!-- <dd>
						作者
					</dd> -->
					<dd>
						<div class="dl_dd_abstracts">
							<font class="dl_font">${blog.abstracts}</font>
						</div>
						<div>
							<div class="dl_dd_label">
								<span class="glyphicon glyphicon-star" aria-hidden="true"></span><font class="dl_font_label">标签</font>
							</div>
						</div>
					</dd>
	
				</dl>
			</c:forEach>  
		</div>

<%-- 
		<c:if test="${currentPage == 1}">
            <span class="disabled"> 前一页</span>        
        </c:if>
        <c:if test="${currentPage != 1}">
            <a href="listUser.do?page=${currentPage-1}">  前一页</a>
        </c:if>
        <c:if test="${currentPage == 1}">
        <span class="current">1</span>
        </c:if>
        <c:if test="${currentPage != 1}">
        <a href="listUser.do?page=1">1</a>
        </c:if>
        <%
                int pageTimes = (Integer)session.getAttribute("pageTimes");
                for(int i=1;i<pageTimes;i++)
                {
                    request.setAttribute("page", i+1);
        %>            
        <c:if test="${currentPage == page}">
            <span class="current"><%=i+1%></span>       
        </c:if>
        <c:if test="${currentPage != page}">
             <a href="listUser.do?page=<%=i+1%>"><%=i+1%></a>
        </c:if>
        <%} %>
        
        <c:if test="${currentPage == pageTimes}">
            <span class="disabled">后一页 >></span>        
        </c:if>
        <c:if test="${currentPage != pageTimes}">
        <a href="listUser.do?page=${currentPage+1}">后一页 >></a>
        </c:if>
 --%> 
		<div class="page_divide">
			<ul class="pagination">   
	        <c:choose>
               <c:when test="${curpage==1}">
                  <li class='disabled'><a href='#'>&laquo;</a></li>
               </c:when>
               <c:otherwise>
                   <li class='active'><a href='?curpage=${curpage-1}&searchstr=${searchstr}'>&laquo;</a></li>
                   <c:if test="${sumpage gt 6 }">
			            <li class='disabled'><a href='#'>…</a></li>       
			       </c:if>
               </c:otherwise>
            </c:choose>
            <c:forEach varStatus="i" begin="1" end="${sumpage}">
                 <c:choose>
                    <c:when test="${curpage==i.count}">
                       <li class='disabled'><a href='#'>${i.count}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li  class='active'><a href='?curpage=${i.count}&searchstr=${searchstr}'>${i.count}</a></li>
                    </c:otherwise>
                 </c:choose>
            </c:forEach>
	        <c:choose>
               <c:when test="${curpage==sumpage}">
                  <li class='disabled'><a href='#'>&raquo;</a></li>
               </c:when>
               <c:otherwise>
	                <c:if test="${sumpage gt 6 }">
			       		<li class='disabled'><a href='#'>…</a></li>
		       		</c:if>
		       		 <li>
				            <c:if test="${not empty blogsByTitle}">
				            	<a href='?curpage=${curpage+1}&searchstr=${searchstr}'>&raquo;</a>
				            </c:if>
				            <c:if test="${empty blogsByTitle}">
				            	<a href='#'>&raquo;</a>
				            </c:if>
		            </li>  
               </c:otherwise>
            </c:choose>
	            
				<%-- <li class="disabled"><a href="#">&laquo;</a></li> 
				<c:forEach items="${cur}" var="blog" varStatus="loop">
				    <li><a href="<%=path%>/search?curpage=${curpage}">5</a></li>
				</c:forEach>	
			    <li class="active"><a href="#" >${curpage+1}</a></li>
			    <c:forEach items="${cur}" var="blog" varStatus="loop">
					 <li><a href="#">2</a></li> 
				</c:forEach>
				<li><a href="#">&raquo;</a></li> --%>
			</ul>
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