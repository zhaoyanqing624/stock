package com.stock.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.stock.database.logic.IBaseDao;
import com.stock.database.model.ErrorPersistence;

public interface ErrorPersistenceMapper extends IBaseDao<ErrorPersistence, String>{
	@Select("SELECT * FROM TBL_Error WHERE ERRORSYMBOL=#{0}")
	List<ErrorPersistence> getErrorBySymbol(String symbol);
}
