/******************************************************************************
 *  Product: Posterita Web-Based POS and Adempiere Plugin                     *
 *  Copyright (C) 2008  Posterita Ltd                                         *
 *  This file is part of POSterita                                            *
 *                                                                            *
 *  POSterita is free software; you can redistribute it and/or modify         *
 *  it under the terms of the GNU General Public License as published by      *
 *  the Free Software Foundation; either version 2 of the License, or         *
 *  (at your option) any later version.                                       *
 *                                                                            *
 *  This program is distributed in the hope that it will be useful,           *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of            *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             *
 *  GNU General Public License for more details.                              *
 *                                                                            *
 *  You should have received a copy of the GNU General Public License along   *
 *  with this program; if not, write to the Free Software Foundation, Inc.,   *
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.               *
 *****************************************************************************/
package org.posterita.beans;

import java.sql.Timestamp;

/**
 * @author Ashley G Ramdass Apr 14, 2008
 */
public class TerminalBean extends UDIBean
{
    protected Integer terminalId;
    protected Integer purchasePriceListId;
    protected Integer salesPriceListId;

    protected String  cashbookTransferType;
    protected String  checkTransferType;
    protected String  cardTransferType;
    protected Integer cardBankAccountId;
    protected Integer checkBankAccountId;
    protected Integer templateBPartnerId;
    protected Integer cashbookTransferCashbookId;
    protected Integer cashbookTransferBankAccountId;
    protected Integer checkTransferCashbookId;
    protected Integer checkTransferBankAccountId;
    protected Integer cardTransferCashbookId;
    protected Integer cardTransferBankAccountId;
    protected Boolean autoLock;
    protected Boolean locked;
    protected Integer lockingTime;
    protected Timestamp lastLockTime;
    protected Timestamp unlockingTime;
    
    private String orgName;
    private String poPriceList;
    private String soPriceList;
    private Boolean isCurrentTerminal = false;

    public Integer getTerminalId()
    {
        return (terminalId == null ? 0 : terminalId) ;
    }

    public void setTerminalId(Integer terminalId)
    {
        this.terminalId = terminalId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getOrgId()
    {
        return (orgId == null ? 0 : orgId);
    }

    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }

    public Integer getBpartnerId()
    {
        return (bpartnerId == null ? 0 : bpartnerId);
    }

    public void setBpartnerId(Integer bpartnerId)
    {
        this.bpartnerId = bpartnerId;
    }

    public Integer getWarehouseId()
    {
        return (warehouseId == null ? 0 : warehouseId);
    }

