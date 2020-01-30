package org.adempiere.pipo.handler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.MField;
import org.compiere.model.MTab;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

import javax.xml.transform.sax.TransformerHandler;
import java.util.Properties;

public class TabElementHandler extends GenericPOHandler {

    public void create(Properties ctx, TransformerHandler document) throws SAXException {
        int tabId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Tab_ID);
        PackOut packOut = (PackOut) ctx.get("PackOutProcess");
        if (packOut == null) {
            packOut = new PackOut();
            packOut.setLocalContext(ctx);
        }

        MTab tab = MTab.get(ctx, tabId);

        if(tab.getAD_Table_ID() > 0) {
            packOut.createTable(tab.getAD_Table_ID(), document);
        }
        //	Create Tab
        packOut.createGenericPO(document, tab, true, null);
        for(MField field : tab.getFields(true, null)) {
            packOut.createField(field.get_ID(), document);
        }

    }
}
