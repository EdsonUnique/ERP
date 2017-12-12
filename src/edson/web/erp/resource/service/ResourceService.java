package edson.web.erp.resource.service;

import java.util.List;
import java.util.Map;

import edson.web.erp.resource.dao.ResourceDaoInter;
import edson.web.erp.resource.domain.Resource;
import edson.web.erp.utils.base.BaseService;
import edson.web.erp.utils.publicBean.PageBean;

public class ResourceService extends BaseService<Resource>implements ResourceServiceInter {

	private ResourceDaoInter dao;
	
	@Override
	public void save(Resource t) {
		dao.save(t);
	}

	@Override
	public Resource findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Resource t) {
		dao.update(t);
	}

	@Override
	public void delete(Resource t) {
		dao.delete(t);
	}

	@Override
	public PageBean<Resource> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Resource> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource findByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findByCriteriaMap(criteriaMap);
	}

	@Override
	public List<Resource> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public final void setDao(ResourceDaoInter dao) {
		this.dao = dao;
	}
	
	

}