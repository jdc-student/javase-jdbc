package com.jdc;

import java.sql.SQLException;

import com.jdc.util.ConnectionManager;

public class ResultDemo {

	public static void main(String[] args) throws SQLException {
		System.out.println(ConnectionManager.getConnection());

	}

}
