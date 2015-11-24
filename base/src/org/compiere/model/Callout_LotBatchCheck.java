

	package org.compiere.model;

    import org.adempiere.exceptions.AdempiereException;

    import java.util.Properties;

    /**
     * @author Abhijeet Singh
     *
     */

    public class Callout_LotBatchCheck extends CalloutEngine {
        String Lot;
        String SerialNo;
        String MandatoryType;

        public Callout_LotBatchCheck() {
            // TODO Auto-generated constructor stub
        }

        public String LotCheck (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
        {
            if ( isCalloutActive()) 	{
                return "";
            }

            SerialNo = (String)mTab.getValue(X_M_AttributeSet.COLUMNNAME_IsSerNo).toString();
            MandatoryType=(String)mTab.getValue(X_M_AttributeSet.COLUMNNAME_MandatoryType);
            Lot = (String)value.toString();
            if(Lot.equals("true") &&  MandatoryType!=null)
            {
                if(MandatoryType.equals("S"))
                {
                mTab.setValue(X_M_AttributeSet.COLUMNNAME_IsLot, "false");
                throw new AdempiereException("Lot and When Shipping Mandatory Type can not be Enabled simultaneously.Choose any one of them!");

                }
            }
            else if(SerialNo.equals("true") && Lot.equals("true"))
            {
                mTab.setValue(X_M_AttributeSet.COLUMNNAME_IsLot, "false");
                throw new AdempiereException("Serial No. and Lot can not be Enabled simultaneously.Choose any one of them!");
            }

            return "";
        }


        public String MandatoryTypeCheck (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
        {
            if ( isCalloutActive() ||  (String)value == null)	{
                return "";
            }

            SerialNo = (String)mTab.getValue(X_M_AttributeSet.COLUMNNAME_IsSerNo).toString();
            Lot = (String)mTab.getValue(X_M_AttributeSet.COLUMNNAME_IsLot).toString();
            MandatoryType=(String)value.toString();
            if(Lot.equals("true") &&  MandatoryType.equals("S"))
            {
                //mTab.setValue(X_M_AttributeSet.COLUMNNAME_MandatoryType, "N");
                mTab.setValue(X_M_AttributeSet.COLUMNNAME_IsLot, "false");
                throw new AdempiereException("Lot and When Shipping Mandatory Type can not be Enabled simultaneously.Choose any one of them!");

            }
            else if(SerialNo.equals("true") && MandatoryType.equals("S"))
            {
                mTab.setValue(X_M_AttributeSet.COLUMNNAME_IsSerNo, "false");
                throw new AdempiereException("Serial No. and Shipping Mandatory Type can not be Enabled simultaneously.Choose any one of them!");
            }


            return "";
        }

        public String SerialNoCheck (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
        {
            if ( isCalloutActive()) 	{
                return "";
            }

            Lot = (String)mTab.getValue(X_M_AttributeSet.COLUMNNAME_IsLot).toString();
            MandatoryType=(String)mTab.getValue(X_M_AttributeSet.COLUMNNAME_MandatoryType);
            SerialNo = (String)value.toString();
            if(SerialNo.equals("true") &&  MandatoryType!=null)
            {
                if(MandatoryType.equals("S"))
                {
                mTab.setValue(X_M_AttributeSet.COLUMNNAME_IsSerNo, "false");
                throw new AdempiereException("Serial No. and When Shipping Mandatory Type can not be Enabled simultaneously.Choose any one of them!");

                }


            }
            else if(SerialNo.equals("true") && Lot.equals("true"))
            {
                mTab.setValue(X_M_AttributeSet.COLUMNNAME_IsSerNo, "false");
                throw new AdempiereException("Serial No. and Lot can not be Enabled simultaneously.Choose any one of them!");
            }

            return "";
        }





    }


