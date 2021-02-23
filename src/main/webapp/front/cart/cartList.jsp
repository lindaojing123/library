<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jquery-1.8.3.min.js"></script>
		<link href="${pageContext.request.contextPath}/front/css/book.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/second.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/shopping_vehicle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function countFun(obj){
                var num= $(obj).prev().val();
                var checkoutFlag = true;
                var regu = '^[0-9]+$';
                var re = new RegExp(regu);
                if(!re.test(num) ){
                    alert("请输入数字");
                    checkoutFlag = false;
                    return false;
                }
                if(checkoutFlag == true){
                    var numtxt = $(obj).parent().prev().text(num);
                    var bid= $(obj).next().text();
                    // var ddPrice = $(obj).parent().prev().prev().children(":first").text();
                    // var price = $(obj).parent().prev().prev().prev().children(":first").text();
                    // var totalPrice = $("#total_account");
                    // var addprice = parseFloat(ddPrice)*parseInt(num-1);
                    <%--var pms = '&&bid=' + $("#bid").text();--%>
                    <%--var pmd = 'number='+num;--%>
                    <%--a.href = '${pageContext.request.contextPath}/cart/changeCart?number='+num+ pms;--%>
                    window.location.href = "${pageContext.request.contextPath}/cart/changeCart?number="+num+"&&bid="+bid;
                }
			}
		</script>
	</head>
	<body>
		<br />

		<br />
		<div class="my_shopping">
			<img class="pic_shop" src="${pageContext.request.contextPath}/front/images/pic_myshopping.gif" />
		</div>


		<div id="div_no_choice">
			<div class="choice_title"></div>
			<div class="no_select">
				<c:if test="${sessionScope.cart.size() == 0}">
				您还没有挑选商品<a href='${pageContext.request.contextPath}/main/show'> 继续挑选商品&gt;&gt;</a>
				</c:if>
			 </div>
		</div>


		<div id="div_choice" class="choice_merch">
			<h2 id="cart_tips">
				您已选购以下商品
			</h2>
			<div class="choice_bord">
				<table class="tabl_buy" id="tbCartItemsNormal">
					<tr class="tabl_buy_title">
						<td class="buy_td_6">
							<span>&nbsp;</span>
						</td>
						<td>
							<span class="span_w1">商品名</span>
						</td>
						<td class="buy_td_5">
							<span class="span_w2">市场价</span>
						</td>
						<td class="buy_td_4">
							<span class="span_w3">当当价</span>
						</td>
						<td class="buy_td_1">
							<span class="span_w2">数量</span>
						</td>
						<td class="buy_td_2">
							<span>变更数量</span>
						</td>
						<td class="buy_td_1">
							<span>删除</span>
						</td>
					</tr>
					<tr class='objhide' over="no">
						<td colspan="8">
							&nbsp;
						</td>
					</tr>

					<!-- 购物列表开始 -->
					<c:forEach items="${sessionScope.cart}" var="cartItem">
						<td style='display: none'>
								${cartItem.value.book.bookName}
						</td>

						<td class="buy_td_6">
							<span class="objhide"><img /> </span>
						</td>
						<td>
							<a href="#">${cartItem.value.book.bookName}</a>
						</td>
						<td class="buy_td_5">
							<span class="c_gray">${cartItem.value.book.price}</span>
						</td>
						<td class="buy_td_4">
							<span id="price">${cartItem.value.book.ddPrice}</span>
						</td>
						<td class="buy_td_1">
								${cartItem.value.num}
						</td>
						<td>
							<input class="del_num,onlyNum" type="text" size="3" maxlength="4" id="number"/>
							<button onclick="countFun(this)">change</button>
							<span id="bid" style="display:none">${cartItem.value.book.id}</span>
							<a href='${pageContext.request.contextPath}/cart/deleteCart?bookId=${cartItem.value.book.id}' onclick="return confirm('是否删除？')">删除</a>
						</td>
						</tr>
					</c:forEach>
					<!-- 购物列表结束 -->
				</table>

				<div class="choice_balance">
					<div class="select_merch">
						<a href='${pageContext.request.contextPath}/main/show'> 继续挑选商品>></a>
					</div>
					<div class="total_balance">
						<div class="save_total">
							您共节省：
							<span class="c_red"> ￥<span id="total_economy"></span> ${sessionScope.leavePrice}</span>
							<span id='total_vip_economy' class='objhide'>
								( 其中享有优惠：
								<span class="c_red"> ￥<span id='span_vip_economy'></span></span>
								)
							</span>
							<span style="font-size: 14px">|</span>
							<span class="t_add">商品金额总计：</span>
							<span class="c_red_b">￥<span id='total_account'>${sessionScope.totalPrice}</span>
							</span>
						</div>
						<div id="balance" class="balance">
							<a name='checkout' href='${pageContext.request.contextPath}/front/order/orderInfo.jsp' >
								<img src="${pageContext.request.contextPath}/front/images/butt_balance.gif" alt="结算" border="0" title="结算" />
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 用户删除恢复区 -->
		<div id="divCartItemsRemoved" class="del_merch">
			<div class="del_title">
				您已删除以下商品，如果想重新购买，请点击“恢复”
			</div>
			<table class=tabl_del id=del_table>
				<tbody>
					<tr>
						<td width="58" class=buy_td_6>
							&nbsp;
						</td>
						<td width="365" class=t2>
							<a href="#">Java基础</a>
						</td>
						<td width="106" class=buy_td_5>
							￥56
						</td>
						<td width="134" class=buy_td_4>
							<span>￥50</span>
						</td>
						<td width="56" class=buy_td_1>
							<a href="#">恢复</a>
						</td>
						<td width="16" class=objhide>
							&nbsp;
						</td>
					</tr>
					<tr class=td_add_bord>
						<td colspan=8>
							&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 用户删除恢复区结束 -->
		<br />
		<br />
		<br />
		<br />
		<!--页尾开始 -->
		<%@include file="/front/common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>



