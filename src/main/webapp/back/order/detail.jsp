<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>order</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/btn.css" />
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">

  </head>
  <body style="background-color: #f0f9fd;text-align: center">
  <div align=center style="font-size:25px">订单详细信息</div><hr/>
  	 <table bordercolor='#898E90' align='center' border='3px' cellpadding='5px' cellspacing="0px">
			 <tr bgcolor="lightblue" align="center">
				 <td>订单号：${requestScope.order.orderNumber}</td>
				 <td colspan="2">订单金额：¥：${requestScope.order.totalPrice}</td>
				 <td colspan="2">订单状态：
				     <c:if test="${requestScope.order.isPay == 0}">
						 未付款
					 </c:if>
					 <c:if test="${requestScope.order.isPay == 1}">
						 已付款
					 </c:if>
				 </td>
			 </tr>


            <c:forEach items="${requestScope.orderItems}" var="orderItem">
				<tr align='center'>
					<td>${orderItem.bookName}</td>
					<td style="width: 100px;"><img style="width:32px;height: 20px" src="${pageContext.request.contextPath}/upload/${orderItem.bookSrc}"></td>
					<td style="width: 200px;">当当价：¥${orderItem.price}</td>
					<td style="width: 100px;">小计：¥${orderItem.amount}</td>
					<td style="width: 100px;">${orderItem.num}本</td>
				</tr>
			</c:forEach>
     </table>
	<input class="button btn-p" value="返回上级" onclick="history.go(-1);" style="margin-top: 10px"/>
  </body>
</html>
