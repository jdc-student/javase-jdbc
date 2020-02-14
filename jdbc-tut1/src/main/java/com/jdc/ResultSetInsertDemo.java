package com.jdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.jdc.util.ConnectionManager;

public class ResultSetInsertDemo {

	public static void main(String[] args) {
		try(Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)){
			
			stmt.addBatch("insert into student(s_name) values('Chue Chue')"); 
			stmt.addBatch("update student set s_name='Aye Aye' where s_id=2");
			//stmt.addBatch("delete from student where s_id = 1");
			
			int[] executeBatch = stmt.executeBatch();
			for (int i : executeBatch) {
				System.out.println("ExecuteBatch result : " + i);
			}
			
			ResultSet rs = stmt.executeQuery("select * from student");
			rs.moveToInsertRow();
			rs.updateString(2, "Phyo Thiha");
			rs.insertRow();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("insert finish..");
		}

	}

}
