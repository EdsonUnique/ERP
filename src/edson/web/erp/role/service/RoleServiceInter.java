package edson.web.erp.role.service;

import edson.web.erp.resource.domain.Resource;
import edson.web.erp.role.domain.*;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import edson.web.erp.utils.base.*;
@Transactional
public interface RoleServiceInter extends BaseServiceInter<Role>{

	public List<Resource> findResourcesList();

}