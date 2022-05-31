package org.compiere.minigrid;

import org.compiere.model.MInfoColumn;
import org.compiere.model.MInfoWindow;

/**
 * Create object of embedded tab on info window.
 * 
 * @author Sachin Bhimani
 */
public class EmbedWinInfo
{
	private MInfoWindow	infowin;
	private IMiniTable	infoTbl;
	private String		infoSql;
	private String		linkColumnSql;
	private MInfoColumn	linkInfoColumn;
	private String		linkColumnName;

	public EmbedWinInfo(MInfoWindow iw, IMiniTable mt, String isql, String linkName, MInfoColumn linkColumn)
	{
		infowin = iw;
		infoTbl = mt;
		infoSql = isql;
		linkColumnSql = linkName;
		linkInfoColumn = linkColumn;
		linkColumnName = linkColumn.getName();
	}

	public MInfoWindow getInfowin()
	{
		return infowin;
	}

	public IMiniTable getInfoTbl()
	{
		return infoTbl;
	}

	public String getInfoSql()
	{
		return infoSql;
	}

	public MInfoColumn getLinkColumn()
	{
		return linkInfoColumn;
	}

	public String getLinkColumnSql()
	{
		return linkColumnSql;
	}

	public String getLinkColumnName()
	{
		return linkColumnName;
	}

}
