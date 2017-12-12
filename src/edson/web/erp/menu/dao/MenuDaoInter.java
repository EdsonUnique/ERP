package edson.web.erp.menu.dao;

import edson.web.erp.menu.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;

public interface MenuDaoInter extends BaseDaoInter<Menu>{

	/**
	 * 否定条件  如 name不等于***
	 * @param criteriaMap 条件
	 * @return 所有菜单项
	 */
	List<Menu> findListByNotCriteriaMap(Map<String, Object> criteriaMap);

	List<Menu> findMyCriteriaMap(Map<String, Object> criteriaMap);

	List<Menu> findOneLevelMenuMenu();

}