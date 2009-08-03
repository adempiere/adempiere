package org.adempiere.pipo.handler;

import java.sql.ResultSet;
import javax.xml.transform.sax.TransformerHandler;
import org.adempiere.pipo.PackOut;

public interface IPackOutHandler {
	
	public void packOut(PackOut packout, ResultSet header, ResultSet detail,TransformerHandler packOutDocument,TransformerHandler packageDocument,int recordId) throws Exception;

}


