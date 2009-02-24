/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                           *
 * http://www.adempiere.org                                            *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Daniel Tamm - usrdno                                              *
 *                                                                     *
 * Sponsors:                                                           *
 * - Company (http://www.notima.se)                                    *
 * - Company (http://www.cyberphoto.se)                                *
 ***********************************************************************/

package org.adempiere.process;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.process.*;

/**
 * Creates expense type products from a given range of expense account 
 * elements.
 * With all expense accounts from the chart of accounts added as expense
 * type products, every vendor invoice can be registered without having
 * to register additional products.
 * FR 2619262
 *
 * @author Daniel Tamm
 */
public class ExpenseTypesFromAccounts extends SvrProcess {

    private int m_clientId;
    private int m_acctSchemaId;
    private int m_priceListId;
    private String  m_productValuePrefix = "";
    private String  m_productValueSuffix = "";
    private String  m_startElement;
    private String  m_endElement;
    private int		m_productCategoryId;
    private int     m_taxCategoryId;
    private int     m_uomId;


    @Override
    protected void prepare() {

        // Get parameters
        ProcessInfoParameter[] para = getParameter();
        for (int i = 0; i < para.length; i++) {
            String name = para[i].getParameterName();
            if (para[i].getParameter() == null);
              else if (name.equals("M_Product_Category_ID")) {
				m_productCategoryId = para[i].getParameterAsInt();
            } else if (name.equals("C_AcctSchema_ID")) {
				m_acctSchemaId = para[i].getParameterAsInt();
            } else if (name.equals("M_PriceList_ID")) {
                m_priceListId = para[i].getParameterAsInt();
            } else if (name.equals("C_UOM_ID")) {
                m_uomId = para[i].getParameterAsInt();
            } else if (name.equals("C_TaxCategory_ID")) {
                m_taxCategoryId = para[i].getParameterAsInt();
            } else if (name.equals("ProductValuePrefix")) {
                m_productValuePrefix = para[i].getParameter().toString();
            } else if (name.equals("ProductValueSuffix")) {
                m_productValueSuffix = para[i].getParameter().toString();
            } else if (name.equals("StartElement")) {
                m_startElement = para[i].getParameter().toString();
            } else if (name.equals("EndElement")) {
                m_endElement = para[i].getParameter().toString();
            } else {
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
            }
        }

    }

    @Override
    protected String doIt() throws Exception {

        // Fetch price list
        MPriceList priceList = new MPriceList(getCtx(), m_priceListId, get_TrxName());
        // Get current client id from price list since I for some reason can't read it from
        // context.
        m_clientId = priceList.getAD_Client_ID();

        // Get active price list version
        MPriceListVersion pv = priceList.getPriceListVersion(null);
        if (pv==null) throw new Exception("Pricelist " + priceList.getName() + " has no default version.");

        MProduct product;

        // Read all existing applicable products into memory for quick comparison.
        Query q1 = new Query(
                getCtx(),
                MProduct.Table_Name,
                "ProductType=?",
                get_TrxName());
        q1.setParameters(new Object[]{MProduct.PRODUCTTYPE_ExpenseType});
        List<MProduct> products = q1.list();
        Map<String,MProduct> productMap = new TreeMap<String, MProduct>();
        for (Iterator<MProduct> it = products.iterator(); it.hasNext();) {
            product = it.next();
            productMap.put(product.getValue(), product);
        }

        // Read all existing valid combinations comparison
        MAccount validComb;
        q1 = new Query(getCtx(), MAccount.Table_Name, "C_AcctSchema_ID=? and AD_Client_ID=? and AD_Org_ID=0", get_TrxName());
        q1.setParameters(new Object[]{m_acctSchemaId, m_clientId});
        List<MAccount> validCombs = q1.list();
        Map<Integer, MAccount> validCombMap = new TreeMap<Integer, MAccount>();
        for (Iterator<MAccount> it = validCombs.iterator(); it.hasNext();) {
            validComb = it.next();
            validCombMap.put(validComb.getAccount_ID(), validComb);
        }

        // Read all accounttypes that fit the given criteria.
        Query q2 = new Query(
                getCtx(),
                MElementValue.Table_Name,
                "AccountType=? and isSummary='N' and Value>=? and Value<=? and AD_Client_ID=?",
                get_TrxName());
        q2.setParameters(new Object[]{MElementValue.ACCOUNTTYPE_Expense, m_startElement, m_endElement, m_clientId});
        List<MElementValue> result = q2.list();

        MElementValue elem;
        MProductPrice priceRec;
        X_M_Product_Acct productAcct;
        String expenseItemValue;
        BigDecimal zero = new BigDecimal("0");
        int addCount = 0;
        int skipCount = 0;

        for (Iterator<MElementValue> it = result.iterator(); it.hasNext();) {
            elem = it.next();
            expenseItemValue = m_productValuePrefix + elem.getValue() + m_productValueSuffix;
            // See if a product with this key already exists
            product = productMap.get(expenseItemValue);
            if (product==null) {
                // Create a new product from the account element
                product = new MProduct(getCtx(), 0, get_TrxName());
                product.set_ValueOfColumn("AD_Client_ID", new Integer(m_clientId));
                product.setValue(expenseItemValue);
                product.setName(elem.getName());
                product.setDescription(elem.getDescription());
                product.setIsActive(true);
                product.setProductType(MProduct.PRODUCTTYPE_ExpenseType);
                product.setM_Product_Category_ID(m_productCategoryId);
                product.setC_UOM_ID(m_uomId);
                product.setC_TaxCategory_ID(m_taxCategoryId);
                product.setIsStocked(false);
                product.setIsPurchased(true);
                product.setIsSold(false);
                // Save the product
                product.save(get_TrxName());

                // Add a zero product price to the price list so it shows up in the price list
                priceRec = new MProductPrice(getCtx(), pv.get_ID(), product.get_ID(), get_TrxName());
                priceRec.set_ValueOfColumn("AD_Client_ID", new Integer(m_clientId));
                priceRec.setPrices(zero, zero, zero);
                priceRec.save();

                // Set the revenue and expense accounting of the product to the given account element
                // Get the valid combination
                validComb = validCombMap.get(elem.getC_ElementValue_ID());
                if (validComb==null) {
                    // Create new valid combination
                    validComb = new MAccount(getCtx(), 0, get_TrxName());
                    validComb.set_ValueOfColumn("AD_Client_ID", new Integer(m_clientId));
                    validComb.setAD_Org_ID(0);
                    validComb.setAlias(elem.getValue());
                    validComb.setAccount_ID(elem.get_ID());
                    validComb.setC_AcctSchema_ID(m_acctSchemaId);
                    validComb.save(get_TrxName());
                }

                q1 = new Query(getCtx(), X_M_Product_Acct.Table_Name, "M_Product_ID=?", get_TrxName());
                q1.setParameters(new Object[]{product.get_ID()});
                productAcct = q1.first();
                productAcct.setP_Expense_Acct(validComb.get_ID());
                productAcct.setP_Revenue_Acct(validComb.get_ID());
                productAcct.save(get_TrxName());

                addCount++;
            } else {
                skipCount++;
            }
        }

        String returnStr = addCount + " products added.";
        if (skipCount>0) returnStr += " " + skipCount + " products skipped.";
        return(returnStr);

    }



}
