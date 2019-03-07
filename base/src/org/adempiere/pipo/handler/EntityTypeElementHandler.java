/**
 * 
 */
package org.adempiere.pipo.handler;

import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.I_AD_EntityType;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 * @author Teo Sarca
 * 			<li>FR [ 2847694 ] 2pack import/export AD_EntityType functionality						 
 * 				https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2847694&group_id=176962
 */
public class EntityTypeElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int entityTypeId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_EntityType_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	Task
		packOut.createGenericPO(document, I_AD_EntityType.Table_ID, entityTypeId, true, null);
	}
}
