<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- Jquery -->
<script src="${ctx}/static/jquery/jquery-2.2.3.min.js" type="text/javascript"></script>

<!-- Bootstrap -->
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<script>
    function toIndex() {
        window.location.href = '${ctx}/account/index.do';
    }
</script>