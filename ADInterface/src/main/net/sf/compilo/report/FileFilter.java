/**
 *   reportServer for JasperReport.
 *   Copyright (C) 2004  Peter Shen.
 *   Shanghai, China.
 *   Email: zpshen@gmail.com
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *   Contributor: Marco LOMBARDO, Compilo subAdministrator.
 *                lombardo@mayking.com, mar9000@gmail.com
 *                Italy.
 **/
package net.sf.compilo.report;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 	@author 	Peter Shen
 * 	@version 	$Id: FileFilter.java,v 1.2 2005/07/16 02:39:17 pshen Exp $
 **/
public class FileFilter implements FilenameFilter
    {
	    private String reportStart;
        private File directory;
        private String extension[];

        public FileFilter(String reportStart, File directory, String[] extension)
        {
        	this.reportStart = reportStart;
           this.directory = directory;
            this.extension = extension;
        }

        public boolean accept(File file, String name)
        {    	
            if (file.equals( directory))
            {
               	if (name.startsWith( reportStart))
                {
               	    for(int i=0; i<extension.length; i++)
               	    {
               	        int pos = name.lastIndexOf(extension[i]);
               	        if ( (pos!=-1) && (pos==(name.length() - extension[i].length()))) 
               	            return true;
               	    }
                }   
            }
            return false;
        }
    }
