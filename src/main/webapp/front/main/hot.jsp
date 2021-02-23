<%@page contentType="text/html;charset=utf-8"%>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>热销图书
</h2>
<div class="book_c_04">

	<!--热销图书A开始-->
	<c:forEach items="${requestScope.saleBooks}" var="book" >
		<div class="second_d_wai">
			<div class="img">
				<a href="${pageContext.request.contextPath}/main/bookDetail?bookId=${book.id}&&cateId=${book.cateId}" target='_blank'>
					<img src="${pageContext.request.contextPath}/upload/${book.src}" border=0 />
				</a>
			</div>
			<div class="shuming">
				<a href="${pageContext.request.contextPath}/main/bookDetail?bookId=${book.id}&&cateId=${book.cateId}" target="_blank">${book.bookName}</a><a href="#" target="_blank"></a>
			</div>
			<div class="price">
				定价：￥${book.price}
			</div>
			<div class="price">
				当当价：￥${book.ddPrice}
			</div>
			<div class="price">
				销量：<font color="red">${book.saleCount}</font>
			</div>
		</div>
		<div class="book_c_xy_long"></div>
	</c:forEach>
	<!--热销图书A结束-->

</div>
<div class="clear"></div>
