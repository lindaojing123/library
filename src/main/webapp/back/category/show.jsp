<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/btn.css" />
	</head>
	<body style="background-color: #f0f9fd;text-align: center;">

		<div style="font-size:25px">类别管理</div><hr/>
		<div style="margin-bottom: 10px;">
			<div class="button btn-p" onclick="location.href='${pageContext.request.contextPath}/back/category/addFirst.jsp'">添加一级类别&raquo;</div>
			<div class="button btn-p" onclick="location.href='${pageContext.request.contextPath}/category/addSecondView'">添加二级类别&raquo;</div>
		</div>
		<table bordercolor='#898E90' align='center' border='3px' cellpadding='10px' cellspacing="0px" >
			<tr bgcolor='lightblue'>
				<td>Id</td>
				<td>类别名</td>
				<td>所属类别</td>
				<td>级别</td>
				<td>操作</td>
			</tr>

           <c:forEach items="${requestScope.pages.items}" var="category">
			   <tr>
				   <td>${category.id}</td>
				   <td>${category.name}</td>
				   <td>${category.categoryParent.name}</td>
				   <td>${category.levels}</td>
				   <td>
					   <div class="button" onclick="location.href='${pageContext.request.contextPath}/category/delete?categoryId=${category.id}' ">删除 &raquo;</div>
				   </td>
			   </tr>
		   </c:forEach>
		</table><br/>
		<!-- 分页 -->
		<center>
			<c:if test="${param.currentPage>1 }">
				<a href="${pageContext.request.contextPath}/category/show?currentPage=${param.currentPage-1 }">上一页</a>
			</c:if>
			'   &nbsp;
			<!-- 展示分页的页码 -->
			<div style="text-align:center;">
				<c:forEach begin="1" end="${requestScope.pages.pageTotal}" var="p">
					<a href="${pageContext.request.contextPath }/category/show?currentPage=${p }">${p }</a> &nbsp;
				</c:forEach>
			</div>
			&nbsp
			<c:if test="${param.currentPage< requestScope.pages.pageTotal }">
				<a href="${pageContext.request.contextPath }/category/show?currentPage=${param.currentPage+1 }">下一页</a>
			</c:if>`
		</center>


	</body>
</html>



