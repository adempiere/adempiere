/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on 28-Jun-2005 by alok
 *
 */
package org.posterita.beans;

import java.util.ArrayList;


public class UDIPair implements Comparable
{
	
		private Integer ID;
		private String key;
		private String value;
        
        private UDIPair pair;
        private ArrayList arr;        
		
		public UDIPair(Integer ID, String value)
		{
			this.ID = ID;
			this.value = value;
		}
        
        public UDIPair(String key, String value)
        {
            this.key = key;
            this.value = value;
        }   
		
		
		public UDIPair(UDIPair pair, ArrayList arr)
		{
			this.pair = pair;
			this.arr = arr;
		}	

        
        
		public ArrayList getArr() {
            return arr;
        }


        public void setArr(ArrayList arr) {
            this.arr = arr;
        }


        public UDIPair getPair() {
            return pair;
        }


        public void setPair(UDIPair pair) {
            this.pair = pair;
        }


        /**
		 * @return Returns the iD.
		 */
		public Integer getID() {
			return ID;
		}
		/**
		 * @param id The iD to set.
		 */
		public void setID(Integer id) {
			ID = id;
		}
		/**
		 * @return Returns the value.
		 */
		public String getValue() {
			return value;
		}
		/**
		 * @param value The value to set.
		 */
		public void setValue(String value) {
			this.value = value;
		}
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Object o) 
		{
			UDIPair pair = (UDIPair) o;

			return this.getValue().compareTo(pair.getValue());
		}
		
		public String getKey()
		{
			return key;
		}

		public void setKey(String key)
		{
			this.key = key;
		}
		
		public String toString()
		{
			String str ="ID:" +getID() + " Value:" 	+getValue();
			return str;
		}
	}

