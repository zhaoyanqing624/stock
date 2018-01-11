package com.stock.database.helper;

import java.util.List;

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
	/**
	 * @desc 根据股票的ID获取股票涨幅信息
	 * @param stockInfoId
	 */
	public static List<StockDataPersistence> getStockDataByStockInfoId(String stockInfoId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockDataPersistenceMapper mapper = session.getMapper(StockDataPersistenceMapper.class);
		List<StockDataPersistence> list = mapper.getStockDataByStockId(stockInfoId);
		session.close();
		return list;
	}
	/**
	 * @desc 根据股票的ID和日期获取股票涨幅信息
	 * @param stockInfoId date
	 */
	public static List<StockDataPersistence> getStockDataByStockInfoId_Date(String stockInfoId,String date){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockDataPersistenceMapper mapper = session.getMapper(StockDataPersistenceMapper.class);
		List<StockDataPersistence> list = mapper.getStockDataByStockInfoId_Date(stockInfoId,date);
		session.close();
		return list;
	}
	/**
	 * @desc 更新股票信息
	 * @param stockDataPersistence:股票开市信息
	 */
	public static void updateStockData(StockDataPersistence dataPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockDataPersistenceMapper mapper = session.getMapper(StockDataPersistenceMapper.class);
		mapper.update(dataPersistence);
		session.close();
	}
}
