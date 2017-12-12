package edson.web.erp.utils.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edson.web.erp.resource.domain.Resource;
import edson.web.erp.resource.service.ResourceServiceInter;

/**
 * 权限监听器
 * 服务器启动时便加载需要权限的所有资源
 * @author Edson
 *
 */
public class PrivilegeListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//通过WebApplicationContext获取bean
		WebApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		ResourceServiceInter service=(ResourceServiceInter) ctx.getBean("resourceService");
		
		//获取所有权限资源
		List<Resource> res=service.findListByCriteriaMap(null);
		
		//提高比较查询效率，将所有资源拼成字符串
		StringBuilder sb=new StringBuilder();
		for(Resource r:res){
			sb.append(r.getPath());
			sb.append("#");//以#分割
		}
		
		//将字符串存到servletContext中
		event.getServletContext().setAttribute("resAll", sb);
		
	}

}























