package edson.web.erp.order.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.goods.domain.Goods;
import edson.web.erp.goodsType.domain.GoodsType;
import edson.web.erp.order.dao.OrderDaoInter;
import edson.web.erp.order.domain.Order;
import edson.web.erp.orderItem.domain.OrderItem;
import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.utils.base.BaseService;
import edson.web.erp.utils.ensecret.OrderIdGner;
import edson.web.erp.utils.exception.MessageException;
import edson.web.erp.utils.publicBean.PageBean;

public class OrderService extends BaseService<Order>implements OrderServiceInter {
	
	private OrderDaoInter dao;
	
	public final void setDao(OrderDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public void resovleBeforeSave(String[] goods, String[] nums,
			String[] prices,String gt_ids[],Supplier supplier, Employee login) {
		//给订单对象设置相应的值
		Order order=new Order();
		
		String createTime=new Date().toLocaleString();
		order.setCreateTime(createTime);
		
		order.setOrderType(Order.ORDER_TYPE_OF_BUY);
		order.setState(Order.ORDER_STATE_OF_NO_CHECK);
		
		order.setTotalNum(nums.length);
		
		order.setCreater(login);
		order.setSupplier(supplier);
		
		double totalPrice=0.0;
		for(int i=0;i<nums.length;i++){
			totalPrice+=Integer.valueOf(nums[i])*Double.valueOf(prices[i]);
		}
		order.setTotalPrice(totalPrice);
		
		//订单号生成
		order.setOrderNum(OrderIdGner.generateOrderId());
		
		//设置订单项  ---- 级联保存
		
		for(int i=0;i<nums.length;i++){
			OrderItem item=new OrderItem();
			
			item.setPrice(Double.valueOf(prices[i]));
			item.setNum(Integer.valueOf(nums[i]));
			item.setSum(Double.valueOf(prices[i])*Integer.valueOf(nums[i]));
			
			item.setOrder(order);
			Goods g=new Goods();
			g.setGoods_id(Integer.valueOf(goods[i]));
			item.setGoods(g);
			
			GoodsType gt=new GoodsType();
			gt.setGoodsType_id(Integer.valueOf(gt_ids[i]));
			
			item.setGoodsType(gt);
			
			
			order.getOrderItems().add(item);
		}
		
		save(order);
	}
	
	@Override
	public void save(Order t) {
		dao.save(t);
	}

	@Override
	public Order findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Order t) {
		dao.update(t);
	}

	@Override
	public void delete(Order t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageBean<Order> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Order> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findByQuery(Map<String, Object> criteriaMap) {
		return dao.findByQuery(criteriaMap);
	}

	@Override
	public void pass(Integer id,Employee checker) throws MessageException {
		Order order=findById(id);

		//逻辑校验  避免已审核的订单再次审核  即地址栏输入
		if(order.getState()==Order.ORDER_STATE_OF_NO_CHECK){
			order.setState(Order.ORDER_STATE_OF_PASS);
			//设置相关数据：审核人，审核时间
			order.setChecker(checker);
			order.setCheckTime(new Date().toLocaleString());
			update(order);
		}else{
			throw new MessageException("悟空，你又调皮了！");
		}
	}

	@Override
	public void noPass(Integer id,Employee checker) throws MessageException {
		Order order=findById(id);

		//逻辑校验  避免已审核的订单再次审核  即地址栏输入
		if(order.getState()==Order.ORDER_STATE_OF_NO_CHECK){
			order.setState(Order.ORDER_STATE_OF_NO_PASS);
			
			order.setChecker(checker);
			order.setCheckTime(new Date().toLocaleString());
			update(order);
		}else{
			throw new MessageException("悟空，你又调皮了！");
		}
				
	}

	@Override
	public void assignUpdate(Integer order_id, Employee completer) throws MessageException {
		Order order=findById(order_id);
		
		order.setCompleter(completer);
		
		//逻辑校验:只有审核通过的订单才能进行任务分配
		if(order.getState()!=Order.ORDER_STATE_OF_PASS){
			throw new MessageException("悟空，你又调皮了!");
		}
		
		order.setState(Order.ORDER_STATE_OF_BUYING);
		update(order);
	}

	@Override
	public List<Order> findAllCheck() {
		return dao.findAllCheck();
	}

	@Override
	public void endTask(Integer order_id) throws MessageException {
		//采购任务完成，修改订单状态
		Order order=findById(order_id);
		
		if(order.getState()!=Order.ORDER_STATE_OF_BUYING){
			throw new MessageException("悟空，你又调皮了!");
		}
		
		order.setState(Order.ORDER_STATE_OF_IN_WAREHOUSE);
		order.setEndTime(new Date().toLocaleString());
		update(order);
	}

	@Override
	public List<Order> findAssigned() {
		return dao.findAssigned();
	}

	

}