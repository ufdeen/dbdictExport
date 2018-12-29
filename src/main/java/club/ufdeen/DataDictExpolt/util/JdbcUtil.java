package club.ufdeen.DataDictExpolt.util;


import java.beans.PropertyVetoException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	private static String driverClass;
	private static String jdbcUrl;
	private static String user;
	private static String password;
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	 static {
		 driverClass = PropertiesUtil.getValue("driverClass");
		 jdbcUrl = PropertiesUtil.getValue("jdbcUrl");
		 user = PropertiesUtil.getValue("user");
		 password = PropertiesUtil.getValue("password");
		 
		 //初始化连接池
		 try {
			 ds.setDriverClass(driverClass);
			 ds.setJdbcUrl(jdbcUrl);
			 ds.setUser(user);
			 ds.setPassword(password);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
	 }
	 
	 
	
	 public static <T> T query(String sql,ResultSetHandler<T> handler,Object[] params) throws Exception {
		 QueryRunner qr = new QueryRunner(ds); 
		 return qr.query(sql, handler, params);
	 }
}
