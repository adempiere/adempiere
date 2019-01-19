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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.engine.freight;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_Shipper;
import org.compiere.model.MOrder;
import org.compiere.util.CLogger;

import java.lang.reflect.Constructor;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 20/08/16.
 */
public class FreightEngine {


    /** Package for Freight Rule classes */
    static final String packageName = "org.eevolution.engine.freight";
    private static FreightEngine instanceFreightEngine;
    /** Freight Rule (negative cache) */
    private static TreeSet<String> freightRuleNoImplement = new TreeSet<>();
    /** Logger */
    protected CLogger log = CLogger.getCLogger(getClass());
    /** Cache Freight Rules */
    private HashMap<String, FreightRuleInterface> freightRules = new HashMap<String, FreightRuleInterface>();

    public FreightEngine() {
        registerRules(true);
    }

    public static FreightEngine get() {
        if (instanceFreightEngine == null) {
            instanceFreightEngine = new FreightEngine();
        }
        return instanceFreightEngine;
    }

    protected void registerRules(boolean reset) {
        if (reset)
            freightRules.clear();

        getFreightRuleFactory(null, MOrder.FREIGHTCOSTRULE_Calculated);
        getFreightRuleFactory(null, MOrder.FREIGHTCOSTRULE_FreightIncluded);
        getFreightRuleFactory(null, MOrder.FREIGHTCOSTRULE_Line);
    }

    public String getPackageName() {
        return this.packageName;
    }

    protected <T extends FreightRuleInterface> Class<T> getClass(String className)
            throws ClassNotFoundException {
        return (Class<T>) Class.forName(getPackageName() + "." + className);
    }

    public FreightRuleInterface getFreightRuleFactory(I_M_Shipper shipper , String freightRule) {
        String className = "";
        Boolean useFreightRule = (shipper != null && shipper.getCalculationClass() == null) || (shipper == null);
        if (useFreightRule)
            className = FreightRule.class.getSimpleName();
        else if (shipper.getCalculationClass() != null)
            className = shipper.getCalculationClass();


        if (freightRules.containsKey(className)) {
            return (FreightRuleInterface) freightRules.get(className);
        }
        try {
            Class<? extends FreightRuleInterface> cl = getClass(className);
            Constructor<?> constructor = null;
            try {
                constructor = cl.getDeclaredConstructor();
            } catch (Exception e) {
                log.fine("Freight Not Found");
            }
            if (constructor != null) {
                FreightRuleInterface rule = (FreightRuleInterface) constructor
                        .newInstance();
                freightRules.put(className, rule);
                return rule;
            }
            constructor = cl.getDeclaredConstructor();
            FreightRuleInterface rule = (FreightRuleInterface) constructor.newInstance();
            freightRules.put(className, rule);
            return rule;
        } catch (ClassNotFoundException e) {
            freightRuleNoImplement.add(className);
        } catch (Throwable e) {
            throw new AdempiereException(e);
        }
        return null;
    }
}
