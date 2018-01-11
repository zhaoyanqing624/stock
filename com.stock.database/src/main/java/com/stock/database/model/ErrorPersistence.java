package com.stock.database.model;

import com.stock.database.ann.Table;
import com.stock.database.ann.TableField;
import com.stock.database.ann.TableKey;
import com.stock.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Error")
public class ErrorPersistence {
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="ERRORID")
	private String ERRORID;
	@TableField(columnName="ERRORSYMBOL")
	private String ERRORSYMBOL;
	@TableField(columnName="ERRORDESC")
	private String ERRORDESC;
	public String getERRORID() {
		return ERRORID;
	}
	public void setERRORID(String eRRORID) {
		ERRORID = eRRORID;
	}
	public String getERRORSYMBOL() {
		return ERRORSYMBOL;
	}
	public void setERRORSYMBOL(String eRRORSYMBOL) {
		ERRORSYMBOL = eRRORSYMBOL;
	}
	public String getERRORDESC() {
		return ERRORDESC;
	}
	public void setERRORDESC(String eRRORDESC) {
		ERRORDESC = eRRORDESC;
	}
}
