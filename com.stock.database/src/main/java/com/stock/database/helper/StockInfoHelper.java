package com.stock.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.stock.database.logic.SqlSessionManager;
import com.stock.database.mapper.StockInfoPersistenceMapper;
import com.stock.database.model.StockInfoPersistence;

public class StockInfoHelper {
	/**
	 * @desc 将股票信息存入数据库
	 * @param stockInfoPersistence:股票基本信息
	 */
	public static void saveStockInfo(StockInfoPersistence stockInfoPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockInfoPersistenceMapper mapper = session.getMapper(StockInfoPersistenceMapper.class);
		mapper.save(stockInfoPersistence);
		session.close();
	}
	/**
	 * @desc 将更新的股票信息存入数据库
	 * @param stockInfoPersistence
	 */
	public static void updataStockInfo(StockInfoPersistence stockInfoPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockInfoPersistenceMapper mapper = session.getMapper(StockInfoPersistenceMapper.class);
		mapper.update(stockInfoPersistence);
		session.close();
	}
	/**
	 * @desc 根据股票代号,查询股票的基本信息
	 * @param symbol:股票的代号
	 */
	public static List<StockInfoPersistence> getStockInfoBySymbol(String symbol){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockInfoPersistenceMapper mapper = session.getMapper(StockInfoPersistenceMapper.class);
		List<StockInfoPersistence> list = mapper.getStockInfoBySymbol(symbol);
		session.close();
		return list;
	}
	/**
	 * @desc 根据股票的市场,查询股票的基本信息
	 * @param belongMarketId:所属交易市场ID
	 */
	public static List<StockInfoPersistence> getStockInfoByBelongId(String belongMarketId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockInfoPersistenceMapper mapper = session.getMapper(StockInfoPersistenceMapper.class);
		List<StockInfoPersistence> list = mapper.getStockInfoByBelongId(belongMarketId);
		session.close();
		return list;
	}
	
}
