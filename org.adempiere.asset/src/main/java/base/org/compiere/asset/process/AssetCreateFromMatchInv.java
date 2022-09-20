package org.compiere.asset.process;

import java.util.logging.Level;

import org.compiere.model.MMatchInv;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.asset.exceptions.AssetException;
import org.compiere.asset.model.MAssetAddition;



/**
 * Create asset from match invoice process
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class AssetCreateFromMatchInv extends SvrProcess {
	private int p_M_MatchInv_ID = -1;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_MatchInv_ID"))
				p_M_MatchInv_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "@UnknownParameter@ " + name);
		}
	}	//	prepare 
	
	protected String doIt() throws Exception
	{
		MMatchInv match = new MMatchInv(getCtx(), p_M_MatchInv_ID, get_TrxName());
		if (match == null || match.get_ID() <= 0) {
			throw new AssetException("@NotFound@ @M_MatchInv_ID@=" + match + "(ID="+p_M_MatchInv_ID+")");
		}
		MAssetAddition assetAdd = MAssetAddition.createAsset(match);
		
		return "@A_Asset_Addition_ID@ - " + assetAdd;
	}
}
