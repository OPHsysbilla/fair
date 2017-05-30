<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <%=path %>/ --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>springmvc图片上传与ueditor兼容集成</title>
<script type="text/javascript" src="<%=path %>/js/jquery.mini.js"></script>
<script type="text/javascript" src="<%=path %>/js/image.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.city.js"></script>
<script type="text/javascript" src="<%=path %>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=path %>/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>

<table>
		<tr>
			<div>
				 <form action="<%=path %>/addData" method="post">
				    <h1>a demo</h1>
				    <script id="editor" type="text/plain" style="width:1024px;height:500px;">
    				</script>
    				<textarea id="context" name="context" style="display: none"></textarea>
				    <input type="submit" value="编辑完成" onclick="getContent();">
				</form>
			</div>
		</tr>
	</table>
</body>


<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
	

    function getContent() {
        var arr = [];
        arr.push(UE.getEditor('editor').getContent());       
        document.getElementById("context").value=UE.getEditor('editor').getContent();
    }
</script>


</html>