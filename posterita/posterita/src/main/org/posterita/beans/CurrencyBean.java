package org.posterita.beans;

import java.math.BigDecimal;

public class CurrencyBean extends UDIBean
{
	private Integer currencyId;
	private BigDecimal roundOffFactor;
	private Integer stdPrecision;
	private String isoCode;
	private String curSymbol;
	private String description;
	private boolean isActive;
	
	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getIsoCode()
	{
		return isoCode;
	}
	public void setIsoCode(String isoCode)
	{
		this.isoCode = isoCode;
	}	
	public Integer getStdPrecision() 
	{
		return stdPrecision;
	}
	public void setStdPrecision(Integer stdPrecision) 
	{
		this.stdPrecision = stdPrecision;
	}
	public Integer getCurrencyId()
	{
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) 
	{
		this.currencyId = currencyId;
	}
	
	public BigDecimal getRoundOffFactor()
	{
		return roundOffFactor;
	}
	
	public void setRoundOffFactor(BigDecimal roundOffFactor)
	{
		this.roundOffFactor = roundOffFactor;
	}
	public String getCurSymbol() 
	{
		return curSymbol;
	}
	public void setCurSymbol(String curSymbol)
	{
		this.curSymbol = curSymbol;
	}
}
