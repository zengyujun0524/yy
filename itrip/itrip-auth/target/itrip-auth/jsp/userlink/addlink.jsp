
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/retoken.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#addtijiao").click(function () {
                var str = {
                    "linkUserName": $("#name").val()
                };
                var arrs = JSON.stringify(str);
                $.ajax({
                    "url": "http://localhost:8001/itrip-biz/api/userinfo/queryuserlinkuser",
                    "type": "POST",
                    "contentType": 'application/json',
                    "dataType": "json",
                    "data": arrs,
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            var list = data.data;
                            $("#tabDiv").html("");
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td></tr>");
                            });
                        } else {
                            alert("token失效，请重新登录");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                });
            });
        });
    </script>
</head>
<body>
    <div id="addtijiao">
        <form align="center">
            <table align="center">
                <tr>
                    <td>联系人姓名:</td>
                    <td><input type="text" name="linkUserName"></td>
                </tr>
                <tr>
                    <td>身份证</td>
                    <td><input type="text" name="linkIdCard"></td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td><input type="text" name="linkPhone"></td>
                </tr>
                <tr>
                    <td><input type="button" id="tijiao" value="提交">
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
