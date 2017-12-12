<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">供应商管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath}${model.supplier_id==null?'/supplier/supplier_add':'/supplier/supplier_update'}" method="post">
  			<input type="hidden" name="supplier_id" value="${model.supplier_id }">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商名称</td>
				      <td width="82%" colspan="3">
				      	<input type="text" size="82" name="name" value="${model.name }"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商地址</td>
				      <td width="82%" colspan="3">
				      	<input type="text" size="82" name="address" value="${model.address }"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">联系人</td>
				      <td width="32%">
				      	<input type="text" size="25" name="contact" value="${model.contact }"/>
				      </td>
				      <td width="18%" align="center">电话</td>
				      <td width="32%">
				      	<input type="text" size="25" name="telephone" value="${model.telephone }"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">送货方式</td>
				      <td width="32%">
				      		<select style="width:190px" name="is_send">
								<option value="-1">----请-选-择----</option>
								<option value="1" ${model.is_send==1?'selected':'' }>送货</option>
								<option value="0" ${model.is_send==0?'selected':'' }>自提</option>
							</select>
				      </td>
				      <td width="18%" align="center">&nbsp;</td>
				      <td width="32%">
				      	&nbsp;
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
				
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:document.forms[0].submit()"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a>
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
