<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jquery-1.8.3.min.js"></script>
		<link href="${pageContext.request.contextPath}/front/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			// 设置秒数
			var second = 5;
			// 定义秒数显示方式
			function showTime() {
				// 执行1次秒数自减
				second--;
				// 判断，如果秒数小于0则跳转页面
				if (second <= 0){
					// 设置href
					location.href = "${pageContext.request.contextPath}/main/show";
				}
				// 更改span标签的显示内容
				$("#time").html(second.toString());
			}
			// 定义定时器，每隔1秒执行一次showTime方法
			setInterval(showTime,1000);
		</script>
	</head>
	<body>
		<%@include file="/front/common/head1.jsp"%>
		<div class="login_step">
			生成订单步骤: 1.确认订单 > 2.填写送货地址 >
			<span class="red_bold">3.订单成功</span>
		</div>


		<div class="login_success">
			<div class="login_bj">
				<div class="succ">
					<img src="${pageContext.request.contextPath}/front/images/order_success.jpg" />
				</div>
				<h5>
					订单已经生成
				</h5>
				<h6>
					您刚刚生成的订单号是：<font color="red"><strong>${requestScope.order.orderNumber}</strong></font>，
					金额总计<font color="red"><strong>${requestScope.order.totalPrice}</strong></font>
				</h6>

				<ul>
					<li class="nobj">
						您现在可以：还有<font color="red"><strong><span id="time"/></strong></font>秒,回到首页！！！
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/main/show">继续浏览并选购商品</a>
					</li>
				</ul>
			</div>
		</div>

		<%@include file="/front/common/foot1.jsp"%>
	</body>
</html>

