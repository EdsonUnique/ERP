<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">

	$(function(){
		//加载struts查询的所有仓库信息------struts标签和html，jquery混用
		
		var ids=new Array();
		var names=new Array();
		var i=0;
		
		<s:iterator value="stores" var="store">
				ids[i]=${store.store_id}
				names[i]='${store.name}'
				i++;
		</s:iterator >

		//点击入库，自动添加行
		$(".oper").click(function(){

			$(".in").hide();//每次动态添加一行，关闭其它添加行

			$the_tr=$(this).parent().parent();

			$tr=$("<tr class='in'></tr>");

			$td1=$("<td align='center'>仓库</td>")

			$td2=$('<td height="30"></td>');

			$select=$('<select style="width:200px"></select>');
			
			for(var i=0;i<ids.length;i++){
				$op=$("<option value='"+ids[i]+"'>"+names[i]+"</option>")
				$select.append($op);
			}
			
			$td2.append($select);

			$preTr=$(this).parent().prev();
			$td3=$('<td align="center">入库量</td><td><input id="inNum" value="'+$preTr.text()+'" type="text"></td>')
			$td4=$('<td align="center"><a href="javascript:void(0)" class="ajaxIn xiu"><img src="${pageContext.request.contextPath}/images/icon_3.gif">确定</a></td>')

			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			$tr.append($td4);


			$the_tr.after($tr);
		})

		//点击确定，ajax提交入库信息,数据存入数据库后修改页面信息
		$(".ajaxIn").live("click",function(){

			//传递参数给后台：订单项id,仓库id,入库量
			$orderItem_id=$(this).parent().parent().prev().attr("orderItem_id");
			$store_id=$(this).parent().parent().children("td:eq(1)").children("select").val();
			$num=$(this).parent().parent().children("td:eq(3)").children().val();

			$input=$(this).parent().parent().children("td:eq(3)").children();
			$surplusTd=$(this).parent().parent().prev().children("td:eq(3)");
			$item_tr=$(this).parent().parent().prev();
			$nowTr=$(this).parent().parent()

			var paramJson={};
			paramJson["orderItem_id"]=$orderItem_id
			paramJson["store_id"]=$store_id
			paramJson["num"]=$num

			$.post("store_storeGoods",paramJson,function(data){
				//后台传回未入库量data
				var surplus=data;
				//若该项商品入库完成，删除该项内容否则修改剩余商品量
				if(surplus>0){
					$input.val(surplus);
					$surplusTd.text(surplus);
				}else{
					$item_tr.remove()
					$nowTr.remove();
				}

				//全部入库完成显示返回按钮
			
				if($(".is_done").length<=0){
					//修改订单状态
					var order_id=$("#return").attr("order_id");

					$.post("store_storeComplete",{"order_id":order_id},function(data){})

					$("#return").css("display","block");
				}

			})
		})

	})

	/*
		
	*/

	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg">${order.orderNum }</td>
						<td>商品总量:</td>
						<td class="order_show_msg">${order.totalNum }</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center id="inOrderTitle" style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品名称</td>
						<td width="30%">总数量</td>
						<td width="10%">已入库数量</td>
						<td width="30%">剩余数量</td>
						<td width="10%">入库</td>
					
					<s:iterator value="order.orderItems" var="item">
						<s:if test="item.surplus>0">
							<tr orderItem_id=${item.orderItem_id} class="is_done" align="center" bgcolor="#FFFFFF">
								<td height="30">${item.goods.name }</td>
								<td>${item.num }</td>
								<td>${item.num-item.surplus }</td>
								<td>${item.surplus }</td>
								<td>
									<a href="javascript:void(0)" class="oper xiu">
										<img src="${pageContext.request.contextPath}/images/icon_3.gif" />
										入库
									</a>
								</td>
							</tr>
						</s:if>
					</s:iterator>
							
				</table>
				
				<table id="return" order_id="${order.order_id}" style="display:none" >
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a action="list.jsp" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(${pageContext.request.contextPath}/images/btn_bg.jpg)">
								返回
							</a>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
