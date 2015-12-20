package samplelog4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;


public class test 
{
	public static void main(String[] args)throws ClassNotFoundException,SQLException
	{
		System.out.println("this is main");
		 Scanner scn=new Scanner(System.in);
		 
		Logger logger=Logger.getLogger("test.class");
		SimpleLayout layout=new SimpleLayout();
		ConsoleAppender appender=new ConsoleAppender(layout);
		logger.addAppender(appender);
		logger.setLevel((Level)Level.DEBUG);
		try{
		Class cls=Class.forName("oracle.jdbc.OracleDriver");
		logger.debug("class is loading");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rmStore","rmStore");
		logger.debug("connection is eastblish");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from stockmaster");
		
		if(rs==null)
		{
			logger.warn("rs is null");
		}
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+""+rs.getInt(2));
			/*System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+""+rs.getInt(4));*/
		}
		 logger.debug("data is geting");
		 
		 st.close();
		 con.close();
		 }

          
		
	
	catch (SQLException se) 
	{
     se.printStackTrace();
 

	}
	catch (Exception e) {
		e.printStackTrace();
	}
	}
}
