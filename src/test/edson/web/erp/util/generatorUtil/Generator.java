package test.edson.web.erp.util.generatorUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import edson.web.erp.storeOper.domain.StoreOper;

/**
 * 代码生成器
 * 基本原理：文件IO流和反射
 * 命名规则：
 * 		模块名：
 * 			domain:javabean不能自动生成    映射文件 javabean名称.hbm.xml
 * 			action:模块名Action  例如emp  empAction
 * 			service:模块名Service或模块名ServiceInter  例如emp  empService  empServiceInter
 * 			dao:模块名Dao或模块名DaoInter  例如emp  empDao empDaoInter
 * 		spring文件：applicationContext_emp.xml
 * @author Edson
 *
 */
public class Generator {
	
	private Class clazz;//javabean类
	private String packageName;
	private String modelName;
	private String big_modelName;//模块名首字母大写
	private String programeDirs;
	private String realProgrameDirs;
	private String modelDirs;
	private String beanName;//javabean名称
	
	private String basePath="edson.web.erp.utils.base.*";//将base包打成jar包，固定路径下存放
	
	private File actionFile;
	private File serviceFile;
	private File daoFile;
	/**
	 * 通过javabean实体类创建dao,service,action已经相关配置文件
	 * @param clazz javabean类
	 * @throws Exception
	 */
	public Generator(Class clazz) throws Exception{
		this.clazz=clazz;
		//数据(包名、模块名、包路径等)初始化
		init();
		//创建代码存放的目录
		generateDirs();
		//创建dao,daoInter
		generatorDao();
		
		//创建service serviceInter
		generateService();
		
		//创建action
		generateAction();
		
		//创建hbm.xml文件
		generateHbmXml();
		
		//创建applicationContext.xml
		generateApplicationContext();
		
		//打印提示信息
		System.out.println("创建成功！\n提示信息：\nstruts.xml文件没有创建");
		System.out.println("项目目录下必须创建与src同级且名为resources的文件夹用于存放配置文件");
		System.out.println("applicationContext.xml主文件没有配置数据库相关信息，事务的开启和映射文件的配置");
		System.out.println("映射文件中的表结构关系未配置");
		System.out.println("若创建的类出现错误可能是因为未实现其接口的对应方法");
	}




	//数据初始化
	private void init() {
		//获取包名
		 packageName=clazz.getPackage().getName();//edson.web.erp.emp.domain
		//获取模块目录
		modelDirs=packageName.substring(0,packageName.length()-7);//edson.web.erp.emp
		//获取模块名
		modelName=modelDirs.substring(modelDirs.lastIndexOf(".")+1);//emp
		//获取项目名路径
		programeDirs=modelDirs.substring(0,modelDirs.lastIndexOf("."));//edson.web.erp
		//项目带‘/’路径
		realProgrameDirs=programeDirs.replace('.', '/');//edson/web/erp
		//获取javabean名称
		beanName=clazz.getSimpleName();
	}

	private void generateDirs() {
		
		actionFile=new File("src/"+realProgrameDirs+"/"+modelName+"/action");
		if(!actionFile.exists()){
			actionFile.mkdirs();
		}
		
		serviceFile=new File("src/"+realProgrameDirs+"/"+modelName+"/service");
		if(!serviceFile.exists()){
			serviceFile.mkdirs();
		}
		
		daoFile=new File("src/"+realProgrameDirs+"/"+modelName+"/dao");
		if(!daoFile.exists()){
			daoFile.mkdirs();
		}
	}
	
	//创建dao  daoInter
	private void generatorDao() throws Exception {
		//创建daoInter
		big_modelName=toFirstUpString(modelName);//将模块名首字母大写
		File file=new File(daoFile,big_modelName+"DaoInter.java");
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		
		bw.write(generateInter("dao").toString());
		bw.flush();
		bw.close();
		
		//创建dao
		file=new File(daoFile,big_modelName+"Dao.java");
		BufferedWriter bw2=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		
		bw2.write(generateImpl("dao").toString());
		bw2.flush();
		bw2.close();
		
		
	}
	
	//创建service serviceInter
	private void generateService() throws Exception {
		File file=new File(serviceFile,big_modelName+"ServiceInter.java");
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		
		bw.write(generateInter("service").toString());
		bw.flush();
		bw.close();
		//创建service实现类
		file=new File(serviceFile,big_modelName+"Service.java");
		BufferedWriter bw2=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		
		bw2.write(generateImpl("service").toString());
		bw2.flush();
		bw2.close();
		
	}
	
