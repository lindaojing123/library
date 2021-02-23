<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jquery-1.8.3.min.js"></script>
		<link href="${pageContext.request.contextPath}/front/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/page_bottom.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript">
			function  changeImage(img){
				img.src = '${pageContext.request.contextPath }/code/captcha?'+(new Date()).getTime()
			}


			function ckName(obj){
				var regu = '^[a-zA-Z0-9\u4e00-\u9fa5]+$';
				var re = new RegExp(regu);
				var v = re.test(obj.value)
				var msg = document.getElementById('nickNameValidMsg');
				if(v){
					msg.innerHTML = '<img src="${pageContext.request.contextPath}/front/images/yes.png">';
				}
				return v;
            }

			function ckEmail(obj){
				var flag = obj.value.endsWith('.com');
				var msg = document.getElementById('emailValidMsg');
				if(flag){
					msg.innerHTML = '<img src="${pageContext.request.contextPath}/front/images/yes.png">';
				}
				return flag;
			}

			function ckPwd(obj){
				var regu = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
				var re = new RegExp(regu);
				var v = re.test(obj.value);
				if(v && obj.value.length>=6){
					var msg = document.getElementById('passwordValidMsg');
					msg.innerHTML ='<img src="${pageContext.request.contextPath}/front/images/yes.png">';
					return true;
				}
				return false;
			}

			function ckPwd2(obj){
				var pwd = obj.value;
				var pwd2 = document.getElementById('txtPassword').value;
				var msg =  document.getElementById('repeatPassValidMsg');
				if(pwd == pwd2){
					msg.innerHTML ='<img src="${pageContext.request.contextPath}/front/images/yes.png">';
					return true;
				}
				return false;
			}

			function form_submit(){
				var email = document.getElementById('txtEmail');
				isOkEmail = ckEmail(email);

				var name = document.getElementById('txtNickName');
				isOkName = ckName(name);

				var pwd = document.getElementById('txtPassword');
				isOkPwd = ckPwd(pwd);

				var pwd2 = document.getElementById('txtRepeatPass');
				isOkPwd2 = ckPwd2(pwd2);

				var isOks = [isOkPwd,isOkEmail,isOkName,isOkPwd2];
				for(idx in isOks){
					if(!isOks[idx]){
						return false;
					}
				}
				return true;
			}

			$(function (){
			    $("#txtEmail").change(function (){
			    	var val = $(this).val();
			    	val = $.trim(val);
			    	if(val != ""){
                        var url = "${pageContext.request.contextPath}/check.action";
                        var args = {"email":val};
                        $.post(url,args,function (data){
                        	$("#emailInfo").html(data);
						},"html");
					}
				});
			})
		</script>
    </head>
	<body>
		<%@include file="/front/common/head1.jsp"%>
		<div class="login_step">
			注册步骤:
			<span class="red_bold">1.填写信息</span> > 2.验证邮箱 > 3.注册成功
		</div>
		<div class="fill_message">
			<form name="ctl00" method="post" action="${pageContext.request.contextPath}/userView/verifyForm" id="f" onsubmit="return form_submit()">
				<h2>
					以下均为必填项
				</h2>
				<table class="tab_login" >
					<tr>
						<td valign="top" class="w1">
							请填写您的Email地址：
						</td>
						<td>
							<input name="user.email" type="text" id="txtEmail" class="text_input" onkeyup="ckEmail(this)"/>
							<div class="text_left" id="emailValidMsg">
								<p>
									请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件。
								</p>
							</div>
							<span id="emailInfo" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置您在当当网的昵称：
						</td>
						<td>
							<input name="user.nickName" type="text" id="txtNickName" class="text_input" onkeyup="ckName(this)"/>
							<div class="text_left" id="nickNameValidMsg">
								<p>
									您的昵称可以由小写英文字母、中文,数字
								</p>
								<p>
									长度4－20个字符，一个汉字为两个字符。
								</p>
								<span id="name.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置密码：
						</td>
						<td>
							<input name="user.password" type="password" id="txtPassword" class="text_input" onkeyup="ckPwd(this)"/>
							<div class="text_left" id="passwordValidMsg">
								<p>
									您的密码可以由大小写英文字母，长度6－20位。
								</p>
								<span id="password.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							再次输入您设置的密码：
						</td>
						<td>
							<input name="password1" type="password" id="txtRepeatPass" class="text_input"  onkeyup="ckPwd2(this)"/>
							<div class="text_left" id="repeatPassValidMsg">
							<span id="password1.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							验证码：
						</td>
						<td>
							<img class="yzm_img" id='imgVcode' src="${pageContext.request.contextPath }/code/captcha" onClick="changeImage(this)"/>
							<input name="number" type="text" id="txtVerifyCode" class="yzm_input"/>
							<div class="text_left t1">
								<p class="t1">
									<span id="vcodeValidMsg">请输入图片中的四个字母。</span>
									<span id="number.info" style="color:red"></span>
									<a href="javascript:;" onclick="document.getElementById('imgVcode').src = '${pageContext.request.contextPath }/code/captcha?'+(new Date()).getTime()" id="changeImg">看不清楚？换个图片</a>
								</p>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">

					<input id="btnClientRegister" class="button_1" name="submit"  type="submit" value="注 册"/>
				</div>
			</form>
		</div>
		<%@include file="/front/common/foot1.jsp"%>
	</body>
</html>

