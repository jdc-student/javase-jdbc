package com.jdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdc.util.ConnectionManager;

public class ResultSetUpdateDemo {

	public static void main(String[] args) throws SQLException {
		try(Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)){
			
			ResultSet rs = stmt.executeQuery("select * from student");
			
			while(rs.next()) {
				if(2 == rs.getInt("s_id")) {
					rs.updateString(2, "Ms.Aye Aye");
					rs.updateRow();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
