/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Created on May 8, 2006 by alok
 */

package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.model.MBPartner;
import org.compiere.model.MCash;
import org.compiere.model.MCashBook;
import org.compiere.model.MCurrency;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSTerminal;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.model.MStore;
import org.compiere.model.MWarehouse;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.TerminalBean;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.InvalidTerminalCashBookException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TerminalInactivateException;
import org.posterita.exceptions.TerminalLockedException;
import org.posterita.exceptions.TerminalNotFoundException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;

public class POSTerminalManager 
{
    public static final CLogger logger = CLogger.getCLogger(POSTerminalManager.class);
    
    /**
     * Creates a new POS Terminal
     * @param ctx Context
     * @param orgId Organisation
     * @param name Name of the terminal
     * @param cashbookId Cash Book
     * @param bankAccountId Bank Account
     * @param poPriceListId Purchase Price list
     * @param soPriceListid Sales Price list
     * @param warehouseId Warehouse
     * @param cashBPartnerId BPartner to be used for Cash Transactions
     * @param salesRepId Sales Representative
     * @param trxName Transaction
     * @return POS Terminal
     * @throws OperationException If the terminal cannot be created
     */
    public static MPOSTerminal createPOSTerminal(Properties ctx, int orgId, String name, 
            int cashbookId, int bankAccountId, int poPriceListId, int soPriceListid, int warehouseId,int cashBPartnerId, 
            int salesRepId, String trxName) throws OperationException
    {
        MPOSTerminal terminal = new MPOSTerminal(ctx, 0, trxName);
        terminal.setAD_Org_ID(orgId);
        terminal.setName(name);
        terminal.setC_CashBook_ID(cashbookId);
        terminal.setM_Warehouse_ID(warehouseId);
        terminal.setPO_PriceList_ID(poPriceListId);
        terminal.setSO_PriceList_ID(soPriceListid);
        terminal.setCard_BankAccount_ID(bankAccountId);
        terminal.setCheck_BankAccount_ID(bankAccountId);
        terminal.setC_CashBPartner_ID(cashBPartnerId);
        terminal.setCashBookTransferType(MPOSTerminal.CASHBOOKTRANSFERTYPE_BankAccount);
        terminal.setCashTransferBankAccount_ID(bankAccountId);
        PoManager.save(terminal);
        return terminal;
    }
    
    
    /**
     * Migrates old POS Terminal structure to the new one
     * @param ctx Context
     * @throws OperationException If new terminal cannot be created
     */
    public static void migrateOldPOSTerminal(Properties ctx) throws OperationException
    {
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            String whereClause = "IsActive='Y' AND AD_Client_ID=" + Env.getAD_Client_ID(ctx);
            
            int oldPosIds[] = MPOS.getAllIDs(MPOS.Table_Name, whereClause, trx.getTrxName());
            
            for (int i = 0; i < oldPosIds.length; i++)
            {
                MPOS oldPOS = new MPOS(ctx, oldPosIds[i], trx.getTrxName());
                
                String posName = oldPOS.getName();
                int warehouseId = oldPOS.getM_Warehouse_ID();
                int soPriceListId = oldPOS.getM_PriceList_ID();
                int cashBookId = oldPOS.getC_CashBook_ID();
                int cashBPartnerId = oldPOS.getC_BPartnerCashTrx_ID();
                int adOrgId = oldPOS.getAD_Org_ID();
                int bankAccountId = BankManager.getDefaultBankAccountId(ctx, adOrgId, trx.getTrxName());
                int salesRepId = oldPOS.getSalesRep_ID();
                MStore store = StoreManager.getStore(ctx);
                if (store.get_ID() == 0)
                {
                    throw new OperationException("Store could not be loaded");
                }
                
                int poPriceListId = store.getM_PriceList_ID();
                
                MPOSTerminal terminal = createPOSTerminal(ctx, adOrgId, posName, cashBookId, bankAccountId, poPriceListId, soPriceListId, warehouseId, cashBPartnerId, salesRepId, trx.getTrxName());
                StringBuffer updateStmt = new StringBuffer();
                updateStmt.append("UPDATE C_Order SET C_POS_ID=").append(terminal.get_ID());
                updateStmt.append(" WHERE C_POS_ID=").append(oldPOS.get_ID());
                
                DB.executeUpdate(updateStmt.toString(), trx.getTrxName());
                
                oldPOS.setIsActive(false);
                PoManager.save(oldPOS);
                trx.commit();
            }
        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE, "Could not migrate old pos terminal", ex);
        }
        finally
        {
            trx.rollback();
            trx.close();
        }
    }
    
    
    /**
     * Checks whether a check book can be set on a terminal
     * @param ctx Context
     * @param terminalId Terminal
     * @param cashBookId Cash Book
     * @param trxName Transaction
     * @return Whether the cash book can be set on the terminal
     * @throws OperationException If data cannot be retrieved
     */
    public static boolean isCashBookAssignedValid(Properties ctx, int terminalId, int cashBookId, String trxName) throws OperationException
    {
        boolean valid = false;
        
        StringBuffer sqlStmt = new StringBuffer();
        sqlStmt.append("SELECT COUNT(*) FROM U_POSTerminal ");
        sqlStmt.append("WHERE AD_Client_ID=? AND U_POSTerminal_ID<>? AND C_CashBook_ID=? ");
        sqlStmt.append("AND U_POSTerminal.IsActive='Y' ");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {
            pstmt = DB.prepareStatement(sqlStmt.toString(), trxName);
            pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            pstmt.setInt(2, terminalId);
            pstmt.setInt(3, cashBookId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next())
            {
                valid = (rs.getInt(1) == 0);
            }
        }
        catch (Exception ex)
        {
            throw new OperationException("Could not check validity of Cash Book", ex);
        }
        finally
        {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        
        return valid;
    }
    
    /**
     * Checks whether a cash book has been assigned on a terminal
     * @param ctx Context
     * @param cashBookId Context
     * @param trxName Transaction
     * @return True if cash book is present on a terminal
     */
    public static boolean isCashbookPresentOnTerminal(Properties ctx, int cashBookId, String trxName)
    {
        String sqlStmt = "SELECT U_POSTerminal_ID FROM U_POSTerminal WHERE AD_Client_ID=? AND C_CashBook_ID=? AND IsActive='Y'";
        
        int terminalId = DB.getSQLValue(trxName, sqlStmt, Env.getAD_Client_ID(ctx), cashBookId);
        
        return (terminalId != -1);
    }
    
    /**
     * Creates or updates a terminal based on Data provided
     * @param ctx Context
     * @param bean Terminal data
     * @param trxName Transaction
     * @return POS Terminal PO
     * @throws OperationException If data could not be updated 
     */
    public static MPOSTerminal createUpdatePOSTerminal(Properties ctx, TerminalBean bean, String trxName) throws OperationException
    {
        MPOSTerminal terminal = getPO(ctx, bean, trxName);
        
        if (bean.getIsActive() && !isCashBookAssignedValid(ctx, bean.getTerminalId(), bean.getCashBookId(), trxName))
        {
            throw new InvalidTerminalCashBookException("The cash book assigned is already present on another terminal");
        }
        else if (!bean.getIsActive() && !canInactivateTerminal(ctx, terminal.getAD_Org_ID(), terminal.get_ID(), trxName))
        {
            throw new TerminalInactivateException("This terminal cannot be inactivated as no other active terminal available");
        }
        
        PoManager.save(terminal);
        bean.setTerminalId(terminal.get_ID());
        return terminal;
    }
    
    /**
     * Returns the Persistent Object from the model used for the Terminal
     * @param ctx Context
     * @param bean Data model used
     * @param trxName Transaction
     * @return Terminal PO
     * @throws OperationException If data in the model is not valid to construct the PO
     */
    public static MPOSTerminal getPO(Properties ctx, TerminalBean bean, String trxName) throws OperationException
    {
        MPOSTerminal terminal = new MPOSTerminal(ctx, bean.getTerminalId(), trxName);
        if (terminal.get_ID() != bean.getTerminalId())
        {
            // Happens only when trying to load a terminal that is not present
            // and the PO model resets the Id to 0
            throw new OperationException("Could not load terminal");
        }
        terminal.setAD_Org_ID(bean.getOrgId());
        terminal.setName(bean.getName());
        terminal.setDescription(bean.getDescription());
        terminal.setC_CashBook_ID(bean.getCashBookId());
        terminal.setC_CashBPartner_ID(bean.getBpartnerId());
        terminal.setC_TemplateBPartner_ID(bean.getTemplateBPartnerId());
        terminal.setCheck_BankAccount_ID(bean.getCheckBankAccountId());
        terminal.setCard_BankAccount_ID(bean.getCardBankAccountId());
        terminal.setIsActive(bean.getIsActive());
        terminal.setLocked(bean.getLocked());
        terminal.setM_Warehouse_ID(bean.getWarehouseId());
        terminal.setPO_PriceList_ID(bean.getPurchasePriceListId());
        terminal.setSO_PriceList_ID(bean.getSalesPriceListId());
        
        terminal.setAutoLock(bean.getAutoLock());
        
        if (bean.getAutoLock())
        {
            terminal.setLockTime(bean.getLockingTime());
        }
        else
        {
            terminal.setLockTime(0);
        }
        
        terminal.setCashBookTransferType(bean.getCashbookTransferType());
        if ( MPOSTerminal.CASHBOOKTRANSFERTYPE_CashBook.equals(bean.getCashbookTransferType()))
        {
            if (bean.getCashbookTransferCashbookId() == 0)
            {
                throw new OperationException("No cash book defined to transfer cash transactions");
            }
            terminal.setCashTransferCashBook_ID(bean.getCashbookTransferCashbookId());
            terminal.setCashTransferBankAccount_ID(0);
        }
        else if (MPOSTerminal.CASHBOOKTRANSFERTYPE_BankAccount.equals(bean.getCashbookTransferType()))
        {
            if (bean.getCashbookTransferBankAccountId() == 0)
            {
                throw new OperationException("No bank account defined to transfer cash transactions");
            }
            terminal.setCashTransferCashBook_ID(0);
            terminal.setCashTransferBankAccount_ID(bean.getCashbookTransferBankAccountId());
        }
        
        
        terminal.setCheckTransferType(bean.getCheckTransferType());
        if (MPOSTerminal.CHECKTRANSFERTYPE_CashBook.equals(bean.getCheckTransferType()))
        {
            if (bean.getCheckTransferCashbookId() == 0)
            {
                throw new OperationException("No cash book defined to transfer check transactions");
            }
            terminal.setCheckTransferCashBook_ID(bean.getCheckTransferCashbookId());
            terminal.setCheckTransferBankAccount_ID(0);
        }
        else if (MPOSTerminal.CHECKTRANSFERTYPE_BankAccount.equals(bean.getCheckTransferType()))
        {
            if (bean.getCheckTransferBankAccountId() == 0)
            {
                throw new OperationException("No bank account defined to transfer check transactions");
            }
            terminal.setCheckTransferCashBook_ID(0);
            terminal.setCheckTransferBankAccount_ID(bean.getCheckTransferBankAccountId());
        }
        
        terminal.setCardTransferType(bean.getCardTransferType());
        if (MPOSTerminal.CARDTRANSFERTYPE_CashBook.equals(bean.getCardTransferType()))
        {
            if (bean.getCardTransferCashbookId() == 0)
            {
                throw new OperationException("No cash book defined to transfer credit card transactions");
            }
            terminal.setCardTransferCashBook_ID(bean.getCardTransferCashbookId());
            terminal.setCardTransferBankAccount_ID(0);
        }
        else if (MPOSTerminal.CARDTRANSFERTYPE_BankAccount.equals(bean.getCardBankAccountId()))
        {
            if (bean.getCardBankAccountId() == 0)
            {
                throw new OperationException("No bank account defined to transfer credit card transactions");
            }
            terminal.setCardTransferCashBook_ID(0);
            terminal.setCardTransferBankAccount_ID(bean.getCardTransferBankAccountId());
        }
        return terminal;
    }
    
    
    /**
     * Retrieves the terminal id from the cookie stored in the client's browser
     * @param request Server Request
     * @return Terminal ID present in the cookie or null if not present
     */
    public static String getTerminalFromCookie(HttpServletRequest request) 
    {
        return POSManager.getDataFromCookie(request, Constants.POSTERMINAL);
    }
    
    /**
     * Retrieves the terminal id from the cookie stored in the client's browser
     * @param request Server Request
     * @return Terminal ID present in the cookie
     * @throws OperationException If the Terminal Id is not valid
     */
    public static int getTerminalIdFromCookie(HttpServletRequest request) throws OperationException 
    {
        String strTerminalId = getTerminalFromCookie(request);
        
        int terminalId = -1;
        
        try
        {
            terminalId = Integer.parseInt(strTerminalId);
            Properties ctx = TmkJSPEnv.getCtx(request);
            loadTerminal(ctx, terminalId, null);
        }
        catch (Exception ex)
        {
            throw new OperationException("Could not retrieve terminal Id");
        }
        return terminalId;
    }
    
    /**
     * Set the terminal id in the cookie that is stored in the client's browser
     * @param response Response to be sent to the client
     * @param terminalId Terminal Id
     */
    public static void setTerminalInCookie(HttpServletResponse response, int terminalId)
    {
        Cookie cookie =  new Cookie(Constants.POSTERMINAL, String.valueOf(terminalId));
        cookie.setMaxAge(365*24*60*60);     
        response.addCookie(cookie);
    }
    
    /**
     * Reset the terminal id in the cookie that is stored in the client's browser
     * @param response Response to be sent to client
     */
    public static void resetTerminalInCookie(HttpServletResponse response)
    {
        Cookie cookie =  new Cookie(Constants.POSTERMINAL, "");
        response.addCookie(cookie);
    }
    
    /**
     * Checks whether a POS Terminal is present with the id provided 
     * and the context organisation
     * @param ctx Context
     * @param posId POS Terminal
     * @return
     */
    public static boolean isPOSTerminalAccessible(Properties ctx, int terminalId)
    {
        String whereClause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx) + 
            " AND AD_Org_ID=" + Env.getAD_Org_ID(ctx) + " AND U_PosTerminal_ID=" + terminalId
            + " AND IsActive='Y'";
        
        int posIds[] = MPOSTerminal.getAllIDs(MPOSTerminal.Table_Name, whereClause, null);
        
        if(posIds.length == 0)
        {
            return false;
        }
        return true;
    }
    
    
    /**
     * Returns the terminal data model from the Terminal persistent object
     * @param terminal Terminal PO
     * @return Data model
     */
    public static TerminalBean getBean(MPOSTerminal terminal)
    {
        int currentTerminalId = getTerminalId(terminal.getCtx());
        TerminalBean bean = new TerminalBean();
        bean.setTerminalId(terminal.getU_POSTerminal_ID());
        bean.setOrgId(terminal.getAD_Org_ID());
        bean.setName(terminal.getName());
        bean.setDescription(terminal.getDescription());
        bean.setAutoLock(terminal.isAutoLock());
        bean.setBpartnerId(terminal.getC_CashBPartner_ID());
        bean.setCashBookId(terminal.getC_CashBook_ID());
        bean.setCashbookTransferType(terminal.getCashBookTransferType());
        bean.setCashbookTransferBankAccountId(terminal.getCashTransferBankAccount_ID());
        bean.setCashbookTransferCashbookId(terminal.getCashTransferCashBook_ID());
        bean.setCheckBankAccountId(terminal.getCheck_BankAccount_ID());
        bean.setCheckTransferBankAccountId(terminal.getCheckTransferBankAccount_ID());
        bean.setCheckTransferCashbookId(terminal.getCheckTransferCashBook_ID());
        bean.setCheckTransferType(terminal.getCheckTransferType());
        bean.setCardBankAccountId(terminal.getCard_BankAccount_ID());
        bean.setCardTransferBankAccountId(terminal.getCardTransferBankAccount_ID());
        bean.setCardTransferCashbookId(terminal.getCardTransferCashBook_ID());
        bean.setCardTransferType(terminal.getCardTransferType());
        bean.setIsActive(terminal.isActive());
        bean.setLocked(terminal.isLocked());
        bean.setLockingTime(terminal.getLockTime());
        bean.setPurchasePriceListId(terminal.getPO_PriceList_ID());
        bean.setSalesPriceListId(terminal.getSO_PriceList_ID());
        bean.setTemplateBPartnerId(terminal.getC_TemplateBPartner_ID());
        bean.setWarehouseId(terminal.getM_Warehouse_ID());
        
        if (currentTerminalId == terminal.get_ID())
        {
            bean.setIsCurrentTerminal(true);
        }
        
        return bean;
    }
    
    
    /**
     * Retrieves POS Terminal
     * @param ctx Context
     * @param terminalId Terminal ID
     * @param trxName Transaction
     * @return Terminal
     * @throws TerminalNotFoundException If the terminal does not exist or could not be loaded
     */
    public static TerminalBean getTerminalBean(Properties ctx, int terminalId, String trxName) throws TerminalNotFoundException
    {
        MPOSTerminal posTerminal = new MPOSTerminal(ctx, terminalId, trxName);
        if (posTerminal.get_ID() == 0)
        {
            throw new TerminalNotFoundException("Could not load terminal with id: " + terminalId);
        }
        
        return getBean(posTerminal);
    }
    
    /**
     * Checks whether can activate
     * @param ctx
     * @param adOrgId
     * @param trxName
     * @return
     */
    private static boolean canInactivateTerminal(Properties ctx, int adOrgId, int terminalId, String trxName)
    {
        String sqlStmt = "SELECT COUNT(*) FROM U_POSTerminal WHERE IsActive='Y' AND AD_Org_ID=? AND U_POSTerminal_ID<>?";
        
        int count = DB.getSQLValue(trxName, sqlStmt, adOrgId, terminalId);
        return (count >= 1);
    }
    
    /**
     * Updates the Terminal active state
     * @param ctx Context
     * @param terminalId Terminal ID
     * @param active State of the terminal
     * @param trxName Transaction
     * @return Terminal Data model
     * @throws OperationException If Terminal could not be updated
     */
    public static TerminalBean updateTerminalStatus(Properties ctx, int terminalId, boolean active, String trxName) throws OperationException
    {
        MPOSTerminal terminal = loadTerminal(ctx, terminalId, trxName);
        if (active && !isCashBookAssignedValid(ctx, terminalId, terminal.getC_CashBook_ID(), trxName))
        {
            throw new InvalidTerminalCashBookException("Terminal cannot be reactivated as the cash book assigned to it is already being used");
        }
        if (!active && !canInactivateTerminal(ctx, terminal.getAD_Org_ID(), terminalId, trxName))
        {
            throw new TerminalInactivateException("Cannot inactivate this terminal as no active terminals present");
        }
        terminal.setIsActive(active);
        PoManager.save(terminal);
        return getBean(terminal);
    }
    
    /**
     * Retrieves the Terminal ID from the applica
     * @param ctx
     * @return
     */
    public static int getTerminalId(Properties ctx)
    {
        int terminalId = Env.getContextAsInt(ctx, UdiConstants.TERMINAL_ID);
        return terminalId;
    }
    
    /**
     * Retrieves POS Terminal
     * @param ctx Context
     * @param terminalId Terminal ID
     * @param trxName Transaction
     * @return Terminal
     * @throws TerminalNotFoundException If the terminal does not exist or could not be loaded
     */
    public static MPOSTerminal loadTerminal(Properties ctx, int terminalId, String trxName) throws TerminalNotFoundException
    {
        MPOSTerminal posTerminal = new MPOSTerminal(ctx, terminalId, trxName);
        if (posTerminal.get_ID() == 0)
        {
            throw new TerminalNotFoundException("Could not load terminal with id: " + terminalId);
        }
        
        return posTerminal;
    }
    
    /**
     * Loads the PO of the terminal present in the application context
     * @param ctx Context
     * @return Terminal PO
     * @throws TerminalNotFoundException If no terminal ID is present in the application context
     *         or the terminal ID is invalid
     */
    public static MPOSTerminal loadTerminal(Properties ctx) throws TerminalNotFoundException
    {
        return loadTerminal(ctx, getTerminalId(ctx), null);
    }
    
    /**
     * Returns the Terminal PO from the terminal ID present in the application context
     * @param ctx Context
     * @return Terminal PO
     * @throws TerminalNotFoundException If the terminal cannot be loaded
     */
    public static MPOSTerminal getTerminal(Properties ctx) throws TerminalNotFoundException
    {
        int terminalId = getTerminalId(ctx);
        return getTerminal(ctx, terminalId);
    }
    
    /**
     * Returns the Terminal PO from the terminal ID provided
     * @param ctx Context
     * @param terminalId
     * @return
     * @throws TerminalNotFoundException If the terminal cannot be loaded
     */
    private static MPOSTerminal getTerminal(Properties ctx, int terminalId) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = MPOSTerminal.get(ctx, terminalId);
        if (terminal == null)
        {
            throw new TerminalNotFoundException("Terminal cannot be loaded; terminalId["+terminalId+"]");
        }
        return terminal;
    }
    
    /**
     * Get all terminals from a search based on the name of the terminals
     * @param ctx Context
     * @param searchText Name search term
     * @param trxName Transaction
     * @return List of Terminals
     * @throws OperationException If data cannot be retrieved
     */
    public static ArrayList<TerminalBean> getTerminals(Properties ctx, String searchText, String trxName) throws OperationException
    {
        ArrayList<TerminalBean> terminalList = new ArrayList<TerminalBean>();
        StringBuffer sqlStmt = new StringBuffer();
        
        sqlStmt.append("SELECT pos.*, o.Name as orgName, p1.Name as poPriceList, p2.Name as soPriceList ");
        sqlStmt.append("FROM U_POSTerminal pos ");
        sqlStmt.append("LEFT JOIN AD_Org o ON pos.AD_Org_ID=o.AD_Org_ID ");
        sqlStmt.append("LEFT JOIN M_PriceList p1 ON pos.PO_PriceList_ID=p1.M_PriceList_ID ");
        sqlStmt.append("LEFT JOIN M_PriceList p2 ON pos.SO_PriceList_ID=p2.M_PriceList_ID ");
        sqlStmt.append("WHERE UPPER(pos.Name) LIKE UPPER(?) AND pos.AD_Client_ID=? ");
        sqlStmt.append("AND pos.AD_Org_ID IN (").append(Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)).append(") ");
        sqlStmt.append("ORDER BY pos.Name");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        if (searchText == null)
        {
            searchText = "";
        }
        
        searchText = "%" + searchText.trim() + "%";
        
        int currentTerminalId = getTerminalId(ctx);
        
        try
        {
            pstmt = DB.prepareStatement(sqlStmt.toString(), trxName);
            pstmt.setString(1, searchText);
            pstmt.setInt(2, Env.getAD_Client_ID(ctx));
            
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                MPOSTerminal terminal = new MPOSTerminal(ctx, rs, trxName);
                TerminalBean terminalBean = getBean(terminal);
                terminalBean.setOrgName(rs.getString("orgName"));
                terminalBean.setPoPriceList(rs.getString("poPriceList"));
                terminalBean.setSoPriceList(rs.getString("soPriceList"));
                terminalBean.setIsCurrentTerminal(false);
                
                if (terminal.get_ID() == currentTerminalId)
                {
                    terminalBean.setIsCurrentTerminal(true);
                }
                
                terminalList.add(terminalBean);
            }
        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE, "Could not retrieve terminals", ex);
            throw new OperationException("Could not retrieve terminals, cause: " + ex.getMessage());
        }
        finally
        {
            DB.close(rs, pstmt);
        }
        
        return terminalList;
    }
    
    
    /**
     * Retrieves list of active terminals
     * @param ctx Context
     * @param adOrgId Organisation
     * @param trxName Transaction
     * @return List of Active Terminals
     * @throws OperationException If data cannot be retrieved
     */
    public static ArrayList<TerminalBean> getAllActiveTerminals(Properties ctx, int adOrgId, String trxName) throws OperationException
    {
        ArrayList<TerminalBean> terminalList = new ArrayList<TerminalBean>();
        
        String sqlStmt = "SELECT * FROM U_POSTerminal WHERE AD_Client_ID=? " +
        		"AND AD_Org_ID=? AND IsActive='Y' ORDER BY Name";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {
            pstmt = DB.prepareStatement(sqlStmt, trxName);
            pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            pstmt.setInt(2, adOrgId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                MPOSTerminal terminal = new MPOSTerminal(ctx, rs, trxName);
                TerminalBean terminalBean = getBean(terminal);
                terminalList.add(terminalBean);
            }
        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE, "Could not retrieve terminals", ex);
            throw new OperationException("Could not retrieve terminals, cause: " + ex.getMessage());
        }
        finally 
        {
            DB.close(rs, pstmt);
        }
        
        return terminalList;
    }
    
    
    /**
     * Retrieves the currency for the Sales Price List for the POS Terminal
     * @param ctx Context
     * @return Currency of Sales PriceList
     * @throws TerminalNotFoundException 
     */
    public static MCurrency getDefaultSalesCurrency(Properties ctx) throws TerminalNotFoundException
    {
        int salesPriceListId = POSTerminalManager.getSOPriceListId(ctx);
        MPriceList priceList = new MPriceList(ctx, salesPriceListId, null);
        MCurrency currency = new MCurrency(ctx, priceList.getC_Currency_ID(), null);
        return currency;
    }
    
    /**
     * Retrieves the currency for the Sales Price List for the POS Terminal
     * @param ctx Context
     * @return Currency of Sales PriceList
     * @throws OperationException 
     */
    public static MCurrency getDefaultPurchaseCurrency(Properties ctx) throws OperationException
    {
        int poPriceListId = getPOPriceListId(ctx);
        MPriceList priceList = new MPriceList(ctx, poPriceListId, null);
        MCurrency currency = new MCurrency(ctx,priceList.getC_Currency_ID(),null);
        return currency;
    }

    /**
     * Retrieves the BPartner to be used for Cash Transactions for the the terminal present in 
     * the application context
     * @param ctx Context
     * @return BPartner
     * @throws OperationException If the BPartner cannot be loaded
     */
    public static MBPartner getCashBPartner(Properties ctx) throws OperationException
    {
        MPOSTerminal terminal = getTerminal(ctx); 
        int cashBPartnerId = terminal.getC_CashBPartner_ID();
        return BPartnerManager.loadBPartner(ctx, cashBPartnerId, null);
    }
    
    /**
     * Retrieves the ID of the BPartner defined on the Terminal present in application context 
     * that is to be used for Cash Transactions
     * @param ctx Context
     * @return ID of BPartner for cash transactions
     * @throws TerminalNotFoundException If the terminal cannot be loaded
     */
    public static int getCashBPartnerId(Properties ctx) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = getTerminal(ctx); 
        int cashBPartnerId = terminal.getC_CashBPartner_ID();
        return cashBPartnerId;
    }
    
    /**
     * Returns the name of the printer to be used for server printing
     * @param ctx Context
     * @return Name of printer to be used for server printing
     * @throws TerminalNotFoundException If the terminal cannot be loaded
     */
    public static String getPOSPrinter(Properties ctx) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = getTerminal(ctx);
        return terminal.getPrinterName();
    }

    /**
     * Returns the Cash Book associated with the terminal defined in the application context
     * @param ctx Context
     * @return Cash Book
     * @throws OperationException If the cash book cannot be
     */
    public static MCashBook getCashBook(Properties ctx) throws OperationException
    {
        int terminalId = Env.getContextAsInt(ctx,UdiConstants.TERMINAL_ID);
        return getCashBook(ctx, terminalId);
    }
    
    /**
     * Returns the Cash Book ID of the Cash Book associated with the terminal defined in the
     * application context 
     * @param ctx Context
     * @return Cash Book ID
     * @throws OperationException If the terminal cannot be loaded
     */
    public static int getCashBookId(Properties ctx) throws OperationException
    {
        return getTerminal(ctx).getC_CashBook_ID();
    }
    
    /**
     * Returns the Cash Book associated with the terminal provided
     * @param ctx Context
     * @param terminalId Terminal
     * @return Cash Book
     * @throws OperationException If the terminal/cash book cannot be loaded
     */
    public static MCashBook getCashBook(Properties ctx, int terminalId) throws OperationException
    {
        MPOSTerminal terminal = getTerminal(ctx, terminalId);
        int cashBookId = terminal.getC_CashBook_ID();
        MCashBook cashBook = CashManager.loadCashBook(ctx, cashBookId, null);
        return cashBook;
    }
    
    /**
     * Retrieves the cash book ID associated with the terminal provided
     * @param ctx Context
     * @param terminalId Terminal ID
     * @return Cash Book ID
     * @throws OperationException If the terminal cannot be loaded
     */
    public static int getCashBookId(Properties ctx, int terminalId) throws OperationException
    {
        MPOSTerminal terminal = getTerminal(ctx, terminalId);
        return terminal.getC_CashBook_ID();
    }

    /**
     * Retrieves the Warehouse associated with the terminal present in the application context
     * @param ctx Context
     * @return Warehouse
     * @throws TerminalNotFoundException If the terminal cannot be loaded
     */
    public static MWarehouse getWarehouse(Properties ctx) throws TerminalNotFoundException
    {
        int terminalId = Env.getContextAsInt(ctx,UdiConstants.TERMINAL_ID);
        return getWarehouse(ctx, terminalId); 
    }
    
    /**
     * Retrieves the Warehouse associated with the terminal provided
     * @param ctx Context
     * @param terminalId Terminal
     * @return Warehouse
     * @throws TerminalNotFoundException If terminal cannot be loaded
     */
    public static MWarehouse getWarehouse(Properties ctx, int terminalId) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = loadTerminal(ctx, terminalId, null);
        int warehouseId = terminal.getM_Warehouse_ID();
        MWarehouse warehouse = new MWarehouse(ctx,warehouseId,null);
        return warehouse;
    }
    
    /**
     * Retrieves the currency of the Cash Book associated with terminal in the application context
     * @param ctx Context
     * @return Currency of Cash Book
     * @throws OperationException If terminal/cash book cannot be loaded
     */
    public static MCurrency getCurrencyOfTerminalCashBook(Properties ctx) throws OperationException
    {
        int terminalId = Env.getContextAsInt(ctx,UdiConstants.TERMINAL_ID);
        return getCurrencyOfTerminalCashBook(ctx, terminalId);
    }
    
    /**
     * Retrieves the currency of the Cash Book associated with the terminal provided
     * @param ctx Context
     * @param terminalId Terminal
     * @return Currency of Cash Book
     * @throws OperationException If Terminal/Cash Book cannot be loaded 
     */
    public static MCurrency getCurrencyOfTerminalCashBook(Properties ctx, int terminalId) throws OperationException
    {
        MCashBook cashBook = getCashBook(ctx, terminalId);
        MCurrency currency = new MCurrency(ctx, cashBook.getC_Currency_ID(), null);
        return currency;
    }
    
    
    /**
     * Retrieves the name of the POS Terminal in context
     * @param ctx Context
     * @return Terminal Name
     * @throws TerminalNotFoundException If the terminal cannot be loaded
     */
    public static String getTerminalName(Properties ctx) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = getTerminal(ctx);
        return terminal.getName();
    }
    
    /**
     * Retrieves the name of the POS Terminal
     * @param ctx Context
     * @param terminalId Terminal
     * @return Terminal Name
     * @throws TerminalNotFoundException If the terminal cannot be loaded
     */
    public static String getTerminalName(Properties ctx, int terminalId) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = getTerminal(ctx, terminalId);
        return terminal.getName();
    }

    /**
     * Retrieves the id of the sales price list associated with the POS Terminal in context.
     * @param ctx   context
     * @return      price list id
     * @throws TerminalNotFoundException 
     */
    public static int getSOPriceListId(Properties ctx) throws TerminalNotFoundException
    {
        return getPriceListId(ctx, true);
    }
    
    /**
     * Retrieves the id of the purchase price list associated with the POS Terminal in context.
     * @param ctx   context
     * @return      price list id
     * @throws TerminalNotFoundException 
     */
    public static int getPOPriceListId(Properties ctx) throws TerminalNotFoundException
    {
        return getPriceListId(ctx, false);
    }
    
    /**
     * Retrieves price list associated with the POS Terminal in context
     * @param ctx Context
     * @param isSOTrx Sales Pricelist
     * @return Price List ID (Sales or Purchase)
     * @throws TerminalNotFoundException 
     */
    public static int getPriceListId(Properties ctx, boolean isSOTrx) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = getTerminal(ctx);
        
        if (isSOTrx)
        {
            return terminal.getSO_PriceList_ID();
        }
        else
        {
            return terminal.getPO_PriceList_ID();
        }
    }
    
    /**
     * Updates the price list associated with the POS Terminal in context.
     * @param ctx           context
     * @param priceListId   price list id
     * @param isSOTrx True for sales price list and false for purchase price list
     * @throws OperationException 
     */
    public static void setTerminalPriceListId(Properties ctx, int priceListId, 
            boolean isSOTrx, String trxName) throws OperationException
    {
         MPOSTerminal terminal = loadTerminal(ctx);
         terminal.set_TrxName(trxName);
         
         if (isSOTrx)
         {
             terminal.setSO_PriceList_ID(priceListId);
         }
         else
         {
             terminal.setPO_PriceList_ID(priceListId);
         }
         PoManager.save(terminal);
    }
    
    /**
     * Retrieves the organisation associated with the POS Terminal in context.
     * @param ctx context
     * @return Organisation Id (AD_Org_ID)
     * @throws TerminalNotFoundException 
     */
    public static int getOrgId(Properties ctx) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = getTerminal(ctx);
        return terminal.getAD_Org_ID();
    }
    
    /**
     * Retrieves the Warehouse associated with the POS Terminal in context.
     * @param ctx context
     * @return Warehouse Id (M_Warehouse_ID)
     * @throws TerminalNotFoundException 
     */
    public static int getWarehouseId(Properties ctx) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = getTerminal(ctx);
        return terminal.getM_Warehouse_ID();
    }
    
    /**
     * Return the Bank Account specified for a tender type on the terminal
     * @param ctx Context
     * @param tenderType Payment Tender Type
     * @return Bank Account Id
     * @throws OperationException If no Bank Account is defined for tender type
     */
    public static int getBankAccountId(Properties ctx, String tenderType) throws OperationException
    {
        MPOSTerminal terminal = getTerminal(ctx);
        int bankAccountId = 0;
        
        if (MPayment.TENDERTYPE_Check.equals(tenderType))
        {
            bankAccountId = terminal.getCheck_BankAccount_ID();
        }
        else if (MPayment.TENDERTYPE_CreditCard.equals(tenderType))
        {
            bankAccountId = terminal.getCheck_BankAccount_ID();
        }
        
        if (bankAccountId <= 0)
        {
            throw new OperationException("No bank account defined on the terminal for the tender type: " + tenderType);
        }
        
        return bankAccountId;
    }
    
    /**
     * Retrieves the lock status of the terminal present in the application context is locked
     * @param ctx Context
     * @return Lock status of the terminal
     * @throws TerminalNotFoundException If terminal cannot be loaded
     */
    public static boolean isTerminalLocked(Properties ctx) throws TerminalNotFoundException
    {
        int terminalId = getTerminalId(ctx);
        return isTerminalLocked(ctx, terminalId);
    }
    
    /**
     * Retrieves the locked status of the terminal provided
     * @param ctx Context
     * @param terminalId Terminal
     * @return Lock status of the terminal
     * @throws TerminalNotFoundException If the terminal cannot be loaded
     */
    public static boolean isTerminalLocked(Properties ctx, int terminalId) throws TerminalNotFoundException
    {
        MPOSTerminal terminal = getTerminal(ctx, terminalId);
        return terminal.isLocked();
    }
    
    /**
     * Update the lock status of the terminal in context
     * @param ctx Context
     * @param terminalId Terminal
     * @param trxName Transaction
     * @throws OperationException If terminal cannot be loaded/cannot update terminal status 
     */
    public static void setTerminalLockStatus(Properties ctx, int terminalId, boolean locked, String trxName) throws OperationException
    {
        MPOSTerminal terminal = loadTerminal(ctx);
        terminal.setLocked(locked);
        PoManager.save(terminal);
    }
    
    
    /**
     * Closes the till (Terminal). Does all the transfer of money that has been defined on the terminal
     * @param ctx Context
     * @param terminalId Terminal
     * @param cashJournal Cash Journal being used from the cash book
     * @param cashAmount Cash amount that need to be transfered
     * @param checkAmount Check amount that need to be transfered
     * @param cardAmount Card amount that need to be transfered
     * @param trxName Transaction
     * @throws OperationException If terminal is locked or transfers cannot be done
     */
    public static void closeTerminal(Properties ctx, int terminalId, MCash cashJournal, BigDecimal cashAmount, 
            BigDecimal checkAmount, BigDecimal cardAmount, String trxName) throws OperationException
    {
        if (isTerminalLocked(ctx, terminalId))
        {
            throw new TerminalLockedException("Terminal is locked");
        }
        
        MPOSTerminal terminal = loadTerminal(ctx, terminalId, trxName);
        int cashBookId = cashJournal.getC_CashBook_ID();
        MCashBook cashBook = new MCashBook(ctx, cashBookId, trxName);
        int currencyId = cashBook.getC_Currency_ID();
        Timestamp dateAcct = cashJournal.getStatementDate();
        
        if (MPOSTerminal.CASHBOOKTRANSFERTYPE_BankAccount.equals(terminal.getCashBookTransferType()))
        {
            int bankAccountId = terminal.getCashTransferBankAccount_ID();
            CashManager.createBankTransferCashEntry(ctx, cashJournal, cashAmount, bankAccountId, trxName);
        }
        else if (MPOSTerminal.CASHBOOKTRANSFERTYPE_CashBook.equals(terminal.getCashBookTransferType()))
        {
            int toCashBookId = terminal.getCashTransferCashBook_ID();
            CashTransferManager.transferFromJournalToCashBook(ctx, cashJournal, toCashBookId, currencyId, cashAmount, dateAcct, trxName);
        }
        
        if (MPOSTerminal.CHECKTRANSFERTYPE_BankAccount.equals(terminal.getCheckTransferType()))
        {
            int fromBankAccountId = terminal.getCheck_BankAccount_ID();
            int toBankAccountId = terminal.getCheckTransferBankAccount_ID();
            CashTransferManager.transferBankToBank(ctx, fromBankAccountId, toBankAccountId, currencyId, checkAmount, dateAcct, trxName);
        }
        else if (MPOSTerminal.CHECKTRANSFERTYPE_CashBook.equals(terminal.getCheckTransferType()))
        {
            int fromBankAccountId = terminal.getCheck_BankAccount_ID();
            int toCashBookId = terminal.getCheckTransferCashBook_ID();
            MCash toCashJournal = CashManager.getCashJournal(ctx, toCashBookId, trxName);
            CashManager.createBankTransferCashEntry(ctx, toCashJournal, checkAmount.negate(), fromBankAccountId, trxName);
        }
        
        
        if (MPOSTerminal.CARDTRANSFERTYPE_BankAccount.equals(terminal.getCardTransferType()))
        {
            int fromBankAccountId = terminal.getCard_BankAccount_ID();
            int toBankAccountId = terminal.getCard_BankAccount_ID();
            CashTransferManager.transferBankToBank(ctx, fromBankAccountId, toBankAccountId, currencyId, cardAmount, dateAcct, trxName);
        }
        else if (MPOSTerminal.CARDTRANSFERTYPE_CashBook.equals(terminal.getCardTransferType()))
        {
            int fromBankAccountId = terminal.getCard_BankAccount_ID();
            int toCashBookId = terminal.getCardTransferCashBook_ID();
            MCash toCashJournal = CashManager.getCashJournal(ctx, toCashBookId, trxName);
            CashManager.createBankTransferCashEntry(ctx, toCashJournal, cardAmount.negate(), fromBankAccountId, trxName);
        }
        
        if (terminal.isAutoLock())
        {
            // Lock the terminal
            terminal.setLocked(true);
        }
        
        PoManager.save(terminal);
    }
}
