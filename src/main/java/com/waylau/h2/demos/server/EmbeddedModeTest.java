package com.waylau.h2.demos.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 说明：内嵌模式下的性能测试
 *
 * @author <a href="http://www.waylau.com">waylau.com</a> 2015年8月21日
 */
public class EmbeddedModeTest {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * 
	 */
	public EmbeddedModeTest() {
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
		Statement statement = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String sql = null;
		int count = 0;
		Class.forName("org.h2.Driver");

		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa",
				"");


		try {
			statement = conn.createStatement();
			
			// 创建测试表
			statement.executeUpdate("DROP TABLE IF EXISTS TEST;");
			statement.executeUpdate("CREATE TABLE TEST(ID INT  PRIMARY KEY, NAME VARCHAR(255)"
					+ ", NAME2 VARCHAR(255) , NAME3 VARCHAR(255) , NAME4 VARCHAR(255)"
					+ ", NAME5 VARCHAR(255) , NAME6 VARCHAR(255) , NAME7 VARCHAR(255)"
					+ ", NAME8 VARCHAR(255) , NAME9 VARCHAR(255) , NAME10 VARCHAR(255));");
			sql = "INSERT INTO TEST VALUES(?,?,?,?,?,?,?,?,?,?,?);";
			preparedStatement = conn.prepareStatement(sql);
			
			// INSERT 性能测试
			long timeStart = System.currentTimeMillis();
			
			for (int i=0; i<100000; i++){
				preparedStatement.setInt(1, i+1);
				preparedStatement.setString(2, "20000"+1);
				preparedStatement.setString(3, "20000"+1);
				preparedStatement.setString(4, "20000"+1);
				preparedStatement.setString(5, "20000"+1);
				preparedStatement.setString(6, "20000"+1);
				preparedStatement.setString(7, "20000"+1);
				preparedStatement.setString(8, "20000"+1);
				preparedStatement.setString(9, "20000"+1);
				preparedStatement.setString(10, "20000"+1);
				preparedStatement.setString(11, "20000"+1);
				
				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();  
			
			long timeEnd = System.currentTimeMillis();
			long longTime = timeEnd - timeStart;
			
			logger.info( "INSERT TIME:" + longTime );

			// QUERY 性能测试
			timeStart = System.currentTimeMillis();
			
			sql = "SELECT * FROM TEST where NAME='20001' ORDER BY ID ;";
			rs = statement.executeQuery(sql);
			
			timeEnd = System.currentTimeMillis();
			longTime = timeEnd - timeStart;
			
			logger.info( "QUERY TIME:" + longTime );
			logger.info( "rs:" + rs.toString() );
			
			// UPDATE 性能测试
			timeStart = System.currentTimeMillis();
			
			sql = "UPDATE  TEST SET  NAME='waylau';";
			count = statement.executeUpdate(sql);
			
			timeEnd = System.currentTimeMillis();
			longTime = timeEnd - timeStart;
			
			logger.info( "UPDATE TIME:" + longTime );
			logger.info( "count:" + count );
			
			// DELETE 性能测试
			timeStart = System.currentTimeMillis();
			
			sql = "DELETE  TEST WHERE  NAME='waylau';";
			count = statement.executeUpdate(sql);
			
			timeEnd = System.currentTimeMillis();
			longTime = timeEnd - timeStart;
			
			logger.info( "DELETE TIME:" + longTime );
			logger.info( "count:" + count );
			
 
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

}
