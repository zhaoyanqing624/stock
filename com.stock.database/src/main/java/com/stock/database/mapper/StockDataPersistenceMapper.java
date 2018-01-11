package com.stock.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.stock.database.logic.IBaseDao;
import com.stock.database.model.StockDataPersistence;

public interface StockDataPersistenceMapper extends IBaseDao<StockDataPersistence, String>{
	@Select("SELECT * FROM TBL_StockData WHERE STOCKINFOID=#{0}")
	List<StockDataPersistence> getStockDataByStockId(String stockInfoId);
	@Select("SELECT * FROM TBL_StockData WHERE STOCKINFOID=#{0} AND DATE=#{1}")
	List<StockDataPersistence> getStockDataByStockInfoId_Date(String stockInfoId, String date);

}
