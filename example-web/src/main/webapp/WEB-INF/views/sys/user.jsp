<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户列表</title>
    <%@include file="../common/include.jsp" %>
</head>
<body>
<script>
    function toList() {
        window.location.href = '${ctx}/user/list.do';
    }
</script>
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <br/>
            <div class="panel panel-default">
                <div class="panel-body">
                    <button type="button" onclick="preSave()" class="btn btn-default">添加</button>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>姓名</th>
                        <th>年龄</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${pageData.data}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.age}</td>
                            <td>
                                <a href="javascript:void(0);" onclick="preSave('${user.id}')" title="编辑">
                                    <i class="glyphicon glyphicon-edit"></i>
                                </a>
                                <a href="javascript:void(0);" onclick="del('${user.id}')" title="删除">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    function preSave(id) {
        if (id == undefined || id == null || id == '') {
            window.location.href = '${ctx}/user/preSave.do';
        } else {
            window.location.href = '${ctx}/user/preSave.do?id=' + id;
        }
    }

    function del(id) {
        $.ajax({
            type: 'POST',
            url: '${ctx}/user/delete.do',
            data: {
                id: id
            },
            dataType: 'json',
            success: function (result) {
                alert(result.resultMsg);
                if (result.data) {
                    setTimeout('toList()', 100);
                }
            },
            error: function () {
                alert("出错了,请重试!");
            }
        });
    }
</script>
</body>
</html>