<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN" class="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="renderer" content="webkit" />
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
<title>fair - 登录</title>

<link rel="stylesheet" href="<%=path %>/css/main.db431217.css">

<script src="<%=path %>/js/instant.14757a4a.js"></script>

</head>
<body class="zhi ">
<div class="index-main">
<div class="index-main-body">
<div class="index-header">

<h1>fair</h1>
</div>

<div class="desk-front sign-flow clearfix sign-flow-simple">

<div class="index-tab-navs">
<div class="navs-slider">

<a class="active">登录</a>
<span class="navs-slider-bar"></span>
</div>
</div>


<div class="view view-signin selected" data-za-module="SignInForm">
<form action="<%=path%>/account/login" method="POST">
	<input type="hidden" name="_xsrf" value="98af7cbfcfa2d52ee567cd5b0d1d2ed4"/>
	<div class="group-inputs">

		<div class="account input-wrapper">

			<input type="text" name="username" aria-label="用户名" placeholder="用户名" required="required"/>
		</div>
		<div class="verification input-wrapper">
			<input type="password" name="password" aria-label="密码" placeholder="密码" required="required" />
		</div>

		<div class="Captcha input-wrapper" data-type="cn" data-za-module="Captcha">

	</div>
	</div>

	<div class="button-wrapper command">
	<button class="sign-button submit" type="submit">登录</button>
	</div>
	<div >${ error }</div>

</form>

</div>

</div>
</div>

</div>


<script src="js/vendor.cb14a042.js"></script>
<script src="https://static.zhihu.com/static/revved/-/js/closure/base.4ed4c84b.js"></script>

<script src="js/common.bee7f424.js"></script>
<script src="js/page-index.e2ea1e7f.js"></script>

<meta name="entry" content="ZH.entrySignPage" data-module-id="page-index">


<input type="hidden" name="_xsrf" value="98af7cbfcfa2d52ee567cd5b0d1d2ed4"/>
</body>
</html>


<%-- <body class="zhi ">
<div class="index-main">
<div class="index-main-body">
<div class="index-header">

<h1>fair</h1>
</div>

<div class="desk-front sign-flow clearfix sign-flow-simple">

<div class="index-tab-navs">
<div class="navs-slider">

<a class="active">登录</a>
<span class="navs-slider-bar"></span>
</div>
</div>


<div class="view view-signin selected" data-za-module="SignInForm">
<form:form  modelAttribute="sessionuser"   method="post">
	<input type="hidden" name="_xsrf" value="98af7cbfcfa2d52ee567cd5b0d1d2ed4"/>
	<div class="group-inputs">

		<div class="account input-wrapper">

			<form:input type="text" path="username" name="account" aria-label="用户名" placeholder="用户名" required="required"/>
		</div>
		<div class="verification input-wrapper">
			<form:input type="password" path="password" name="password" aria-label="密码" placeholder="密码" required="required" />
		</div>

		<div class="Captcha input-wrapper" data-type="cn" data-za-module="Captcha">

	</div>
	</div>

	<div class="button-wrapper command">
		<input class="sign-button submit" value="登录" type="submit"/>
	</div>
	<div >${ error }</div>

</form:form >

</div>

</div>
</div>

</div>


<script src="js/vendor.cb14a042.js"></script>
<script src="https://static.zhihu.com/static/revved/-/js/closure/base.4ed4c84b.js"></script>

<script src="js/common.bee7f424.js"></script>
<script src="js/page-index.e2ea1e7f.js"></script>

<meta name="entry" content="ZH.entrySignPage" data-module-id="page-index">


<input type="hidden" name="_xsrf" value="98af7cbfcfa2d52ee567cd5b0d1d2ed4"/>
</body> --%>
</html>