<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<!-- 静态包含base标签、css样式、jQuery文件 -->
	<%@include file="/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给删除的 a 标签绑定单击事件，用于删除的确认提示操作
			$("#receive").click(function () {
				// 在事件的 function 函数中，有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象。
				/** confirm 是确认提示框函数
				 * 参数是它的提示内容
				 * 它有两个按钮，一个确认，一个是取消。
				 * 返回 true 表示点击了，确认，返回 false 表示点击取消。
				 */
				return confirm("你确定要签收吗?");
				// return false// 阻止元素的默认行为===不提交请求
			});

		})
	</script>
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
			<span class="wel_word">我的订单</span>
		<!-- 静态包含，登陆成功之后的菜单 -->
		<%@include file="/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>		

			<c:forEach items="${sessionScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<c:if test="${order.status == 0}">
						<td>未发货</td>
					</c:if>
					<c:if test="${order.status == 1}">
						<td><a id="receive" href="orderServlet?action=receive&orderId=${order.orderId}">已发货，是否签收</a></td>
					</c:if>
					<c:if test="${order.status == 2}">
						<td>已签收</td>
					</c:if>

					<td><a href="#">查看详情</a></td>
				</tr>
			</c:forEach>

		</table>
		
	
	</div>

	<!-- 静态包含页脚内容 -->
	<%@include file="/common/footer.jsp"%>
</body>
</html>