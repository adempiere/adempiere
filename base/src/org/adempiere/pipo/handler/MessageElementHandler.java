/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com
 * Contributor(s):	Low Heng Sin hengsin@avantz.com
 * 					Teo Sarca, teo.sarca@gmail.com
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.I_AD_Message;
import org.compiere.model.MMessage;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Message;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

public class MessageElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int messageId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Message_ID);
		int entityTypeId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_EntityType_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		String whereClause;
		Object[] params;
		if (messageId > 0) {
			whereClause = X_AD_Message.COLUMNNAME_AD_Message_ID+"=?";
			params = new Object[]{messageId};
		} else if (entityTypeId > 0) {
			whereClause = " EXISTS (SELECT 1 FROM AD_EntityType et"
				+" WHERE et.AD_EntityType_ID=? AND et.EntityType=AD_Message.EntityType)";
			params = new Object[]{entityTypeId};
		} else {
			throw new IllegalArgumentException("@AD_Message_ID@ / @AD_EntityType_ID@");
		}
		//	Iterate
		List<MMessage> list = new Query(ctx, X_AD_Message.Table_Name, whereClause, null)
		.setParameters(params)
		.setOrderBy(MMessage.COLUMNNAME_AD_Message_ID)
		.list();
		for(MMessage message : list) {
			packOut.createGenericPO(document, I_AD_Message.Table_ID, message.getAD_Message_ID(), true, null);
		}
	}
}
