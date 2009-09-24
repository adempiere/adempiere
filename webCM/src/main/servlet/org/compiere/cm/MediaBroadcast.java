/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.cm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MImage;
import org.compiere.model.MMedia;
import org.compiere.util.WebEnv;

/**
 *  Broadcast Servlet
 *
 *  @author $Author$
 *  @version  $Id$
 */
@SuppressWarnings("serial")
public class MediaBroadcast extends HttpServletCM
{
	/**
	 * Process the HTTP Get request
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//  Create New Session
		HttpSession sess = request.getSession(true);
		sess.setMaxInactiveInterval(WebEnv.TIMEOUT);

		if (configLoaded && !fatalError) {
			String requestURL = request.getRequestURL().toString();
			String serverName = request.getServerName();
			String baseURL = requestURL.substring(0,requestURL.indexOf(serverName)+serverName.length()+6)+request.getContextPath();
			String relativeURL = requestURL.substring(baseURL.length());
			// If the relativeURL still contains / we will simply strip them off...
			if (relativeURL.indexOf('/')>=0) 
				relativeURL = relativeURL.substring(relativeURL.lastIndexOf('/')+1);
			
			// We should have only an ID before the first dot.
			Integer mediaID = null;
			try {
				if (relativeURL.indexOf('.')>=0) {
					mediaID = Integer.parseInt(relativeURL.substring(0,relativeURL.indexOf('.')));
				} else {
					mediaID = Integer.parseInt(relativeURL);
				}
			} catch (NumberFormatException ne) {
				
			}
			
			if (mediaID!=null && mediaID.intValue()>0) {
			
				MMedia thisMedia = new org.compiere.model.MMedia(webProjectCache.getCtx(),mediaID,null);
				if (thisMedia!=null && thisMedia.get_ID()>0) {
					if (thisMedia.getMediaType ().equals ("CSS")) {
						response.setContentType("text/css");
						// Text Content will get handled via direct Stream
						response.setContentLength (thisMedia.getContentText ().length ());
						PrintWriter out;
						out = response.getWriter ();
						out.print (thisMedia.getContentText ());
						out.close ();
					} else {
						response.setContentType(thisMedia.getMediaType());
						// Binary / Image content will get handled here
						MImage thisImage = thisMedia.getImage();
						if (thisImage != null) {
							response.setContentLength(thisImage.getData().length);

							// Open the file and output streams
							byte[] buffer = thisImage.getData();
							ByteArrayInputStream in = new ByteArrayInputStream(buffer);
							OutputStream out = response.getOutputStream();

							// Copy the contents of the file to the output stream
							byte[] buf = new byte[1024];
							int count = 0;
							while ((count = in.read(buf)) >= 0) {
								out.write(buf, 0, count);
							}
							in.close();
							out.close();
						}
					}
				} else {
					response.sendError(404);
				}
			} else {
				response.sendError(404);
			}
		} else if (fatalError) {
			response.sendError(500, ErrorMessage);
		}
	}   //  doGet


	/**
	 * Process the HTTP Post request
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		doGet(request, response);
	}   //  doPost

}   //  Broadcast
