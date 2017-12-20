package com.stock.database.model;

import com.stock.database.ann.Table;
import com.stock.database.ann.TableField;
import com.stock.database.ann.TableKey;
import com.stock.database.ann.TableKey.Strategy;

@Table(tablename="TBL_StockMarket")
public class StockMarketPersistence {
	
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="STOCKMARKETID")
	private String STOCKMARKETID;
	@TableField(columnName="STOCKMARKETNAME_EN")
	private String STOCKMARKETNAME_EN;
	@TableField(columnName="STOCKMARKETNAME_CN")
	private String STOCKMARKETNAME_CN;
	@TableField(columnName="AREA")
	private String AREA;
	
	public String getSTOCKMARKETID() {
		return STOCKMARKETID;
	}
	public void setSTOCKMARKETID(String sTOCKMARKETID) {
		STOCKMARKETID = sTOCKMARKETID;
	}
	public String getSTOCKMARKETNAME_EN() {
		return STOCKMARKETNAME_EN;
	}
	public void setSTOCKMARKETNAME_EN(String sTOCKMARKETNAME_EN) {
		STOCKMARKETNAME_EN = sTOCKMARKETNAME_EN;
	}
	public String getSTOCKMARKETNAME_CN() {
		return STOCKMARKETNAME_CN;
	}
	public void setSTOCKMARKETNAME_CN(String sTOCKMARKETNAME_CN) {
		STOCKMARKETNAME_CN = sTOCKMARKETNAME_CN;
	}
	public String getAREA() {
		return AREA;
	}
	public void setAREA(String aREA) {
		AREA = aREA;
	}
	
}
