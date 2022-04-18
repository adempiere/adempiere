/************************************************************************************
 * Copyright (C) 2012-2020 E.R.P. Consultores y Asociados, C.A.                     *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                     *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 2 of the License, or                *
 * (at your option) any later version.                                              *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the                     *
 * GNU General Public License for more details.                                     *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.	If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.adempiere.util.rpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.compiere.model.MClient;
import org.compiere.model.MEXPFormat;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Persistence Object wrapper for data
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class EntityWrapper {

	/** Map for key and values  */
    private Map<String, Object> attributes = new HashMap<String, Object>();
    /** Static constant for ID */
    public static final String ID_KEY = "Record_ID";
    /** Static constant for Revision */
    public static final String UUID_KEY = "UUID";
    /**	Table Name	*/
    public static final String TABLE_NAME_KEY = "TableName";
    /**	Client Attributes	*/
    public static final String CLIENT_VALUE_KEY = "AD_Client_Value";
    public static final String CLIENT_UUID_KEY = "AD_Client_UUID";
    /**	Export Format	*/
    public static final String EXPORT_FORMAT_VALUE_KEY = "EXP_Format_Value";
    public static final String EXPORT_FORMAT_UUID_KEY = "EXP_Format_UUID";
    public static final String EXPORT_FORMAT_DESCRIPTION_KEY = "EXP_Format_Description";
    public static final String EXPORT_FORMAT_VERSION_KEY = "Version";
    /**	Replication Mode	*/
    public static final String REPLICATION_MODE_KEY = "ReplicationMode";
    /**	Replication Event	*/
    public static final String REPLICATION_EVENT_KEY = "ReplicationEvent";
    /**	Replication Type	*/
    public static final String REPLICATION_TYPE_KEY = "ReplicationType";
    /**	Transaction Name	*/
    private String transactionName;
    /**	Context	*/
    private Properties context;
    /**	List of tables	*/
    private Map<String, Boolean> tablesMap = new HashMap<String, Boolean>();
	
	/**
	 * Set attributes for wrapper
	 * @param exportFormat
	 * @param client
	 * @param replicationMode
	 * @param replicationType
	 * @param replicationEvent
	 */
	public void setAttributes(MEXPFormat exportFormat, MClient client, int replicationMode, String replicationType, int replicationEvent) {
		setExportFormatAttributes(exportFormat);
		setClientAttributes(client);
		setReplicationMode(replicationMode);
		setReplicationType(replicationType);
		setReplicationEvent(replicationEvent);
	}
    
	/**
	 * Set client attributes
	 * @param client
	 */
	public void setClientAttributes(MClient client) {
		setValue(CLIENT_VALUE_KEY, client.getValue());
		setValue(CLIENT_UUID_KEY, client.getUUID());
	}
	
	/**
	 * Set attributes from export format
	 * @param exportFormat
	 */
	public void setExportFormatAttributes(MEXPFormat exportFormat) {
		setValue(EXPORT_FORMAT_VALUE_KEY, exportFormat.getValue());
		setValue(EXPORT_FORMAT_UUID_KEY, exportFormat.getUUID());
		if(!Util.isEmpty(exportFormat.getDescription())) {
			setValue(EXPORT_FORMAT_DESCRIPTION_KEY, exportFormat.getDescription());
		}
		setValue(EXPORT_FORMAT_VERSION_KEY, exportFormat.getVersion());
	}
	
	/**
	 * Get export format Value
	 * @return
	 */
	public String getExportFormatValue() {
		return getValueAsString(EXPORT_FORMAT_VALUE_KEY);
	}
	
	/**
	 * Get export format UUID
	 * @return
	 */
	public String getExportFormatUuid() {
		return getValueAsString(EXPORT_FORMAT_UUID_KEY);
	}
	
	/**
	 * Get export format Description
	 * @return
	 */
	public String getExportFormatDescription() {
		return getValueAsString(EXPORT_FORMAT_DESCRIPTION_KEY);
	}
	
	/**
	 * Get export format Version
	 * @return
	 */
	public String getExportFormatVersion() {
		return getValueAsString(EXPORT_FORMAT_VERSION_KEY);
	}
	
	/**
	 * Get client Value
	 * @return
	 */
	public String getClientValue() {
		return getValueAsString(CLIENT_VALUE_KEY);
	}
	
	/**
	 * Get client UUID
	 * @return
	 */
	public String getClientUuid() {
		return getValueAsString(CLIENT_UUID_KEY);
	}
	
	/**
	 * Set replication mode
	 * @param replicationMode
	 */
	public void setReplicationMode(Integer replicationMode) {
		if(replicationMode != null) {
			setValue(REPLICATION_MODE_KEY, replicationMode);
		}
	}
	
	/**
	 * Get Replication Mode
	 * @return
	 */
	public int getReplicationMode() {
		return getValueAsInt(REPLICATION_MODE_KEY);
	}
	
	/**
	 * Set replication event
	 * @param replicationEvent
	 */
	public void setReplicationEvent(Integer replicationEvent) {
		if(replicationEvent != null) {
			setValue(REPLICATION_EVENT_KEY, replicationEvent);
		}
	}
	
	/**
	 * Get Replication event
	 * @return
	 */
	public int getReplicationEvent() {
		return getValueAsInt(REPLICATION_EVENT_KEY);
	}
	
	/**
	 * Set replication type
	 * @param replicationType
	 */
	public void setReplicationType(String replicationType) {
		if(replicationType != null) {
			setValue(REPLICATION_TYPE_KEY, replicationType);
		}
	}
	
	/**
	 * Get Replication Type
	 * @return
	 */
	public String getReplicationType() {
		return getValueAsString(REPLICATION_TYPE_KEY);
	}
	
    /**
     * Add a child for entity
     * @param child
     */
    public void addChild(String key, EntityWrapper child) {
    	if(Util.isEmpty(child.getTableName())) {
    		return;
    	}
    	List<EntityWrapper> children = null;
    	if(attributes.containsKey(key)) {
    		children = getChildren(key);
    	} else {
    		children = new ArrayList<EntityWrapper>();
    	}
    	//	Add new child
    	children.add(child);
    	attributes.put(key, children);
    	tablesMap.put(child.getTableName(), true);
    }
    
    /**
     * Set reference
     * @param key
     * @param reference
     */
    public void setReference(String key, EntityWrapper reference) {
    	if(Util.isEmpty(reference.getTableName())) {
    		return;
    	}
    	attributes.put(key, reference);
    	tablesMap.put(reference.getTableName(), true);
    }
    
    /**
     * Get reference
     * @param key
     * @return
     */
    public EntityWrapper getReference(String key) {
    	Object value = attributes.get(key);
    	if(value instanceof EntityWrapper) {
    		return (EntityWrapper) value;
    	}
    	return null;
    }
    
    /**
     * Get Map with key and values, used for save data
     * @return
     */
    public Map<String, Object> getMap() {
        return attributes;
    }

    /**
     * Set Map (Used only for populate from DB)
     * @param map
     */
    public void setMap(Map<String, Object> map) {
        attributes.putAll(map);
    }

    /**
     * Get Identifier for this object
     * @return
     */
    public int getId() {
        return getValueAsInt(attributes.get(ID_KEY));
    }

    /**
     * Set ID for entity
     * @param id
     */
    public void setId(int id) {
        attributes.put(ID_KEY, id);
    }
    
    /**
     * Get UUID for this object
     * @return
     */
    public String getUuid() {
        return getValueAsString(attributes.get(UUID_KEY));
    }

    /**
     * Set UUID for entity
     * @param uuid
     */
    public void setUuid(String uuid) {
        attributes.put(UUID_KEY, uuid);
    }
    
    /**
     * Get Table Name for this object
     * @return
     */
    public String getTableName() {
        return getValueAsString(attributes.get(TABLE_NAME_KEY));
    }

    /**
     * Set Table Name for entity
     * @param tableName
     */
    public void setTableName(String tableName) {
        attributes.put(TABLE_NAME_KEY, tableName);
        tablesMap.put(tableName, false);
    }
    
    /**
     * Get List of tables including children
     * @return
     */
    public List<String> getTablesList() {
    	return new ArrayList<>(tablesMap.keySet());
    }


    /**************************************************************
     * Helper Methods
     *************************************************************/

    /**
     * Get a value from map
     * @param key
     * @return
     */
    public Object getValueAsObject(String key) {
        return attributes.get(key);
    }


    /**
     *  Get Value as int
     *  @param key or column
     *  @return int value or 0
     */
    public int getValueAsInt(String key) {
        return getValueAsInt(attributes.get(key));
    }   //  getValueAsInt

    /**
     * 	Get Column Value
     *	@param key name
     *	@return value or ""
     */
    public String getValueAsString (String key) {
        return getValueAsString(attributes.get(key));
    }	//	get_ValueAsString

    /**
     * Get value as Boolean
     * @param key
     * @return boolean value
     */
    public boolean getValueAsBoolean(String key) {
        return getValueAsBoolean(attributes.get(key));
    }

    /**
     * Get value as Timestamp
     * @param key
     * @return boolean value
     */
    public Timestamp getValueAsTimestamp(String key) {
        return getValueAsTimestamp(attributes.get(key));
    }

    /**
     * For BigDecimal
     * @param key
     * @return BigDecimal
     */
    public BigDecimal getValueAsBigDecimal(String key) {
        return getValueAsBigDecimal(attributes.get(key));
    }

    /**
     * Get Database value as list
     * @param key
     * @return
     */
    public <T> List<T> getValueAsList(String key) {
        return getValueAsList(attributes.get(key));
    }
    
    /**
     * Get Database value as list
     * @param key
     * @return
     */
    public List<EntityWrapper> getChildren(String key) {
        return getValueAsList(attributes.get(key));
    }

    /**************************************************************************
     * 	Set AD_Client
     * 	@param clientId client
     */
    protected final void setClientId(int clientId) {
        setValueNoCheck("AD_Client_ID", Integer.valueOf(clientId));
    }	//	setAD_Client_ID

    /**
     * 	Get AD_Client
     * 	@return AD_Client_ID
     */
    public final int getClientId() {
        return getValueAsInt("AD_Client_ID");
    }	//	getClientId

    /**
     * 	Set AD_Org
     * 	@param orgId org
     */
    public final void setOrgId(int orgId) {
        setValueNoCheck("AD_Org_ID", Integer.valueOf(orgId));
    }	//	setOrgId

    /**
     * 	Get AD_Org
     * 	@return AD_Org_ID
     */
    public int getOrgId() {
    	return getValueAsInt("AD_Org_ID");
    }	//	getOrgId

    /**
     * 	Overwrite Client Org if different
     *	@param clientId client
     *	@param orgId org
     */
    protected void setClientOrg (int clientId, int orgId) {
        if (clientId != getClientId())
            setClientId(clientId);
        if (orgId != getOrgId())
            setOrgId(orgId);
    }	//	setClientOrg

    /**
     * 	Overwrite Client Org if different
     *	@param entity persistent object
     */
    protected void setClientOrg (EntityWrapper entity) {
        setClientOrg(entity.getClientId(), entity.getOrgId());
    }	//	setClientOrg

    /**
     * 	Set Active
     * 	@param active active
     */
    public final void setIsActive (boolean active) {
        setValue("IsActive", Boolean.valueOf(active));
    }	//	setActive

    /**
     *	Is Active
     *  @return is active
     */
    public final boolean isActive() {
        return getValueAsBoolean("IsActive");
    }	//	isActive

    /**
     * 	Get Created
     * 	@return created
     */
    final public Timestamp getCreated() {
        return getValueAsTimestamp("Created");
    }	//	getCreated

    /**
     * 	Get Updated
     *	@return updated
     */
    final public Timestamp getUpdated() {
        return getValueAsTimestamp("Updated");
    }	//	getUpdated

    /**
     * 	Get CreatedBy
     * 	@return AD_User_ID
     */
    public final int getCreatedBy() {
        return getValueAsInt("CreatedBy");
    }	//	getCreateddBy

    /**
     * 	Get UpdatedBy
     * 	@return AD_User_ID
     */
    public final int getUpdatedBy() {
        return getValueAsInt("UpdatedBy");
    }	//	getUpdatedBy

    /**
     * 	Set UpdatedBy
     * 	@param userId user
     */
    protected final void setUpdatedBy (int userId) {
        setValueNoCheck("UpdatedBy", Integer.valueOf(userId));
    }	//	setAD_User_ID

    /**
     * Set Value and not check it
     * @param key
     * @param value
     */
    protected final void setValueNoCheck(String key, Object value) {
        attributes.put(key, value);
    }

    /**
     * Set value for all
     * @param key
     * @param value
     */
    public final void setValue(String key, Object value) {
        attributes.put(key, value);
    }
    
    /**
     *  Get Value as int
     *  @param Value for cast
     *  @return int value or 0
     */
    private int getValueAsInt(Object value) {
        //
        if (value instanceof Integer)
            return ((Integer)value).intValue();
        try {
            return Integer.parseInt(value.toString());
        } catch (Exception ex) {
            return 0;
        }
    }   //  getValueAsInt

    /**
     * 	Get Column Value
     *	@param key name
     *	@return value or ""
     */
    private String getValueAsString(Object value) {
        if (value == null)
            return null;
        return value.toString();
    }	//	get_ValueAsString

    /**
     * Get value as Boolean
     * @param Value for cast
     * @return boolean value
     */
    private boolean getValueAsBoolean(Object value) {
        if (value != null) {
            if (value instanceof Boolean)
                return ((Boolean)value).booleanValue();
            return "Y".equals(value);
        }
        return false;
    }

    /**
     * Get value as big decimal
     * @param value
     * @return
     */
    private BigDecimal getValueAsBigDecimal(Object value) {
        BigDecimal bigDecimalValue = Env.ZERO;
        if (value != null) {
            if (value instanceof BigDecimal) {
                bigDecimalValue = (BigDecimal) value;
                return bigDecimalValue;
            } else if(value instanceof Double) {
                Double doubleValue = (Double) value;
                //
                bigDecimalValue = new BigDecimal(doubleValue);
            } else if(value instanceof Float) {
                Float floatValue = (Float) value;
                //
                bigDecimalValue = new BigDecimal(floatValue);
            } else if(value instanceof Integer) {
                Integer integerValue = (Integer) value;
                //
                bigDecimalValue = new BigDecimal(integerValue);
            }
        }
        //  Default
        return bigDecimalValue;
    }

    /**
     * Get value as Timestamp
     * @param Value for cast
     * @return boolean value
     */
    public static Timestamp getValueAsTimestamp(Object value) {
        if (value != null) {
            if (value instanceof Timestamp) {
                return (Timestamp) value;
            } else if(value instanceof Long) {
                return new Timestamp((Long) value);
            }
        }
        return null;
    }

    /**
     * Get Database value as list
     * @param Value for cast
     * @return
     */
    @SuppressWarnings("unchecked")
	private <T> List<T> getValueAsList(Object value) {
        if (value != null) {
            if (value instanceof List)
                return ((List<T>) value);
        }
        return null;
    }

	/**
	 * @return the transactionName
	 */
	public final String getTransactionName() {
		return transactionName;
	}

	/**
	 * @param transactionName the transactionName to set
	 */
	public final void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	/**
	 * @return the context
	 */
	public final Properties getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public final void setContext(Properties context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "EntityWrapper [attributes=" + attributes + "]";
	}
}
