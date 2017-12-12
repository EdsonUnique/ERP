package test.edson.web.erp.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import edson.web.erp.emp.domain.Employee;

public class TestReflect {

	public static void test(Object bean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class clazz=bean.getClass();
		Field[] fs=clazz.getDeclaredFields();
		Method[] ms=clazz.getDeclaredMethods();
		for(Field f:fs){
			//System.out.println(f.getName());
		}
		for(Method m:ms){
			//System.out.println(m.getName());
//			if(m.getName().startsWith("get")){//没有getClass属性
//				System.out.println(m.getName());
//				System.out.println(m.invoke(bean, null));
//			}
			System.out.println(m.getReturnType());
		}
		
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Employee emp=new Employee();
		emp.setEmail("qq");
		test(emp);
	}
}
