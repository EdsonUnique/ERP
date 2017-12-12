package edson.web.erp.store.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.orderItem.dao.OrderItemDaoInter;
import edson.web.erp.orderItem.domain.OrderItem;
import edson.web.erp.store.dao.StoreDaoInter;
import edson.web.erp.store.domain.Store;
import edson.web.erp.storeItem.dao.StoreItemDaoInter;
import edson.web.erp.storeItem.domain.StoreItem;
import edson.web.erp.storeOper.dao.StoreOperDaoInter;
import edson.web.erp.storeOper.domain.StoreOper;
import edson.web.erp.utils.base.BaseService;
import edson.web.erp.utils.exception.MessageException;
import edson.web.erp.utils.publicBean.PageBean;

public class StoreService extends BaseService<Store>implements StoreServiceInter {

	private StoreDaoInter dao;
	private OrderItemDaoInter ot_dao;
	private StoreItemDaoInter st_dao;
	private StoreOperDaoInter oper_dao;
	
	private Map<String,Object>criteriaMap=new HashMap<String,Object>();
	
	
	public final void setDao(StoreDaoInter dao) {
		this.dao = dao;
	}
	
	
	public final void setSt_dao(StoreItemDaoInter st_dao) {
		this.st_dao = st_dao;
	}

	
	public final void setOper_dao(StoreOperDaoInter oper_dao) {
		this.oper_dao = oper_dao;
	}


	public final void setOt_dao(OrderItemDaoInter ot_dao) {
		this.ot_dao = ot_dao;
	}


	@Override
	public void save(Store t) {

		dao.save(t);
	}

	@Override
	public Store findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Store t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Store t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageBean<Store> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Store> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store findByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findByCriteriaMap(criteriaMap);
	}

	@Override
	public List<Store> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeGoods(String orderItem_id, String store_id, String num,
			Employee login) throws MessageException {
		//更新订单项中的商品数据量
		OrderItem item=ot_dao.findById(Integer.valueOf(orderItem_id));
		int new_num=item.getSurplus()-Integer.valueOf(num);
		
		if(new_num<0){//入库数量
			throw new MessageException("入库数量超过限制！");
		}
		
		item.setSurplus(new_num);
		ot_dao.update(item);
		
		//更新仓库明细表
			//若仓库明细表中存在该商品项(商品和商品存放的仓库相同)，则修改数据即可
			//不存在则新建商品仓库明细
		criteriaMap.clear();
		criteriaMap.put("goods.goods_id",item.getGoods().getGoods_id());
		criteriaMap.put("store.store_id",Integer.valueOf(store_id));
		StoreItem st=st_dao.findByCriteriaMap(criteriaMap);
		
		Store store=new Store();//存放商品的仓库
		store.setStore_id(Integer.valueOf(store_id));
		if(st!=null){
			st.setNum(st.getNum()+Integer.valueOf(num));
			st_dao.update(st);
		}else{
			st=new StoreItem();
			st.setGoods(item.getGoods());
			st.setNum(Integer.valueOf(num));
			st.setStore(store);
			st_dao.save(st);
		}
		
		//记录操作日志
		StoreOper oper=new StoreOper();
		oper.setGoods(item.getGoods());
		oper.setNum(Integer.valueOf(num));
		oper.setStore(store);
		oper.setType(StoreOper.IN_STORE);
		oper.setOperator(login);
		oper.setOperTime(new Date().toLocaleString());
		
		oper_dao.save(oper);
		
		
	}

}