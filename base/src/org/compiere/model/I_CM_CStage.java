/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for CM_CStage
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:34.109
     */
    public interface I_CM_CStage 
{

    /** TableName=CM_CStage */
    public static final String Table_Name = "CM_CStage";

    /** AD_Table_ID=866 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = new BigDecimal(6);

    /** Load Meta Data */

    /** Column name CM_CStageLink_ID */
    public static final String COLUMNNAME_CM_CStageLink_ID = "CM_CStageLink_ID";

	/** Set Container Link.
	  * Stage Link to another Container in the Web Project
	  */
	public void setCM_CStageLink_ID (int CM_CStageLink_ID);

	/** Get Container Link.
	  * Stage Link to another Container in the Web Project
	  */
	public int getCM_CStageLink_ID();

    /** Column name CM_CStage_ID */
    public static final String COLUMNNAME_CM_CStage_ID = "CM_CStage_ID";

	/** Set Web Container Stage.
	  * Web Container Stage contains the staging content like images, text etc.
	  */
	public void setCM_CStage_ID (int CM_CStage_ID);

	/** Get Web Container Stage.
	  * Web Container Stage contains the staging content like images, text etc.
	  */
	public int getCM_CStage_ID();

    /** Column name CM_Template_ID */
    public static final String COLUMNNAME_CM_Template_ID = "CM_Template_ID";

	/** Set Template.
	  * Template defines how content is displayed
	  */
	public void setCM_Template_ID (int CM_Template_ID);

	/** Get Template.
	  * Template defines how content is displayed
	  */
	public int getCM_Template_ID();

	public I_CM_Template getI_CM_Template() throws Exception;

    /** Column name CM_WebProject_ID */
    public static final String COLUMNNAME_CM_WebProject_ID = "CM_WebProject_ID";

	/** Set Web Project.
	  * A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public void setCM_WebProject_ID (int CM_WebProject_ID);

	/** Get Web Project.
	  * A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public int getCM_WebProject_ID();

	public I_CM_WebProject getI_CM_WebProject() throws Exception;

    /** Column name ContainerLinkURL */
    public static final String COLUMNNAME_ContainerLinkURL = "ContainerLinkURL";

	/** Set External Link (URL).
	  * External Link (IRL) for the Container
	  */
	public void setContainerLinkURL (String ContainerLinkURL);

	/** Get External Link (URL).
	  * External Link (IRL) for the Container
	  */
	public String getContainerLinkURL();

    /** Column name ContainerType */
    public static final String COLUMNNAME_ContainerType = "ContainerType";

	/** Set Web Container Type.
	  * Web Container Type
	  */
	public void setContainerType (String ContainerType);

	/** Get Web Container Type.
	  * Web Container Type
	  */
	public String getContainerType();

    /** Column name ContainerXML */
    public static final String COLUMNNAME_ContainerXML = "ContainerXML";

	/** Set ContainerXML.
	  * Autogenerated Containerdefinition as XML Code
	  */
	public void setContainerXML (String ContainerXML);

	/** Get ContainerXML.
	  * Autogenerated Containerdefinition as XML Code
	  */
	public String getContainerXML();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsIndexed */
    public static final String COLUMNNAME_IsIndexed = "IsIndexed";

	/** Set Indexed.
	  * Index the document for the internal search engine
	  */
	public void setIsIndexed (boolean IsIndexed);

	/** Get Indexed.
	  * Index the document for the internal search engine
	  */
	public boolean isIndexed();

    /** Column name IsModified */
    public static final String COLUMNNAME_IsModified = "IsModified";

	/** Set Modified.
	  * The record is modified
	  */
	public void setIsModified (boolean IsModified);

	/** Get Modified.
	  * The record is modified
	  */
	public boolean isModified();

    /** Column name IsSecure */
    public static final String COLUMNNAME_IsSecure = "IsSecure";

	/** Set Secure content.
	  * Defines whether content needs to get encrypted
	  */
	public void setIsSecure (boolean IsSecure);

	/** Get Secure content.
	  * Defines whether content needs to get encrypted
	  */
	public boolean isSecure();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

    /** Column name IsValid */
    public static final String COLUMNNAME_IsValid = "IsValid";

