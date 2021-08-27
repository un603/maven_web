<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>哈工大图书馆会员注册页面</title>
	<!-- 静态包含base标签、css样式、jQuery文件 -->
	<%@include file="/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
			<!-- 静态包含，登陆成功之后的菜单 -->
			<%@include file="/common/login_success_menu.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	
		</div>

		<!-- 静态包含页脚内容 -->
		<%@include file="/common/footer.jsp"%>
</body>
</html>