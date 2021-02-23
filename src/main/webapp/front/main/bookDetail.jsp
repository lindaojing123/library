<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
		<title>图书详情</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front/css/book_detail.css"/>
		<link href="${pageContext.request.contextPath}/front/css/public_footer.css" rel="stylesheet" type="text/css" />
		<script>
			$(function () {
				$('#booksale').click(function () {
					$('#img').attr('src', '${pageContext.request.contextPath}/front/images/load.gif');

					setTimeout(function () {
						$('#img').attr('src', '${pageContext.request.contextPath}/front/images/right.gif');
					}, 1000);
					setTimeout(function () {
						$('#img').attr('src', '${pageContext.request.contextPath}/front/images/buttom_goumai.gif');
					}, 2000);
                    $(this).attr('href','${pageContext.request.contextPath}/cart/addCart?bookId=${requestScope.book.id}');

				});
			});
		</script>
	</head>
	<body>
		<br/>
		<div>
			<a href="${pageContext.request.contextPath}/main/show">回到首页</a>
			<h1>
				丛书名：${requestScope.book.bookName}
			</h1>
			<hr/>
		</div>
		<table width="80%" border="0" align="center" cellspacing="0" cellpadding="5">
			<tr>
				<td rowspan="9" width="20%" valign="top">
					<img src="${pageContext.request.contextPath}/upload/${book.src}" width="240px" height="340px" /></td>
				<td colspan="2">作者：${requestScope.book.author}</td>
			</tr>
			<tr>
				<td colspan="2">出版社：${requestScope.book.company}</td>
			</tr>
			<tr>
				<td>出版时间： ${requestScope.book.publishTime}</td>
				<td>字数：${requestScope.book.wordNum}</td>
			</tr>
			<tr>
				<td>版次：${requestScope.book.edition}</td>
				<td>页数：${requestScope.book.pageNum}</td>
			</tr>
			<tr>
				<td>印刷时间：${requestScope.book.printTime}</td>
				<td>开本：${requestScope.book.sizes}</td>
			</tr>
			<tr>
				<td>印次：</td>
				<td>纸张：${requestScope.book.paper}</td>
			</tr>
			<tr>
				<td>ISBN：${requestScope.book.isbn}</td>
				<td>销量：<font color="red">${requestScope.book.saleCount}</font></td>
			</tr>
			<tr>
				<td>所属类别：<font style='color: #cc3300'><strong>${requestScope.category.categoryParent.name}	</strong>&gt;&gt;${requestScope.category.name}</font>
			</tr>
			<tr>
				<td colspan="2">定价：￥&nbsp;&nbsp;${requestScope.book.price}&nbsp;
				<font color="red">当当价：￥ ${requestScope.book.ddPrice}</font>&nbsp;&nbsp;&nbsp;&nbsp;
				节省：￥${requestScope.book.price - requestScope.book.ddPrice}</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="javascript:void(0)" id="booksale">
						<img src='${pageContext.request.contextPath}/front/images/buttom_goumai.gif' id="img"/>
					</a>
				</td>
			</tr>
		</table>
		<hr style="border:1px dotted #666"/>
		<h2>编辑推荐:</h2>
		<p>&nbsp; ${requestScope.book.editorRecommend}&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>内容简介</h2>
		<p>&nbsp; ${requestScope.book.contentAbstract}&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>作者简介</h2>
		<p>${requestScope.book.authorAbstract}&nbsp;&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>目录</h2>
		<p>${requestScope.book.director}&nbsp;&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>媒体评论</h2>
		<p>${requestScope.book.mediaCommentary}&nbsp;&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>书摘插图</h2>
		<p>&nbsp;&nbsp;</p>
		<!--页尾开始 -->
		<div class="public_footer">
			<div class="public_footer_bottom">
				<div class="public_footer_icp" style="line-height: 48px;">
					Copyright (C) 当当网 2004-2008, All Rights Reserved
					<a href="#" target="_blank"><img src="${pageContext.request.contextPath}/front/images/bottom/validate.gif" border="0" align="middle" /> </a>
					<a href="#" target="_blank" style="color: #666;">京ICP证041189号</a>
				</div>
			</div>
		</div>
		<!--页尾结束 -->
	</body>
</html>
