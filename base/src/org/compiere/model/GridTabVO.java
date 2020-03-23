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
 *****************************************************************************/
package org.compiere.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluator;
import org.compiere.util.Util;
import org.spin.util.ASPUtil;

/**
 *  Model Tab Value Object
 *
 *  @author Jorg Janke
 *  @version  $Id: GridTabVO.java,v 1.4 2006/07/30 00:58:38 jjanke Exp $
 *  @author Dixon Martinez, dmartinez@erpcya.com, <a href="http://www.erpcya.com">ERPCyA</a>
 *  	<li> <a href="https://github.com/adempiere/adempiere/issues/162">BR [162]</a> -
 *  		Readonly Logic for tab does not prevent the data from modifying
 */
public class GridTabVO implements Evaluatee, Serializable {

	/**************************************************************************
	 *	Create MTab VO
	 *
	 *  @param wVO value object
	 *  @param tabNo tab no
	 *	@param rs ResultSet from AD_Tab_v
	 *	@param isRO true if window is r/o
	 *  @param onlyCurrentRows if true query is limited to not processed records
	 *  @return TabVO
	 */
	public static GridTabVO create (GridWindowVO wVO, int tabNo, MTab tab, 
		boolean isRO, boolean onlyCurrentRows) {
		CLogger.get().config("#" + tabNo);

		GridTabVO vo = new GridTabVO (wVO.ctx, wVO.WindowNo);
		vo.AD_Window_ID = wVO.AD_Window_ID;
		vo.TabNo = tabNo;
		//
		if (!loadTabDetails(vo, tab))
			return null;

		if (isRO)
		{
			CLogger.get().fine("Tab is ReadOnly");
			vo.IsReadOnly = true;
		}
		vo.onlyCurrentRows = onlyCurrentRows;

		//  Create Fields
		if (vo.IsSortTab)
		{
			vo.Fields = new ArrayList<GridFieldVO>();	//	dummy
		}
		return vo;
	}	//	create
	
