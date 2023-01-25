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
 * Copyright (C) 2003-Present E.R.P. Consultores y Asociados, C.A.            *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.spin.queue.notification;

import java.io.File;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Stub for Attachments
 */
public class AttachmentStub {
	/**	Attachment	*/
	private File attachment;
	/**	Embedded Comment	*/
	private String comment;
	
	/**
	 * Default constructor
	 * @param attachment
	 * @param comment
	 */
	public AttachmentStub(File attachment, String comment) {
		this.attachment = attachment;
		this.comment = comment;
	}
	/**
	 * @return the attachment
	 */
	public final File getAttachment() {
		return attachment;
	}
	
	/**
	 * @return the comment
	 */
	public final String getComment() {
		return comment;
	}
}
