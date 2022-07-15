<%--
  Created by IntelliJ IDEA.
  User: crh80
  Date: 2022/2/14
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h3>表单提交</h3>
    <form action="test/add " method="post">
        <p>图书名称：<input type="text" name="bookName"/></p>
        <p>图书作者：<input type="text" name="bookAuthor"/></p>
        <p>图书价格：<input type="text" name="bookPrice"/></p>
        <p>出版时间：<input type="text" name="publishTime"/></p>
        <P><input type="submit" value="提交"></P>
    </form>


    <h3>超链接提交</h3>
    <a href="/book/add?bookName=Java"/>

    <h3>AJAX提交</h3>
    <input type="button" value="ajax提交" id="btn1">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $("#btn1").click(function (){
            var obj={};
            obj.bookName="java";
            obj.bookAuthor="札那是高分";
            obj.bookPrice=3.33;
            var  s=JSON.stringify(obj);
            console.log(s);
            $.ajax({
                url:"book/update",
                type:"post",
                // headers:{
                //     token:"wdwdwdwdwddddwd"
                // },
                contentType:"application/json",
                dataType:"json",
                data:s,
                success:function (res){
                    console.log(res);
                }
            })
        });
    </script>


</body>
</html>
