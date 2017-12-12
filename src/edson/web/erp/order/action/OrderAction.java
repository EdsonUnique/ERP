package edson.web.erp.order.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.emp.service.EmpServiceInter;
import edson.web.erp.goods.domain.Goods;
import edson.web.erp.goods.service.GoodsServiceInter;
import edson.web.erp.goodsType.domain.GoodsType;
import edson.web.erp.goodsType.service.GoodsTypeServiceInter;
import edson.web.erp.order.domain.Order;
import edson.web.erp.order.service.OrderServiceInter;
import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.supplier.service.SupplierServiceInter;
import edson.web.erp.utils.base.BaseAction;
import edson.web.erp.utils.exception.MessageException;

public class OrderAction extends BaseAction implements ModelDriven<Order>{

	private OrderServiceInter service;
	private Map<String,Object>criteriaMap=new HashMap<String,Object>();//条件map集合
	
	private SupplierServiceInter supp_service;
	private GoodsTypeServiceInter gt_service;
	private GoodsServiceInter g_service;
	private EmpServiceInter emp_service;
	
	
	private List<Supplier> suppliersAll;
	private List<GoodsType> goodsTypesAll;
	private List<Goods> the_goodsAll;
	private List<Employee>emps;
	
	private List<Order>orders;
	private Order order=new Order();
	
	public final List<Order> getOrders() {
		return orders;
	}
	
	public final List<Employee> getEmps() {
		return emps;
	}

	public final void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public final List<Supplier> getSuppliersAll() {
		return suppliersAll;
	}
	public final List<GoodsType> getGoodsTypesAll() {
		return goodsTypesAll;
	}
	public final List<Goods> getThe_goodsAll() {
		return the_goodsAll;
	}
	public final void setSupp_service(SupplierServiceInter supp_service) {
		this.supp_service = supp_service;
	}
	public final void setGt_service(GoodsTypeServiceInter gt_service) {
		this.gt_service = gt_service;
	}
	public final void setG_service(GoodsServiceInter g_service) {
		this.g_service = g_service;
	}
	public final void setService(OrderServiceInter service) {
		this.service = service;
	}
	public final void setEmp_service(EmpServiceInter emp_service) {
		this.emp_service = emp_service;
	}

	
	
//##########################################################################################
	//action请求
	
	public String list() throws Exception {
		//查询所有采购订单
		criteriaMap.clear();
		criteriaMap.put("orderType", Order.ORDER_TYPE_OF_BUY);
		
		orders=service.findListByCriteriaMap(criteriaMap);
		
		return super.list();
	}
	@Override
	public String add() throws Exception {
		//获取页面的参数：商品id数组  数量   单价  供应商id
		String supplier_id=getParamter("supplier_ids");
		String goods[]=getParamters("goods");
		String goodsTypes[]=getParamters("goodsTypes");
		String nums[]=getParamters("num");
		String prices[]=getParamters("price");
		
		Employee login=(Employee) getLogin(Employee.EMP_LOGIN_OBJECT);
		Supplier supplier=new Supplier();
		supplier.setSupplier_id(Integer.valueOf(supplier_id));
		//存储订单
		//存订单前，在业务逻辑层设置订单对象
		service.resovleBeforeSave(goods,nums,prices,goodsTypes,supplier,login);
		
		
		return list();
	}
	/**
	 * 查看订单明细
	 * @return
	 */
	public String orderItemDetail(){
		order=service.findById(order.getOrder_id());
		return "success_orderItemDetail";
	}
	
	
	
