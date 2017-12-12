package edson.web.erp.store.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.emp.service.EmpServiceInter;
import edson.web.erp.order.domain.Order;
import edson.web.erp.order.service.OrderServiceInter;
import edson.web.erp.orderItem.service.OrderItemServiceInter;
import edson.web.erp.store.domain.Store;
import edson.web.erp.store.service.StoreServiceInter;
import edson.web.erp.utils.base.BaseAction;
import edson.web.erp.utils.exception.MessageException;

public class StoreAction extends BaseAction implements ModelDriven<Store>{
	
	private StoreServiceInter service;
	private OrderServiceInter o_service;
	private EmpServiceInter emp_service;
	private OrderItemServiceInter ot_service;
	
	private List<Store>stores;
	private List<Employee>emps;
	private Store store=new Store();
	private Order order;
	
	private Map<String,Object>criteriaMap=new HashMap<String,Object>();
	private List<Order>orders;
	
	@Override
	public String list() throws Exception {
		stores=service.findListByCriteriaMap(null);
		return super.list();
	}

	@Override
	public String addOrUpdateUI() throws Exception {
		//加载所有仓库管理员信息
		criteriaMap.clear();
		Employee login=(Employee) getLogin(Employee.EMP_LOGIN_OBJECT);
		criteriaMap.put("dept.did",login.getDept().getDid() );
		emps=emp_service.findListByCriteriaMap(criteriaMap);
		return super.addOrUpdateUI();
	}
	public String add() throws Exception {
		service.save(store);
		return list();
	}
	/**
	 * 跳转到入库页面
	 * @return
	 */
	public String inStoreList(){
		//加载正入库的业务订单，将货物入库
		criteriaMap.clear();
		criteriaMap.put("state", Order.ORDER_STATE_OF_IN_WAREHOUSE);
		orders=o_service.findListByCriteriaMap(criteriaMap);
		return "success_inStoreList";
	}
	/**
	 * 入库页面
	 * @return
	 */
	public String inStoreDetail(){
		order=o_service.findById(Integer.valueOf(getParamter("order_id")));
		
		//加载所有仓库，页面入库时添加
		stores=service.findListByCriteriaMap(null);
		
		return "success_inStoreDetail";
	}
	
//#########################################################################################
	//Ajax
	
	public Integer surplus;//json返回剩余未入库数量
	
	public final Integer getSurplus() {
		return surplus;
	}

	/**
	 * 商品入库
	 * @return
	 * @throws MessageException 
	 */
	public String storeGoods() throws MessageException{
		
		String orderItem_id=getParamter("orderItem_id");
		String store_id=getParamter("store_id");
		String num=getParamter("num");
		Employee login=(Employee) getLogin(Employee.EMP_LOGIN_OBJECT);
		
		service.storeGoods(orderItem_id,store_id,num,login);
		
		surplus=ot_service.findById(Integer.valueOf(orderItem_id)).getSurplus();
		
		return "success_storeGoods";
	}
	
	//入库完成修改   订单状态
	public void storeComplete() throws MessageException{
		String order_id=getParamter("order_id");
		order=o_service.findById(Integer.valueOf(order_id));
		
		if(order.getState()!=Order.ORDER_STATE_OF_IN_WAREHOUSE){
			throw new MessageException("操作错误！！");
		}
		
		order.setState(Order.ORDER_STATE_OF_END);
		o_service.update(order);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//#########################################################################################
	
	
	public final List<Store> getStores() {
		return stores;
	}

	public final void setService(StoreServiceInter service) {
		this.service = service;
	}


	public final List<Order> getOrders() {
		return orders;
	}

	@Override
	public Store getModel() {
		return store;
	}

	public final List<Employee> getEmps() {
		return emps;
	}

	public final void setEmp_service(EmpServiceInter emp_service) {
		this.emp_service = emp_service;
	}

	public final void setO_service(OrderServiceInter o_service) {
		this.o_service = o_service;
	}

	public final Order getOrder() {
		return order;
	}

	public final void setOt_service(OrderItemServiceInter ot_service) {
		this.ot_service = ot_service;
	}

	
	
	

}