package edson.web.erp.order.service;

import java.util.List;
import java.util.Map;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.order.domain.Order;
import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.utils.base.BaseServiceInter;
import edson.web.erp.utils.exception.MessageException;

public interface OrderServiceInter extends BaseServiceInter<Order>{

	/**
	 * 订单存入数据库之前给订单设值
	 * @param goods 商品id数组
	 * @param nums 数量数组
	 * @param prices 单价数组
	 * @param goodsTypes 商品类别id
	 * @param supplier 供应商
	 * @param login 登录对象
	 */
	public void resovleBeforeSave(String[] goods, String[] nums, String[] prices,
			String[] goodsTypes, Supplier supplier, Employee login);
	/**
	 * 根据条件查询订单集合
	 * @param criteriaMap
	 * @return
	 */
	public List<Order> findByQuery(Map<String, Object> criteriaMap);
	public void pass(Integer id,Employee checker) throws MessageException;
	public void noPass(Integer order_id, Employee checker) throws MessageException;
	/**
	 * 分配任务后修改订单信息
	 * @param order_id
	 * @param completer
	 * @throws MessageException 
	 */
	public void assignUpdate(Integer order_id, Employee completer) throws MessageException;
	/**
	 * 查询所有已审核通过的订单(除去未审核和驳回的订单)进行任务分配
	 * @return
	 */
	public List<Order> findAllCheck();
	public void endTask(Integer order_id) throws MessageException;
	/**
	 * 查询所有已分配任务或结单或入库的订单
	 * @return
	 */
	public List<Order> findAssigned();

}