package edson.web.erp.goods.service;

import edson.web.erp.goods.dao.GoodsDaoInter;
import edson.web.erp.goods.domain.*;

import java.util.*;

import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.utils.base.*;
import edson.web.erp.utils.publicBean.PageBean;

public class GoodsService extends BaseService<Goods>implements GoodsServiceInter {

	private GoodsDaoInter dao;
	
	
	
	public final void setDao(GoodsDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public void save(Goods t) {
		dao.save(t);
	}

	@Override
	public Goods findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Goods t) {
		dao.update(t);
	}

	@Override
	public void delete(Goods t) {
		dao.delete(t);
	}

	@Override
	public PageBean<Goods> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Goods> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Goods findByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findByCriteriaMap(criteriaMap);
	}

	@Override
	public List<Goods> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findListByMyCriteriaMap(
			Map<String, Object> criteriaMap) {
		return dao.findListByMyCriteriaMap(criteriaMap);
	}

}