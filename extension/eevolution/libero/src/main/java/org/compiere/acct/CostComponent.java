/**
 * 
 */
package org.compiere.acct;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.compiere.util.Env;

/**
 * @author Teo Sarca, www.arhipac.ro
 *
 */
public class CostComponent
{
	public BigDecimal qty = Env.ZERO;
	public BigDecimal priceActual = Env.ZERO;
//	private int M_CostElement_ID = -1;
	
	private int scale = 4;
	private BigDecimal percent = Env.ZERO;

	public CostComponent(BigDecimal qty, BigDecimal priceActual)
	{
		this.qty = qty;
		this.priceActual = priceActual;
	}
	
	public BigDecimal getAmount()
	{
		BigDecimal amt = qty.multiply(priceActual);
		if (this.percent.signum() != 0)
		{
			amt = amt.multiply(this.percent);
			amt = amt.divide(Env.ONEHUNDRED, this.scale, RoundingMode.HALF_UP);
		}
		if (amt.scale() > this.scale)
		{
			amt = amt.setScale(this.scale, RoundingMode.HALF_UP);
		}
		return amt;
	}

	public int getScale()
	{
		return scale;
	}

	public void setScale(int scale)
	{
		this.scale = scale;
	}

	public BigDecimal getPercent()
	{
		return percent;
	}

	public void setPercent(BigDecimal percent)
	{
		this.percent = percent;
	}
	
	public String toString()
	{
		return "qty=" + qty + ", price=" + priceActual + ", percentage="+percent;
	}

}
