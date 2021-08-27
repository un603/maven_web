<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <!-- 静态包含base标签、css样式、jQuery文件 -->
    <%@include file="/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
                //给删除的 a 标签绑定单击事件，用于删除的确认提示操作
                $("a.deleteClass").click(function () {
                    // 在事件的 function 函数中，有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象。
                    /** confirm 是确认提示框函数
                     * 参数是它的提示内容
                     * 它有两个按钮，一个确认，一个是取消。
                     * 返回 true 表示点击了，确认，返回 false 表示点击取消。
                     */
                    return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
                    // return false// 阻止元素的默认行为===不提交请求
                });

            //跳到指定的页码
            $("#searchPageBtn").click(function () {
                const pageNo = $("#pn_input").val();


                if (pageNo > 0 && pageNo <=${requestScope.page.pageTotal}) {
                    <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                    <%--alert(pageTotal);--%>
                    // javaScript 语言中提供了一个 location 地址栏对象
                    // 它有一个属性叫 href.它可以获取浏览器地址栏中的地址
                    // href 属性可读，可写
                    location.href = "${pageScope.basePath}manager/bookServlet?action=page&pageNo=" + pageNo;
                } else {
                    $("span.errorMsg").text("请输入正确的页码！");
                    return false;
                }

            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <!-- 静态包含manager管理模块的菜单 -->
    <%@include file="/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>


    <%@include file="/common/page_nav.jsp"%>


</div>

<!-- 静态包含页脚内容 -->
<%@include file="/common/footer.jsp" %>
</body>
</html>