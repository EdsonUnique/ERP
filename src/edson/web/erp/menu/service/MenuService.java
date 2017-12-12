package edson.web.erp.menu.service;

import edson.web.erp.menu.dao.MenuDao;
import edson.web.erp.menu.dao.MenuDaoInter;
import edson.web.erp.menu.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;
import edson.web.erp.utils.publicBean.PageBean;

public class MenuService extends BaseService<Menu>implements MenuServiceInter {

	private MenuDaoInter dao;
	
	public final void setDao(MenuDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public void save(Menu t) {
		dao.save(t);
	}

	@Override
	public Menu findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Menu t) {
		dao.update(t);
	}

	@Override
	public void delete(Menu t) {
		dao.delete(t);
	}

	@Override
	public PageBean<Menu> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Menu> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu findByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findListByNotCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findListByNotCriteriaMap(criteriaMap);
	}

	@Override
	public List<Menu> findByMyCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findMyCriteriaMap(criteriaMap);
	}

	@Override
	public List<Menu> findOneLevelMenu() {
		return dao.findOneLevelMenuMenu();
	}

	
}