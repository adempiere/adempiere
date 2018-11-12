/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 * @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]                  *
 *****************************************************************************/
package org.compiere.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;


/**
 *  Field Model Value Object
 *
 *  @author Jorg Janke
 *  @author Victor Perez , e-Evolution.SC FR [ 1757088 ] , [1877902] Implement JSR 223 Scripting APIs to Callout
 *    <li>Implement embedded or horizontal tab panel https://adempiere.atlassian.net/browse/ADEMPIERE-319
 *  @author Carlos Ruiz, qss FR [1877902]
 *  @author Juan David Arboleda (arboleda), GlobalQSS, [ 1795398 ] Process Parameter: add display and readonly logic
 *  @see  http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1877902&group_id=176962 to FR [1877902]
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>BR [ 344 ] Smart Browse Search View is not MVC
 * 		@see https://github.com/adempiere/adempiere/issues/344
 * 		<li>FR [ 349 ] GridFieldVO attribute is ambiguous
 * 		@see https://github.com/adempiere/adempiere/issues/349
 * 		<a href="https://github.com/adempiere/adempiere/issues/566">
 * 		@see FR [ 566 ] Process parameter don't have a parameter like only information</a>
 *  @author Raul Munoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *    <li>  FR [ 566 ] Get Correct Validation Code 
 *    <li>  FR [ 1710 ] Get Correct Validation Code 
 *  @version  $Id: GridFieldVO.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class GridFieldVO implements Serializable
{

	/**
	 *  Return the SQL statement used for the MFieldVO.create
	 *  @param ctx context
	 *  @return SQL with or w/o translation and 1 parameter
	 */
	public static String getSQL (Properties ctx)
	{
		//	IsActive is part of View
		String sql = "SELECT * FROM AD_Field_v WHERE AD_Tab_ID=?"
			+ " ORDER BY IsDisplayed DESC, SeqNo";
		if (!Env.isBaseLanguage(ctx, "AD_Tab"))
			sql = "SELECT * FROM AD_Field_vt WHERE AD_Tab_ID=?"
				+ " AND AD_Language='" + Env.getAD_Language(ctx) + "'"
				+ " ORDER BY IsDisplayed DESC, SeqNo";
		return sql;
	}   //  getSQL

	public String InfoFactoryClass = null;

	/**
	 *  Create Field Value Object
	 *  @param ctx context
	 *  @param WindowNo window
	 *  @param TabNo tab
	 *  @param AD_Window_ID window
	 *  @param AD_Tab_ID tab
	 *  @param readOnly r/o
	 *  @param rs resultset AD_Field_v
	 *  @return MFieldVO
	 */
	public static GridFieldVO create (Properties ctx, int WindowNo, int TabNo, 
		int AD_Window_ID, int AD_Tab_ID, boolean readOnly, ResultSet rs)
	{
		GridFieldVO vo = new GridFieldVO (ctx, WindowNo, TabNo, 
			AD_Window_ID, AD_Tab_ID, readOnly);
		String columnName = "ColumnName";
		int AD_Field_ID = 0;
		try
		{
			vo.ColumnName = rs.getString("ColumnName");
			if (vo.ColumnName == null)
				return null;

			CLogger.get().fine(vo.ColumnName);

			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
			{
				columnName = rsmd.getColumnName (i);
				if (columnName.equalsIgnoreCase("Name"))
					vo.Header = rs.getString(i);
				else if (columnName.equalsIgnoreCase("AD_Reference_ID"))
					vo.displayType = rs.getInt(i);
				else if (columnName.equalsIgnoreCase("AD_Column_ID"))
					vo.AD_Column_ID = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("AD_Table_ID"))
					vo.AD_Table_ID = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("DisplayLength"))
					vo.DisplayLength = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("IsSameLine"))
					vo.IsSameLine = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsDisplayed"))
					vo.IsDisplayed = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsDisplayedGrid"))
					vo.IsDisplayedGrid = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("SeqNoGrid"))
					vo.SeqNoGrid = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("DisplayLogic"))
					vo.DisplayLogic = rs.getString (i);
				else if (columnName.equalsIgnoreCase("DefaultValue"))
					vo.DefaultValue = rs.getString (i);
				else if (columnName.equalsIgnoreCase("IsMandatory"))
					vo.IsMandatory = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsReadOnly"))
					vo.IsReadOnly = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsUpdateable"))
					vo.IsUpdateable = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsAlwaysUpdateable"))
					vo.IsAlwaysUpdateable = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsHeading"))
					vo.IsHeading = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsFieldOnly"))
					vo.IsFieldOnly = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsEncryptedField"))
					vo.IsEncryptedField = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsEncryptedColumn"))
					vo.IsEncryptedColumn = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsSelectionColumn"))
					vo.IsSelectionColumn = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("SortNo"))
					vo.SortNo = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("FieldLength"))
					vo.FieldLength = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("VFormat"))
					vo.VFormat = rs.getString (i);
				else if (columnName.equalsIgnoreCase("ValueMin"))
					vo.ValueMin = rs.getString (i);
				else if (columnName.equalsIgnoreCase("ValueMax"))
					vo.ValueMax = rs.getString (i);
				else if (columnName.equalsIgnoreCase("FieldGroup"))
					vo.FieldGroup = rs.getString (i);
				else if (columnName.equalsIgnoreCase("FieldGroupType"))
					vo.FieldGroupType = rs.getString (i);
				else if (columnName.equalsIgnoreCase("IsKey"))
					vo.IsKey = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsParent"))
					vo.IsParent = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("Description"))
					vo.Description = rs.getString (i);
				else if (columnName.equalsIgnoreCase("Help"))
					vo.Help = rs.getString (i);
				else if (columnName.equalsIgnoreCase("Callout"))
					vo.Callout = rs.getString (i);
				else if (columnName.equalsIgnoreCase("AD_Process_ID"))
					vo.AD_Process_ID = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("AD_Image_ID"))
					vo.AD_Image_ID = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("AD_Chart_ID"))
					vo.AD_Chart_ID = rs.getInt (i);
				else if (columnName.equalsIgnoreCase("ReadOnlyLogic"))
					vo.ReadOnlyLogic = rs.getString (i);
				else if (columnName.equalsIgnoreCase("MandatoryLogic"))
					vo.MandatoryLogic = rs.getString (i);	
				else if (columnName.equalsIgnoreCase("ObscureType"))
					vo.ObscureType = rs.getString (i);
				//
				else if (columnName.equalsIgnoreCase("AD_Reference_Value_ID"))
					vo.AD_Reference_Value_ID = rs.getInt(i);
				else if (columnName.equalsIgnoreCase("ValidationCode"))
					vo.ValidationCode = rs.getString(i);
				else if (columnName.equalsIgnoreCase("ColumnSQL"))
					vo.ColumnSQL = rs.getString(i);
				//Feature Request FR [ 1757088 ]
				else if (columnName.equalsIgnoreCase("Included_Tab_ID"))
					vo.Included_Tab_ID = rs.getInt(i);
				// Collapse Default State
				else if (columnName.equalsIgnoreCase("IsCollapsedByDefault"))
					vo.IsCollapsedByDefault = "Y".equals(rs.getString(i));
				//Info Factory class
				else if (columnName.equalsIgnoreCase("InfoFactoryClass"))
					vo.InfoFactoryClass  = rs.getString(i);
