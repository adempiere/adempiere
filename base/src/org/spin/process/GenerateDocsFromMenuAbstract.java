/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Generate Documentation from Menu)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateDocsFromMenuAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "GenerateDocsFromMenu";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Documentation from Menu";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54128;
	/**	Parameter Name for Export Format	*/
	public static final String DOCSEXPORTFORMAT = "DocsExportFormat";
	/**	Parameter Name for File_Directory	*/
	public static final String FILE_DIRECTORY = "File_Directory";
	/**	Parameter Value for Export Format	*/
	private String docsExportFormat;
	/**	Parameter Value for File_Directory	*/
	private String directory;

	@Override
	protected void prepare() {
		docsExportFormat = getParameterAsString(DOCSEXPORTFORMAT);
		directory = getParameterAsString(FILE_DIRECTORY);
	}

	/**	 Getter Parameter Value for Export Format	*/
	protected String getDocsExportFormat() {
		return docsExportFormat;
	}

	/**	 Setter Parameter Value for Export Format	*/
	protected void setDocsExportFormat(String docsExportFormat) {
		this.docsExportFormat = docsExportFormat;
	}

	/**	 Getter Parameter Value for File_Directory	*/
	protected String getDirectory() {
		return directory;
	}

	/**	 Setter Parameter Value for File_Directory	*/
	protected void setDirectory(String directory) {
		this.directory = directory;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}