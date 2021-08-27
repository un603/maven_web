<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
	<%@include file="/common/head.jsp" %>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">结算</span>
		<!-- 静态包含，登陆成功之后的菜单 -->
		<%@include file="/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
		
	
	</div>

	<!-- 静态包含页脚内容 -->
	<%@include file="/common/footer.jsp"%>
</body>
</html>