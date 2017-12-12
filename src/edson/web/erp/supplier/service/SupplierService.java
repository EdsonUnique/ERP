package edson.web.erp.supplier.service;

import edson.web.erp.supplier.dao.SupplierDaoInter;
import edson.web.erp.supplier.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;
import edson.web.erp.utils.publicBean.PageBean;

public class SupplierService extends BaseService<Supplier>implements SupplierServiceInter {

	private SupplierDaoInter dao;
	
	public final void setDao(SupplierDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public void save(Supplier t) {
		dao.save(t);
	}

	@Override
	public Supplier findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Supplier t) {
		dao.update(t);
	}

	@Override
	public void delete(Supplier t) {
		dao.delete(t);
	}

	@Override
	public PageBean<Supplier> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Supplier> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier findByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Supplier> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Supplier> findListByNotCriteriaMap(
			Map<String, Object> criteriaMap) {
		return dao.findListByNotCriteriaMap(criteriaMap);
	}

	@Override
	public List<Supplier> findListWithoutType() {
		return dao.findListWithoutType();
	}

	@Override
	public List<Supplier> findHasAllSuppliers() {
		return dao.findHasAllSuppliers();
	}

}