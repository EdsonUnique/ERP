package edson.web.erp.role.service;

import edson.web.erp.resource.domain.Resource;
import edson.web.erp.role.dao.RoleDaoInter;
import edson.web.erp.role.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;
import edson.web.erp.utils.publicBean.PageBean;

public class RoleService extends BaseService<Role>implements RoleServiceInter {

	private RoleDaoInter dao;
	
	@Override
	public void save(Role t) {
		dao.save(t);
	}

	@Override
	public Role findById(Integer id) {
		
		return dao.findById(id);
	}

	@Override
	public void update(Role t) {
		
		dao.update(t);
	}

	@Override
	public void delete(Role t) {
		dao.delete(t);
		
	}

	@Override
	public PageBean<Role> findListByPageBean(Integer currentPage) {
		
		return null;
	}

	@Override
	public PageBean<Role> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		
		return null;
	}

	@Override
	public Role findByCriteriaMap(Map<String, Object> criteriaMap) {
		
		return null;
	}

	@Override
	public List<Role> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		return null;
	}

	public final void setDao(RoleDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public List<Resource> findResourcesList() {
		return dao.finResourceList();
	}
	
	

}