//				Feature Request FR [ 2003044 ]
				else if (columnName.equalsIgnoreCase("IsAutocomplete"))
					vo.IsAutocomplete  = "Y".equals(rs.getString(i));
				// FR 3051618 - Grid View improvements
				//else if (columnName.equalsIgnoreCase("HideInListView"))
				//	vo.HideInListView = "Y".equals(rs.getString(i));
				else if (columnName.equalsIgnoreCase("PreferredWidth"))
					vo.PreferredWidth = rs.getInt(i);
				//Allows Copy
				else if (columnName.equalsIgnoreCase(I_AD_Field.COLUMNNAME_IsAllowCopy))
					vo.IsAllowsCopy = "Y".equals(rs.getString(i));
				else if (columnName.equalsIgnoreCase("IsRange"))
					vo.IsRangeLookup = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("isEmbedded"))
					vo.isEmbedded = "Y".equals(rs.getString (i));
				else if (columnName.equalsIgnoreCase("IsAllowCopy"))
					vo.IsAllowCopy  = "Y".equals(rs.getString(i));
				else if (columnName.equalsIgnoreCase("AD_Field_ID"))
					vo.AD_Field_ID = rs.getInt(i);
				else if (columnName.equalsIgnoreCase("IsAllowNewAttributeInstance"))
					vo.IsAllowNewAttributeInstance  = "Y".equals(rs.getString(i));
				//  FR [ 1710 ] 
				else if (columnName.equalsIgnoreCase("AD_FieldDefinition_ID"))
					vo.AD_FieldDefinition_ID  = rs.getInt(i);
				else if (columnName.equalsIgnoreCase("IsQuickEntry"))
					vo.IsQuickEntry = "Y".equals(rs.getString (i));

			}
			if (vo.Header == null)
				vo.Header = vo.ColumnName;
			AD_Field_ID  = rs.getInt("AD_Field_ID");
		}
		catch (SQLException e)
		{
			CLogger.get().log(Level.SEVERE, "ColumnName=" + columnName, e);
			return null;
		}
		// ASP
		if (vo.IsDisplayed) {
			MClient client = MClient.get(ctx);
			// ASP for fields has a different approach - it must be defined as a field but hidden
			//   in order to have the proper context variable filled with defaults
			// Validate field and put IsDisplayed=N if must be hidden
			if (! client.isDisplayField(AD_Field_ID))
				vo.IsDisplayed = false;
		}
		//
		vo.initFinish();
		return vo;
	}   //  create

	/**
	 *  Init Field for Process Parameter
	 *  @param ctx context
	 *  @param WindowNo window
	 *  @param processParameter process parameter
	 *  @return MFieldVO
	 */
	public static GridFieldVO createParameter (Properties ctx, int WindowNo, MProcessPara processParameter) {
		GridFieldVO vo = new GridFieldVO (ctx, WindowNo, 0, 0, 0, false);
		vo.isProcess = true;
		vo.IsDisplayed = true;
		vo.IsReadOnly = false;
		vo.IsUpdateable = true;

		vo.AD_Table_ID = 0;
		vo.AD_Column_ID = processParameter.getAD_Process_Para_ID();	//	**
		vo.ColumnName = processParameter.getColumnName();
		vo.Header = processParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Name);
		vo.Description = processParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Description);
		vo.Help = processParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Help);
		vo.displayType = processParameter.getAD_Reference_ID();
		vo.IsMandatory = processParameter.isMandatory();
		vo.FieldLength = processParameter.getFieldLength();
		vo.DisplayLength = vo.FieldLength;
		vo.DefaultValue = processParameter.getDefaultValue();
		vo.DefaultValue2 = processParameter.getDefaultValue2();
		vo.VFormat = processParameter.getVFormat();
		vo.ValueMin = processParameter.getValueMin();
		vo.ValueMax = processParameter.getValueMax();
		vo.IsRange = processParameter.isRange();
		//
		vo.AD_Reference_Value_ID = processParameter.getAD_Reference_Value_ID();
    //  FR [ 566 ]
		if(processParameter.getAD_Val_Rule_ID() != 0) {
		  MValRule valRule = MValRule.get(ctx, processParameter.getAD_Val_Rule_ID());
      vo.ValidationCode = valRule.getCode();
		}
		
		//	
		vo.ReadOnlyLogic = processParameter.getReadOnlyLogic();
		vo.DisplayLogic= processParameter.getDisplayLogic();
		//	FR [ 566 ]
		vo.IsInfoOnly = processParameter.isInfoOnly();
		//
		vo.initFinish();
		if (vo.DefaultValue2 == null)
			vo.DefaultValue2 = "";
		return vo;
	}   //  createParameter

	/**
	 *  Create range "to" Parameter Field from "from" Parameter Field
	 *  @param voF field value object
	 *  @return to MFieldVO
	 */
	public static GridFieldVO createParameter (GridFieldVO voF)
	{
		GridFieldVO voT = new GridFieldVO (voF.ctx, voF.WindowNo, voF.TabNo, 
			voF.AD_Window_ID, voF.AD_Tab_ID, voF.tabReadOnly);
		voT.isProcess = true;
		voT.IsDisplayed = true;
		voT.IsReadOnly = false;
		voT.IsUpdateable = true;
		//
		voT.AD_Table_ID = voF.AD_Table_ID;
		voT.AD_Column_ID = voF.AD_Column_ID;    //  AD_Process_Para_ID
		voT.ColumnName = voF.ColumnName;
		voT.Header = voF.Header;
		voT.Description = voF.Description;
		voT.Help = voF.Help;
		voT.displayType = voF.displayType;
		voT.IsMandatory = voF.IsMandatory;
		voT.FieldLength = voF.FieldLength;
		voT.DisplayLength = voF.FieldLength;
		voT.DefaultValue = voF.DefaultValue;
		voT.DefaultValue2 = voF.DefaultValue2;
		voT.VFormat = voF.VFormat;
		voT.ValueMin = voF.ValueMin;
		voT.ValueMax = voF.ValueMax;
		voT.IsRange = voF.IsRange;
		voT.isEmbedded= voF.isEmbedded;
		voT.DisplayLogic = voF.DisplayLogic;
		voT.ReadOnlyLogic = voF.ReadOnlyLogic;
		voT.ValidationCode = voF.ValidationCode;
		voT.InfoFactoryClass = voF.InfoFactoryClass;
		voT.ColumnNameAlias = voF.ColumnNameAlias;
		//	FR [ 566 ]
		voT.IsInfoOnly = voF.IsInfoOnly;
		
		//
		// Genied: For a range parameter the second field 
		// lookup behaviour should match the first one.
		voT.AD_Reference_Value_ID = voF.AD_Reference_Value_ID;
		voT.initFinish();
		
		return voT;
	}   //  createParameter


	/**
	 *  Make a standard field (Created/Updated/By)
	 *  @param ctx context
	 *  @param WindowNo window
	 *  @param TabNo tab
	 *  @param AD_Window_ID window
	 *  @param AD_Tab_ID tab
	 *  @param tabReadOnly rab is r/o
	 *  @param isCreated is Created field
	 *  @param isTimestamp is the timestamp (not by)
	 *  @return MFieldVO
	 */
	public static GridFieldVO createStdField (Properties ctx, int WindowNo, int TabNo, 
		int AD_Window_ID, int AD_Tab_ID, boolean tabReadOnly,
		boolean isCreated, boolean isTimestamp)
	{
		GridFieldVO vo = new GridFieldVO (ctx, WindowNo, TabNo, 
			AD_Window_ID, AD_Tab_ID, tabReadOnly);
		vo.ColumnName = isCreated ? "Created" : "Updated";
		if (!isTimestamp)
			vo.ColumnName += "By";
		vo.displayType = isTimestamp ? DisplayType.DateTime : DisplayType.Table;
		if (!isTimestamp)
			vo.AD_Reference_Value_ID = 110;		//	AD_User Table Reference
		vo.IsDisplayed = false;
		vo.IsMandatory = false;
		vo.IsReadOnly = false;
		vo.IsUpdateable = true;
		vo.initFinish();
		return vo;
	}   //  initStdField

	
	/**************************************************************************
	 *  Private constructor.
	 *  @param Ctx context
	 *  @param windowNo window
	 *  @param tabNo tab
	 *  @param ad_Window_ID window
	 *  @param ad_Tab_ID tab
	 *  @param TabReadOnly tab read only
	 */
	private GridFieldVO (Properties Ctx, int windowNo, int tabNo, 
		int ad_Window_ID, int ad_Tab_ID, boolean TabReadOnly)
	{
		ctx = Ctx;
		WindowNo = windowNo;
		TabNo = tabNo;
		AD_Window_ID = ad_Window_ID;
		AD_Tab_ID = ad_Tab_ID;
		tabReadOnly = TabReadOnly;
	}   //  MFieldVO

	static final long serialVersionUID = 4385061125114436797L;
	
	/** Context                     */
	public Properties   ctx = null;
	
	
	/** RangeLookup     */	
	//	FR [ 349 ]
	public boolean      IsRangeLookup = false;
	/** isEmbedded **/
	public boolean      isEmbedded = false;	
	/** Window No                   */
	public int          WindowNo;
	/** Tab No                      */
	public int          TabNo;
	/** AD_Winmdow_ID               */
	public int          AD_Window_ID;
	/** AD_Tab_ID					*/
	public int			AD_Tab_ID;
	/** Is the Tab Read Only        */
	public boolean      tabReadOnly = false;

	/** Is Process Parameter        */
	public boolean      isProcess = false;

	/**	Column name		*/
	public String       ColumnName = "";
	/**	Column sql		*/
	public String       ColumnSQL;
	/**	Label			*/
	public String       Header = "";
	/**	DisplayType		*/
	public int          displayType = 0;
	/**	Table ID		*/
	public int          AD_Table_ID = 0;
	/**	Clumn ID		*/
	public int          AD_Column_ID = 0;
	/**	Display Length	*/
	public int          DisplayLength = 0;
	/**	Same Line		*/
	public boolean      IsSameLine = false;
	/**	Displayed		*/
	public boolean      IsDisplayed = false;
	/**	Displayed Grid		*/
	public boolean      IsDisplayedGrid = true;
	/**	Displayed in Quick Entry Form		*/
	public boolean      IsQuickEntry = false;
	/** Grid Display sequence	*/
	public int	SeqNoGrid = 0;
	/** Preferred size in list view */
	public int			PreferredWidth = 0;
	/**	Dislay Logic	*/
	public String       DisplayLogic = "";
	/**	Default Value	*/
	public String       DefaultValue = "";
	/**	Mandatory		*/
	public boolean      IsMandatory = false;
	/**	Read Only		*/
	public boolean      IsReadOnly = false;
	/**	Updateable		*/
	public boolean      IsUpdateable = false;
	/**	Always Updateable	*/
	public boolean      IsAlwaysUpdateable = false;
	/**	Heading Only	*/
	public boolean      IsHeading = false;
	/**	Field Only		*/
	public boolean      IsFieldOnly = false;
	/**	Display Encryption	*/
	public boolean      IsEncryptedField = false;
	/**	Storage Encryption	*/
	public boolean      IsEncryptedColumn = false;
	/**	Find Selection		*/
	public boolean		IsSelectionColumn = false;
	/**	Order By		*/
	public int          SortNo = 0;
	/**	Field Length		*/
	public int          FieldLength = 0;
	/**	Format enforcement		*/
	public String       VFormat = "";
	/**	Min. Value		*/
	public String       ValueMin = "";
	/**	Max. Value		*/
	public String       ValueMax = "";
	/**	Field Group		*/
	public String       FieldGroup = "";
	/**	Field Group	Type	*/
	public String       FieldGroupType = "";
	/**	PK				*/
	public boolean      IsKey = false;
	/**	FK				*/
	public boolean      IsParent = false;
	/**	Callout			*/
	public String       Callout = "";
	/**	Process			*/
	public int          AD_Process_ID = 0;
	/** Image 			*/
	public int			AD_Image_ID = 0;
	/** Chart			*/
	public int			AD_Chart_ID = 0;
	/**	Description		*/
	public String       Description = "";
	/**	Help			*/
	public String       Help = "";
	/**	Mandatory Logic	*/
	public String 		MandatoryLogic = "";
	/**	Read Only Logic	*/
	public String       ReadOnlyLogic = "";
	/**	Display Obscure	*/
	public String		ObscureType = null;
	/**	Allow Copy		*/
	public boolean 		IsAllowsCopy = false;
	/**	Is information Only	*/
	public boolean		IsInfoOnly = false;
	/**	Lookup Validation code	*/
	public String		ValidationCode = "";
	/**	Reference Value			*/
	public int			AD_Reference_Value_ID = 0;

	/**	Process Parameter Range		*/
	public boolean      IsRange = false;
	/**	Process Parameter Value2	*/
	public String       DefaultValue2 = "";

	/** Lookup Value Object     */
	public MLookupInfo  lookupInfo = null;
	
	/** Field ID */
	public int AD_Field_ID = 0;
	
	//*  Feature Request FR [ 1757088 ]
	public int          Included_Tab_ID = 0;

	/** Collapse By Default * */
	public boolean IsCollapsedByDefault = false;
	/**  Autocompletion for textfields - Feature Request FR [ 1757088 ] */
	public boolean IsAutocomplete = false;
	/** Define alias by smart browser */
	public String ColumnNameAlias = "";
	//	FR [ 344 ]
	/**	Is ColumnSQL like reference	*/
	public boolean IsColumnSQLReference = false;
	/* Allow copy - IDEMPIERE-67 - Carlos Ruiz - globalqss */
	public boolean IsAllowCopy = false;
	/** Allow New Attribute Instance Adaxa Ticket#1406 - jobriant */
	public boolean IsAllowNewAttributeInstance = false;
	/** Allow New Attribute Instance Adaxa Ticket#1406 - jobriant */
	public int AD_FieldDefinition_ID = 0;
	
	/**
	 *  Set Context including contained elements
	 *  @param newCtx new context
	 */
	public void setCtx (Properties newCtx)
	{
		ctx = newCtx;
		if (lookupInfo != null)
			lookupInfo.ctx = newCtx;
	}   //  setCtx

	/**
	 *  Validate Fields and create LookupInfo if required
	 */
	public void initFinish()
	{
		//  Not null fields
		if (DisplayLogic == null)
			DisplayLogic = "";
		if (DefaultValue == null)
			DefaultValue = "";
		if (FieldGroup == null)
			FieldGroup = "";
		if (FieldGroupType == null)
			FieldGroupType = "";
		if (Description == null)
			Description = "";
		if (Help == null)
			Help = "";
		if (Callout == null)
			Callout = "";
		if (ReadOnlyLogic == null)
			ReadOnlyLogic = "";
		if (MandatoryLogic == null)
			MandatoryLogic = "";
		if (ColumnNameAlias == null)
			ColumnNameAlias = "";


		//  Create Lookup, if not ID
		if (DisplayType.isLookup(displayType) && IsDisplayed)
		{
			loadLookupInfo();
		}
	}   //  initFinish

	/**
	+	 * load lookup info.
	+	 * used by findwindow to loadlookupinfo for invisible field
	+	 */
		public void loadLookupInfo() {
			try
			{
				lookupInfo = MLookupFactory.getLookupInfo (ctx, WindowNo, AD_Column_ID, displayType,
					Env.getLanguage(ctx), ColumnName, AD_Reference_Value_ID,
					IsParent, ValidationCode);
				if (lookupInfo == null)
					displayType = DisplayType.ID;
				else
					lookupInfo.InfoFactoryClass = this.InfoFactoryClass;
			}
			catch (Exception e)     //  Cannot create Lookup
			{
				CLogger.get().log(Level.SEVERE, "No LookupInfo for " + ColumnName, e);
				displayType = DisplayType.ID;
			}
		}  //  loadLookupInfo

	/**
	 * 	Clone Field.
	 *	@param Ctx ctx
	 *	@param windowNo window no
	 *	@param tabNo tab no
	 *	@param ad_Window_ID window id
	 *	@param ad_Tab_ID tab id
	 *	@param TabReadOnly r/o
	 *	@return Field or null
	 */
	public GridFieldVO clone(Properties Ctx, int windowNo, int tabNo, 
		int ad_Window_ID, int ad_Tab_ID, 
		boolean TabReadOnly)
	{
		GridFieldVO clone = new GridFieldVO(Ctx, windowNo, tabNo, 
			ad_Window_ID, ad_Tab_ID, TabReadOnly);
		//
		clone.isProcess = false;
		//  Database Fields
		clone.ColumnName = ColumnName;
		clone.ColumnSQL = ColumnSQL;
		clone.Header = Header;
		clone.displayType = displayType;
		clone.AD_Table_ID = AD_Table_ID;
		clone.AD_Column_ID = AD_Column_ID;
		clone.DisplayLength = DisplayLength;
		clone.IsSameLine = IsSameLine;
		clone.IsDisplayed = IsDisplayed;
		clone.IsDisplayedGrid = IsDisplayedGrid;
		clone.SeqNoGrid = SeqNoGrid;
		clone.PreferredWidth = PreferredWidth;
		clone.DisplayLogic = DisplayLogic;
		clone.DefaultValue = DefaultValue;
		clone.IsMandatory = IsMandatory;
		clone.IsReadOnly = IsReadOnly;
		clone.IsUpdateable = IsUpdateable;
		clone.IsAlwaysUpdateable = IsAlwaysUpdateable;
		clone.IsHeading = IsHeading;
		clone.IsFieldOnly = IsFieldOnly;
		clone.IsEncryptedField = IsEncryptedField;
		clone.IsEncryptedColumn = IsEncryptedColumn;
		clone.IsSelectionColumn = IsSelectionColumn;
		clone.IsAutocomplete = IsAutocomplete;
		clone.SortNo = SortNo;
		clone.FieldLength = FieldLength;
		clone.VFormat = VFormat;
		clone.ValueMin = ValueMin;
		clone.ValueMax = ValueMax;
		clone.FieldGroup = FieldGroup;
		clone.FieldGroupType = FieldGroupType;
		clone.IsKey = IsKey;
		clone.IsParent = IsParent;
		clone.Callout = Callout;
		clone.AD_Process_ID = AD_Process_ID;
		clone.AD_Image_ID = AD_Image_ID;
		clone.AD_Chart_ID = AD_Chart_ID;
		clone.Description = Description;
		clone.Help = Help;
		clone.ReadOnlyLogic = ReadOnlyLogic;
		clone.MandatoryLogic = MandatoryLogic;
		clone.ObscureType = ObscureType;
		//	Lookup
		clone.ValidationCode = ValidationCode;
		clone.AD_Reference_Value_ID = AD_Reference_Value_ID;
		clone.lookupInfo = lookupInfo;

		//  Process Parameter
		clone.IsRange = IsRange;
		//	FR [ 349 ]
		clone.IsRangeLookup = IsRangeLookup;
		clone.isEmbedded = isEmbedded;
		clone.DefaultValue2 = DefaultValue2;
		clone.ColumnNameAlias = ColumnNameAlias;		
		//	FR [ 566 ]
		clone.IsInfoOnly = IsInfoOnly;
		// IsQuickEntry
		clone.IsQuickEntry = IsQuickEntry;

		clone.AD_FieldDefinition_ID = AD_FieldDefinition_ID;	
		return clone;
	}	//	clone
	
	
	@Override
  public String toString() {
    return "GridFieldVO [InfoFactoryClass=" + InfoFactoryClass + ", ctx=" + ctx + ", IsRangeLookup=" + IsRangeLookup
        + ", isEmbedded=" + isEmbedded + ", WindowNo=" + WindowNo + ", TabNo=" + TabNo + ", AD_Window_ID="
        + AD_Window_ID + ", AD_Tab_ID=" + AD_Tab_ID + ", tabReadOnly=" + tabReadOnly + ", isProcess=" + isProcess
        + ", ColumnName=" + ColumnName + ", ColumnSQL=" + ColumnSQL + ", Header=" + Header + ", displayType="
        + displayType + ", AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID + ", DisplayLength="
        + DisplayLength + ", IsSameLine=" + IsSameLine + ", IsDisplayed=" + IsDisplayed + ", IsDisplayedGrid="
        + IsDisplayedGrid + ", SeqNoGrid=" + SeqNoGrid + ", PreferredWidth=" + PreferredWidth + ", DisplayLogic="
        + DisplayLogic + ", DefaultValue=" + DefaultValue + ", IsMandatory=" + IsMandatory + ", IsReadOnly="
        + IsReadOnly + ", IsUpdateable=" + IsUpdateable + ", IsAlwaysUpdateable=" + IsAlwaysUpdateable + ", IsHeading="
        + IsHeading + ", IsFieldOnly=" + IsFieldOnly + ", IsEncryptedField=" + IsEncryptedField
        + ", IsEncryptedColumn=" + IsEncryptedColumn + ", IsSelectionColumn=" + IsSelectionColumn + ", SortNo="
        + SortNo + ", FieldLength=" + FieldLength + ", VFormat=" + VFormat + ", ValueMin=" + ValueMin + ", ValueMax="
        + ValueMax + ", FieldGroup=" + FieldGroup + ", FieldGroupType=" + FieldGroupType + ", IsKey=" + IsKey
        + ", IsParent=" + IsParent + ", Callout=" + Callout + ", AD_Process_ID=" + AD_Process_ID
		+ ", AD_Image_ID=" + AD_Image_ID+ ", AD_Chart_ID="
        + AD_Chart_ID + ", Description=" + Description + ", Help=" + Help + ", MandatoryLogic=" + MandatoryLogic
        + ", ReadOnlyLogic=" + ReadOnlyLogic + ", ObscureType=" + ObscureType + ", IsAllowsCopy=" + IsAllowsCopy
        + ", IsInfoOnly=" + IsInfoOnly + ", ValidationCode=" + ValidationCode + ", AD_Reference_Value_ID="
        + AD_Reference_Value_ID + ", IsRange=" + IsRange + ", DefaultValue2=" + DefaultValue2 + ", lookupInfo="
        + lookupInfo + ", Included_Tab_ID=" + Included_Tab_ID + ", IsCollapsedByDefault=" + IsCollapsedByDefault
        + ", IsAutocomplete=" + IsAutocomplete + ", ColumnNameAlias=" + ColumnNameAlias + ", IsColumnSQLReference="
        + IsColumnSQLReference +  ", AD_FieldDefinition_ID=" + AD_FieldDefinition_ID +  "]";
  }

	/**
	 * @author <a href="mailto:sbhimani@logilite.com">Sachin Bhimani</a> - Feature #1449
	 * @param ctx
	 * @param WindowNo
	 * @param AD_Column_ID
	 * @param ColumnName
	 * @param Name
	 * @param AD_Reference_ID
	 * @param AD_Reference_Value_ID
	 * @param IsMandatory
	 * @param IsEncrypted
	 * @return GridFieldVO
	 */
	public static GridFieldVO createParameter(Properties ctx, int WindowNo, int AD_Column_ID, String ColumnName,
			String Name, int AD_Reference_ID, int AD_Reference_Value_ID, boolean IsMandatory, boolean IsEncrypted)
	{
		GridFieldVO vo = new GridFieldVO(ctx, WindowNo, 0, 0, 0, false);
		vo.isProcess = true;
		vo.IsDisplayed = true;
		vo.IsReadOnly = false;
		vo.IsUpdateable = true;
		vo.AD_Table_ID = 0;
		vo.AD_Column_ID = AD_Column_ID;
		vo.ColumnName = ColumnName;
		vo.Header = Name;
		vo.displayType = AD_Reference_ID;
		vo.AD_Reference_Value_ID = AD_Reference_Value_ID;
		vo.IsMandatory = IsMandatory;
		vo.IsEncryptedField = IsEncrypted;
		vo.initFinish();

		return vo;

	} // createParameter
	
}   //  MFieldVO