	/** Set Valid.
	  * Element is valid
	  */
	public void setIsValid (boolean IsValid);

	/** Get Valid.
	  * Element is valid
	  */
	public boolean isValid();

    /** Column name Meta_Author */
    public static final String COLUMNNAME_Meta_Author = "Meta_Author";

	/** Set Meta Author.
	  * Author of the content
	  */
	public void setMeta_Author (String Meta_Author);

	/** Get Meta Author.
	  * Author of the content
	  */
	public String getMeta_Author();

    /** Column name Meta_Content */
    public static final String COLUMNNAME_Meta_Content = "Meta_Content";

	/** Set Meta Content Type.
	  * Defines the type of content i.e. "text/html;
 charset=UTF-8"
	  */
	public void setMeta_Content (String Meta_Content);

	/** Get Meta Content Type.
	  * Defines the type of content i.e. "text/html;
 charset=UTF-8"
	  */
	public String getMeta_Content();

    /** Column name Meta_Copyright */
    public static final String COLUMNNAME_Meta_Copyright = "Meta_Copyright";

	/** Set Meta Copyright.
	  * Contains Copyright information for the content
	  */
	public void setMeta_Copyright (String Meta_Copyright);

	/** Get Meta Copyright.
	  * Contains Copyright information for the content
	  */
	public String getMeta_Copyright();

    /** Column name Meta_Description */
    public static final String COLUMNNAME_Meta_Description = "Meta_Description";

	/** Set Meta Description.
	  * Meta info describing the contents of the page
	  */
	public void setMeta_Description (String Meta_Description);

	/** Get Meta Description.
	  * Meta info describing the contents of the page
	  */
	public String getMeta_Description();

    /** Column name Meta_Keywords */
    public static final String COLUMNNAME_Meta_Keywords = "Meta_Keywords";

	/** Set Meta Keywords.
	  * Contains the keywords for the content
	  */
	public void setMeta_Keywords (String Meta_Keywords);

	/** Get Meta Keywords.
	  * Contains the keywords for the content
	  */
	public String getMeta_Keywords();

    /** Column name Meta_Language */
    public static final String COLUMNNAME_Meta_Language = "Meta_Language";

	/** Set Meta Language.
	  * Language HTML Meta Tag
	  */
	public void setMeta_Language (String Meta_Language);

	/** Get Meta Language.
	  * Language HTML Meta Tag
	  */
	public String getMeta_Language();

    /** Column name Meta_Publisher */
    public static final String COLUMNNAME_Meta_Publisher = "Meta_Publisher";

	/** Set Meta Publisher.
	  * Meta Publisher defines the publisher of the content
	  */
	public void setMeta_Publisher (String Meta_Publisher);

	/** Get Meta Publisher.
	  * Meta Publisher defines the publisher of the content
	  */
	public String getMeta_Publisher();

    /** Column name Meta_RobotsTag */
    public static final String COLUMNNAME_Meta_RobotsTag = "Meta_RobotsTag";

	/** Set Meta RobotsTag.
	  * RobotsTag defines how search robots should handle this content
	  */
	public void setMeta_RobotsTag (String Meta_RobotsTag);

	/** Get Meta RobotsTag.
	  * RobotsTag defines how search robots should handle this content
	  */
	public String getMeta_RobotsTag();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Notice */
    public static final String COLUMNNAME_Notice = "Notice";

	/** Set Notice.
	  * Contains last write notice
	  */
	public void setNotice (String Notice);

	/** Get Notice.
	  * Contains last write notice
	  */
	public String getNotice();

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name RelativeURL */
    public static final String COLUMNNAME_RelativeURL = "RelativeURL";

	/** Set Relative URL.
	  * Contains the relative URL for the container
	  */
	public void setRelativeURL (String RelativeURL);

	/** Get Relative URL.
	  * Contains the relative URL for the container
	  */
	public String getRelativeURL();

    /** Column name StructureXML */
    public static final String COLUMNNAME_StructureXML = "StructureXML";

	/** Set StructureXML.
	  * Autogenerated Containerdefinition as XML Code
	  */
	public void setStructureXML (String StructureXML);

	/** Get StructureXML.
	  * Autogenerated Containerdefinition as XML Code
	  */
	public String getStructureXML();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();
}
