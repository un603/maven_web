<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>

    <!-- 静态包含base标签、css样式、jQuery文件 -->
    <%@include file="/common/head.jsp" %>
    <script type="text/javascript">

        $(function () {
            $("a.deleteItem").click(function () {
                // 在事件的 function 函数中，有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象。
                /** confirm 是确认提示框函数
                 * 参数是它的提示内容
                 * 它有两个按钮，一个确认，一个是取消。
                 * 返回 true 表示点击了，确认，返回 false 表示点击取消。
                 */
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
                // return false// 阻止元素的默认行为===不提交请求
            });

            $("#clearCart").click(function () {

                return confirm("你确定要清空购物车吗？");
            });

            //给输入框绑定失去焦点事件 === onchange内容发生改变事件
            $(".updateCount").change(function () {
                //获取商品名称
                const name = $(this).parent().parent().find("td:first").text();
                const id = $(this).attr('bookId');
                //获取商品数量
                const value = this.value;

                if (confirm("你确定要将【" + name + "】商品修改数量为：" + value + "吗？")) {

                    //发起请求。给服务器保存修改
                    location.href =
                        "${pageScope.basePath}cartServlet?action=updateCount&count=" +
                        value +
                        "&id=" +
                        id;
                } else {
                    //defaultValue属性是表单项Dom对象的属性，它表示默认的value属性值
                    this.value = this.defaultValue;
                }
            });

        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>

    <!-- 静态包含，登陆成功之后的菜单 -->
    <%@include file="/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a></td>
            </tr>
        </c:if>

        <!-- 如果购物车非空才输出页面的内容 -->
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>

                        <input class="updateCount"
                               bookId="${entry.value.id}"
                               style="width: 80px;" type="text" value="${entry.value.count}">

                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>


</div>
<!-- 静态包含页脚内容 -->
<%@include file="/common/footer.jsp" %>
</body>
</html>