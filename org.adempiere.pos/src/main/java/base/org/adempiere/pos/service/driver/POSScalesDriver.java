/** ****************************************************************************
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/

package org.adempiere.pos.service.driver;


import org.adempiere.pos.AdempierePOSException;

import java.math.BigDecimal;

/**
 * POS Scales Driver Implementation
 * Grupo Pastora author Ivan Cortes
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 08/02/16.
 */
public class POSScalesDriver implements POSScalesDriverInterface {

    private String electronicScales;
    private String measureRequestCode;
    private boolean loadLibrary;

    private native boolean openPort(String devicePort);

    private native String getMeasureMessage();

    public native void closePort();

    /*static {
        try {
            System.loadLibrary("scale");
        } catch (java.lang.UnsatisfiedLinkError exception) {
            throw new AdempierePOSException(exception.getMessage());
        }
    }*/

    public void setup() {
        loadLibrary = false;

        if (getElectronicScales() == null)
            throw new AdempierePOSException("@NotFound@ @Port@ @ElectronicScales@");
        if (getMeasureRequestCode() == null)
            throw new AdempierePOSException("@NotFound@ @MeasureRequestCode@");
    }


    public POSScalesDriver(String electronicScales, String measureRequestCode) {
        this.setElectronicScales(electronicScales);
        this.setMeasureRequestCode(measureRequestCode);
        setup();
    }

    public String getElectronicScales() {
        return electronicScales;
    }

    public void setElectronicScales(String electronicScales) {
        this.electronicScales = electronicScales;
    }

    public String getMeasureRequestCode() {
        return measureRequestCode;
    }

    public void setMeasureRequestCode(String measureRequestCode) {
        this.measureRequestCode = measureRequestCode;
    }

    public  boolean loadLibrary()
    {
        if (loadLibrary)
            return loadLibrary;

        try {
            System.loadLibrary("scale");
            loadLibrary = true;
            return loadLibrary;
        } catch (java.lang.UnsatisfiedLinkError exception) {
           return false;
        }
    }

    public BigDecimal getMeasure() {
        if(loadLibrary()) {
            if (!openPort(getElectronicScales()))
                throw new AdempierePOSException("@NotFound@ @Port@ @ElectronicScales@");

            String measure = this.getMeasureMessage().trim();
            String[] tokens;
            if (measure != null && !measure.equals("NEG")) {
                tokens = measure.split(" ");
                if (tokens != null && tokens.length > 0) {
                    try {
                        BigDecimal measureQuantity = new BigDecimal(tokens[0].trim());
                        if (measureQuantity != null && measureQuantity.signum() > 0) {
                            closePort();
                            return measureQuantity;
                        }
                        return BigDecimal.ONE;
                    } catch (Exception e) {
                        throw new org.adempiere.pos.AdempierePOSException("@Error@ @From@ @ElectronicScales@ : " + measure);
                    }
                }
            }
        }
        return BigDecimal.ONE;
    }
}
