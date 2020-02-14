package com.jdbc.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jdbc.dao.BaseDao;
import com.jdbc.dao.CategoryDao;
import com.jdbc.dto.Category;
import com.jdbc.util.ConnectionManager;

public class CatagoryDaoTest {
	
	private static BaseDao<Category> dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionManager.dropTables("category");
		
		dao = new CategoryDao();
		Arrays.asList(new Category("Food"), new Category("Drink"), new Category("Snack"))
		.forEach(dao::insert);
	}

	@Test
	public void test1() {
		assertEquals(3, dao.findAll().size());
		assertEquals("Food", dao.findAll().get(1).getName());
	}

	@Test
	public void test2() {
		Category category = dao.findAll().get(1);
		category.setName("Foods");
		dao.update(category);
		category = dao.findAll().get(1);
		assertEquals("Foods", category.getName());
	}
	
	@Test
	public void test3() {
		dao.delete(dao.findAll().get(0));
		assertEquals(2, dao.findAll().size());
	}
}
