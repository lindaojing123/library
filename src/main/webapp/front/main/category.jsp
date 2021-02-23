<%@page contentType="text/html;charset=utf-8"%>
<div class="book_l_border1" id="__FenLeiLiuLan">
	<div class="book_sort_tushu">
		<h2>
			分类浏览
		</h2>

		<!--1级分类开始-->
		<c:forEach items="${requestScope.categorys}" var="category1">
		<c:if test="${category1.levels == 1}">
			<div class="bg_old" onmouseover="this.className = 'bg_white';"
				onmouseout="this.className = 'bg_old';">

					<h3>
						[<a href='#'>${category1.name}</a>]
					</h3>
					<ul class="ul_left_list">

						<!--2级分类开始-->
						<c:forEach items="${category1.categories}" var="category2">
							<li>
								<a href='${pageContext.request.contextPath}/main/bookList?cid=${category1.id}&&c2id=${category2.id}&&currentPage=1'>${category2.name}</a>
							</li>
						</c:forEach>
						<!--2级分类结束-->
					</ul>


				<div class="empty_left">
				</div>
			</div>

			<div class="more2">
			</div>
		</c:if>
		</c:forEach>
		<!--1级分类结束-->
	</div>
</div>
