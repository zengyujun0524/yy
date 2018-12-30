<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/retoken.js"></script>
    <script type="text/javascript">
        $(function () {

            $("#search").click(function () {
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
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>修改</td><td colspan='2'>操作</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td> <a href='#' class='modify'>修改</a></td>  <td><a href='#' class='del'>删除</a></td></tr>");
                            });
                            $(".del").click(function () {
                                var linkIdCards=[$(this).parent().siblings(0).html()];
                                alert(linkIdCards);
                                var arrs = JSON.stringify(linkIdCards);
                                console.log(arrs);
                                $.ajax({
                                    "url": "http://localhost:8001/itrip-biz/api/userinfo/deluserlinkuser",
                                    "type": "GET",
                                    "contentType": 'application/json',
                                    "dataType": "json",
                                    "data": arrs,
                                    "success": function (data) {
                                        console.log("删除联系人成功");
                                        if (data.success == "true") {
                                            var list = data.data;
                                            $("#tabDiv").html("");
                                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>修改</td><td colspan='2'>操作</td></tr>").appendTo($("#tabDiv"));
                                            $(list).each(function () {
                                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td> <a href='#' class='modify'>修改</a></td>  <td><a href='#' class='del'>删除</a></td></tr>");
                                            })
                                        } else {
                                            alert("token失效，请重新登录");
                                        }
                                    },
                                    beforeSend: function (request) {
                                        request.setRequestHeader("token", $.cookie("token"));
                                    }
                                });
                            });

                            $(".modify").click(function () {
                                var linkUserName=$(this).parent().siblings(0).html();
                                alert(linkUserName);
                                var arrs = JSON.stringify(linkUserName);
                                $.ajax({
                                    "url": "http://localhost:8001/itrip-biz/api/userinfo/getlinkusername",
                                    "type": "POST",
                                    "contentType": 'application/json',
                                    "dataType": "json",
                                    "data": arrs,
                                    "success": function (data) {
                                        console.log("查询联系人成功");
                                        $("#tabDiv11").show();
                                        if (data.success == "true") {
                                            var list = data.data;
                                            $("#linkUserName1").val(list.linkUserName);
                                            $("#linkIdCard1").val(list.linkIdCard);
                                            $("#linkPhone1").val(list.linkPhone);
                                            $("#linkUserId").val(list.id);
                                        } else {
                                            alert("token失效，请重新登录");
                                        }
                                    },
                                    beforeSend: function (request) {
                                        request.setRequestHeader("token", $.cookie("token"));
                                    }
                                });
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


            $("#add").click(function () {
                $("#addDiv").toggle();
            });

            $("#modifytijiao").click(function () {
                var str = {
                    linkUserName: $("#linkUserName1").val(),
                    linkIdCard: $("#linkIdCard1").val(),
                    linkPhone: $("#linkPhone1").val(),
                    id:$("#linkUserId").val(),
                    linkIdCardType: 0
                };
                var arrs = JSON.stringify(str);
                console.log(arrs);
                $.ajax({
                    "url": "http://localhost:8001/itrip-biz/api/userinfo/modifyuserlinkuser",
                    "type": "POST",
                    "contentType": 'application/json',
                    "dataType": "json",
                    "data": arrs,
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            var list = data.data;
                            $("#tabDiv").html("");
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td><td>修改</td><td colspan='2'>操作</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td><td> <a href='#' class='modify'>修改</a></td>  <td><a href='#' class='del'>删除</a></td></tr>");
                            })
                        } else {
                            alert("token失效，请重新登录");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                });
            });


            $("#addTijiao").click(function () {
                var str = {
                    linkUserName: $("#linkUserName").val(),
                    linkIdCard: $("#linkIdCard").val(),
                    linkPhone: $("#linkPhone").val(),
                    linkIdCardType:0
                };
                var arrs = JSON.stringify(str);
                console.log(arrs);
                $.ajax({
                    "url": "http://localhost:8001/itrip-biz/api/userinfo/adduserlinkuser",
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
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</tr>");
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
<div>
    旅客姓名：<input type="text" id="name"/>
    <input type="button" id="search" value="获取常用联系人列表"/>
    <input type="button" id="add" value="新增" />
    <input type="button" id="del" value="删除"/>

</div>
<div id="tabDiv">
</div>
<div id="addDiv" style="display: none;">
    <form>
        <table>
            <tr>
                <td>
                    常用联系人姓名:
                </td>
                <td>
                    <input type="text" id="linkUserName" name="linkUserName">
                </td>
            </tr>
            <tr>
                <td>
                    证件号码:
                </td>
                <td>
                    <input type="text" id="linkIdCard" name="linkIdCard">
                </td>
            </tr>
            <tr>
                <td>
                    常用联系人电话:
                </td>
                <td>
                    <input type="text" id="linkPhone" name="linkPhone">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" id="addTijiao" value="提交"/>
                </td>
                <td>
                    <input type="reset" value="重置"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="tabDiv11" style="display: none">
    <table align="center">
        <tr >
            <td>用户ID:</td>
            <td><input type="text" name="id" id="linkUserId" ></td>
        </tr>
        <tr>
            <td>联系人姓名:</td>
            <td><input type="text" name="linkUserName" id="linkUserName1"></td>
        </tr>
        <tr>
            <td>证件号码:</td>
            <td><input type="text" name="linkIdCard" id="linkIdCard1"></td>
        </tr>
        <tr>
            <td>联系电话:</td>
            <td><input type="text" name="linkPhone" id="linkPhone1"></td>
        </tr>
        <tr>
            <td><input type="button" id="modifytijiao" value="修改">
                <input type="reset" value="重置"></td>
        </tr>
    </table>
</div>
</body>
</html>