	/**
	 * 	Load Tab Details from rs into vo
	 * 	@param vo Tab value object
	 *	@param tab tab model object from AD_Tab
	 * 	@return true if read ok
	 */
	private static boolean loadTabDetails (GridTabVO vo, MTab tab) {
		MRole role = MRole.getDefault(vo.ctx, false);
		boolean showTrl = "Y".equals(Env.getContext(vo.ctx, "#ShowTrl"));
		boolean showAcct = "Y".equals(Env.getContext(vo.ctx, "#ShowAcct"));
		boolean showAdvanced = "Y".equals(Env.getContext(vo.ctx, "#ShowAdvanced"));
		//	TODO: Translation
		vo.AD_Tab_ID = tab.getAD_Tab_ID();
		Env.setContext(vo.ctx, vo.WindowNo, vo.TabNo, GridTab.CTX_AD_Tab_ID, String.valueOf(vo.AD_Tab_ID));
		vo.Name = tab.getName();
		Env.setContext(vo.ctx, vo.WindowNo, vo.TabNo, GridTab.CTX_Name, vo.Name);
		MTable table = MTable.get(vo.ctx, tab.getAD_Table_ID());
		//	Translation Tab	**
		if (tab.isTranslationTab()) {
			//	Document Translation
			vo.TableName = table.getTableName();
			if (!Env.isBaseTranslation(vo.TableName)	//	C_UOM, ...
				&& !Env.isMultiLingualDocument(vo.ctx))
				showTrl = false;
			if (!showTrl)
			{
				CLogger.get().config("TrlTab Not displayed - AD_Tab_ID=" 
					+ vo.AD_Tab_ID + "=" + vo.Name + ", Table=" + vo.TableName
					+ ", BaseTrl=" + Env.isBaseTranslation(vo.TableName)
					+ ", MultiLingual=" + Env.isMultiLingualDocument(vo.ctx));
				return false;
			}
		}
		//	Advanced Tab	**
		if (!showAdvanced && tab.isAdvancedTab()) {
			CLogger.get().config("AdvancedTab Not displayed - AD_Tab_ID=" 
				+ vo.AD_Tab_ID + " " + vo.Name);
			return false;
		}
		//	Accounting Info Tab	**
		if (!showAcct && tab.isInfoTab()) {
			CLogger.get().fine("AcctTab Not displayed - AD_Tab_ID=" 
				+ vo.AD_Tab_ID + " " + vo.Name);
			return false;
		}
		
		//	DisplayLogic
		vo.DisplayLogic = tab.getDisplayLogic();
		
		//	Access Level
		vo.AccessLevel = table.getAccessLevel();
		if (!role.canView (vo.ctx, vo.AccessLevel))	//	No Access
		{
			CLogger.get().fine("No Role Access - AD_Tab_ID=" + vo.AD_Tab_ID + " " + vo. Name);
			return false;
		}	//	Used by MField.getDefault
		Env.setContext(vo.ctx, vo.WindowNo, vo.TabNo, GridTab.CTX_AccessLevel, vo.AccessLevel);

		//	Table Access
		vo.AD_Table_ID = tab.getAD_Table_ID();
		Env.setContext(vo.ctx, vo.WindowNo, vo.TabNo, GridTab.CTX_AD_Table_ID, String.valueOf(vo.AD_Table_ID));
		if (!role.isTableAccess(vo.AD_Table_ID, true))
		{
			CLogger.get().config("No Table Access - AD_Tab_ID=" 
				+ vo.AD_Tab_ID + " " + vo. Name);
			return false;
		}
		if (tab.isReadOnly())
			vo.IsReadOnly = true;
		vo.ReadOnlyLogic = tab.getReadOnlyLogic();
		//	BR [162]
		if(!vo.IsReadOnly && vo.ReadOnlyLogic != null) {
			vo.IsReadOnly = Evaluator.evaluateLogic(vo, vo.ReadOnlyLogic);
		}
		
		if (!tab.isInsertRecord())
			vo.IsInsertRecord = false;
		
		//
		vo.Description = tab.getDescription();
		if (vo.Description == null)
			vo.Description = "";
		vo.Help = tab.getHelp();
		if (vo.Help == null)
			vo.Help = "";

		if (tab.isSingleRow())
			vo.IsSingleRow = true;
		if (tab.isHasTree())
			vo.HasTree = true;

		vo.AD_Table_ID = tab.getAD_Table_ID();
		vo.TableName = table.getTableName();
		if (table.isView())
			vo.IsView = true;
		vo.AD_Column_ID = tab.getAD_Column_ID();   //  Primary Link Column
		vo.Parent_Column_ID = tab.getParent_Column_ID();   // Parent tab link column

		// TODO: see it
		if (table.isSecurityEnabled())
			vo.IsSecurityEnabled = true;
		if (table.isDeleteable())
			vo.IsDeleteable = true;
		if (table.isHighVolume())
			vo.IsHighVolume = true;

		vo.CommitWarning = tab.getCommitWarning();
		if (vo.CommitWarning == null)
			vo.CommitWarning = "";
		vo.WhereClause = tab.getWhereClause();
		if (vo.WhereClause == null)
			vo.WhereClause = "";
		//jz col=null not good for Derby
		if (vo.WhereClause.indexOf("=null")>0)
			vo.WhereClause.replaceAll("=null", " IS NULL ");
		// Where Clauses should be surrounded by parenthesis - teo_sarca, BF [ 1982327 ] 
		if (vo.WhereClause.trim().length() > 0) {
			vo.WhereClause = "("+vo.WhereClause+")";
		}

		vo.OrderByClause = tab.getOrderByClause();
		if (vo.OrderByClause == null)
			vo.OrderByClause = "";

		vo.AD_Process_ID = tab.getAD_Process_ID();
		vo.AD_Image_ID = tab.getAD_Image_ID();
		vo.Included_Tab_ID = tab.getIncluded_Tab_ID();
		//
		vo.TabLevel = tab.getTabLevel();
		//
		vo.IsSortTab = tab.isSortTab();
		if (vo.IsSortTab) {
			vo.AD_ColumnSortOrder_ID = tab.getAD_ColumnSortOrder_ID();
			vo.AD_ColumnSortYesNo_ID = tab.getAD_ColumnSortYesNo_ID();
		}
		//
		//	Replication Type - set R/O if Reference
		vo.ReplicationType = table.getReplicationType();
		if (!Util.isEmpty(vo.ReplicationType)
				&& "R".equals(vo.ReplicationType)) {
			vo.IsReadOnly = true;
		}
		return true;
	}	//	loadTabDetails

