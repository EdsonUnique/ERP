package edson.web.erp.role.dao;

import edson.web.erp.resource.domain.Resource;
import edson.web.erp.role.domain.*;
import java.util.*;

import edson.web.erp.utils.base.*;

public interface RoleDaoInter extends BaseDaoInter<Role>{

	public List<Resource> finResourceList();

}