package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.jdbc.util.ConnectionManager;

public abstract class AbstractDao<T> implements BaseDao<T> {
	
	@Override
	public void insert(T t) {
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(insertSql(), Statement.RETURN_GENERATED_KEYS)){
			
			setParameter(stmt, insertParam(t));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(T t) {
		
	}

	@Override
	public List<T> findAll() {
		return null;
	}

	@Override
	public void delete(T t) {
		
	}

	private void setParameter(PreparedStatement stmt, Object[] params) throws SQLException {
		for (int i = 0; i < params.length; i++) {
			stmt.setObject(i + 1, params[i]);
		}
	}
	
	protected abstract String insertSql();
	protected abstract String updateSql();
	protected abstract String selectSql();
	protected abstract String deleteSql();
	protected abstract Object[] insertParam(T t);
	protected abstract Object[] updateParam(T t);
	protected abstract Object[] deleteParam(T t);
	protected abstract T getObject(ResultSet rs) throws SQLException;


}
