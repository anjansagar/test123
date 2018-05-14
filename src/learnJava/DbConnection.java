package learnJava;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dnl.utils.text.table.TextTable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DbConnection {

	static Connection conn;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

	}

	public static boolean dbConnect() throws ClassNotFoundException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://localhost:1433;"
					+ "databaseName=test;user=sa;password=Qentelli@123";
			conn = DriverManager.getConnection(connectionUrl);

			if (!conn.isClosed() || conn != null) {
				System.out.println("Connected to Database");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public static void getUser() throws SQLException, ClassNotFoundException {

		if (dbConnect()) {
			System.out.println("Connected to db, now perform what u want");
			Statement st = conn.createStatement();
			String sqlStr = "select * from QEmployee";
			ResultSet rs = st.executeQuery(sqlStr);
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(6));
			}
		} else {
			System.out.println("connection problem");
		}

	}

	public static void oe() throws ClassNotFoundException, SQLException {

		if (dbConnect()) {
			System.out.println("Connected to db, now perform what u want");
			
			Statement st = conn.createStatement();
			String sqlStr = "select * from ziosk";
			ResultSet rs = st.executeQuery(sqlStr);			
			
			//Get no.of columns of a table
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			System.out.println("Total columns : " +columnsNumber);			
			
			while (rs.next()) {
				//System.out.println(rs.getString(1));
				for(int i = 1 ; i <= columnsNumber; i++){
				      System.out.print(rs.getString(i) + " "); //Print one element of a row
				}
				System.out.println();//Move to the next line to print the next row.
			}
			
			
			//Get column names and store in an array
			List<String> list = new ArrayList<String>();
			for(int i = 1 ; i <= columnsNumber; i++){
				 //System.out.println(rsmd.getColumnName(i));
				 list.add(rsmd.getColumnName(i));
			}
			//System.out.println(list);
			String[] columnsNames = new String[] {};			
			String[] tempArray = new String[list.size()];
			columnsNames = list.toArray(tempArray);
			

			Object[][] data = { 
					{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)}, 
					{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)}, 
					{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)}, 
					{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)}, 
					{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)} 
			};
			
			TextTable tt = new TextTable(columnsNames, data);                                                      
			tt.printTable();  
		} else {
			System.out.println("connection problem");
		}
	}

}
