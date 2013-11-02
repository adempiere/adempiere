package org.compiere.process;

import java.sql.PreparedStatement;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class UniversalSubstitution extends SvrProcess {

	int productId = 0;
	int replacementId = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("M_Product_ID"))
				productId  = para[i].getParameterAsInt();
			else if (name.equals("Substitute_ID"))
				replacementId =para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
	}

	@Override
	protected String doIt() throws Exception {

		if ( productId == 0 || replacementId == 0 )
			throw new AdempiereException("Product and replacement product required");
		
		
	String update = "UPDATE M_Product_BOM bb " +
		"SET M_PRODUCTBOM_ID = ? " +
		"WHERE bb.M_PRODUCTBOM_ID = ?";
		
		PreparedStatement pstmt = DB.prepareStatement(update, get_TrxName());
		pstmt.setInt(1, replacementId);
		pstmt.setInt(2, productId);
		
		int count = pstmt.executeUpdate();

		return count + " BOM products updated";
	}

}
