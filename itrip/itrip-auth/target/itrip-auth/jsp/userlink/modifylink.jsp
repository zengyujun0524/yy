<%--
  Created by IntelliJ IDEA.
  User: 10418
  Date: 2018/12/11
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加页面</title>
</head>
<body>
<div id="modifyDiv">
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
</body>
</html>