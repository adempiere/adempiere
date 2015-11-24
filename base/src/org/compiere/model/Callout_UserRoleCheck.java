	package org.compiere.model;

    import org.adempiere.exceptions.AdempiereException;

    import java.util.Properties;

    /**
     * @author Abhijeet Singh
     *
     */

    public class Callout_UserRoleCheck extends CalloutEngine {

        String User,Role;

        public Callout_UserRoleCheck() {
            // TODO Auto-generated constructor stub
        }

        public String RoleCheck (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
        {
            if ( isCalloutActive())
            {
                return "";
            }

            if(mTab.getValue(X_AD_UserDef_Win.COLUMNNAME_AD_User_ID)!=null)
            {
            User=mTab.getValue(X_AD_UserDef_Win.COLUMNNAME_AD_User_ID).toString();
            }
            else
            {
                User=(String)mTab.getValue(X_AD_UserDef_Win.COLUMNNAME_AD_User_ID);
            }

            if(User!=null && mTab.getValue(X_AD_UserDef_Win.COLUMNNAME_AD_Role_ID)!=null)
            {
                mTab.setValue(X_AD_UserDef_Win.COLUMNNAME_AD_Role_ID, "false");
                throw new AdempiereException("Role and User can not be Enabled simultaneously.Choose any one of them!");
            }

            return "";
        }


        public String UserCheck (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
        {
            if ( isCalloutActive()) 	{
                return "";
            }

            if(mTab.getValue(X_AD_UserDef_Win.COLUMNNAME_AD_Role_ID)!=null)
            {
            Role=mTab.getValue(X_AD_UserDef_Win.COLUMNNAME_AD_Role_ID).toString();
            }
            else
            {
                Role=(String)mTab.getValue(X_AD_UserDef_Win.COLUMNNAME_AD_Role_ID);
            }

            if(Role!=null && mTab.getValue(X_AD_UserDef_Win.COLUMNNAME_AD_User_ID)!=null)
            {
                mTab.setValue(X_AD_UserDef_Win.COLUMNNAME_AD_User_ID, "false");
                throw new AdempiereException("Role and User can not be Enabled simultaneously.Choose any one of them!");
            }

            return "";
        }
}


