package com.stock.database.helper;

import org.apache.ibatis.session.SqlSession;

import com.stock.database.logic.SqlSessionManager;
import com.stock.database.mapper.StockDataPersistenceMapper;
import com.stock.database.model.StockDataPersistence;

public class StockDataHelper {
	/**
	 * @desc 将开市股票信息存入数据库
	 * @param stockDataPersistence:股票开市信息
	 */
	public static void saveStockData(StockDataPersistence stockDataPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockDataPersistenceMapper mapper = session.getMapper(StockDataPersistenceMapper.class);
		mapper.save(stockDataPersistence);
		session.close();
	}
}
