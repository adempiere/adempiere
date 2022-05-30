/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.server;

import java.sql.Timestamp;
import java.util.Optional;

import org.compiere.acct.SessionPoster;
import org.compiere.model.MAcctProcessor;
import org.compiere.model.MAcctProcessorLog;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaProvider;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;

/**
 * Accounting Processor
 * 
 * @author Jorg Janke
 * @version $Id: AcctProcessor.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class AcctProcessor extends AdempiereServer {

    private MAcctProcessor model = null;
    private MAcctSchemaProvider provider = new MAcctSchemaProvider();
    private SessionPoster sessionPoster = new SessionPoster();
    private StringBuilder summary = new StringBuilder("Just initialized ");

    /**
     * Accounting Processor
     * 
     * @param model the MAcctProcessor model that extends AdempiereProcessor 
     */
    public AcctProcessor(MAcctProcessor model) {
    
        super(model, 30); // 30 seconds delay
        setModel(model);
    
    }

    MAcctSchemaProvider getAcctSchemaProvider() {

        return provider;

    }

    private MAcctSchema[] getAcctSchemas() {
    
        return getAcctSchemaProvider().getAcctSchemas(getCtx(),
                getAD_Client_ID(),
                getC_AcctSchema_ID(), null);
    
    }

    int getC_AcctSchema_ID() {

        return getModel().getC_AcctSchema_ID();

    }

    int getAD_Client_ID() {

        return getModel().getAD_Client_ID();

    }

    @Override
    public MAcctProcessor getModel() {

        return model;

    }

    /**
     * Get Server Info
     * 
     * @return info
     */
    public String getServerInfo() {
    
        return "#" + p_runCount + " - Last=" + Optional.ofNullable(summary.toString()).orElse("");
    
    }

    SessionPoster getSessionPoster() {
    
        return sessionPoster;
    
    }

    private void setModel(MAcctProcessor model) {
    
        this.model = model;
    
    }

    void updateLog(String results) {
        
        summary = new StringBuilder(results);
        
        int no = getModel().deleteLog();
        summary.append("Logs deleted=").append(no);
        if (getModel().get_TrxName() == null) {
            Trx.run(this::addAcctProcessorLog);
        } else {
            addAcctProcessorLog(getModel().get_TrxName());
        }
    }

    /**
     * Add Acct Processor Log
     * @param trxName
     */
    private void addAcctProcessorLog(String trxName) {
        MAcctProcessorLog acctProcessorLog = new MAcctProcessorLog(getModel(), summary.toString(), trxName);
        acctProcessorLog.setReference("#" + p_runCount + " - " + TimeUtil.formatElapsed(new Timestamp(p_startWork)));
        acctProcessorLog.saveEx();
    }

    protected void doWork() {

        String results = getSessionPoster()
                .withAccountingSchemas(getAcctSchemas())
                .post();

        updateLog(results);

    } 

} 
