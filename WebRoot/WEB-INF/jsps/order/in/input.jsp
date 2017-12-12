<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>

<script type="text/javascript">
	
	//数据联动
	$(function(){
		
		//供应商联动商品类别和商品
		$("#suppliers").change(function(){
			//添加按钮若为隐藏则显示
			$("#add").css("display","inline");

			var supplier_id=$(this).val();
			//根据供应商id发送ajax请求，获取商品类别和商品信息
			$.post("order_ajaxSupplierWithType",{"supplier_id":supplier_id},function(data){
				//数据回来
				var goodsList=data.goodsList;
				var gtList=data.gtList;

				//清空商品类别信息，并创建option
				$(".goodsType").empty();
				for(var i=0;i<gtList.length;i++){
					$op=$("<option value='"+gtList[i].goodsType_id+"'>"+gtList[i].name+"</option>");
					$(".goodsType").append($op);
				}
				//清空商品信息，显示第一个商品类别的商品信息
				$(".goods").empty();
				for(var i=0;i<goodsList.length;i++){
					$op=$("<option value='"+goodsList[i].goods_id+"'>"+goodsList[i].name+"</option>");
					$(".goods").append($op);
				}

				//修改商品价格和小计
				$(".price").eq(0).val(jsFormat(data.first_sale_price));
				$(".total").eq(0).text(jsFormat(data.first_sale_price));
				$(".num").eq(0).val(1);
				addAll();//计算总计

			});

		});

		//商品类别联动商品信息
		$(".goodsType").live("change",function(){
			
			var gt_id=$(this).val();
			//获取当前价格和总计选项
			var $price=$(this).parent().parent().children("td:eq(3)").children();
			var $total=$(this).parent().parent().children("td:eq(4)");
			var $num=$(this).parent().parent().children("td:eq(2)").children();
			
			var $goodsSelect=$(this).parent().next().children("select");//记住当前的商品select   修改该select中的商品数据

			//过滤已出现过的商品(新建情况下)
			var goodsIds=""
			for(var i=0;i<$(".goods").length;i++){
				goodsIds=goodsIds+"'"+$(".goods").eq(i).val()+"'"
			}

			$.post("order_ajaxTypeWithGoods",{"gt_id":gt_id,"goodsIds":goodsIds},function(data){
				var goodsList=data.goodsListAjax;
				//修改商品信息
				$goodsSelect.empty();
				for(var i=0;i<goodsList.length;i++){
					$op=$("<option value='"+goodsList[i].goods_id+"'>"+goodsList[i].name+"</option>");
					$goodsSelect.append($op);
				}


				//修改商品价格和小计
				$price.val(jsFormat(data.first_sale_price));
				$total.text(jsFormat(data.first_sale_price));
				$num.val(1);
				addAll();//计算总计

			});

		});

		//商品联动价格
		$(".goods").live("change",function(){
			var goods_id=$(this).val();

			//获取当前价格和总计选项
			var $price=$(this).parent().parent().children("td:eq(3)").children();
			var $total=$(this).parent().parent().children("td:eq(4)");
			var $num=$(this).parent().parent().children("td:eq(2)").children();

			$.post("order_ajaxGoodsWithPrice",{"goods_id":goods_id},function(data){

				var price=data.goods.sale_price;
				//修改单价和小计
				$price.val(jsFormat(price));
				$total.text(jsFormat(price));
				$num.val(1);
				addAll();//计算总计
			});
		});

		//设置变量控制点击的速度
		//当数据回来后再点击，避免点击过快导致数据错误
		var click_ok;

		//点击新建按钮，动态添加行数据
		$("#add").click(function(){
			

			//点击新建后，锁定供应商，前商品类别和商品信息
			$("#suppliers").attr("disabled","true");
			$(".goodsType").attr("disabled","true");
			$(".goods").attr("disabled","true");
			
			//先加载数据后填充表格
			var gtList;//类别信息
			var gList;//商品信息

			//加载同一供应商的类别信息，过滤已使用过的商品
			    //当一类别的商品都已用完是加载下一类别
			    //同一供应商的所有类别的所有商品都已用完，控制新建按钮
			    var supplier_id=$("#suppliers").val();
			    var $the_gtSelect=$("")
			    
			    var used="";//已使用的商品

			    for(var i=0;i<$(".goods").size();i++){
			    	used=used+"'"+$(".goods").eq(i).val()+"',";
			    }

			    if(click_ok!=false){
			    	click_ok=false;
				    $.post("order_ajaxNewTypeWithGoods",{"supplier_id":supplier_id,"used":used},function(data){
				    	gtList=data.newGtList;//过滤后的商品类别
				    	gList=data.newGoodsList;//商品信息
				    	click_ok=true;

				    	//控制新建按钮，隐藏按钮表示该供应商的所有商品类别和商品都已展示完
				    	
				    	if(gtList=="" ||  gList==""|| (gtList.length<=1 && gList.length<=1)){
				    		$("#add").css("display","none");
				    	}

				    	$tr=$("<tr align='center' bgcolor='#FFFFFF'></tr>");

						$td1=$("<td  height='30'></td>");
						$td2=$("<td></td>");
						$td3=$("<td></td>");
						$td4=$("<td></td>");
						$td5=$("<td  class='total' align='right'></td>");
						$td6=$("<td></td>");

						//类别信息
						var str_gt="";
						str_gt=str_gt+"<select class='goodsType' name='goodsTypes' style='width:200px'>";

						var op="";
						
						for(var i=0;i<gtList.length;i++){
							
							op=op+"<option value='"+gtList[i].goodsType_id+"'>"+gtList[i].name+"</option>"
						}
						str_gt=str_gt+op+"</select>";
						$td1.append(str_gt);

						//商品信息
						str_g="";
						str_g=str_g+"<select class='goods' name='goods' style='width:200px'>";
						
						var op="";
						for(var i=0;i<gList.length;i++){
							op=op+"<option value='"+gList[i].goods_id+"'>"+gList[i].name+"</option>"
						}
						str_g=str_g+op+"</select>";
						$td2.append(str_g);

						//商品个数信息
						str="";
						str=str+"<input  name='num' class='num order_num' style='width:67px;border:1px solid black;text-align:right;padding:2px;' type='text' value='1'/>"
						$td3.append(str);

						//价格信息
						str="";
						str=str+"<input  name='price' class='price order_num' style='width:93px;border:1px solid black;text-align:right;padding:2px' type='text' value='"+jsFormat(data.first_sale_price)+"'/> 元";
						$td4.append(str);

						//小计
						str="";
						str=str+jsFormat(data.first_sale_price)+"&nbsp;元";
						$td5.append(str);

						str="";
						str=str+"<a href='javascript:void(0)' class='deleteBtn delete xiu' value='4'><img src='${pageContext.request.contextPath}/images/icon_04.gif' /> 删除</a>";
						$td6.append(str);

						$tr.append($td1);
						$tr.append($td2);
						$tr.append($td3);
						$tr.append($td4);
						$tr.append($td5);
						$tr.append($td6);

						$("#finalTr").before($tr);
						addAll();//计算总计

				    });
				 }
		});

		$(".deleteBtn").live("click",function(){
			
			//至少保留一行
			if($(".goods").length<=1){
				return;
			}

			//删除当前行
			$the_tr=$(this).parent().parent();
			$the_tr.remove();
			//删除行的同时显示新建按钮
			$("#add").css("display","inline");
			addAll();//计算总计
		});

		//数量联动小计
		$(".num").live("keyup",function(){
			
			var the_num=$(this).val().replace(/[^\d]/g,'');//只能输入正整数
			$(this).val(the_num);

			var $sum=$(this).parent().parent().children("td:eq(4)");
			var $price=$(this).parent().parent().children("td:eq(3)").children();

			$sum.text(jsFormat(the_num*$price.val()))
			addAll();//计算总计
		});

		//单价联动小计
		$(".price").live("keyup",function(){
			var $price=$(this).val().replace(/[^\d.]/g,'');//只能输入正数,小数
			$(this).val($price);
			var $the_num=$(this).parent().parent().children("td:eq(2)").children();
			var $sum=$(this).parent().parent().children("td:eq(4)");

			$sum.text(jsFormat($the_num.val()*$price));
			addAll();//计算总计
		});

		$("#submitOrder").live("click",function(){
			//保存
			$(".goods").removeAttr("disabled");//解除数据锁定，才能提交
			$("#suppliers").removeAttr("disabled");
			$(".goodsType").removeAttr("disabled");

			$("form:first").submit();
		})

	});
	
	//js保留两位小数
	function jsFormat(data){
		return new Number(data).toFixed(2);
	}

	function addAll(){
		//改变总计
		$prices=$(".price");
		$nums=$(".num");
		
		var temp=0.00;
		for(var i=0;i<$prices.length;i++){
			temp=temp+$prices.eq(i).val()*$nums.eq(i).val();
			
		}

		$(".all").html(jsFormat(temp)+"&nbsp;元");

	}
		
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${pageContext.request.contextPath}/order/order_add" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="24">&nbsp;</td>
					</tr>
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
							 <select id="suppliers" name="supplier_ids" class="suppliers" class="kuan" style="width:190px">
								<s:iterator value="suppliersAll" var="supp">
									<option value="${supp.supplier_id }">${supp.name }</option>
								</s:iterator>
							</select>
						</td>
						<td height="30">
							<a id="add"><img src="${pageContext.request.contextPath}/images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
							<select class="goodsType" name="goodsTypes" style="width:200px">
								<s:iterator value="goodsTypesAll" var="gta">
									<option value="${gta.goodsType_id }">${gta.name }</option>
								</s:iterator>
							</select>
						</td>
						<td>
							<select class="goods" name="goods" style="width:200px">
								<s:iterator value="the_goodsAll" var="tga">
									<option value="${tga.goods_id }">${tga.name }</option>
								</s:iterator>
							</select>
						</td>
						<td><input name="num" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input name="price" class="price order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="${ the_goodsAll.get(0).sale_price}"/> 元</td>
						<td class="total" align="right">${ the_goodsAll.get(0).sale_price}&nbsp;元</td>
						<td>
							<a href='javascript:void(0)' class="deleteBtn delete xiu" value="4"><img src="${pageContext.request.contextPath}/images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(${pageContext.request.contextPath}/images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" id="totalPrice" width="16%" align="right">${ the_goodsAll.get(0).sale_price}&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="submitOrder"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:history.go(-1)"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</div>
		</form>
	</div>
	
	<div class="content-bbg"></div>
</div>