    public void setWarehouseId(Integer warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Integer getPurchasePriceListId()
    {
        return (purchasePriceListId == null ? 0 : purchasePriceListId);
    }

    public void setPurchasePriceListId(Integer purchasePriceListId)
    {
        this.purchasePriceListId = purchasePriceListId;
    }

    public Integer getSalesPriceListId()
    {
        return (salesPriceListId == null ? 0 : salesPriceListId);
    }

    public void setSalesPriceListId(Integer salesPriceListId)
    {
        this.salesPriceListId = salesPriceListId;
    }

    public Integer getCashBookId()
    {
        return (cashBookId == null ? 0 : cashBookId);
    }

    public void setCashBookId(Integer cashBookId)
    {
        this.cashBookId = cashBookId;
    }

    public Integer getCheckBankAccountId()
    {
        return (checkBankAccountId == null ? 0 : checkBankAccountId);
    }

    public void setCheckBankAccountId(Integer checkBankAccountId)
    {
        this.checkBankAccountId = checkBankAccountId;
    }

    public Boolean getAutoLock()
    {
        return autoLock;
    }

    public void setAutoLock(Boolean autoLock)
    {
        this.autoLock = autoLock;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getTemplateBPartnerId()
    {
        return (templateBPartnerId == null ? 0 : templateBPartnerId);
    }

    public void setTemplateBPartnerId(Integer templateBPartnerId)
    {
        this.templateBPartnerId = templateBPartnerId;
    }

    public Integer getCashbookTransferCashbookId()
    {
        return (cashbookTransferCashbookId == null ? 0 : cashbookTransferCashbookId);
    }

    public void setCashbookTransferCashbookId(Integer cashTransferCashbookId)
    {
        this.cashbookTransferCashbookId = cashTransferCashbookId;
    }

    public Integer getCashbookTransferBankAccountId()
    {
        return cashbookTransferBankAccountId;
    }

    public void setCashbookTransferBankAccountId(Integer cashTransferBankAccountId)
    {
        this.cashbookTransferBankAccountId = cashTransferBankAccountId;
    }

    public Integer getCheckTransferCashbookId()
    {
        return checkTransferCashbookId;
    }

    public void setCheckTransferCashbookId(Integer checkTransferCashbookId)
    {
        this.checkTransferCashbookId = checkTransferCashbookId;
    }

    public Integer getCheckTransferBankAccountId()
    {
        return checkTransferBankAccountId;
    }

    public void setCheckTransferBankAccountId(Integer checkTransferBankAccountId)
    {
        this.checkTransferBankAccountId = checkTransferBankAccountId;
    }

    public Integer getCardTransferCashbookId()
    {
        return cardTransferCashbookId;
    }

    public void setCardTransferCashbookId(Integer cardTransferCashbookId)
    {
        this.cardTransferCashbookId = cardTransferCashbookId;
    }

    public Integer getCardTransferBankAccountId()
    {
        return (cardTransferBankAccountId == null ? 0 : cardTransferBankAccountId);
    }

    public void setCardTransferBankAccountId(
            Integer cardTransferBankAccountId)
    {
        this.cardTransferBankAccountId = cardTransferBankAccountId;
    }

    public Boolean getLocked()
    {
        return locked;
    }

    public void setLocked(Boolean locked)
    {
        this.locked = locked;
    }

    public Integer getCardBankAccountId()
    {
        return (cardBankAccountId == null ? 0 : cardBankAccountId);
    }

    public void setCardBankAccountId(Integer cardBankAccountId)
    {
        this.cardBankAccountId = cardBankAccountId;
    }

    public String getCashbookTransferType()
    {
        return cashbookTransferType;
    }

    public void setCashbookTransferType(String cashbookTransferType)
    {
        this.cashbookTransferType = cashbookTransferType;
    }

    public String getCheckTransferType()
    {
        return checkTransferType;
    }

    public void setCheckTransferType(String checkTransferType)
    {
        this.checkTransferType = checkTransferType;
    }

    public String getCardTransferType()
    {
        return cardTransferType;
    }

    public void setCardTransferType(String cardTransferType)
    {
        this.cardTransferType = cardTransferType;
    }

    /**
     * @return the lockingTime
     */
    public Integer getLockingTime()
    {
        return lockingTime;
    }

    /**
     * @param lockingTime the lockingTime to set
     */
    public void setLockingTime(Integer lockingTime)
    {
        this.lockingTime = lockingTime;
    }

    /**
     * @return the orgName
     */
    public String getOrgName()
    {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    /**
     * @return the poPriceList
     */
    public String getPoPriceList()
    {
        return poPriceList;
    }

    /**
     * @param poPriceList the poPriceList to set
     */
    public void setPoPriceList(String poPriceList)
    {
        this.poPriceList = poPriceList;
    }

    /**
     * @return the soPriceList
     */
    public String getSoPriceList()
    {
        return soPriceList;
    }

    /**
     * @param soPriceList the soPriceList to set
     */
    public void setSoPriceList(String soPriceList)
    {
        this.soPriceList = soPriceList;
    }

    /**
     * @return the lastLockTime
     */
    public Timestamp getLastLockTime()
    {
        return lastLockTime;
    }

    /**
     * @param lastLockTime the lastLockTime to set
     */
    public void setLastLockTime(Timestamp lastLockTime)
    {
        this.lastLockTime = lastLockTime;
    }

    /**
     * @return the unlockingTime
     */
    public Timestamp getUnlockingTime()
    {
        return unlockingTime;
    }

    /**
     * @param unlockingTime the unlockingTime to set
     */
    public void setUnlockingTime(Timestamp unlockingTime)
    {
        this.unlockingTime = unlockingTime;
    }

    /**
     * @return The isCurrentTerminal
     */
    public Boolean getIsCurrentTerminal()
    {
        return isCurrentTerminal;
    }

    /**
     * @param isCurrentTerminal The isCurrentTerminal to set
     */
    public void setIsCurrentTerminal(Boolean isCurrentTerminal)
    {
        this.isCurrentTerminal = isCurrentTerminal;
    }
}
