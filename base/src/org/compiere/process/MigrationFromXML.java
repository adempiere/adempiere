package org.compiere.process;

import java.io.File;
import java.io.FilenameFilter;

import org.adempiere.ad.migration.xml.XMLLoader;
import org.adempiere.exceptions.AdempiereException;

public class MigrationFromXML extends SvrProcess
{

	private String fileName;

	@Override
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			if (para.getParameterName().equals("FileName"))
			{
				fileName = (String)para.getParameter();
			}
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		final File file = new File(fileName);
		if (!file.exists())
		{
			throw new AdempiereException("@FileNotFound@");
		}

		final File[] migrationFiles;
		if (file.isDirectory())
		{
			migrationFiles = file.listFiles(new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					return name.endsWith(".xml");
				}
			});
		}
		else
		{
			migrationFiles = new File[] { file };
		}

		for (final File migrationFile : migrationFiles)
		{
			final XMLLoader loader = new XMLLoader(migrationFile.getAbsolutePath());
			loader.load(get_TrxName());
		}

		return "Import complete";
	}
}
