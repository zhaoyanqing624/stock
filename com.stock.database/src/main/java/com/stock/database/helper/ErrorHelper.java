package com.stock.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.stock.database.logic.SqlSessionManager;
import com.stock.database.mapper.ErrorPersistenceMapper;
import com.stock.database.mapper.StockInfoPersistenceMapper;
import com.stock.database.model.ErrorPersistence;
import com.stock.database.model.StockInfoPersistence;

public class ErrorHelper {
	/**
	 * @desc 记录出错的数据名称
	 * @param errorPersistence
	 */
	public static void saveError(ErrorPersistence errorPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ErrorPersistenceMapper mapper = session.getMapper(ErrorPersistenceMapper.class);
		mapper.save(errorPersistence);
		session.close();
	}
	
	public static List<ErrorPersistence> getErrorBySymbol(String symbol){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ErrorPersistenceMapper mapper = session.getMapper(ErrorPersistenceMapper.class);
		List<ErrorPersistence> list = mapper.getErrorBySymbol(symbol);
		session.close();
		return list;
	}
}
