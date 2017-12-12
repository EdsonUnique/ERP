<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
		<form action="inApproveList.jsp" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td  height="30">下单时间:</td>
						<td>
							<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>到</td>
						<td>
							&nbsp;&nbsp;<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>总量:</td>
						<td><input type="text" size="14" /></td>
						<td>到 </td>
						<td>&nbsp;&nbsp;<input type="text" size="14" /></td>
					</tr>
					<tr>
						<td>总额:</td>
						<td><input type="text" size="14" /></td>
						<td>到</td>
						<td>&nbsp;&nbsp;<input type="text" size="14" /></td>
						<td>下单人:</td>
						<td><input type="text" size="14" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;&nbsp;<a id="query"> 
							<img src="${pageContext.request.contextPath}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">订单号</td>
						<td width="17%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="9%">审核</td>
					</tr>
					<s:iterator value="orders" var="od">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30">${od.orderNum }</td>
							<td>${od.supplier.name }</td>
							<td>${od.creater.name }</td>
							<td>${od.createTime }</td>
							<td>${od.totalNum }</td>
							<td align="right">${od.totalPrice } 元</td>
							<td>
								<s:if test="#od.state==@edson.web.erp.order.domain.Order@ORDER_STATE_OF_NO_CHECK">
									<a href="${pageContext.request.contextPath}/order/order_toApprove?order_id=${ od.order_id}">${od.state_view }</a>
								</s:if>
								<s:else>
									<span style="color:red">${od.state_view }</span>
								</s:else>
							</td>
						</tr>
					</s:iterator>
					
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