	private void generateAction() throws Exception {
		File file=new File(actionFile,big_modelName+"Action.java");
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		//创建action类
		StringBuffer sb=new StringBuffer("package "+modelDirs+".action;");
		sb.append("\n\nimport java.util.*;\n")
			.append("import "+basePath+";\n");//导入base类
		
		sb.append("\npublic class "+big_modelName+"Action extends BaseAction{");
		sb.append("\n\n}");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void generateHbmXml() throws Exception {
		String domainPath="src/"+clazz.getPackage().getName().replace(".", "/");
		File file=new File(domainPath,toFirstUpString(clazz.getSimpleName())+".hbm.xml");
		
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		//创建hbm.xml文件
		StringBuffer sb=new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("\n<!DOCTYPE hibernate-mapping PUBLIC\n")
			.append("    \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\n");
		sb.append("    \"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">\n");
		sb.append("<hibernate-mapping>\n").append("	<class name=\""+clazz.getName()+"\" table=\"tbl_"+clazz.getSimpleName().toLowerCase()+"\" >\n");
		sb.append("		<id name=\""+modelName+"_id"+"\" column=\""+modelName+"_id"+"\">\n");
		sb.append("			<generator class=\"native\"></generator>\n");
		sb.append("		</id>\n\n");
		
		//属性
		Field[] fields=clazz.getDeclaredFields();
		
		for(Field f:fields){
			String fName=f.getName();
			if(!fName.equals(modelName+"_id") && f.getModifiers()==Modifier.PRIVATE){//去除主键
				sb.append("\t\t<property name=\""+fName+"\" ");
				if(f.getType().equals(String.class)){
					sb.append("length=\"120\"");
				}
				sb.append("/>\n");
			}
			
			
		}
		
		sb.append("\n	</class>\n\n").append("</hibernate-mapping>");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private void generateApplicationContext() throws Exception {
		File file=new File("resources","applicationContext_"+modelName+".xml");
		if(file.exists()){
			return;
		}
		
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		//创建applicationContext_模块名.xml文件
		StringBuffer sb=new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\t"+
				"<beans xmlns=\"http://www.springframework.org/schema/beans\"\n\t" +
			"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n\t" +
			"xmlns:context=\"http://www.springframework.org/schema/context\"\n\t" +
			"xmlns:aop=\"http://www.springframework.org/schema/aop\"\n\t" +
			"xmlns:tx=\"http://www.springframework.org/schema/tx\"\n\t" +
			"xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n\t" + 
			"http://www.springframework.org/schema/beans/spring-beans.xsd\n\t" +
			"http://www.springframework.org/schema/context\n\t" +
			"http://www.springframework.org/schema/context/spring-context.xsd\n\t" +
			"http://www.springframework.org/schema/aop\n\t" +
			"http://www.springframework.org/schema/aop/spring-aop.xsd\n\t" +
			"http://www.springframework.org/schema/tx\n\t"+
			"http://www.springframework.org/schema/tx/spring-tx.xsd\">\n\n");
			sb.append("\t<bean id=\""+modelName+"Action\" class=\""+modelDirs+".action."+big_modelName+"Action"+"\" scope=\"prototype\" >\n")
				.append("\t\t<property name=\"service\" ref=\""+modelName+"Service\"/>\n\t</bean>\n");
		
			sb.append("\t<bean id=\""+modelName+"Service\" class=\""+modelDirs+".service."+big_modelName+"Service"+"\" >\n")
			.append("\t\t<property name=\"dao\" ref=\""+modelName+"Dao\"/>\n\t</bean>\n");
			
			sb.append("\t<bean id=\""+modelName+"Dao\" class=\""+modelDirs+".dao."+big_modelName+"Dao"+"\" >\n")
			.append("\t\t<property name=\"sessionFactory\" ref=\"sessionFactory\"/>\n\t</bean>\n\n</beans>");
			
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	//首字母大写
	public String toFirstUpString(String str){
		return str.substring(0,1).toUpperCase()+str.substring(1);
	}
	
	/**
	 * 产生类文件内容（接口）
	 * @param str 类名:service或dao  
	 * @return 类文件
	 */
	private StringBuffer generateInter(String str){
		//包名
		StringBuffer sb=new StringBuffer("package "+modelDirs+"."+str+";");
		String big_str=str.substring(0,1).toUpperCase()+str.substring(1);
		//导包
		sb.append("\n\nimport "+packageName+".*;");//项目中用到的javabean类
		sb.append("\nimport java.util.*;");
		sb.append("\nimport "+basePath+";");//base类
		sb.append("\n\npublic interface ").append(big_modelName+big_str+"Inter extends Base"+big_str+"Inter<"+beanName+">{");
		sb.append("\n\n}");
		return sb;
	}
	/**
	 * 
	 *产生类文件内容（实现类）
	 * @param str 类名:service或dao  
	 * @return 类文件
	 */
	private StringBuffer generateImpl(String str){
		
		StringBuffer sb=new StringBuffer("package "+modelDirs+"."+str+";");
		String big_str=str.substring(0,1).toUpperCase()+str.substring(1);
		
		//导包
		sb.append("\n\nimport "+packageName+".*;");//项目中用到的javabean类
		sb.append("\nimport java.util.*;");
		sb.append("\nimport "+programeDirs+".utils.base.*;");//base类
		
		sb.append("\n\npublic class ").append(big_modelName+big_str+" extends Base"+big_str+"<"+beanName+">implements "+big_modelName+big_str+"Inter {");
		sb.append("\n\n}");
		return sb;
	}
	
	public static void main(String[] args) throws Exception {
		new Generator(StoreOper.class);//T 为javabean类
	}

	
	
}
