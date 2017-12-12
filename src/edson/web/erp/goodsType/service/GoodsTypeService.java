package edson.web.erp.goodsType.service;

import edson.web.erp.goodsType.dao.GoodsTypeDaoInter;
import edson.web.erp.goodsType.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;
import edson.web.erp.utils.publicBean.PageBean;

public class GoodsTypeService extends BaseService<GoodsType>implements GoodsTypeServiceInter {

	private GoodsTypeDaoInter dao;
	
	
	
	public final void setDao(GoodsTypeDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public void save(GoodsType t) {
		dao.save(t);
	}

	@Override
	public GoodsType findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(GoodsType t) {
		dao.update(t);
	}

	@Override
	public void delete(GoodsType t) {
		dao.delete(t);
	}

	@Override
	public PageBean<GoodsType> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<GoodsType> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoodsType findByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsType> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

}