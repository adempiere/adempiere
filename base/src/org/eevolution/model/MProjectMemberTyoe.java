package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MProjectMemberTyoe extends X_C_ProjectMemberType{
    public MProjectMemberTyoe(Properties ctx, int C_ProjectMemberType_ID, String trxName) {
        super(ctx, C_ProjectMemberType_ID, trxName);
    }

    public MProjectMemberTyoe(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
