/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.jsf;

import javax.faces.el.PropertyNotFoundException;
import javax.faces.el.PropertyResolver;
import org.compiere.util.CLogger;

/**
 * this class continues the el lookup initiated by DynamicFieldVariableResolver,
 * resolving the value to the proper name
 * 
 * TODO: this should deduce the tab number from the hidden field id = "tabNo"
 */
public class DynamicFieldPropertyResolver extends PropertyResolver
{
    // yes these should probably be an enum
    public static final String DYNAMIC_VALUE_TOKEN="value";
    public static final String DYNAMIC_LABEL_TOKEN="label";
    public static final String DYNAMIC_TOOLTIP_TOKEN="tooltip";
    public static final String DYNAMIC_COLLECTION_TOKEN="fields";
    // less related to the others, these are more application state than 1:1 with compiere model
    //public static final String DYNAMIC_TAB_TOKEN="tabNo";
    //public static final String DYNAMIC_GRID_TOKEN="gridView";

    private final static CLogger log=CLogger.getCLogger(DynamicFieldPropertyResolver.class);

    private PropertyResolver originalResolver;

    public DynamicFieldPropertyResolver(PropertyResolver propertyResolver)
    {
        originalResolver = propertyResolver;
    }

    public Object getValue(Object o1,Object o2) throws PropertyNotFoundException
    {
    	//log.info("entered getValue(Object,Object), base is type "+o1.getClass().getName()+" property is type "+o2.getClass().getName()+" value "+o2.toString());
        if (o1 instanceof DynamicFieldLookup)
        {
        	//log.info("matches dynamic lookup");
            DynamicFieldLookup dynamicFieldLookup=(DynamicFieldLookup)o1;
            if(o2 instanceof Long)
            {
            	//log.info("looking up tab index "+(Long)o2);
                dynamicFieldLookup.setTabNumber((Long)o2);
                return dynamicFieldLookup;
            }
            else
            {
            	//log.info("looking up named property "+(String)o2);
                String s=(String)o2;
                if(s.equals(DYNAMIC_VALUE_TOKEN))
                {
                    Object o=dynamicFieldLookup.getProxy().getValue();
                    return o;
                }
                else if(s.equals(DYNAMIC_LABEL_TOKEN))
                    return dynamicFieldLookup.getProxy().getLabel();
                else if(s.equals(DYNAMIC_TOOLTIP_TOKEN))
                    return dynamicFieldLookup.getProxy().getTooltip();
                else if(s.equals(DYNAMIC_COLLECTION_TOKEN))
                	return dynamicFieldLookup.getProxies();
                //else if(s.equals(DYNAMIC_TAB_TOKEN))
                //    return dynamicFieldLookup.getTabNo();
                //else if(s.equals(DYNAMIC_GRID_TOKEN))
                //    return dynamicFieldLookup.getGridView();
                else
                {
                    dynamicFieldLookup.setColumnName(s);
                    return dynamicFieldLookup;
                }
            }
        }
        return originalResolver.getValue(o1,o2);
    }

    public Object getValue(Object obj,int i) throws PropertyNotFoundException
    {
        if(obj instanceof DynamicFieldLookup)
            throw new PropertyNotFoundException();
        return originalResolver.getValue(obj,i);
    }

    /**
     * TODO: test this method
     */
    public void setValue(Object base,Object property,Object value) throws PropertyNotFoundException
    {
        if(base instanceof DynamicFieldLookup)
        {
            //log.info("setValue called for column "+((DynamicFieldLookup)base).getColumnName()+" value type "+value.getClass().getName()+" value "+value.toString());
            DynamicFieldLookup dfl=(DynamicFieldLookup)base;
            String s=(String)property;
            if(s.equals(DYNAMIC_VALUE_TOKEN)) // FIXME: this will probably break
            {
                dfl.getProxy().setValue(value.toString());
            }
            else // tabNo fits this case
                log.info("attempted to call set on read-only field: "+((DynamicFieldLookup)base).getColumnName()+" with terminating token "+s);
        }
        else
        {
            originalResolver.setValue(base,property,value);
        }
    }

    public void setValue(Object obj,int i,Object obj1) throws PropertyNotFoundException
    {
        if(obj instanceof DynamicFieldLookup)
        {
            //log.info("setValue with int arg called");
            throw new PropertyNotFoundException();
        }
        else
            originalResolver.setValue(obj,i,obj1);
    }

    // FIXME: this likely needs to be a non constant return for custom lookup (false for value lookup?)
    public boolean isReadOnly(Object base,Object property) throws PropertyNotFoundException
    {
        //log.info("invoked isReadOnly");
        if(base instanceof DynamicFieldLookup)
        {
            // FIXME: lookups obviously have more properties than just a name
            // so we should associate them logically, java enums? or give them to different resolver classes perhaps
            log.info("base id DynamicFieldLookup, property is "+property.toString());
            if(property instanceof String && ( ((String)property).equals("value") || ((String)property).equals("gridView") || ((String)property).equals("tabNo") ))
                return false;
            else
                return true;
        }
        return originalResolver.isReadOnly(base,property);
    }

    public boolean isReadOnly(Object obj,int i) throws PropertyNotFoundException
    {
        //log.info("");
        if(obj instanceof DynamicFieldLookup)
            return true;
        return originalResolver.isReadOnly(obj,i);
    }

    public Class getType(Object base,Object property) throws PropertyNotFoundException
    {
        //log.info("getType called, base is of type "+base.getClass().getName()+" value "+base.toString()+" property is of type "+property.getClass().getName()+" value "+property.toString()+" and column name is "+((DynamicFieldLookup)base).getColumnName());
        // FIXME: this needs to return the type (to convert to from String) of the column
            //return originalResolver.getType(base,property);
        //return java.lang.String.class;
        return ((DynamicFieldLookup)base).getProxy().getType();
    }

    public Class getType(Object obj,int i) throws PropertyNotFoundException
    {
        if(obj instanceof DynamicFieldLookup)
            throw new PropertyNotFoundException();
        return originalResolver.getType(obj,i);
    }
}