	/**************************************************************************
	 *  Create Tab Fields
	 *  @param gridTabVO tab value object
	 *  @return true if fields were created
	 */
	private static boolean createFields (GridTabVO gridTabVO) {
		Optional<GridTabVO> maybeGridTabVO = Optional.ofNullable(gridTabVO);
		ArrayList<GridFieldVO> gridFieldVOList = new ArrayList<GridFieldVO>();
		maybeGridTabVO.ifPresent(gridTabValueObject -> {
			Optional<ASPUtil> maybeInstance = Optional.ofNullable(ASPUtil.getInstance(gridTabValueObject.ctx));
			maybeInstance.ifPresent(instance -> {
				List<MField> fields = instance.getWindowFields(gridTabValueObject.AD_Tab_ID).stream().filter(Objects::nonNull).collect(Collectors.toList());
				fields.forEach(field -> {
					GridFieldVO gridFieldVO = GridFieldVO.create(gridTabValueObject.ctx,
							gridTabValueObject.WindowNo, gridTabValueObject.TabNo,
							gridTabValueObject.AD_Window_ID, gridTabValueObject.AD_Tab_ID,
							gridTabValueObject.IsReadOnly, field);
					gridFieldVOList.add(gridFieldVO);
				});
				gridTabVO.Fields = gridFieldVOList;
				gridTabVO.initFields = true;
			});
		});
		return gridFieldVOList.size() != 0;
	}   //  createFields
	
	/**************************************************************************
	 *  Private constructor - must use Factory
	 *  @param Ctx context
	 *  @param windowNo window
	 */
	private GridTabVO (Properties Ctx, int windowNo)
	{
		ctx = Ctx;
		WindowNo = windowNo;
	}   //  MTabVO

	static final long serialVersionUID = 9160212869277319305L;
	
	/** Context - replicated    */
	public  Properties      ctx;
	/** Window No - replicated  */
	public  int				WindowNo;
	/** AD Window - replicated  */
	public  int             AD_Window_ID;

	/** Tab No (not AD_Tab_ID) 0.. */
	public  int				TabNo;

	/**	Tab	ID			*/
	public	int		    AD_Tab_ID;
	/** Name			*/
	public	String	    Name = "";
	/** Description		*/
	public	String	    Description = "";
	/** Help			*/
	public	String	    Help = "";
	/** Single Row		*/
	public	boolean	    IsSingleRow = false;
	/** Read Only		*/
	public  boolean     IsReadOnly = false;
	/** Insert Record	*/
	public 	boolean		IsInsertRecord = true;
	/** Tree			*/
	public  boolean	    HasTree = false;
	/** Table			*/
	public  int		    AD_Table_ID;
	/** Primary Link Column   */
	public  int		    AD_Column_ID = 0;
	/** Parent Tab Link Column */
	public	int			Parent_Column_ID = 0;
	/** Table Name		*/
	public  String	    TableName;
	/** Table is View	*/
	public  boolean     IsView = false;
	/** Table Access Level	*/
	public  String	    AccessLevel;
	/** Security		*/
	public  boolean	    IsSecurityEnabled = false;
	/** Table Deleteable	*/
	public  boolean	    IsDeleteable = false;
	/** Table High Volume	*/
	public  boolean     IsHighVolume = false;
	/** Process			*/
	public	int		    AD_Process_ID = 0;
	/** Commot Warning	*/
	public  String	    CommitWarning;
	/** Where			*/
	public  String	    WhereClause;
	/** Order by		*/
	public  String      OrderByClause;
	/** Tab Read Only	*/
	public  String      ReadOnlyLogic;
	/** Tab Display		*/
	public  String      DisplayLogic;
	/** Level			*/
	public  int         TabLevel = 0;
	/** Image			*/
	public int          AD_Image_ID = 0;
	/** Included Tab	*/
	public int          Included_Tab_ID = 0;
	/** Replication Type	*/
	public String		ReplicationType = "L";

	/** Sort Tab			*/
	public boolean		IsSortTab = false;
	/** Column Sort			*/
	public int			AD_ColumnSortOrder_ID = 0;
	/** Column Displayed	*/
	public int			AD_ColumnSortYesNo_ID = 0;

	/** Only Current Rows - derived	*/
	public  boolean     onlyCurrentRows = true;
	/**	Only Current Days - derived	*/
	public int			onlyCurrentDays = 0;

