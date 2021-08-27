<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2021/7/26
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%
    String basePath = request.getScheme() +
            "://" +
            request.getServerName() +
            ":" +
            request.getServerPort() +
            request.getContextPath() +
            "/";
%>

<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

