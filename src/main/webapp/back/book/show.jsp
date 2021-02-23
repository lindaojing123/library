<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>index.html</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/btn.css" />
    <meta charset="utf-8">

	<style type="text/css">
		table {
			border-right: 1px solid black;
			border-bottom: 1px solid black;
		}

		table td {
			padding: 10px;
			text-align:center;
			border-left: 1px solid black;
			border-top: 1px solid black;
		}
</style>
  </head>

  <body style="background-color: #f0f9fd;">

  	<div align=center style="font-size:25px">图书管理</div><hr/>
  	<div style="margin: 0 auto;text-align:center;">
  		<form action="${pageContext.request.contextPath}/book/search" method="post">
  			<input onclick="location.href='${pageContext.request.contextPath}/book/addView'" class="button btn-p" value="添加图书&raquo;" />&emsp;&emsp;
		  	<select name="key" class="el-select__inner inner2">
		  		<option value="name">书名</option>
		  		<option value="author">作者</option>
		  		<option value="press">出版社</option>
		  	</select>
		  	<input class="el-input__inner" type="text" placeholder="请输入查询条件" name="content"/>
		  	<input class="el-search-content" type="submit" value="搜索"/>
	  	</form>
  	</div>
  	<div style="margin-top:20px">
  	<table bordercolor='#898E90' align='center' border='3px' cellpadding='5px' cellspacing="0px" >
  		<tr bgcolor='lightblue' align='center'>
			<td style="width: 100px">名称</td>
			<td>作者</td>
			<td>定价</td>
			<td>当当价</td>
			<td>出版社</td>
			<td>出版时间</td>
			<td>封面图</td>
			<td>销量</td>
			<td>库存</td>
			<td>操作</td>
  		</tr>

  		<c:forEach items="${requestScope.pages.items}" var="book">
			<tr align='center'>
				<td>${book.bookName}</td>
				<td>${book.author}</td>
				<td>${book.price}</td>
				<td>${book.ddPrice}</td>
				<td>${book.company}</td>
				<td>${book.publishTime}</td>
				<td>
					<img width="32px" height="20px" src="${pageContext.request.contentType}/upload/${book.src}" >
				</td>
				<td>${book.saleCount}</td>
				<td>${book.stock}</td>
				<td>
					<input  class="button"
							onclick="location.href='${pageContext.request.contextPath}/book/updateView?bookId=${book.id}'" value="修改 " />
					<input  class="button-del" onclick="location.href='${pageContext.request.contextPath}/book/delete?bookId=${book.id}'"  value="删除 " />
				</td>
			</tr>
		</c:forEach>

  	</table>

		<center>
			<c:if test="${param.currentPage>1 }">
				<a href="${pageContext.request.contextPath}/book/show?currentPage=${param.currentPage-1 }&&searchId=${searchId}&&searchName=${searchName}">上一页</a>
			</c:if>
			'   &nbsp;
			<!-- 展示分页的页码 -->
			<div style="text-align:center;">
				<c:forEach begin="1" end="${requestScope.pages.pageTotal}" var="p">
					<a href="${pageContext.request.contextPath }/book/show?currentPage=${p }&&searchId=${searchId}&&searchName=${searchName}">${p }</a> &nbsp;
				</c:forEach>
			</div>
			&nbsp
			<c:if test="${param.currentPage< requestScope.pages.pageTotal }">
				<a href="${pageContext.request.contextPath }/book/show?currentPage=${param.currentPage+1 }&&searchId=${searchId}&&searchName=${searchName}">下一页</a>
			</c:if>`
		</center>

	</div><br>
  </body>
</html>
