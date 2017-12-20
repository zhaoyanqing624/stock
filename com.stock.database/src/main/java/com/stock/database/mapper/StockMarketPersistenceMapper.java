package com.stock.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.stock.database.logic.IBaseDao;
import com.stock.database.model.StockMarketPersistence;

public interface StockMarketPersistenceMapper extends IBaseDao<StockMarketPersistence, String>{
	
	@Select("SELECT * FROM TBL_StockMarket WHERE STOCKMARKETNAME_EN=#{0}")
	public List<StockMarketPersistence> getStockMarket(String name_en);
}
