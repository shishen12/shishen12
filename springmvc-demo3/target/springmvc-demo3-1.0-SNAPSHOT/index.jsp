<%--
  Created by IntelliJ IDEA.
  User: crh80
  Date: 2022/2/17
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <table style="width: 100%; height:700px">
        <tr >
            <td style="width: 200px;border-right: deepskyblue 2px solid;background-color: rgba(255,0,0,0.1)">
                <ul>
                    <li><a href="book-add.jsp" target="mainFrame">添加图书</a></li>
                    <li><a href="list.jsp" target="mainFrame">文件列表</a></li>
                </ul>
            </td>
            <td>
                <iframe name="mainFrame" width="100%" height="700" frameborder="0"></iframe>
            </td>
        </tr>
    </table>
</body>
</html>
