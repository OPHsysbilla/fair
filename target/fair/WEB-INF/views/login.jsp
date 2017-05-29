<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>登录</title>

<link rel="stylesheet" href="<%=path %>/css/main.db431217.css">

<script src="<%=path %>/js/instant.14757a4a.js"></script>

</head>



<body class="zhi ">
<div class="index-main">
<div class="index-main-body">
<div class="index-header">

<h1>MiniBlog</h1>
</div>

<div class="desk-front sign-flow clearfix sign-flow-simple">

<div class="index-tab-navs">
<div class="navs-slider">

<!-- <a href="#signup" class="active">注册</a> -->
<a href="javascript:void(0)" onclick="document.getElementById('signup').scrollIntoView();" class="active">注册</a>
<a href="javascript:void(0)" onclick="document.getElementById('signin').scrollIntoView();" >登录</a>
<span class="navs-slider-bar"></span>
</div>
</div> 
<form action="<%=path %>/account/loginaction" method="POST">
	<input type="hidden" name="_xsrf" value="98af7cbfcfa2d52ee567cd5b0d1d2ed4"/>
	<div class="group-inputs">
		<div class="account input-wrapper">
			<input type="text" name="username" aria-label="用户名" placeholder="用户名" required>
		</div>
		<div class="verification input-wrapper">
			<input type="password" name="password" aria-label="密码" placeholder="密码" required />
		</div>
		<div class="Captcha input-wrapper" data-type="cn" data-za-module="Captcha">

	</div>
	</div>

	<div class="button-wrapper command">
		<button class="sign-button submit" type="submit">登录</button>
	</div>
</form>
<!-- 
<div class="view view-signin" data-za-module="SignInForm">
<form action="/login" method="POST">
	<input type="hidden" name="_xsrf" value="98af7cbfcfa2d52ee567cd5b0d1d2ed4"/>
	<div class="group-inputs">
		<div class="account input-wrapper">
			<input type="text" name="account" aria-label="用户名" placeholder="用户名" required>
		</div>
		<div class="verification input-wrapper">
			<input type="password" name="password" aria-label="密码" placeholder="密码" required />
		</div>
		<div class="Captcha input-wrapper" data-type="cn" data-za-module="Captcha">

	</div>
	</div>

	<div class="button-wrapper command">
	<button class="sign-button submit" type="submit">登录</button>
	</div>


</form>

</div>

<div class="view view-signup selected" data-za-module="SignUpForm">
<form class="zu-side-login-box" action="/register/email" id="sign-form-1" autocomplete="off" method="POST">
	<input type="password" hidden> 
	<input type="hidden" name="_xsrf" value="98af7cbfcfa2d52ee567cd5b0d1d2ed4"/>
		<div class="group-inputs">
			<div class="name input-wrapper">
				<input required type="text" name="fullname" aria-label="用户名" placeholder="用户名">
			</div>
			<div class="input-wrapper">
			<input required type="password" name="password" aria-label="密码" placeholder="密码（不少于 6 位）" autocomplete="off">
			</div>
			<div class="input-wrapper captcha-module" data-type="en" >
			</div>
		</div>
	<div class="button-wrapper command">
	<button class="sign-button submit" type="submit">注册</button>
	</div>
</form>

</div>  -->


</div>
</div>

</div>


<script src="<%=path %>/js/vendor.cb14a042.js"></script>
<script src="https://static.zhihu.com/static/revved/-/js/closure/base.4ed4c84b.js"></script>

<script src="<%=path %>/js/common.bee7f424.js"></script>
<script src="<%=path %>/js/page-index.e2ea1e7f.js"></script>

<meta name="entry" content="ZH.entrySignPage" data-module-id="page-index">


<input type="hidden" name="_xsrf" value="98af7cbfcfa2d52ee567cd5b0d1d2ed4"/>
</body>
</html>