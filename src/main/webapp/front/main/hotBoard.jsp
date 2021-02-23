<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=utf-8"%>
	<h2 class="t_xsrm">
		新书热卖榜
	</h2>
	<div id="NewProduct_1_o_t" onmouseover="">
	<!--新书热卖图书开始-->
		<c:forEach items="${requestScope.saleBooks}" var="book">
			<ul>
				<li>&nbsp;&nbsp;&nbsp;
					<a  target='_blank' href="${pageContext.request.contextPath}/main/bookDetail?bookId=${book.id}&&cateId=${book.cateId}">
						${book.bookName}
					</a>
				</li>
			</ul>
		</c:forEach>
	<!--新书热卖图书结束-->
	<h3 class="second">
		<span class="dot_r"> </span><a href="#" target="_blank"></a>
	</h3>
</div>