	/** Fields contain MFieldVO entities    */
	private ArrayList<GridFieldVO>	Fields = null;

	private boolean initFields = false;
	
	public ArrayList<GridFieldVO> getFields()
	{
		if (!initFields) createFields(this);
		return Fields;
	}
	
	/**
	 *  Set Context including contained elements
	 *  @param newCtx new context
	 */
	public void setCtx (Properties newCtx)
	{
		ctx = newCtx;
		if (Fields != null)
		{
			for (int i = 0; i < Fields.size() ; i++)
			{
				GridFieldVO field = (GridFieldVO)Fields.get(i);
				field.setCtx(newCtx);
			}
		}
	}   //  setCtx
	
	/**
	 * 	Get Variable Value (Evaluatee)
	 *	@param variableName name
	 *	@return value
	 */
	public String get_ValueAsString (String variableName)
	{
		return Env.getContext (ctx, WindowNo, variableName, false);	// not just window
	}	//	get_ValueAsString

	/**
	 * 	Clone
	 * 	@param Ctx context
	 * 	@param windowNo no
	 *	@return MTabVO or null
	 */
	protected GridTabVO clone(Properties Ctx, int windowNo)
	{
		GridTabVO clone = new GridTabVO(Ctx, windowNo);
		clone.AD_Window_ID = AD_Window_ID;
		clone.TabNo = TabNo;
		Env.setContext(Ctx, windowNo, clone.TabNo, GridTab.CTX_AD_Tab_ID, String.valueOf(clone.AD_Tab_ID));
		//
		clone.AD_Tab_ID = AD_Tab_ID;
		clone.Name = Name;
		Env.setContext(Ctx, windowNo, clone.TabNo, GridTab.CTX_Name, clone.Name);
		clone.Description = Description;
		clone.Help = Help;
		clone.IsSingleRow = IsSingleRow;
		clone.IsReadOnly = IsReadOnly;
		clone.IsInsertRecord = IsInsertRecord;
		clone.HasTree = HasTree;
		clone.AD_Table_ID = AD_Table_ID;
		clone.AD_Column_ID = AD_Column_ID;
		clone.Parent_Column_ID = Parent_Column_ID;
		clone.TableName = TableName;
		clone.IsView = IsView;
		clone.AccessLevel = AccessLevel;
		clone.IsSecurityEnabled = IsSecurityEnabled;
		clone.IsDeleteable = IsDeleteable;
		clone.IsHighVolume = IsHighVolume;
		clone.AD_Process_ID = AD_Process_ID;
		clone.CommitWarning = CommitWarning;
		clone.WhereClause = WhereClause;
		clone.OrderByClause = OrderByClause;
		clone.ReadOnlyLogic = ReadOnlyLogic;
		clone.DisplayLogic = DisplayLogic;
		clone.TabLevel = TabLevel;
		clone.AD_Image_ID = AD_Image_ID;
		clone.Included_Tab_ID = Included_Tab_ID;
		clone.ReplicationType = ReplicationType;
		Env.setContext(Ctx, windowNo, clone.TabNo, GridTab.CTX_AccessLevel, clone.AccessLevel);
		Env.setContext(Ctx, windowNo, clone.TabNo, GridTab.CTX_AD_Table_ID, String.valueOf(clone.AD_Table_ID));

		//
		clone.IsSortTab = IsSortTab;
		clone.AD_ColumnSortOrder_ID = AD_ColumnSortOrder_ID;
		clone.AD_ColumnSortYesNo_ID = AD_ColumnSortYesNo_ID;
		//  Derived
		clone.onlyCurrentRows = true;
		clone.onlyCurrentDays = 0;

		clone.Fields = new ArrayList<GridFieldVO>();
		for (int i = 0; i < Fields.size(); i++)
		{
			GridFieldVO field = Fields.get(i);
			GridFieldVO cloneField = field.clone(Ctx, windowNo, TabNo, 
				AD_Window_ID, AD_Tab_ID, IsReadOnly);
			if (cloneField == null)
				return null;
			clone.Fields.add(cloneField);
		}
		
		return clone;
	}	//	clone

	/**
	 * @return the initFields
	 */
	public boolean isInitFields() {
		return initFields;
	}

}   //  MTabVO