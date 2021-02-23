<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>生成订单 - 当当网</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jquery-1.8.3.min.js"></script>
		<link href="${pageContext.request.contextPath}/front/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function ckName(obj){
				var regu = '^[a-zA-Z0-9\u4e00-\u9fa5]+$';
				var re = new RegExp(regu);
				if( re.test($(obj).val())){
					$('#nameValidMsg').html('<img src="${pageContext.request.contextPath}/front/images/yes.png">');
					return true;
				}else{
					$('#nameValidMsg').html('<img src="${pageContext.request.contextPath}/front/images/fail.png">');
				}
				return false;
			}

			function ckAddr(obj){
				var regu = '^[a-zA-Z0-9\u4e00-\u9fa5]+$';
				var re = new RegExp(regu);

				if(re.test($(obj).val()) && $(obj).val().length>6){
					$('#addressValidMsg').html('<img src="${pageContext.request.contextPath}/front/images/yes.png">');
					return true;
				}else{
					$('#addressValidMsg').html('<img src="${pageContext.request.contextPath}/front/images/fail.png">');
				}
				return false;
			}

			function ckZipCode(obj){
				var regu = '^[0-9]+$';
				var re = new RegExp(regu);
				if( re.test($(obj).val()) && $(obj).val().length==5){
					$('#codeValidMsg').html('<img src="${pageContext.request.contextPath}/front/images/yes.png">');
					return true;
				}else{
					$('#codeValidMsg').html('<img src="${pageContext.request.contextPath}/front/images/fail.png">');
				}
				return false;
			}
			function ckPhone(obj){
				var regu = '^[0-9]+$';
				var re = new RegExp(regu);
				if(re.test($(obj).val()) && $(obj).val().length==11){
					$('#mobileValidMsg').html('<img src="${pageContext.request.contextPath}/front/images/yes.png">');
					return true;
				}else{
					$('#mobileValidMsg').html('<img src="${pageContext.request.contextPath}/front/images/fail.png">');
				}
				return false;
			}

			function form_submit(){
				isSuccessName = ckName($("#receiveName"));
				isSuccessAddr = ckAddr($("#fullAddress"));
				isSuccessCode = ckZipCode($("#postalCode"));
				isSuccessPhone = ckPhone($("#mobile"));

				var isSuccesss = [isSuccessName,isSuccessAddr,isSuccessCode,isSuccessPhone];
				for(idx in isSuccesss){
					if(!isSuccesss[idx]){
						return false;
					}
				}
				return true;
			}
            function address(obj){
				var value = $("#address").find("option:selected").val();
				window.location.href='${pageContext.request.contextPath}/order/fillAddress?addressId='+value;
			}

		</script>
	</head>
	<body>
		<%@include file="/front/common/head1.jsp"%>
		<div class="login_step">
			生成订单骤: 1.确认订单 >
			<span class="red_bold"> 2.填写送货地址</span> > 3.订单成功
		</div>
		<div class="fill_message">
			<p>
				选择地址：
				<select id="address" onchange="address(this)">
					<option>请填写地址</option>
					<c:forEach items="${sessionScope.addresses}" var="address">
						<option value="${address.id}">
							${address.detail}
						</option>
					</c:forEach>
				</select>
			</p>
			<form name="ctl00" method="post" action="${pageContext.request.contextPath}/order/orderOk" id="f" onsubmit="return form_submit()">
				<input type="hidden" name="id" id="addressId" />

				<table class="tab_login">
					<tr>
						<td valign="top" class="w1">
							收件人姓名：
						</td>
						<td>
							<input type="text" class="text_input" name="address.name" id="receiveName" onkeyup="ckName(this)" value="${requestScope.address.name}"/>
							<div class="text_left" id="nameValidMsg">
								<p>
									请填写有效的收件人姓名
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1" >
							收件人详细地址：
						</td>
						<td>
							<input type="text" name="address.detail" class="text_input" id="fullAddress" onkeyup="ckAddr(this)" value="${requestScope.address.detail}"/>
							<div class="text_left" id="addressValidMsg">
								<p>
									请填写有效的收件人的详细地址
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1" >
							邮政编码
						</td>
						<td>
							<input type="text" class="text_input" name="address.zipCode" id="postalCode" onkeyup="ckZipCode(this)" value="${requestScope.address.zipCode}"/>
							<div class="text_left" id="codeValidMsg">
								<p>
									请填写有效的收件人的邮政编码
								</p>
							</div>
						</td>
					</tr>
						<td valign="top" class="w1">
							手机
						</td>
						<td>
							<input type="text" class="text_input" name="address.phone" id="mobile" onkeyup="ckPhone(this)" value="${requestScope.address.phone}"/>
							<div class="text_left" id="mobileValidMsg">
								<p>
									请填写有效的收件人的手机
								</p>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">
					<a href="${pageContext.request.contextPath}/front/order/orderInfo.jsp">
						<input  class="button_1" name="submit" type="button" value="取消" />
					</a>
				<input id="btnClientRegister" class="button_1" name="submit" type="submit" value="下一步" />
				</div>
			</form>
		</div>
		<%@include file="/front/common/foot1.jsp"%>
	</body>
</html>

