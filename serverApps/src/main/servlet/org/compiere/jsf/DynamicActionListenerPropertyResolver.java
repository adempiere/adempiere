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
 * this class continues the el lookup initiated by DynamicActionListenerVariableResolver,
 * resolving the value to the proper name
 * hmmm, i think this whole class is unnecessary
 */
public class DynamicActionListenerPropertyResolver extends PropertyResolver
{
    private final static CLogger log=CLogger.getCLogger(DynamicActionListenerPropertyResolver.class);

    private PropertyResolver originalResolver;

    public DynamicActionListenerPropertyResolver(PropertyResolver propertyResolver)
    {
        //log.info("entered");
        originalResolver = propertyResolver;
    }

    public Object getValue(Object base,Object property) throws PropertyNotFoundException
    {
        if (base instanceof DynamicActionListenerLookup)
        {
            if(property instanceof Long)
                ((DynamicActionListenerLookup)base).setTabNo((Long)property);
        	return base;
            //throw new PropertyNotFoundException();
        }
        return originalResolver.getValue(base,property);
    }

    public Object getValue(Object base,int index) throws PropertyNotFoundException
    {
        if(base instanceof DynamicActionListenerLookup)
            throw new PropertyNotFoundException();
        return originalResolver.getValue(base,index);
    }

    /**
     * TODO: test this method
     */
    public void setValue(Object base,Object property,Object value) throws PropertyNotFoundException
    {
        //log.info("entered");
        if(base instanceof DynamicActionListenerLookup)
            throw new PropertyNotFoundException("attempted to call set on a read-only field");
        else
        {
            originalResolver.setValue(base,property,value);
        }
    }

    public void setValue(Object obj,int i,Object obj1) throws PropertyNotFoundException
    {
        //log.info("entered");
        if(obj instanceof DynamicActionListenerLookup)
            throw new PropertyNotFoundException();
        else
            originalResolver.setValue(obj,i,obj1);
    }

    public boolean isReadOnly(Object base,Object property) throws PropertyNotFoundException
    {
        //log.info("entered");
        if(base instanceof DynamicActionListenerLookup)
            throw new PropertyNotFoundException();
        return originalResolver.isReadOnly(base,property);
    }

    public boolean isReadOnly(Object obj,int i) throws PropertyNotFoundException
    {
        //log.info("entered");
        if(obj instanceof DynamicActionListenerLookup)
            throw new PropertyNotFoundException();
        return originalResolver.isReadOnly(obj,i);
    }

    // this should probably throw for our object
    public Class getType(Object obj,Object obj1) throws PropertyNotFoundException
    {
        //log.info("entered");
        //if (!(obj instanceof DynamicFieldLookup))
        // hope this works..
            return originalResolver.getType(obj,obj1);
        //else
        //    return com.ibm.faces.databind.SelectItemsWrapper.class;
    }

    public Class getType(Object obj,int i) throws PropertyNotFoundException
    {
        //log.info("entered");
        if(obj instanceof DynamicActionListenerLookup)
            throw new PropertyNotFoundException();
        return originalResolver.getType(obj,i);
    }
}
