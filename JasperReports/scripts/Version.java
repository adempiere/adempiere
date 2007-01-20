package JasperReports.scripts;

import org.compiere.*;


class Version
{
	public static void  main(String args[])
	{
		String version[];
		version = org.compiere.Compiere.MAIN_VERSION.split(" ");
		System.out.println(version[1]); 
	}
}
