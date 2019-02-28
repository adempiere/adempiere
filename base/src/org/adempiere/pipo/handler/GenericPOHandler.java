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

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.PoFiller;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Element;
import org.compiere.model.I_AD_EntityType;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.X_AD_Table;
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
	/**	Static values	*/
	public static final String TABLE_NAME_TAG = "TableNameTag";
	public static final String TABLE_ID_TAG = "TableIdTag";
	public static final String RECORD_ID_TAG = "RecordIdTag";
	public static final String TAG_Name = "GenericPO";
	/**	Tag for column	*/
	public static final String Column_TAG_Name = TAG_Name + "_" + I_AD_Column.Table_Name;
	
	
	@Override
	public void startElement(Properties ctx, Element element) throws SAXException {
		final String elementValue = element.getElementValue();
		final Attributes atts = element.attributes;
		final String tableName = getStringValue(atts, TABLE_NAME_TAG);
		final int tableId = getIntValue(atts, TABLE_ID_TAG, -1);
		log.info(elementValue + " " + tableName);
		//	Get UUID
		String uuid = getUUIDValue(atts, tableName); 
		if(Util.isEmpty(uuid)
			|| tableId == -1) {
			element.skip = true;
		}
		//	Fill attributes
		POInfo poInfo = POInfo.getPOInfo(ctx, tableId);
		String keyColumnName = poInfo.getKeyColumnName();
		int recordId = 0;
		//	Get Record Id
		if(!Util.isEmpty(keyColumnName)) {
			recordId = getIdFromUUID(ctx, tableName, uuid);
		}
		PO entity = getCreatePO(ctx, tableId, recordId, getTrxName(ctx));
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
			if(poInfo.isColumnLookup(index)) {
				String parentNameUuid = AttributeFiller.getUUIDAttribute(columnName);
				String parentUuid = atts.getValue(parentNameUuid);
				if(!Util.isEmpty(parentUuid)) {
					String parentTableName = getParentTableName(ctx, poInfo.getAD_Column_ID(columnName), poInfo.getColumnDisplayType(index));
					if(!Util.isEmpty(parentTableName)) {
						int foreignId = getParentId(ctx, parentTableName, parentUuid);
						if(foreignId != -1) {
							entity.set_ValueOfColumn(columnName, foreignId);
							continue;
						}
					}
				}
			}
			//	Add Standard
			filler.setAttribute(columnName);
		}
		//	Save
		try {
			beforeSave(entity);
			entity.saveEx(getTrxName(ctx));
			recordLog (ctx, 1, entity.get_ValueAsString(I_AD_Element.COLUMNNAME_UUID), getTagName(entity), entity.get_ID(),
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
		create(ctx, document, null, false, null);
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
		int tableId = 0;
		int recordId = 0;
		if(entity != null) {
			tableId = entity.get_Table_ID();
			recordId = entity.get_ID();
		} else {
			tableId = Env.getContextAsInt(ctx, TABLE_ID_TAG);
			recordId = Env.getContextAsInt(ctx, RECORD_ID_TAG);
		}
		if(tableId <= 0
				|| recordId <= 0) {
			return;
		}
		//	Validate if was processed
		String key = tableId + "|" + recordId;
		if (list.contains(key)) {
			return;
		}
		list.add(key);
		//	Instance PO
		if(entity == null) {
			entity = getCreatePO(ctx, tableId, recordId, null);
		}
		//	Create parents
		if(includeParents) {
			createParent(ctx, document, entity, excludedParentList);
		}
		AttributesImpl atts = createMessageBinding(ctx, entity);
		if(atts != null) {
			document.startElement("", "", getTagName(entity), atts);
			document.endElement("", "", getTagName(entity));
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
			if(excludedParentList!= null) {
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
			if(!isValidAccess(MTable.get(ctx, entity.get_Table_ID()).getAccessLevel(), MTable.get(ctx, parentEntity.get_Table_ID()).getAccessLevel())) {
				continue;
			}
			//	For others
			Env.setContext(ctx, GenericPOHandler.TABLE_ID_TAG, parentEntity.get_Table_ID());
			Env.setContext(ctx, GenericPOHandler.RECORD_ID_TAG, parentEntity.get_ID());
			create(ctx, document);
			ctx.remove(GenericPOHandler.TABLE_ID_TAG);
			ctx.remove(GenericPOHandler.RECORD_ID_TAG);
		}
	}
	
	/**
	 * Validate Access Level
	 * @param accessLevel
	 * @param parentAccessLevel
	 * @return
	 */
	private boolean isValidAccess(String accessLevel, String parentAccessLevel) {
		//	Validate system
		if((parentAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemOnly)
				|| parentAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemPlusClient))
				&& !accessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemOnly)
				&& !accessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemPlusClient)) {
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
	private PO getCreatePO(Properties ctx, int tableId, int recordId, String trxName) {
		return MTable.get(ctx, tableId).getPO(recordId, trxName);
	}
	
	/**
	 * Create export from data
	 * @param entity
	 * @param ctx
	 * @return
	 */
	private AttributesImpl createMessageBinding(Properties ctx, PO entity) {
		AttributesImpl atts = new AttributesImpl();
		atts.clear();
		//	Fill attributes
		POInfo poInfo = POInfo.getPOInfo(entity.getCtx(), entity.get_Table_ID());
		AttributeFiller filler = new AttributeFiller(atts, entity);
		filler.addUUID();
		filler.addString(TABLE_NAME_TAG,entity.get_TableName());
		filler.addInt(TABLE_ID_TAG,entity.get_Table_ID());
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
		if(!DisplayType.isLookup(displayType)) {
			return null;
		}
		//	Validate list
		if(DisplayType.List == displayType) {
			return null;
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
}
