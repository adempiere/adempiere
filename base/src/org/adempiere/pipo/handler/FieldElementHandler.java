package org.adempiere.pipo.handler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

import javax.xml.transform.sax.TransformerHandler;
import java.util.Properties;

public class FieldElementHandler extends GenericPOHandler {


    public void create(Properties ctx, TransformerHandler document) throws SAXException {
        int fieldId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Field_ID);
        PackOut packOut = (PackOut) ctx.get("PackOutProcess");
        if (packOut == null) {
            packOut = new PackOut();
            packOut.setLocalContext(ctx);
        }

        MField field = new Query(ctx, I_AD_Field.Table_Name, I_AD_Field.COLUMNNAME_AD_Field_ID + "=?", null)
                .setParameters(fieldId).first();

        if (field.getAD_Column_ID() > 0) {
            packOut.createColumn(field.getAD_Column_ID(), document);
        }

        packOut.createGenericPO(document, I_AD_Field.Table_ID, field.getAD_Field_ID(), true, null);
    }
}
