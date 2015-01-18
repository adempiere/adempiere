/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/

package org.eevolution.engine.forecast;

/**
 * Forecast Rule Interface
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 *
 */
public interface ForecastRule {

	public void setDataSet (DataSet data ,double  factorAlpha , double factorGamma, double factorBeta, double factorMultiplier, double factorScale, double factorUser);
	
	public void setKey (String M_Product_ID);
	
	public String getKey ();
	
	public DataSet getForecast();
	
	public void setFactorAlpha(double factorAlpha);
	
	public double getFactorAlpha();
	
	public void setFactorBeta(double factorBeta);
	
	public double getFactorBeta();

	public void setFactorUser(double factorUser);
	
	public double getFactorUser();
	
	public void setFactorGamma(double factorGamma);
	
	public double getFactorGamma();
	
	public void setFactorMultiplier(double factorMultiplier);
	
	public double getFactorMultiplier();

	public void setFactorScale(double factorScale);
	
	public double getFactorScale();

}
