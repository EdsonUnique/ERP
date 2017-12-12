package edson.web.erp.resource.action;

import java.util.*;

import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.resource.domain.Resource;
import edson.web.erp.resource.service.ResourceServiceInter;
import edson.web.erp.utils.base.*;

public class ResourceAction extends BaseAction implements ModelDriven<Resource>{

	private Resource resource=new Resource();
	private ResourceServiceInter service;
	private List<Resource>resources;
	
	
	
	public String list() throws Exception {
		resources=service.findListByCriteriaMap(null);
		return super.list();
	}
	
	public String query() throws Exception {
		resources=service.findListByCriteriaMap(popToMap(resource));
		return super.query();
	}
	
	public String addOrUpdateUI() throws Exception {
		if(resource.getResource_id()!=null){
			resource=service.findById(resource.getResource_id());
		}
		return super.addOrUpdateUI();
	}
	
	public String add() throws Exception {
		service.save(resource);
		return list();
	}
	
	public String update() throws Exception {
		service.update(resource);
		return list();
	}
	
	public String delete() throws Exception {
		service.delete(resource);
		return list();
	}
	
	public Map popToMap(Object bean) throws Exception {
		
		return super.popToMap(bean);
	}
	public final void setService(ResourceServiceInter service) {
		this.service = service;
	}
	
	public Resource getModel() {
		
		return resource;
	}

	public final List<Resource> getResources() {
		return resources;
	}
	
	
	
}