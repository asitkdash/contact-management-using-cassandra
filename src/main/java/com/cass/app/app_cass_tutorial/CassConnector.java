package com.cass.app.app_cass_tutorial;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassConnector {
	
	//object created
	private static Cluster cluster;
	private static Session session;
	
	public static Cluster connect(String node) {
		//returns cluster instance using contactpoint 
		//which uses the address of the node that cassandra uses to connect
		return Cluster.builder().addContactPoint(node).build();
	}
	
	public static void main(String[] args) {
		//calls connect method to connect to local ip
		cluster = connect("localhost");
		//connects to the cluster 
		session = cluster.connect();
		//creating a keyspace
		//session.execute("CREATE KEYSPACE myks WITH REPLICATION = "
		//		+ "{ 'class' : 'SimpleStrategy', 'replication_factor' : 1};");
		
		session.execute("USE myks");
		
		/*
		System.out.println("********creating a table");
		
		String cqlcreatestmt = "CREATE TABLE employee("
				+ "emp_id int PRIMARY KEY,"
				+ "emp_name text,"
				+ "emp_city text,"
				+ "emp_sal varint,"
				+ "emp_phone varint)";
		session.execute(cqlcreatestmt);
	
		System.out.println("**********Inserting a row to table");
		String cqlinsertstmt = "INSERT INTO employee(emp_id,emp_name,emp_city,emp_sal,emp_phone) VALUES"
				+ "(2,'asit','california',20000,6572588829)";

		session.execute(cqlinsertstmt);
		
		String cqlinsertstmt = "INSERT INTO employee(emp_id,emp_name,emp_city,emp_sal,emp_phone) VALUES"
				+ "(1,'yashdeep','california',10000,6572588913)";
		
		session.execute(cqlinsertstmt);
		*/
		System.out.println("********Fetching Data from table");

		
		String cqlselectstmt = "SELECT * FROM employee";
		ResultSet resultSet = session.execute(cqlselectstmt);
		
		for(Row row : resultSet) {
			
			System.out.format("%d %s %s %s %s \n", row.getInt("emp_id"), row.getString("emp_name"), row.getString("emp_city"), row.getVarint("emp_sal"), row.getVarint("emp_phone"));
		}
		System.out.println("********updating Data from table");
		
		session.execute("UPDATE employee SET emp_city = 'California' where emp_id = 2");
		
		ResultSet results = session.execute("SELECT * FROM employee where emp_id = 2");
		
		for(Row row : results) {
			
			System.out.format("%d %s %s %s %s \n", row.getInt("emp_id"), row.getString("emp_name"), row.getString("emp_city"), row.getVarint("emp_sal"), row.getVarint("emp_phone"));
		}
		
		
		session.close();
		cluster.close();
	}
}
