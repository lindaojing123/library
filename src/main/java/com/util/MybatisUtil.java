package com.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {

	private static SqlSessionFactory factory;
	/**
	 * 静态初始代码块，在类加载的时候执行一次。
	 * @return
	 */
	static{
		InputStream in = null;
		try {
			//1.读取配置文件 mybstis-config.xml
			in = Resources.getResourceAsStream("mybatis-config.xml");
			//2.创建工厂 SqlSessionFactory
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关流
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// 声明一个ThreadLocal对象 ：  ThreadLocal可以将一个对象绑定到当前线程
	private static ThreadLocal<SqlSession> tol = new ThreadLocal<SqlSession>();

	// 返回SqlSession对象
	public static SqlSession openSession(){
		// 先到当前线程获取以下SqlSession,如果没有则创建新的
		SqlSession sqlSession = tol.get();
		if(sqlSession==null){
			//3.创建SqlSession
			sqlSession = factory.openSession();
			//存入当前线程
			tol.set(sqlSession);
		}
		return sqlSession;
	}

	// 关闭sqlSession
	public static void close(){
		// 获取当前的SqlSession
		SqlSession sqlSession = openSession();
		// 关闭
		if(sqlSession!=null){
			sqlSession.close();
			// 从当前线程移除
			tol.remove();
		}
	}

	// 获取DAO实现类的对象
	public static <T> T getMapper(Class<T> iclass){
		// 获取SqlSession
		SqlSession sqlSession = openSession();
		// 获取dao实现类的对象
		T t = sqlSession.getMapper(iclass);
		return t;
	}

	// 提交事务
	public static void commit(){
		SqlSession sqlSession = openSession();
		if(sqlSession!=null){
			sqlSession.commit();
		}
	}
	// 回滚事务
	public static void rollback(){
		SqlSession sqlSession = openSession();
		if(sqlSession!=null){
			sqlSession.rollback();
		}
	}

}
