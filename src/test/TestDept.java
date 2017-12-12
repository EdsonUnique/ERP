package test;

public class TestDept {

	public TestDept(){
		System.out.println("111");
	}
	
	{//先于构造器执行
		System.out.println("haha");
	}
	
	public void test(String name){
		System.out.println("hell111"+"--"+name);
	}
	
	public static void main(String[] args) {
		TestDept d=new TestDept();
		d.test("name");
	}
}
