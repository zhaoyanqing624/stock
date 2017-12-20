package com.stock.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.stock.common.util.JsonUtil;
import com.stock.database.logic.SqlSessionManager;
import com.stock.database.mapper.StockMarketPersistenceMapper;
import com.stock.database.model.StockMarketPersistence;

public class StockMarketHelper {
	/**
	 * @desc 获取股票市场信息
	 * @param name_en:市场名称
	 */
	public static List<StockMarketPersistence> getStockMarketList(String name_en){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		StockMarketPersistenceMapper mapper = session.getMapper(StockMarketPersistenceMapper.class);
		List<StockMarketPersistence> stockMarketList = mapper.getStockMarket(name_en);
		session.close();
		return stockMarketList;
	}
}
