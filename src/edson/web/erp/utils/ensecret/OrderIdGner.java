package edson.web.erp.utils.ensecret;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class OrderIdGner {
	
	public static int serNum=0;//区分码  实际上应从数据库中读取该值
	/**
	 * 产生订单号：当前系统时间+区分码--->转十六进制
	 * @return
	 */
	public static String generateOrderId(){
		int len=7;
		byte[] zeros=new byte[]{48,48,48,48,48,48};//48为0的ASCII码
		
		//日期字符串产生：171202
		Date dt=new Date();
		SimpleDateFormat d=new SimpleDateFormat("yyMMdd");
		String date=d.format(dt);
		
		serNum++;
		int the_len=String.valueOf(serNum).length();
		String the_num=new String(zeros,0,len-the_len);
		
		String finalStr=date+the_num+String.valueOf(serNum);
		
		long temp=Long.valueOf(finalStr);
		
		//转十六进制
		return Long.toHexString(temp);
		
		
	}
	
		

}
