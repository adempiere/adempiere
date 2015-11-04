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

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Iterator;

import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.SimpleExponentialSmoothingModel;

/**
 * Simple Exponential Smoothing Implementation
 * 
 * @author victor.perez@e-evolution.com, www.e-Evolution.com
 * 
 */
public class SimpleExponentialSmoothing implements ForecastRule {

	private DataSet forecastData = null;
	private org.eevolution.engine.forecast.DataSet forecastDataResult = null;
	private String key = null;
	private double factorAlpha = 0;
	private double factorGamma = 0;
	private double factorMultiplier = 0;
	private double factorScale = 0;
	private double factorBeta = 0;
	private double factorUser = 0;

	@Override
	public void setDataSet(org.eevolution.engine.forecast.DataSet series,
			double factorAlpha, double factorGamma, double factorBeta, double factorMultiplier,
			double factorScale,double factorUser) {
		this.factorAlpha = factorAlpha;
		this.factorGamma = factorGamma;
		this.factorBeta = factorBeta;
		this.factorMultiplier = factorMultiplier;
		this.factorScale = factorScale;
		this.factorUser = factorUser;
		DataSet observedData = new DataSet();
		DataPoint dp;

		Enumeration<DataElement> elements = series.getDataElements();

		while (elements.hasMoreElements()) {
			DataElement element = elements.nextElement();
			BigDecimal value = (BigDecimal) element.getValue();
			dp = new Observation(value.doubleValue());
			dp.setIndependentValue(element.getKey().toString(),
					(double) element.getPeriodNo());
			observedData.add(dp);
		}
		ForecastingModel forecaster = SimpleExponentialSmoothingModel
				.getBestFitModel(observedData, getFactorAlpha());
		forecaster.init(observedData);
		forecastData = forecaster.forecast(observedData);
	}

	@Override
	public org.eevolution.engine.forecast.DataSet getForecast() {

		forecastDataResult = new org.eevolution.engine.forecast.DataSet();
		Iterator it = forecastData.iterator();
		while (it.hasNext()) {
			DataPoint dp = (DataPoint) it.next();

			int peridoNo = (int) dp.getIndependentValue(getKey());
			BigDecimal calculateQty = new BigDecimal(dp.getDependentValue());
			DataElement data = new DataElement(new Integer(getKey()), peridoNo,
					calculateQty, calculateQty.toString());
			forecastDataResult.addDataElement(data);
		}

		return forecastDataResult;
	}

	@Override
	public void setKey(String M_Product_ID) {
		this.key = M_Product_ID;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public void setFactorAlpha(double alphaTolerance) {
		this.factorAlpha = alphaTolerance;
	}

	@Override
	public double getFactorAlpha() {
		return this.factorAlpha;
	}

	@Override
	public void setFactorGamma(double gammaTolerance) {
		this.factorGamma = gammaTolerance;
	}

	@Override
	public double getFactorGamma() {
		return this.factorGamma;
	}

	@Override
	public void setFactorBeta(double factorBeta) {
	this.factorBeta = factorBeta;
	}

	@Override
	public double getFactorBeta() {
		return this.factorBeta;
	}

	@Override
	public void setFactorUser(double factorUser) {
		this.factorUser =  factorUser;
	}

	@Override
	public double getFactorUser() {
		return this.factorUser;
	}
	
	@Override
	public void setFactorMultiplier(double factorMultiplier) {
		this.factorMultiplier = factorMultiplier;
	}

	@Override
	public double getFactorMultiplier() {
		return this.factorMultiplier;
	}

	@Override
	public void setFactorScale(double factorScale) {
		this.factorScale = factorScale;
	}

	@Override
	public double getFactorScale() {
		return this.factorScale;
	}
}
