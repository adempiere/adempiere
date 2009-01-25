package com._3e.ADInterface;

import java.util.Properties;

import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.util.CLogger;
import org.compiere.util.Msg;


public class ADDataStatusListener implements DataStatusListener {

	protected static CLogger	log = CLogger.getCLogger( ADDataStatusListener.class);
	public String m_status_data; 
	public boolean m_is_status_error;
	public String m_error_message;
	public String m_error_info;
	public boolean m_is_error;
	private Properties m_ctx;
	
	public ADDataStatusListener( Properties _ctx )
	{
		m_ctx = _ctx;
		m_is_status_error = false;
		m_is_error = false;
	}
	
	public void dataStatusChanged(DataStatusEvent e)
	{
		this.m_status_data = e.getInfo();
		
		
		String dbInfo = e.getMessage();
		//if (curTab != null && curTab.isQueryActive())
		//	dbInfo = "[ " + dbInfo + " ]";
		//m_status_db = dbInfo;
		
		log.info( "dataStatusChanged : "+e.getMessage() +" "+e.getInfo()+" "+e.getAD_Message()+" ");
		//System.out.println( "dataStatusChanged : "+e.getMessage() +" "+e.getInfo()+" "+e.getAD_Message()+" ");

		//	Set Message / Info
		if (e.getAD_Message() != null || e.getInfo() != null)
		{
			StringBuffer sb = new StringBuffer();
			String msg = e.getMessage();
			if (msg != null && msg.length() > 0)
				sb.append(Msg.getMsg(m_ctx, e.getAD_Message()));
			String info = e.getInfo();
			if (info != null && info.length() > 0)
			{
				if (sb.length() > 0 && !sb.toString().trim().endsWith(":"))
					sb.append(": ");
				sb.append(info);
			}
			if (sb.length() > 0)
			{
				int pos = sb.indexOf("\n");
				if (pos != -1)  // replace CR/NL
					sb.replace(pos, pos+1, " - ");
				setStatusLine (sb.toString (), e.isError ());
			}
		}

		//  Confirm Error
		if (e.isError() && !e.isConfirmed())
		{
			m_is_error = true;
			m_error_message = e.getAD_Message();
			m_error_info = e.getInfo();			
			//setStatusError( Msg.getMsg(m_ctx, e.getAD_Message()), Msg.getMsg(m_ctx, e.getInfo()));
			e.setConfirmed(true);   //  show just once - if GridTable.setCurrentRow is involved the status event is re-issued			
		} else
			m_is_error = false;
	}
	
	public void setStatusLine( String msg, boolean is_error )
	{
		this.m_status_data = msg ;
		this.m_is_status_error = is_error;
		//else
		//	this.m_status_data = " <span class='errormsg'>"+msg+"</span> ";
	}
		
	


}