	public String query() throws Exception {
		
		String createTime1=getParamter("createTime1");
		String totalNum1=getParamter("totalNum1");
		String totalPrice1=getParamter("totalPrice1");
		
		criteriaMap.clear();
		
		if(order.getCreater().getName()==null || order.getCreater().getName().trim().equals("")){
			order.setCreater(null);
		}else{
			Map<String,Object>temp=new HashMap<String,Object>();
			temp.put("name",order.getCreater().getName());
			if(emp_service.findByCriteriaMap(temp)==null){
				Employee creater=new Employee();
				creater.setEmp_id(-1);
				order.setCreater(creater);
			}else{
				
				order.setCreater(emp_service.findByCriteriaMap(temp));
			}
		}
		
		
		criteriaMap=popToMap(order);
		
		if(createTime1!=null && !createTime1.trim().equals("")){
			criteriaMap.put("createTime1", createTime1);
		}
		if(totalNum1!=null && !totalNum1.trim().equals("")){
			criteriaMap.put("totalNum1", Integer.valueOf(totalNum1));
		}
		if(totalPrice1!=null && !totalPrice1.trim().equals("")){
			criteriaMap.put("totalPrice1", Double.valueOf(totalPrice1));
		}
		
		orders=service.findByQuery(criteriaMap);
		
		return super.query();
	}
	
	
	/**
	 * 采购订单管理列表
	 * 跳转到创建订单页面
	 * @return
	 */
	public String mkOrder(){
		//1加载含有商品类别且商品类别含有商品的供应商
		suppliersAll=supp_service.findHasAllSuppliers();
		
		//2加载第一个供应商的商品类别
		if(suppliersAll!=null && suppliersAll.size()>0){
			goodsTypesAll=new ArrayList<GoodsType>(suppliersAll.get(0).getGoodsTypes());
			
		}
		//3加载第一个供应商的第一个商品类别的所有商品
		the_goodsAll=new ArrayList<Goods>(goodsTypesAll.get(0).getThe_goods());
		
		return "mkOrder";
	}
	/**
	 * 跳转采购审批页面
	 * @return
	 */
	public String approveList(){
		//查询所有采购订单
		criteriaMap.clear();
		criteriaMap.put("orderType", Order.ORDER_TYPE_OF_BUY);
		
		orders=service.findListByCriteriaMap(criteriaMap);
		
		return "success_approveList";
	}
	/**
	 * 跳转到采购决策页面
	 * @return
	 */
	public String toApprove(){
		order=service.findById(order.getOrder_id());
		 return "success_toApprove";
	}
	/**
	 * 决策通过
	 * @return
	 * @throws MessageException 
	 */
	public String pass() throws MessageException{
		service.pass(order.getOrder_id(),(Employee) getLogin(Employee.EMP_LOGIN_OBJECT));
		
		return approveList();
	}
	public String noPass() throws MessageException{
		service.noPass(order.getOrder_id(),(Employee) getLogin(Employee.EMP_LOGIN_OBJECT));
		return approveList();
	}
	
	
	//------------------任务指派--------------------
	//------------------任务指派--------------------
	//------------------任务指派--------------------
	//------------------任务指派--------------------
	
	public String toAssignList(){
		//查询所有已审核通过的订单(除去未审核和驳回的订单)进行任务分配
		
		orders=service.findAllCheck();
		
		return "success_toAssignList";
	}
	/**
	 * 跳转到具体分配任务人页面
	 * @return
	 */
	public String assignEmp(){
		//加载订单信息 
		order=service.findById(order.getOrder_id());//加载订单明细
		
		//加载运输部门所有部员信息:订单任务指派功能只能为运输部门主管使用，因此登陆者即为运输部主管
		Employee emp=(Employee) getLogin(Employee.EMP_LOGIN_OBJECT);
		criteriaMap.clear();
		criteriaMap.put("dept.did", emp.getDept().getDid());
		emps=emp_service.findListByCriteriaMap(criteriaMap);
		
		return "success_assignEmp";
	}
	/**
	 * 分配任务后修改订单信息
	 * @return
	 * @throws MessageException 
	 */
	public String assignToEmp() throws MessageException{
		
		Employee completer=emp_service.findById(Integer.valueOf(getParamter("completer_id")));
		service.assignUpdate(order.getOrder_id(),completer);
		return toAssignList();
	}
	/**
	 * 任务查询
	 * @return
	 */
	public String searchAssign(){
		//查询所有已分配任务或结单或入库的订单
		orders=service.findAssigned();
		 return "success_searchAssign";
	}
	/**
	 * 
	 * 查看任务详情
	 * @return
	 */
	public String taskDetail(){
		order=service.findById(order.getOrder_id());
		return "success_taskDetail";
	}
	/**
	 * 任务结单
	 * @throws MessageException 
	 */
	public String taskEnd() throws MessageException{
		service.endTask(order.getOrder_id());
		return searchAssign();
	}
	
	
	
	
	

//##################################################################################################
	//Ajax请求
	
