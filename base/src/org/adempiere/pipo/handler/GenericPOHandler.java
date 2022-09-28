/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.adempiere.pipo.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.core.domains.models.I_AD_Column;
import org.adempiere.core.domains.models.I_AD_Element;
import org.adempiere.core.domains.models.I_AD_EntityType;
import org.adempiere.core.domains.models.I_AD_Ref_List;
import org.adempiere.core.domains.models.I_AD_Table;
import org.adempiere.core.domains.models.I_AD_Tree;
import org.adempiere.core.domains.models.I_AD_TreeNode;
import org.adempiere.core.domains.models.I_C_ElementValue;
import org.adempiere.core.domains.models.I_C_Location;
import org.adempiere.core.domains.models.I_C_ValidCombination;
import org.adempiere.core.domains.models.I_M_Locator;
import org.adempiere.core.domains.models.X_AD_Table;
import org.adempiere.model.GenericPO;
import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.PoFiller;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.MTree;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Generic PO Handler for import and export PO
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class GenericPOHandler extends AbstractElementHandler {
	private final List<String> list = new ArrayList<String>();
	private AttributeFiller customValues = new AttributeFiller(new AttributesImpl());
	/**	Static values	*/
	public static final String TABLE_NAME_TAG = "TableNameTag";
	public static final String TABLE_ID_TAG = "TableIdTag";
	public static final String RECORD_ID_TAG = "RecordIdTag";
	public static final String TAG_Name = "GenericPO";
	public static final String HANDLE_TRANSLATION_FLAG = "2PACK_HANDLE_TRANSLATIONS";
	/**	Tag for column	*/
	public static final String Column_TAG_Name = TAG_Name + "_" + I_AD_Column.Table_Name;
	/**	attributes	*/
	public static final String IGNORE_WHEN_SAVE_ERROR = "Ignore_When_Save_Error";
	public static final String IGNORE_WHEN_MISSING_MANDATORY_REFERENCE = "Ignore_When_Missing_Mandatory_Reference";
	/**	before save class name	*/
	public static final String IMPORT_HANDLER_CLASS_NAME = "ImportClassNameHandler";	
	/**
	 * Add String value to set for export
	 * @param key
	 * @param value
	 */
	protected void addStringValue(String key, String value) {
		customValues.addString(key, value);
	}
	
	/**
	 * Add a boolean value for export
	 * @param key
	 * @param value
	 */
	protected void addBooleanValue(String key, boolean value) {
		customValues.addBoolean(key, value);
	}
	
	/**
	 * Add integer to export
	 * @param key
	 * @param value
	 */
	protected void addIntValue(String key, int value) {
		customValues.addInt(key, value);
	}
	
	/**
	 * Add long value to export
	 * @param key
	 * @param value
	 */
	protected void addLongValue(String key, long value) {
		customValues.addLong(key, value);
	}
	
	/**
	 * Clean default values
	 */
	protected void cleanValues() {
		customValues.cleanValues();
	}
	
	/**
	 * Set Import class name handler (this class extends from GenericPOHandler)
	 * @param className
	 */
	public void setImportHandlerClassName(String className) {
		addStringValue(IMPORT_HANDLER_CLASS_NAME, className);
	}
	
	/**
	 * Set export row for ignore if exist a error saving import
	 * @param ignore
	 */
	public void setIgnoreWhenSaveError(boolean ignore) {
		addBooleanValue(IGNORE_WHEN_SAVE_ERROR, ignore);
	}
	
	/**
	 * Ignore if a parent is missing and mandatory when is imported
	 * @param ignore
	 */
	public void setIgnoreWhenMissingMandatoryReference(boolean ignore) {
		addBooleanValue(IGNORE_WHEN_MISSING_MANDATORY_REFERENCE, ignore);
	}
	
	@Override
	public void startElement(Properties ctx, Element element) throws SAXException {
		final String elementValue = element.getElementValue();
		final Attributes atts = element.attributes;
		final String tableName = getStringValue(atts, TABLE_NAME_TAG);
		log.info(elementValue + " " + tableName);
		//	Get UUID
		String uuid = getUUIDValue(atts, tableName); 
		if(Util.isEmpty(uuid)
			|| Util.isEmpty(tableName)) {
			element.skip = true;
			return;
		}
		//	Fill attributes
		POInfo poInfo = POInfo.getPOInfo(ctx, tableName, getTrxName(ctx));
		String keyColumnName = poInfo.getKeyColumnName();
		int recordId = 0;
		//	Get Record Id
		if(!Util.isEmpty(keyColumnName)) {
			recordId = getIdFromUUID(ctx, tableName, uuid);
		}
		PO entity = null;
		//	Multy-Key
		if(poInfo.hasKeyColumn()) {
			entity = getCreatePO(ctx, tableName, recordId, getTrxName(ctx));
		} else { 
			entity = getCreatePOForMultyKey(ctx, poInfo, atts, getTrxName(ctx));
		}
		//	Validate update time
		long importTime = getLastUpdatedTime(atts);
		long currentPOTime = 0;
		if(entity.getUpdated() != null) {
			currentPOTime = entity.getUpdated().getTime();
		}
		//	Validate update time
		if(!Env.getContext(ctx, "UpdateMode").equals("Y")) {
			//	Validate it
			if(importTime > 0
					&& currentPOTime >= importTime
					&& !entity.is_new()) {
				element.skip = true;
				return;
			}
		}
		//	
		int backupId;
		String objectStatus;
		if (recordId > 0) {		
			backupId = copyRecord(ctx, poInfo.getTableName(), entity);
			objectStatus = "Update";			
		} else {
			objectStatus = "New";
			backupId = 0;
		}
		//	Load filler
		PoFiller filler = new PoFiller(entity, atts);
		entity.setIsDirectLoad(true);
		for(int index = 0; index < poInfo.getColumnCount(); index++) {
			//	No SQL
			if(poInfo.isVirtualColumn(index)) {
				continue;
			}
			//	No Key if is major that Official ID
			if (poInfo.isKey(index)) {
				if(entity.get_ID() > PackOut.MAX_OFFICIAL_ID) {
					continue;
				}
			}
			//	No Encrypted
			if(poInfo.isEncrypted(index)) {
				continue;
			}
			String columnName = poInfo.getColumnName(index);
			//	No user log
			if(columnName.equals(I_AD_Element.COLUMNNAME_Created)
					|| columnName.equals(I_AD_Element.COLUMNNAME_CreatedBy)
					|| columnName.equals(I_AD_Element.COLUMNNAME_Updated)
					|| columnName.equals(I_AD_Element.COLUMNNAME_UpdatedBy)
					|| columnName.equals(I_AD_Element.COLUMNNAME_AD_Client_ID)
					|| columnName.equals(I_AD_Element.COLUMNNAME_AD_Org_ID)) {
				continue;
			}
			//	Verify reference
			if(isLookup(poInfo.getColumnDisplayType(index))) {
				String parentNameUuid = AttributeFiller.getUUIDAttribute(columnName);
				String parentUuid = atts.getValue(parentNameUuid);
				if(!Util.isEmpty(parentUuid)) {
					String parentTableName = getParentTableName(ctx, poInfo.getAD_Column_ID(columnName), poInfo.getColumnDisplayType(index));
					if(!Util.isEmpty(parentTableName)) {
						int foreignId = getParentId(ctx, parentTableName, parentUuid);
						if(foreignId > 0) {
							entity.set_ValueOfColumn(columnName, foreignId);
						} else if(poInfo.isColumnMandatory(index)
								&& getBooleanValue(atts, IGNORE_WHEN_MISSING_MANDATORY_REFERENCE)) {
							element.skip = true;
							return;
						}
						continue;
					}
				}
			}
			//	Multy-Key UUID
			if(columnName.equals(I_AD_Element.COLUMNNAME_UUID)
					&& !poInfo.hasKeyColumn()) {
				if(!Util.isEmpty(entity.get_UUID())) {
					continue;
				}
			}
			//	Add Standard
			filler.setAttribute(columnName);
		}
		//	For tree node
		String treeUuid = atts.getValue(AttributeFiller.getUUIDAttribute(I_AD_Tree.COLUMNNAME_AD_Tree_ID));
		String treeType = atts.getValue(I_AD_Tree.COLUMNNAME_TreeType);
		String treeTableUuid = atts.getValue(AttributeFiller.getUUIDAttribute(I_AD_Tree.COLUMNNAME_AD_Table_ID));
		String sourceUuid = atts.getValue(AttributeFiller.getUUIDAttribute(I_AD_TreeNode.COLUMNNAME_Node_ID));
		String parentUuid = atts.getValue(AttributeFiller.getUUIDAttribute(I_AD_TreeNode.COLUMNNAME_Parent_ID));
		if(!Util.isEmpty(treeUuid)) {
			int treeId = getIdFromUUID(ctx, I_AD_Tree.Table_Name, treeUuid);
			int parentId = getIdFromNodeUUID(ctx, tableName, parentUuid);
			String parentTableName = null;
			if(treeId > 0) {
				MTree tree = MTree.get(ctx, treeId, getTrxName(ctx));
				MTable sourceTable = MTable.get(ctx, tree.getAD_Table_ID());
				parentTableName = sourceTable.getTableName();
			} else {
				int treeTableId = getIdFromUUID(ctx, I_AD_Table.Table_Name, treeTableUuid);
				parentTableName = MTable.getTableName(ctx, treeTableId);
				treeId = getDefaultTreeIdFromTableId(Env.getAD_Client_ID(ctx), treeTableId, treeType, getTrxName(ctx));
			}
			int nodeId = getIdFromUUID(ctx, parentTableName, sourceUuid);
			entity.set_ValueOfColumn(I_AD_Tree.COLUMNNAME_AD_Tree_ID, treeId);
			entity.set_ValueOfColumn(I_AD_TreeNode.COLUMNNAME_Node_ID, nodeId);
			entity.set_ValueOfColumn(I_AD_TreeNode.COLUMNNAME_Parent_ID, parentId);
		}
		//	Save
		try {
			beforeSave(entity);
			try {
				entity.saveEx(getTrxName(ctx));
			} catch (Exception e) {
				if(getBooleanValue(atts, IGNORE_WHEN_SAVE_ERROR)) {
					element.skip = true;
					log.warning("Ignored Entity: " + tableName + " - " + entity + " - Error: " + e.getMessage());
					return;
				} else {
					throw e;
				}
			}
			int originalId = entity.get_ID();
			if(!poInfo.hasKeyColumn()) {
				if(tableName.endsWith("_Trl")) {
					originalId = entity.get_ValueAsInt(tableName.replaceAll("_Trl", "") + "_ID");
				} else {
					MTable table = MTable.get(ctx, tableName);
					for(String keyColumn : table.getKeyColumns()) {
						originalId = entity.get_ValueAsInt(keyColumn);
						if(originalId > 0) {
							break;
						}
					}
				}
			}
			recordLog (ctx, 1, entity.get_ValueAsString(I_AD_Element.COLUMNNAME_UUID), getTagName(entity), originalId,
						backupId, objectStatus,
						I_AD_EntityType.Table_Name, I_AD_EntityType.Table_ID);
			//	After Save
			afterSave(entity);
		} catch (Exception e) {
			if(backupId > 0) {
				recordLog (ctx, 0, entity.get_ValueAsString(I_AD_Element.COLUMNNAME_UUID), getTagName(entity), backupId,
						backupId, objectStatus,
						I_AD_EntityType.Table_Name, I_AD_EntityType.Table_ID);
			}
			throw new POSaveFailedException(e);
		}
	}
	
	/**
	 * Get from table Id, can find on cache
	 * @param clientId
	 * @param tableId
	 * @param elementId
	 * @param trxName
	 * @return
	 */
	public static int getDefaultTreeIdFromTableId(int clientId, int tableId, String treeType, String trxName) {
		if(tableId <= 0) {
			return -1;
		}
		//	
		String whereClause = new String();
		int treeId = 0;
		//	Valid Element
		if (!Util.isEmpty(treeType)) {
			whereClause = " AND TreeType = '" + treeType + "'";
		}
		String sql = "SELECT tr.AD_Tree_ID "
				+ "FROM AD_Tree tr "
				+ "WHERE tr.AD_Client_ID = ? "
				+ "AND tr.AD_Table_ID = ? "
				+ "AND tr.IsActive='Y' "
				+ whereClause
				+ "ORDER BY tr.IsDefault DESC";
			//	Get Tree
		treeId = DB.getSQLValue(trxName, sql, clientId, tableId);
		
		//	Default Return
		return treeId;
	}   //  getDefaultTreeIdFromTableId

	/**
	 * Before Save trigger
	 * @param entity
	 */
	protected void beforeSave(PO entity) {
		//	
	}
	
	/**
	 * After Save trigger
	 * @param entity
	 */
	protected void afterSave(PO entity) {
		//	
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}
	
	/**
	 * With default include parents
	 */
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		create(ctx, document, null, true, null);
	}
	
	/**
	 * With PO and include parents by default
	 * @param ctx
	 * @param document
	 * @param entity
	 * @throws SAXException
	 */
	public void create(Properties ctx, TransformerHandler document, PO entity) throws SAXException {
		create(ctx, document, entity, false, null);
	}
	
	/**
	 * Create Attributes
	 */
	public void create(Properties ctx, TransformerHandler document, PO entity, boolean includeParents, List<String> excludedParentList) throws SAXException {
		create(ctx, document, entity, includeParents, excludedParentList, false);
	}
	
	/**
	 * Create Attributes with parent flag
	 */
	private void create(Properties ctx, TransformerHandler document, PO entity, boolean includeParents, List<String> excludedParentList, boolean isFromParent) throws SAXException {
		int tableId = 0;
		String tableName = null;
		int recordId = 0;
		if(entity != null) {
			tableId = entity.get_Table_ID();
			tableName = entity.get_TableName();
			recordId = entity.get_ID();
		} else {
			tableId = Env.getContextAsInt(ctx, TABLE_ID_TAG);
			recordId = Env.getContextAsInt(ctx, RECORD_ID_TAG);
		}
		if(tableId <= 0) {
			return;
		}
		//	Instance PO
		if(entity == null) {
			entity = getCreatePO(ctx, tableId, recordId, null);
		}
		if(entity == null) {
			entity = getCreatePO(ctx, tableName, recordId, null);
		}
		if(entity == null) {
			return;
		}
		//	Validate if was processed
		String key = entity.get_UUID();
		if (list.contains(key)) {
			return;
		}
		list.add(key);
		//	Create parents
		if(includeParents) {
			createParent(ctx, document, entity, excludedParentList);
		}
		AttributesImpl defaultAttributes = customValues.getAttributes();
		AttributesImpl atts = createBinding(ctx, entity, defaultAttributes);
		if(atts != null) {
			document.startElement("", "", getTagName(entity), atts);
			document.endElement("", "", getTagName(entity));
		}
		//	 Create translation
		createTranslation(ctx, document, entity);
		//	Create Node
		createTreeNode(ctx, document, entity);
		if(!isFromParent) {
			customValues.cleanValues();
		}
	}
	
	/**
	 * Create Parent from lookup columns
	 * @param ctx
	 * @param document
	 * @param entity
	 * @throws SAXException
	 */
	private void createParent(Properties ctx, TransformerHandler document, PO entity, List<String> excludedParentList) throws SAXException {
		POInfo poInfo = POInfo.getPOInfo(entity.getCtx(), entity.get_Table_ID());
		for(int index = 0; index < poInfo.getColumnCount(); index++) {
			//	No SQL
			if(poInfo.isVirtualColumn(index)) {
				continue;
			}
			//	No Encrypted
			if(poInfo.isEncrypted(index)) {
				continue;
			}
			String columnName = poInfo.getColumnName(index);
			//	No user log
			if(columnName.equals(I_AD_Element.COLUMNNAME_Created)
					|| columnName.equals(I_AD_Element.COLUMNNAME_CreatedBy)
					|| columnName.equals(I_AD_Element.COLUMNNAME_Updated)
					|| columnName.equals(I_AD_Element.COLUMNNAME_UpdatedBy)
					|| columnName.equals(I_AD_Element.COLUMNNAME_AD_Client_ID)
					|| columnName.equals(I_AD_Element.COLUMNNAME_AD_Org_ID)) {
				continue;
			}
			String parentTableName = getParentTableName(ctx, poInfo.getAD_Column_ID(columnName), poInfo.getColumnDisplayType(index));
			if(Util.isEmpty(parentTableName)) {
				continue;
			}
			int referenceId = entity.get_ValueAsInt(columnName);
			if(referenceId <= 0) {
				continue;
			}
			//	Verify Exclusion
			if(excludedParentList != null) {
				if(excludedParentList.contains(parentTableName)) {
					continue;
				}
			}
			PO parentEntity = MTable.get(ctx, parentTableName).getPO(referenceId, null);
			if(parentEntity == null
					|| parentEntity.get_ID() <= 0) {
				continue;
			}
			//	Validate Access Level
			if(!isValidAccess(MTable.get(ctx, entity.get_Table_ID()).getAccessLevel(), 
					MTable.get(ctx, parentEntity.get_Table_ID()).getAccessLevel(),
					entity.getAD_Client_ID() == parentEntity.getAD_Client_ID())) {
				continue;
			}
			//	For others
			create(ctx, document, parentEntity, true, excludedParentList, true);
		}
	}
	
	/**
	 * Validate Access Level
	 * @param accessLevel
	 * @param parentAccessLevel
	 * @param isSameClient
	 * @return
	 */
	private boolean isValidAccess(String accessLevel, String parentAccessLevel, boolean isSameClient) {
		//	Is Same client
		if(isSameClient) {
			return true;
		}
		//	Validate system
		if((parentAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemOnly))
				&& !accessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemOnly)) {
			return false;
		}
		//	Ok
		return true;
	}
	
	/**
	 * Get Tag name from PO
	 * @param entity
	 * @return
	 */
	private String getTagName(PO entity) {
		return TAG_Name + "_" + entity.get_TableName();
	}
	
	/**
	 * Create PO from Table and Record ID
	 * @param ctx
	 * @param tableId
	 * @param recordId
	 * @param trxName
	 * @return
	 */
	private PO getCreatePO(Properties ctx, String tableName, int recordId, String trxName) {
		return MTable.get(ctx, tableName).getPO(recordId, trxName);
	}
	
	/**
	 * Get From Table ID
	 * @param ctx
	 * @param tableId
	 * @param recordId
	 * @param trxName
	 * @return
	 */
	private PO getCreatePO(Properties ctx, int tableId, int recordId, String trxName) {
		return MTable.get(ctx, tableId).getPO(recordId, trxName);
	}
	
	/**
	 * Create PO multy-key record
	 * @param ctx
	 * @param language
	 * @param uuId
	 * @param parentId
	 * @param trxName
	 * @return
	 */
	private PO getCreatePOForMultyKey(Properties ctx, POInfo poInfo, Attributes atts, String trxName) {
		MTable table = MTable.get(ctx, poInfo.getTableName());
		String uuid = getUUIDValue(atts, poInfo.getTableName());
		PO entity = new Query(ctx, poInfo.getTableName(), "UUID = ?", trxName)
				.setParameters(uuid)
				.first();
		if(entity == null) {
			List<Object> parameters = new ArrayList<>();
			StringBuffer keyColumnNamesForWhereClause = new StringBuffer();
			for(String keyColumn : table.getKeyColumns()) {
				if(keyColumnNamesForWhereClause.length() > 0) {
					keyColumnNamesForWhereClause.append(" AND ");
				}
				keyColumnNamesForWhereClause.append(keyColumn).append(" = ?");
				String parentUuid = getUUIDValue(atts, keyColumn);
				if(!Util.isEmpty(parentUuid)) {
					String parentTableName = getParentTableName(ctx, poInfo.getAD_Column_ID(keyColumn), poInfo.getColumnDisplayType(poInfo.getColumnIndex(keyColumn)));
					if(Util.isEmpty(parentTableName)) {
						//	For tree
						String treeUuid = atts.getValue(AttributeFiller.getUUIDAttribute(I_AD_Tree.COLUMNNAME_AD_Tree_ID));
						String treeType = atts.getValue(I_AD_Tree.COLUMNNAME_TreeType);
						String treeTableUuid = atts.getValue(AttributeFiller.getUUIDAttribute(I_AD_Tree.COLUMNNAME_AD_Table_ID));
						String sourceUuid = atts.getValue(AttributeFiller.getUUIDAttribute(I_AD_TreeNode.COLUMNNAME_Node_ID));
						if(!Util.isEmpty(treeUuid)
								&& !Util.isEmpty(sourceUuid)) {
							int treeId = getIdFromUUID(ctx, I_AD_Tree.Table_Name, treeUuid);
							if(treeId > 0) {
								MTree tree = MTree.get(ctx, treeId, getTrxName(ctx));
								MTable sourceTable = MTable.get(ctx, tree.getAD_Table_ID());
								parentTableName = sourceTable.getTableName();
							} else {
								int treeTableId = getIdFromUUID(ctx, I_AD_Table.Table_Name, treeTableUuid);
								parentTableName = MTable.getTableName(ctx, treeTableId);
								treeId = getDefaultTreeIdFromTableId(Env.getAD_Client_ID(ctx), treeTableId, treeType, getTrxName(ctx));
							}
							int nodeId = getIdFromUUID(ctx, parentTableName, sourceUuid);
							if(nodeId <= 0) {
								continue;
							}
						} else {
							continue;
						}
					}
					int parentId = getIdFromUUID(ctx, parentTableName, parentUuid);
					parameters.add(parentId);
				} else { 
					PoFiller filler = new PoFiller(poInfo, atts);
					parameters.add(filler.getValueFromType(keyColumn));
				}
			}
			entity = new Query(ctx, poInfo.getTableName(), keyColumnNamesForWhereClause.toString(), trxName)
					.setParameters(parameters)
					.first();
		}
		//	Create by default
		if(entity == null) {
			entity = new GenericPO(poInfo.getTableName(), ctx, -1, trxName);
		}
		return entity;
	}
	
	/**
	 * Create export from data
	 * @param entity
	 * @param ctx
	 * @param defaultAttributes
	 * @return
	 */
	private AttributesImpl createBinding(Properties ctx, PO entity, AttributesImpl defaultAttributes) {
		AttributesImpl atts = new AttributesImpl();
		if(defaultAttributes != null) {
			atts.setAttributes(defaultAttributes);
		} else {
			atts.clear();
		}
		//	Fill attributes
		POInfo poInfo = POInfo.getPOInfo(entity.getCtx(), entity.get_Table_ID());
		AttributeFiller filler = new AttributeFiller(atts, entity);
		filler.addUUID();
		filler.addString(TABLE_NAME_TAG,entity.get_TableName());
		filler.addInt(TABLE_ID_TAG,entity.get_Table_ID());
		//	Add last updated time
		filler.addLastUpdatedTime();
		for(int index = 0; index < poInfo.getColumnCount(); index++) {
			//	No SQL
			if(poInfo.isVirtualColumn(index)) {
				continue;
			}
			//	No Key if is major that Official ID
			if (poInfo.isKey(index) && entity.get_ID() > PackOut.MAX_OFFICIAL_ID) {
				continue;
			}
			//	No Encrypted
			if(poInfo.isEncrypted(index)) {
				continue;
			}
			String columnName = poInfo.getColumnName(index);
			//	No user log
			if(columnName.equals(I_AD_Element.COLUMNNAME_Created)
					|| columnName.equals(I_AD_Element.COLUMNNAME_CreatedBy)
					|| columnName.equals(I_AD_Element.COLUMNNAME_Updated)
					|| columnName.equals(I_AD_Element.COLUMNNAME_UpdatedBy)
					|| columnName.equals(I_AD_Element.COLUMNNAME_AD_Client_ID)
					|| columnName.equals(I_AD_Element.COLUMNNAME_AD_Org_ID)) {
				continue;
			}
			//	
			filler.add(columnName);
			String foreignUuid = getParentUuid(ctx, poInfo.getAD_Column_ID(columnName), poInfo.getColumnDisplayType(index), columnName, entity);
			if(!Util.isEmpty(foreignUuid)) {
				filler.addString(AttributeFiller.getUUIDAttribute(columnName), foreignUuid);
			}
			//	For Tree Node Tables
			if(columnName.equals(I_AD_Tree.COLUMNNAME_AD_Tree_ID)) {
				int treeId = entity.get_ValueAsInt(columnName);
				if(treeId > 0) {
					int nodeId = entity.get_ValueAsInt(I_AD_TreeNode.COLUMNNAME_Node_ID);
					int parentId = entity.get_ValueAsInt(I_AD_TreeNode.COLUMNNAME_Parent_ID);
					MTree tree = MTree.get(ctx, treeId, null);
					MTable sourceTable = MTable.get(ctx, tree.getAD_Table_ID());
					String sourceUuid = getUUIDFromId(ctx, sourceTable.getTableName(), nodeId);
					String parentUuid = getUUIDFromNodeId(ctx, entity.get_TableName(), treeId, parentId);
					//	Set
					filler.addString(AttributeFiller.getUUIDAttribute(I_AD_TreeNode.COLUMNNAME_Node_ID), sourceUuid);
					filler.addString(AttributeFiller.getUUIDAttribute(I_AD_TreeNode.COLUMNNAME_Parent_ID), parentUuid);
					filler.addString(AttributeFiller.getUUIDAttribute(I_AD_Tree.COLUMNNAME_AD_Table_ID), sourceTable.getUUID());
					filler.addString(I_AD_Tree.COLUMNNAME_TreeType, tree.getTreeType());
				}
			}
		}
		//	Return Attributes
		return atts;
	}
	
	/**
	 * Verify if is elegible for parent
	 * @param ctx
	 * @param columnId
	 * @param displayType
	 * @return
	 */
	private String getParentTableName(Properties ctx, int columnId, int displayType) {
		if(!isLookup(displayType)) {
			return null;
		}
		//	Validate list
		if(DisplayType.List == displayType) {
			return I_AD_Ref_List.Table_Name;
		}
		//	Validate account
		if(DisplayType.Account == displayType) {
			return I_C_ValidCombination.Table_Name;
		}
		//	Validate Location Address
		if(DisplayType.Location == displayType) {
			return I_C_Location.Table_Name;
		}
		//	Validate Locator Warehouse
		if(DisplayType.Locator == displayType) {
			return I_M_Locator.Table_Name;
		}
		//	Create Parent
		MLookupInfo info = MLookupFactory.getLookupInfo(ctx, 0, columnId, displayType);
		if(info == null) {
			return null;
		}
		//	Default
		return info.TableName;
	}
	
	/**
	 * Is lookup include location
	 * @param displayType
	 * @return
	 */
	private boolean isLookup(int displayType) {
		return DisplayType.isLookup(displayType)
				|| DisplayType.Account == displayType
				|| DisplayType.Location == displayType
				|| DisplayType.Locator == displayType
				|| DisplayType.PAttribute == displayType;
	}
	
	/**
	 * Get Parent UUID
	 * @param ctx
	 * @param columnId
	 * @param displayType
	 * @param foreignId
	 * @return
	 */
	private String getParentUuid(Properties ctx, int columnId, int displayType, String columnName, PO entity) {
		String parentTableName = getParentTableName(ctx, columnId, displayType);
		if(Util.isEmpty(parentTableName)) {
			return null;
		}
		int foreignId = entity.get_ValueAsInt(columnName);
		//	Add UUID reference
		String referenceUuid = getUUIDFromId(ctx, parentTableName, foreignId);
		//	
		if(Util.isEmpty(referenceUuid)) {
			return null;
		}
		//	Default
		return referenceUuid;
	}
	
	/**
	 * Get Parent ID from UUID
	 * @param ctx
	 * @param parentTableName
	 * @param uuid
	 * @return
	 */
	private int getParentId(Properties ctx, String parentTableName, String uuid) {
		if(Util.isEmpty(parentTableName)) {
			return -1;
		}
		//	Get
		return getIdFromUUID(ctx, parentTableName, uuid);
	}
	
	/**
	 * Create translation
	 * @param ctx
	 * @param entity
	 * @param document
	 * @throws SAXException 
	 */
	public void createTranslation(Properties ctx, TransformerHandler document, PO entity) throws SAXException {
		if(!MSysConfig.getBooleanValue(HANDLE_TRANSLATION_FLAG, false)){
			return;//translation import option is disabled
		}
		//	Validate table
		String tableName = entity.get_TableName() + "_Trl";
		//	Verify if Table Exist
		MTable table = MTable.get(ctx, tableName);
		if(table == null
				|| Util.isEmpty(table.getTableName())) {
			return;
		}
		//	Where clause
		String whereClause = entity.get_TableName() + "_ID = ? "
						+ "AND EXISTS(SELECT 1 FROM AD_Language l "
						+ "WHERE l.AD_Language = " + tableName + ".AD_Language "
						+ "AND l.IsSystemLanguage = ? "
						+ "AND l.IsBaseLanguage = ?)";
		//	Export
		List<PO> translationList = new Query(ctx, tableName, whereClause, null)
			.setParameters(entity.get_ID(), true, false)
			.setOnlyActiveRecords(true)
			.list();
		//	Create
		for(PO translation : translationList) {
			create(ctx, document, translation, false, null, true);
		}
	}
	
	/**
	 * Create Tree Node
	 * @param ctx
	 * @param entity
	 * @param document
	 * @throws SAXException 
	 */
	public void createTreeNode(Properties ctx, TransformerHandler document, PO entity) throws SAXException {
		int tableId = entity.get_Table_ID();
		if (!MTree.hasTree(tableId)) {
			return;
		}
		int treeId = MTree.getDefaultTreeIdFromTableId(entity.getAD_Client_ID(), tableId, entity.get_ValueAsInt(I_C_ElementValue.COLUMNNAME_C_Element_ID));
		if(treeId < 0) {
			return;
		}
		MTree tree = MTree.get(ctx, treeId, null);
		//	Get Node Table Name
		String treeNodeTableName = tree.getNodeTableName();
		if(Util.isEmpty(treeNodeTableName)) {
			return;
		}
		//	Get Node
		PO nodeEntity = new Query(ctx, treeNodeTableName, "AD_Tree_ID = ? AND Node_ID = ?", null)
			.setParameters(treeId, entity.get_ID())
			.first();
		//	Validate if exists
		if(nodeEntity == null
				|| nodeEntity.get_ID() == 0) {
			return;
		}
		//	Create
		create(ctx, document, nodeEntity, false, null);
	}
}
