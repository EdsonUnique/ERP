package edson.web.erp.menu.service;

import edson.web.erp.menu.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;

public interface MenuServiceInter extends BaseServiceInter<Menu>{

	/**
	 * 条件否定查询
	 * @param criteriaMap 条件map  key不等于value
	 * @return 所有菜单
	 */
	List<Menu> findListByNotCriteriaMap(Map<String, Object> criteriaMap);
	/**
	 * 查询根菜单和一级菜单
	 * @param criteriaMap
	 * @return
	 */
	List<Menu> findByMyCriteriaMap(Map<String, Object> criteriaMap);
	/**
	 * 查询一级菜单
	 * @return
	 */
	List<Menu> findOneLevelMenu();
	

}