	private List<GoodsType> gtList;
	private List<Goods> goodsList;
	private List<Goods> goodsListAjax;
	private Double first_sale_price;//第一个商品的单价
	private Goods goods;
	private List<GoodsType>newGtList;
	private List<Goods>newGoodsList;
	
	
	public final List<Goods> getNewGoodsList() {
		return newGoodsList;
	}
	public final List<GoodsType> getNewGtList() {
		return newGtList;
	}
	public final Goods getGoods() {
		return goods;
	}
	public final List<Goods> getGoodsListAjax() {
		return goodsListAjax;
	}
	public final List<GoodsType> getGtList() {
		return gtList;
	}
	public final List<Goods> getGoodsList() {
		return goodsList;
	}
	public final Double getFirst_sale_price() {
		return first_sale_price;
	}
	
	//供应商联动商品类别和商品信息
	public String ajaxSupplierWithType(){
		String supplier_id=ServletActionContext.getRequest().getParameter("supplier_id");
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		criteriaMap.put("supplier.supplier_id", Integer.valueOf(supplier_id));
		
		//1 根据供应商id查询该供应商的商品类别信息
		gtList=gt_service.findListByCriteriaMap(criteriaMap);
		
		//数据过滤
		//一个供应商有多个类别，其中只有一个类别没有商品
		//解决显示其他类别  逆向删除集合中的元素
		for(int i=gtList.size()-1;i>=0;i--){
			if(gtList.get(i).getThe_goods().size()<=0){
				gtList.remove(i);
			}
		}
				
		
		//2 显示第一个商品类别的商品信息
		goodsList=new ArrayList<Goods>(gtList.get(0).getThe_goods());
		
		criteriaMap.put("goodsType.goodsType_id", gtList.get(0).getGoodsType_id());
		first_sale_price=g_service.findByCriteriaMap(criteriaMap).getSale_price();		
		return "ajaxSupplierWithType";
	}
	
	//商品类别联动商品信息
	public String ajaxTypeWithGoods(){
		String goodsType_id=ServletActionContext.getRequest().getParameter("gt_id");
		//已使用过的商品
		String goodsIds=ServletActionContext.getRequest().getParameter("goodsIds");
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		criteriaMap.put("goodsType.goodsType_id", Integer.valueOf(goodsType_id));
		
		goodsListAjax=g_service.findListByCriteriaMap(criteriaMap);
		
		for(int i=goodsListAjax.size()-1;i>=0;i--){
			if(goodsIds.contains("'"+goodsListAjax.get(i).getGoods_id()+"'")){
				goodsListAjax.remove(i);
			}
		}
		
		first_sale_price=goodsListAjax.get(0).getSale_price();
		
		return "ajaxTypeWithGoods";
	}
	
	//商品联动价格
	public String ajaxGoodsWithPrice(){
		String goods_id=ServletActionContext.getRequest().getParameter("goods_id");
		
		goods=g_service.findById(Integer.valueOf(goods_id));
		
		return "ajaxGoodsWithPrice";
	}
	
	//新建商品类别和商品后，过滤已有商品
	//当一类别的商品都已用完是加载下一类别
	public String ajaxNewTypeWithGoods(){
		
		String supplier_id=ServletActionContext.getRequest().getParameter("supplier_id");
		String used=ServletActionContext.getRequest().getParameter("used");
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		criteriaMap.put("supplier.supplier_id", Integer.valueOf(supplier_id));
		//获取供应商的所有类别
		newGtList=gt_service.findListByCriteriaMap(criteriaMap);
		
		//对于每一个类别只要有一个商品没有使用过就保留
		//若都使用过则去除  对于集合元素删除使用逆向删除
		toGt:
		for(int i=newGtList.size()-1;i>=0;i-- ){
			Iterator<Goods> iterator=newGtList.get(i).getThe_goods().iterator();
			while(iterator.hasNext()){
				if(!used.contains("'"+iterator.next().getGoods_id()+"'")){
					//说明该类别包含没使用过的商品，判断下一个类别
					continue toGt;
				}
			}
			
			//说明该类别的所有商品全部使用过
			newGtList.remove(i);
		}
		
		if(newGtList.size()>0){
		
			newGoodsList=new ArrayList<Goods>(newGtList.get(0).getThe_goods());
			//对于已使用过的商品，商品集合中也应去除
			for(int i=newGoodsList.size()-1;i>=0;i--){
				if(used.contains("'"+newGoodsList.get(i).getGoods_id()+"'")){
					newGoodsList.remove(i);
				}
			}
		}
		
		first_sale_price=newGoodsList.get(0).getSale_price();
		return "ajaxNewTypeWithGoods";
	}
	@Override
	public Order getModel() {
		return order;
	}
	
}































