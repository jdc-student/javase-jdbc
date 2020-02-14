package com.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.dto.Category;

public class CategoryDao extends AbstractDao<Category> {

	@Override
	protected String insertSql() {
		return "insert into category(name) value(?)";
	}

	@Override
	protected String updateSql() {
		return "update category set name = ? where id = ?";
	}

	@Override
	protected String selectSql() {
		return "select * from category";
	}

	@Override
	protected String deleteSql() {
		return "delete from categroy where id = ?";
	}

	@Override
	protected Object[] insertParam(Category t) {
		return new Object[] {t.getName()};
	}

	@Override
	protected Object[] updateParam(Category t) {
		return new Object[] {t.getName(), t.getId()};
	}

	@Override
	protected Object[] deleteParam(Category t) {
		return new Object[] {t.getId()};
	}

	@Override
	protected Category getObject(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt(1));
		category.setName(rs.getString(2));
		return category;
	}

}
