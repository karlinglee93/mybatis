package com.safari.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		// 多条件查询
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		params.put("name", "cat");
		
		List<Category> cs = session.selectList("listCategoryByIdAndName",params);
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		
		session.commit();
		session.close();
	}

}
