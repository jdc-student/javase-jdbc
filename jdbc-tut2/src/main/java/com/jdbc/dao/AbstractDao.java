package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import com.jdbc.dto.AutoGenerateable;
import com.jdbc.util.ConnectionManager;

public abstract class AbstractDao<T> implements BaseDao<T> {

	@Override
	public void insert(T t) {
		Predicate<T> pred = obj -> obj instanceof AutoGenerateable;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = pred.test(t)
						? conn.prepareStatement(insertSql(), Statement.RETURN_GENERATED_KEYS)
						: conn.prepareStatement(insertSql())) {

			setParameter(stmt, insertParam(t));

			stmt.executeUpdate();

			if (pred.test(t)) {
				ResultSet rs = stmt.getGeneratedKeys();
				while (rs.next()) {
					((AutoGenerateable) t).setId(rs.getInt(1));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(T t) {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSql())) {

			setParameter(stmt, updateParam(t));

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> findAll() {
		List<T> list = new LinkedList<>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectSql())) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(getObject(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.unmodifiableList(list);
	}

	@Override
	public void delete(T t) {
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(deleteSql())){
			
			setParameter(stmt, deleteParam(t));
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
