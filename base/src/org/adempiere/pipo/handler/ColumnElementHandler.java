package org.adempiere.pipo.handler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.MColumn;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

import javax.xml.transform.sax.TransformerHandler;
import java.util.Properties;

public class ColumnElementHandler extends GenericPOHandler {


    public void create(Properties ctx, TransformerHandler document) throws SAXException {
        int columnId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Column_ID);
        PackOut packOut = (PackOut) ctx.get("PackOutProcess");
        if (packOut == null) {
            packOut = new PackOut();
            packOut.setLocalContext(ctx);
        }

        MColumn column = MColumn.get(ctx, columnId);

        if(column.getAD_Reference_Value_ID() > 0) {
            packOut.createReference(column.getAD_Reference_Value_ID(), document);
        }
        //	Create Process
        if(column.getAD_Process_ID() > 0) {
            packOut.createProcess(column.getAD_Process_ID(), document);
        }
        packOut.createGenericPO(document, column, true, null);
    }
}
