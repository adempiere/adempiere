/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the GNU General Public               *
 * License as published                                                       *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_AD_Ref_List;
import org.adempiere.core.domains.models.I_R_MailText;
import org.adempiere.core.domains.models.X_R_NoticeTemplate;
import org.compiere.model.MMailText;
import org.compiere.model.MRefList;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * 	Callout Mail Template
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *			<li> FR Mail Template for distint events
 */
public class MRNoticeTemplate extends X_R_NoticeTemplate {

	public MRNoticeTemplate(Properties ctx, int requestTemplateId, String trxName) {
		super(ctx, requestTemplateId, trxName);
	}

	public MRNoticeTemplate(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 572121553773316014L;
	
	   /**
     * Cache
     */
    private static CCache<Integer, MRNoticeTemplate> cache = new CCache<Integer, MRNoticeTemplate>(Table_Name, 100);
    /**
     * Cache by Value
     */
    private static CCache<String, MRNoticeTemplate> cacheValue = new CCache<String, MRNoticeTemplate>(Table_Name + "_Value", 100);
    /**
     * Cache for Mail Text
     */
    private static CCache<String, MMailText> cacheMailTextValue = new CCache<String, MMailText>(I_R_MailText.Table_Name + "_Template", 100);
    /**
     * Get Template by ID
     * @param ctx
     * @param requestTemplateId
     * @return
     */
    public static MRNoticeTemplate getById(Properties ctx, int requestTemplateId) {
        if (requestTemplateId <= 0)
            return null;
        //
        MRNoticeTemplate requestTemplate = cache.get(requestTemplateId);
        if (requestTemplate != null)
            return requestTemplate;
        //
        requestTemplate = new MRNoticeTemplate(ctx, requestTemplateId, null);
        if (requestTemplate.get_ID() == requestTemplateId)
            cache.put(requestTemplateId, requestTemplate);
        else
            requestTemplate = null;

        return requestTemplate;
    }
    
    /**
     * Get Concept by Value
     *
     * @param ctx
     * @param templateType
     * @return
     */
    public static MRNoticeTemplate getByType(Properties ctx, String templateType) {
        if (Util.isEmpty(templateType, true))
            return null;

        int clientId = Env.getAD_Client_ID(ctx);
        final String key = clientId + "#" + templateType;
        MRNoticeTemplate requestTemplate = cacheValue.get(key);
        if (requestTemplate != null)
            return requestTemplate;

        final String whereClause = COLUMNNAME_TemplateType + "=? AND AD_Client_ID IN (?,?)";
        requestTemplate = new Query(ctx, Table_Name, whereClause, null)
                .setParameters(templateType, 0, clientId)
                .setOnlyActiveRecords(true)
                .setOrderBy("AD_Client_ID DESC")
                .first();
        if (requestTemplate != null) {
            cacheValue.put(key, requestTemplate);
            cache.put(requestTemplate.get_ID(), requestTemplate);
        }
        return requestTemplate;
    }

    /**
     * Get Mail Template from: (Client + Template Type + Event Type)
     * @param ctx
     * @param templateType
     * @param eventType
     * @return
     */
    public static MMailText getMailTemplate(Properties ctx, String templateType, String eventType) {
    	if (Util.isEmpty(templateType, true)
    			|| Util.isEmpty(eventType, true)) {
    		return null;
    	}
        int clientId = Env.getAD_Client_ID(ctx);
        final String key = clientId + "|" + templateType + "|" + eventType;
        MMailText mailTemplate = cacheMailTextValue.get(key);
        if (mailTemplate != null) {
			mailTemplate.clear();
			return mailTemplate;
        }
        String whereClause = COLUMNNAME_AD_Client_ID + " IN(?,?) "
        		+ "AND EXISTS(SELECT 1 FROM R_NoticeTemplate rt "
        		+ "INNER JOIN R_NoticeTemplateEvent rte ON(rte.R_NoticeTemplate_ID = rt.R_NoticeTemplate_ID) "
        		+ "WHERE rte.R_MailText_ID = R_MailText.R_MailText_ID "
        		+ "AND rte.EventType = ? "
        		+ "AND rt.TemplateType = ? "
        		+ "AND rt.IsActive = 'Y')";
        mailTemplate = new Query(ctx, I_R_MailText.Table_Name, whereClause, null)
                .setParameters(0, clientId, eventType, templateType)
                .setOnlyActiveRecords(true)
                .setOrderBy("AD_Client_ID DESC")
                .first();
        if (mailTemplate != null) {
        	cacheMailTextValue.put(key, mailTemplate);
        }
        //	Mail Template
        return mailTemplate;
    }
    
    @Override
    protected boolean afterSave(boolean newRecord, boolean success) {
    	if(newRecord) {
    		new Query(getCtx(), I_AD_Ref_List.Table_Name, I_AD_Ref_List.COLUMNNAME_AD_Reference_ID + " = ?", get_TrxName())
    			.setParameters(MRNoticeTemplateEvent.EVENTTYPE_AD_Reference_ID)
    			.setOnlyActiveRecords(true)
    			.setOrderBy(I_AD_Ref_List.COLUMNNAME_Value)
    			.<MRefList>list()
    			.forEach(reference -> {
    				MMailText mailText = new MMailText(getCtx(), 0, get_TrxName());
    				mailText.setName(reference.getName());
    				int index = reference.getName().indexOf(":");
    				if(index > 0
    						&& index < reference.getName().length()) {
    					mailText.setMailHeader("@DocumentNo@ - @Subject@ " + reference.getName().substring(index + 1));
    				}
    				mailText.setMailText(reference.getDescription());
    				mailText.setIsHtml(true);
    				mailText.saveEx();
    				//	Update Translation
    				updateTranslations(mailText.getR_MailText_ID(), reference);
    				//	Create Template for Event
    				MRNoticeTemplateEvent noticeEvent = new MRNoticeTemplateEvent(getCtx(), 0, get_TrxName());
    				noticeEvent.setR_NoticeTemplate_ID(getR_NoticeTemplate_ID());
    				noticeEvent.setEventType(reference.getValue());
    				noticeEvent.setR_MailText_ID(mailText.getR_MailText_ID());
    				noticeEvent.saveEx();
    			});
    	}
    	//	
    	return true;
    }
    
	/**
	 * Update Mail translation
	 * @param mailTextId
	 * @param reference
	 */
	private void updateTranslations(int mailTextId, MRefList reference) {
		String tableName = I_R_MailText.Table_Name + "_Trl";
		//
		List<PO> translationList = new Query(getCtx(), tableName,
				I_R_MailText.COLUMNNAME_R_MailText_ID + " = ?", get_TrxName())
			.setParameters(mailTextId)
			.<PO>list();

		if(translationList == null
				|| translationList.size() == 0)
			return;
		//	Set Values
		for(PO translation : translationList) {
			String language = translation.get_ValueAsString("AD_Language");
			String name = reference.get_Translation(I_R_MailText.COLUMNNAME_Name, language);
			String description = reference.get_Translation(I_AD_Ref_List.COLUMNNAME_Description, language);
			if(Util.isEmpty(name)
					|| Util.isEmpty(description)) {
				continue;
			}
			translation.set_ValueOfColumn(I_R_MailText.COLUMNNAME_Name, name);
			int index = name.indexOf(":");
			if(index > 0
					&& index < name.length()) {
				translation.set_ValueOfColumn(I_R_MailText.COLUMNNAME_MailHeader, "@DocumentNo@ - @Subject@ " + name.substring(index + 1));
			}
			translation.set_ValueOfColumn(I_R_MailText.COLUMNNAME_MailText, description);
			translation.saveEx();
		}
	}	//	updateTranslations
}
