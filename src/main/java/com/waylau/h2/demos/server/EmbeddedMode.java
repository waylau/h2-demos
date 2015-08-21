package com.waylau.h2.demos.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 说明：
 *
 * @author <a href="http://www.waylau.com">waylau.com</a> 2015年8月21日
 */
public class EmbeddedMode {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * 
	 */
	public EmbeddedMode() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Class.forName("org.h2.Driver");

		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa",
				"");

		String sql = "SELECT * FROM test";

		try {
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			rsmd = rs.getMetaData();   
		     
			int count = rsmd.getColumnCount();
			logger.info( count );
			
			// 输出数据   
		     while (rs.next()){   
		         for (int i=1; i<=count; i++){   
		 			logger.info( rs.getString(i) );
		         }   
		     } 
 
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

}
