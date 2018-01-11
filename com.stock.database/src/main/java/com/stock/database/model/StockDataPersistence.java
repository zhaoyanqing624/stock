package com.stock.database.model;

import com.stock.database.ann.Table;
import com.stock.database.ann.TableField;
import com.stock.database.ann.TableKey;
import com.stock.database.ann.TableKey.Strategy;

@Table(tablename="TBL_StockData")
public class StockDataPersistence {
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="STOCKDATAID")
	private String STOCKDATAID;
	@TableField(columnName="STOCKINFOID")
	private String STOCKINFOID;
	@TableField(columnName="DATE")
	private String DATE;
	@TableField(columnName="OPEN")
	private String OPEN;
	@TableField(columnName="HIGH")
	private String HIGH;
	@TableField(columnName="LOW")
	private String LOW;
	@TableField(columnName="CLOSE")
	private String CLOSE;
	@TableField(columnName="ADJCLOSE")
	private String ADJCLOSE;
	@TableField(columnName="VOLUME")
	private String VOLUME;
	public String getSTOCKDATAID() {
		return STOCKDATAID;
	}
	public void setSTOCKDATAID(String sTOCKDATAID) {
		STOCKDATAID = sTOCKDATAID;
	}
	public String getSTOCKINFOID() {
		return STOCKINFOID;
	}
	public void setSTOCKINFOID(String sTOCKINFOID) {
		STOCKINFOID = sTOCKINFOID;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public String getOPEN() {
		return OPEN;
	}
	public void setOPEN(String oPEN) {
		OPEN = oPEN;
	}
	public String getHIGH() {
		return HIGH;
	}
	public void setHIGH(String hIGH) {
		HIGH = hIGH;
	}
	public String getLOW() {
		return LOW;
	}
	public void setLOW(String lOW) {
		LOW = lOW;
	}
	public String getCLOSE() {
		return CLOSE;
	}
	public void setCLOSE(String cLOSE) {
		CLOSE = cLOSE;
	}
	public String getADJCLOSE() {
		return ADJCLOSE;
	}
	public void setADJCLOSE(String aDJCLOSE) {
		ADJCLOSE = aDJCLOSE;
	}
	public String getVOLUME() {
		return VOLUME;
	}
	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}
}
