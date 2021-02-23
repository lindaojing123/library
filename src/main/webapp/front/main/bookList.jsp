<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jquery-1.8.3.min.js"></script>
		<link href="${pageContext.request.contextPath}/front/css/book.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/second.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/prototype-1.6.0.3.js"></script>
		<script type="text/javascript" >
			// 文档加载完毕执行
			function sortBook(obj){
				var cid = $("#cid").val();
				var c2id = $("#c2id").val();
				var searchId = $(obj).val();
				window.location.href = '${pageContext.request.contextPath}/main/bookList?searchId='+searchId+'&&c2id='+c2id+'&&cid='+cid+'&&currentPage=1';
			}
		</script>
	</head>
	<body>
		&nbsp;

		<!-- 头部开始 -->
		<%@include file="/front/common/head.jsp"%>
		<!-- 头部结束 -->

		<div style="width: 962px; margin: auto;">
			<a href="#"><img src="${pageContext.request.contextPath}/front/images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>
		<div class='your_position'>
			您现在的位置:&nbsp;
			<a href='${pageContext.request.contextPath}/main/show'>当当图书</a> &gt;&gt;
			<font style='color: #cc3300'><strong>${requestScope.categoryParent.name}</strong> </font>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<div id="__fenleiliulan">
					<div class=second_l_border2>
						<h2>
							分类浏览
						</h2>
						<ul>
							<li>
								<div>
									<div class=second_fenlei>
										&middot;全部&nbsp;
									</div>
								</div>
							</li>
							<div class="clear"></div>

							<!--2级分类开始-->
							<c:forEach items="${requestScope.categories2}" var="category">
							<li>
								<div>
									<div class=second_fenlei>
										&middot;
									</div>
									<div class=second_fenlei>
										<a href="${pageContext.request.contextPath}/main/bookList?c2id=${category.id}&&cid=${category.categoryParent.id}&&currentPage=1">&nbsp;${category.name}</a>
									</div>
								</div>
							</li>
								<div class="clear"></div>
							</c:forEach>
							<!--2级分类结束-->

							<li>
								<div></div>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">

					<div id="book_list" class="list_r_title">
						<div class="list_r_title_text">
							排序方式
						</div>
						<span id="choose"></span>
						<select onchange="sortBook(this)" name='select_order' size='1'
							class='list_r_title_ml'>
							<c:if test="${searchId == 'select_time'}">
								<option value="select_time" selected="selected">
									按上架时间 降序
								</option>
							</c:if>
							<c:if test="${searchId != 'select_time'}">
								<option value="select_time" >
									按上架时间 降序
								</option>
							</c:if>
							<c:if test="${searchId == 'select_price_desc'}">
								<option value="select_price_desc" selected="selected">
									按价格 降序
								</option>
							</c:if>
							<c:if test="${searchId != 'select_price_desc'}">
								<option value="select_price_desc" >
									按价格 降序
								</option>
							</c:if>
							<c:if test="${searchId == 'select_price_asc'}">
								<option value="select_price_asc" selected="selected">
									按价格 升序
								</option>
							</c:if>
							<c:if test="${searchId != 'select_price_asc'}">
								<option value="select_price_asc" >
									按价格 升序
								</option>
							</c:if>
						</select>
						<div id="divTopPageNavi" class="list_r_title_text3">

							<!--分页导航开始-->

							<div class='list_r_title_text3a'>
									<img src='${pageContext.request.contextPath}/front/images/page_up.gif' />
							</div>

							<div class='list_r_title_text3a'>
								<c:if test="${param.currentPage>1 }">
								<a name=link_page_next href="${pageContext.request.contextPath}/main/bookList?currentPage=${param.currentPage-1 }&&searchId=${searchId}&&c2id=${c2id}&&cid=${cid}">
								    <img src='${pageContext.request.contextPath}/front/images/page_up_gray.gif' />
								</a>
								</c:if>
							</div>
							<div class='list_r_title_text3b'>
								第<span>${requestScope.currentPage}</span>页
							</div>
							<div class='list_r_title_text3a'>
                           <c:if test="${param.currentPage<requestScope.pages.pageTotal }">
								<a name=link_page_next href="${pageContext.request.contextPath }/main/bookList?currentPage=${param.currentPage+1 }&&searchId=${searchId}&&c2id=${c2id}&&cid=${cid}">
									<img src='${pageContext.request.contextPath}/front/images/page_down.gif' />
								</a>
						   </c:if>
							</div>

							<div class='list_r_title_text3a'>
								<img src='${pageContext.request.contextPath}/front/images/page_down_gray.gif' />
							</div>
                             <input id="cid" value="${param.cid}" type="hidden"/>
							 <input id="c2id" value="${param.c2id}" type="hidden"/>
							<!--分页导航结束-->
						</div>
					</div>

					<!--商品条目开始-->
                     <c:forEach items="${requestScope.pages.items}" var="book">
						 <div class="clear"></div>
						 <div class="list_r_list">
								<span class="list_r_list_book">
									<a name="link_prd_img" href='${pageContext.request.contextPath}/main/bookDetail?bookId=${book.id}&&cateId=${book.cateId}'>
										<img src="${pageContext.request.contextPath}/upload/${book.src}" />
									</a>
								</span>
							 <h2>
								 <a name="link_prd_name" href='${pageContext.request.contextPath}/main/bookDetail?bookId=${book.id}&&cateId=${book.cateId}'>${book.bookName}</a>
							 </h2>
							 <h3>
								 顾客评分：100
							 </h3>
							 <h4 class="list_r_list_h4">
								 作 者:
								 <a href='#' name='作者'>${book.author}</a>
							 </h4>
							 <h4>
								 出版社：
								 <a href='#' name='出版社'>${book.company}</a>
							 </h4>
							 <h4>
								 出版时间：${book.publishTime}
							 </h4>
							 <h5>
								 ${book.mediaCommentary}
							 </h5>
							 <div class="clear"></div>
							 <h6>
								 <span class="del">￥${book.price}</span>
								 <span class="red">￥${book.ddPrice}</span>
								 节省：￥${book.price - book.ddPrice}
							 </h6>
							 <span class="list_r_list_button">
								<a href="${pageContext.request.contextPath}/cart/addCart?bookId=${book.id}">
									<img src='${pageContext.request.contextPath}/front/images/buttom_goumai.gif' />
								</a>
								</span>
							 <span id="cartinfo"></span>
						 </div>
						 <div class="clear"></div>

					 </c:forEach>


						<!--商品条目结束-->

					<div class="clear"></div>
					<div id="divBottomPageNavi" class="fanye_bottom">
					</div>

				</div>
				<!--图书列表结束-->

			</div>
			<!--中栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="/front/common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
