package com.stock.database.model;

import com.stock.database.ann.Table;
import com.stock.database.ann.TableField;
import com.stock.database.ann.TableKey;
import com.stock.database.ann.TableKey.Strategy;

@Table(tablename="TBL_StockInfo")
public class StockInfoPersistence {
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="STOCKINFOID")
	private String STOCKINFOID;
	@TableField(columnName="STOCKSYMBOL")
	private String STOCKSYMBOL;
	@TableField(columnName="STOCKNAME")
	private String STOCKNAME;
	@TableField(columnName="LASTSALE")
	private String LASTSALE;
	@TableField(columnName="MARKETCAP")
	private String MARKETCAP;
	@TableField(columnName="IPOYEAR")
	private String IPOYEAR;
	@TableField(columnName="SECTOR")
	private String SECTOR;
	@TableField(columnName="INDUSTRY")
	private String INDUSTRY;
	@TableField(columnName="SUMMARYQUOTE")
	private String SUMMARYQUOTE;
	@TableField(columnName="BELONGMARKETID")
	private String BELONGMARKETID;
	
	public String getSTOCKINFOID() {
		return STOCKINFOID;
	}
	public void setSTOCKINFOID(String sTOCKINFOID) {
		STOCKINFOID = sTOCKINFOID;
	}
	public String getSTOCKSYMBOL() {
		return STOCKSYMBOL;
	}
	public void setSTOCKSYMBOL(String sTOCKSYMBOL) {
		STOCKSYMBOL = sTOCKSYMBOL;
	}
	public String getSTOCKNAME() {
		return STOCKNAME;
	}
	public void setSTOCKNAME(String sTOCKNAME) {
		STOCKNAME = sTOCKNAME;
	}
	public String getLASTSALE() {
		return LASTSALE;
	}
	public void setLASTSALE(String lASTSALE) {
		LASTSALE = lASTSALE;
	}
	public String getMARKETCAP() {
		return MARKETCAP;
	}
	public void setMARKETCAP(String mARKETCAP) {
		MARKETCAP = mARKETCAP;
	}
	public String getIPOYEAR() {
		return IPOYEAR;
	}
	public void setIPOYEAR(String iPOYEAR) {
		IPOYEAR = iPOYEAR;
	}
	public String getSECTOR() {
		return SECTOR;
	}
	public void setSECTOR(String sECTOR) {
		SECTOR = sECTOR;
	}
	public String getINDUSTRY() {
		return INDUSTRY;
	}
	public void setINDUSTRY(String iNDUSTRY) {
		INDUSTRY = iNDUSTRY;
	}
	public String getSUMMARYQUOTE() {
		return SUMMARYQUOTE;
	}
	public void setSUMMARYQUOTE(String sUMMARYQUOTE) {
		SUMMARYQUOTE = sUMMARYQUOTE;
	}
	public String getBELONGMARKETID() {
		return BELONGMARKETID;
	}
	public void setBELONGMARKETID(String bELONGMARKETID) {
		BELONGMARKETID = bELONGMARKETID;
	}
}
