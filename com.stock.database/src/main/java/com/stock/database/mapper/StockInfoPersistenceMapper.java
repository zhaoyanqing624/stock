package com.stock.database.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.stock.database.logic.IBaseDao;
import com.stock.database.model.StockInfoPersistence;

public interface StockInfoPersistenceMapper extends IBaseDao<StockInfoPersistence, String>{
	
	@Select("SELECT * FROM TBL_StockInfo WHERE STOCKSYMBOL=#{0}")
	List<StockInfoPersistence> getStockInfoBySymbol(String symbol);
	@Select("SELECT * FROM TBL_StockInfo WHERE BELONGMARKETID=#{0}")
	List<StockInfoPersistence> getStockInfoByBelongId(String belongMarketId);

	
}
