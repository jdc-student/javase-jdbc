package com.jdbc.dao;

import java.util.List;

public interface BaseDao<T> {
	void insert(T t);
	void update(T t);
	List<T> findAll();
	void delete(T t);
}
