<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC表单之-输入框处理</title>
</head>
<body>

    <h2>学生信息</h2>
    <form:form method="POST" action="/mblog/updateData">
        <table>
            <tr>
                <td><form:label path="data">数据：</form:label></td>
                <td><form:input path="data" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="数据" /></td>
            </tr>
        </table>
    </form:form>
</body>
</html>