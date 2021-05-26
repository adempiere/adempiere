package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Test;


class IT_MAcctSchemaProvider extends CommonGWSetup {

    MAcctSchemaProvider provider = new MAcctSchemaProvider();

    @Test
    final void testGetAcctSchema() {
        
        int schemaId = 0;
        MAcctSchema[] schemaArray = provider.getAcctSchemas(ctx, schemaId, trxName);
        assertNotNull(schemaArray);
        assertEquals(1, schemaArray.length);
        
    }
    
    @Test
    final void testGet() {

        int schemaId = provider.getAcctSchemas(ctx, 0, trxName)[0].get_ID();
        MAcctSchema schema = provider.get(ctx, schemaId, trxName);
        assertNotNull(schema);
        assertNotEquals(0, schema.get_ID());
        
    }
    

}
