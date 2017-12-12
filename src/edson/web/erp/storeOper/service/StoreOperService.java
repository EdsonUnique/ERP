package edson.web.erp.storeOper.service;

import edson.web.erp.storeOper.dao.StoreOperDaoInter;
import edson.web.erp.storeOper.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;
import edson.web.erp.utils.publicBean.PageBean;

public class StoreOperService extends BaseService<StoreOper>implements StoreOperServiceInter {
	
	private StoreOperDaoInter dao;
	
	
	
	
	public final void setDao(StoreOperDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public void save(StoreOper t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StoreOper findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(StoreOper t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(StoreOper t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageBean<StoreOper> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<StoreOper> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreOper findByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreOper> findListByCriteriaMap(Map<String, Object> criteriaMap) {
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