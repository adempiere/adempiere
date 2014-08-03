/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form.tree;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
* @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
* @version 1.0, October 14th 2005
*/
public abstract class CachableTreeCellRenderer extends DefaultTreeCellRenderer {

	private boolean virtual;
	private HashMap cache;
	private CachableTreeCellRenderer complement;
	
	protected abstract void init(Object value);
	
	public CachableTreeCellRenderer() {
	
		this(false);
	}
	
	public CachableTreeCellRenderer(boolean virtual) {
		
		super();

		this.virtual = virtual;
		cache = new HashMap();
	}
    
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        
        	super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
    
        	String name = (String)getFromCache(value);
        	if(name == null) {
        		
        		init(value);
        		name = (String)getFromCache(value);
        	}
        	
        	setName(name);
        	
        	return this;
    }
    
    /*
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    
    	super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
    	
        if(!isVirtual()) {
       
        	if(!isInitialized()) {
        		
        		init(value);
        	}
        	
            return this; 
        }
        else {
        	
        	CachableTreeCellRenderer r = null;
        	try {
        		System.out.println(this.getClass()+" class: "+getClass());
        		r = (CachableTreeCellRenderer)this.getClass().newInstance();
        		r.setVirtual(false);
            	r.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            	complement = (CachableTreeCellRenderer)tree.getCellRenderer();
            	tree.setCellRenderer(r);
        	}
        	catch(Exception e) {
        		
        		e.printStackTrace();
        	}

        	return r;
        }
    }
    */
    
    public boolean isInitialized() {
    	
    	return !cache.isEmpty();
    }
    
    public void addToCache(Object key, Object value) {
    	
    	cache.put(key, value);
    }
    
    public Object getFromCache(Object key) {
    	
    	return cache.get(key);
    }
    
	public boolean isVirtual() {

		return virtual;
	}

	public void setVirtual(boolean on) {
		
		this.virtual = on;
	}
}
