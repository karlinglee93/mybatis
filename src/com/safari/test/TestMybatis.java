package com.safari.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.safari.pojo.Category;

public class TestMybatis {

	public static void main(String[] args) throws IOException {
		// 根据配置文件得到SqlSessionFactory
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 根据SqlSessionFactory 得到session
		SqlSession session = sqlSessionFactory.openSession();

		// 通过session.insert调用addCategory对应的SQL语句
		Category c = new Category();
		c.setName("新增加的Category");
		session.insert("addCategory", c);

		listAll(session);

		session.commit();
		session.close();
	}

	private static void listAll(SqlSession session) {
		List<Category> cs = session.selectList("listCategory");
		for (Category c : cs) {
			System.out.println(c.getName());
		}
	}
}
