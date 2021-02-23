<%@page contentType="text/html;charset=utf-8"%>
<h2>
	编辑推荐
</h2>
<div id=__bianjituijian/danpin>
	<div class=second_c_02>
		<!--编辑推荐图书开始-->
		<c:forEach items="${requestScope.randomBooks}" var="book">
			<div class=second_c_02_b1>
				<div class=second_c_02_b1_1>
					<a href='${pageContext.request.contextPath}/main/bookDetail?bookId=${book.id}&&cateId=${book.cateId}' target='_blank'>
						<img src="${pageContext.request.contextPath}/upload/${book.src}" width=70 border=0 /> </a>
				</div>
				<div class=second_c_02_b1_2>
					<h3>
						<a href='${pageContext.request.contextPath}/main/bookDetail?bookId=${book.id}&&cateId=${book.cateId}' target='_blank'>${book.bookName}</a>
					</h3>
					<h4>
						作者：${book.author} 著
						<br />
						出版社： &nbsp;${book.company}
						出版时间：${book.publishTime}
					</h4>
					<h5>
						${book.edition}
						<span class=pot>...</span>
					</h5>
					<h6>
						定价：￥${book.price}&nbsp;
						当当价：￥${book.ddPrice}
						销量：<font color="red">${book.saleCount}</font>
					</h6>
				</div>
			</div>
		</c:forEach>

		<!--编辑推荐图书结束-->
	</div>
</div>
