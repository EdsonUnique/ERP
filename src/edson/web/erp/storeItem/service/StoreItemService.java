package edson.web.erp.storeItem.service;

import edson.web.erp.storeItem.dao.StoreItemDaoInter;
import edson.web.erp.storeItem.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;
import edson.web.erp.utils.publicBean.PageBean;

public class StoreItemService extends BaseService<StoreItem>implements StoreItemServiceInter {

	private StoreItemDaoInter dao;
	
	public final void setDao(StoreItemDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public void save(StoreItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StoreItem findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(StoreItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(StoreItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageBean<StoreItem> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<StoreItem> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreItem findByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreItem> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

}