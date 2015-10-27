<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2015/10/27
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="/js/jquery.js"></script>
</head>
<body>
    <label>json:<input type="text" id="reqText"/></label>
    <a href="#" onclick="doPost()">test post</a>
    <script type="text/javascript">
        function doPost(){
//        /directField?id=12&name=zhouwd
//            {"name":"zhangsan","id":1}
            var dataStr=$("#reqText").val().toString();
            dataStr=eval("("+dataStr+")");
            console.info(dataStr);

//            dataStr = JSON.stringify(dataStr);
//            console.info(dataStr);
//            $.post('/directField',dataStr,function(data){
//                alert(data);
//            });
//            $.get('/directField',dataStr,function(data){
//                alert(data);
//            });
            $.ajax({
                url: "/directField",
                type: "POST",
                data:dataStr,
//                contentType: "application/json",
                success: function(data){
                    alert(data);
                },
                error: function(){
                    alert("发送请求失败。");
                }
            });

            $.ajax({
                url: "/directField",
                type: "GET",
                data:dataStr,
//                contentType: "application/json",
                success: function(data){
                    alert(data);
                },
                error: function(){
                    alert("发送请求失败。");
                }
            });
        }
    </script>
</body>
</html>
