<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function changePwd(){
		/*alert("heh");
		var password=document.getElementById("password").value;
		var newPassword=document.getElementById("newPassword").value;
		var re_newPassword=document.getElementById("re_newPassword").value;
		alert("ssss");
		if(password=="null" || password==""){
			alert("hh");
			var span=document.createElement("span");
			alert(span)
			span.innerText="原始密码不能为空！";
			document.getElementById("password").appendChild(span);
		}
		*/
		
		document.forms[0].submit();
		
		
		
	
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">修改密码</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath }/employee/emp_changePwd" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					<tr  bgcolor="#FFFFFF">
				      <td align="center">原始密码</td>
				      <td colspan="2">
				      	<input type="password" id="password" name="password" size="25"/>
				      </td>
				      <td>
							<span style="color:red;"><s:actionmessage theme="simple"/></span>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
					<tr  bgcolor="#FFFFFF">
				      <td align="center">新&nbsp;密&nbsp;码</td>
				       <td colspan="3">
				      	<input type="password" name="newPassword" id="newPassword" size="25"/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
					<tr  bgcolor="#FFFFFF">
				      <td align="center">确认密码</td>
				       <td colspan="3">
				      	<input type="password" name="re_newPassword" id="re_newPassword" size="25"/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:changePwd()"  ><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${pageContext.request.contextPath}/images/content_bbg.jpg" /></div>
</div>
