<%--
  Created by IntelliJ IDEA.
  User: crh80
  Date: 2022/2/22
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath+}/">
    <title>Title</title>
</head>
<body>
<h3>登入界面</h3>
${tips}
<form action="user/login" method="post">
    <p>账号:<input type="text" name="userName"/></p>
    <p>密码:<input type="password" name="userPwd"/></p>
    <p><input type="submit" value="登录"/></p>
</form>
</body>
</html>
