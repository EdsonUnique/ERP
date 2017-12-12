package edson.web.erp.role.dao;

import edson.web.erp.resource.domain.Resource;
import edson.web.erp.role.domain.*;
import java.util.*;

import edson.web.erp.utils.base.*;

public class RoleDao extends BaseDao<Role>implements RoleDaoInter {

	@Override
	public List<Resource> finResourceList() {
		List<Resource>temp=this.getHibernateTemplate().find("from Resource");
		return temp.size()>0?temp:null;
	}

}