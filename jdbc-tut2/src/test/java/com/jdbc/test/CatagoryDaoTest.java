package com.jdbc.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jdbc.dao.BaseDao;
import com.jdbc.dao.CategoryDao;
import com.jdbc.dto.Category;

public class CatagoryDaoTest {
	
	private static BaseDao<Category> dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new CategoryDao();
		Arrays.asList(new Category("Food"), new Category("Drink"), new Category("Snack"))
		.forEach(dao::insert);
	}

	@Test
	public void test1() {
		assertEquals(3, dao.findAll().size());
	}

}
