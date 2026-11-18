/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.adempiere.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.parsers.DocumentBuilderFactory;

import org.compiere.model.MColumn;
import org.compiere.model.MMigrationData;
import org.compiere.model.MMigrationStep;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Verifies that migration data exports old and new UUID references as XML
 * attributes.
 *
 * @author Carlos Parada, cparada@erpya.com, ERPyA https://erpya.com
 */
class MMigrationDataUUIDXmlTest implements UnitTestTag {

	@Test
	void exportsPortableOldAndNewReferencesAsXmlAttributes() throws Exception {
		MColumn column = mock(MColumn.class);
		when(column.getColumnName()).thenReturn("C_BPartner_ID");

		MMigrationStep parent = mock(MMigrationStep.class);
		when(parent.getAction()).thenReturn(MMigrationStep.ACTION_Update);

		MMigrationData data = mock(MMigrationData.class);
		when(data.getAD_Column()).thenReturn(column);
		when(data.getAD_Column_ID()).thenReturn(2893);
		when(data.getOldValue()).thenReturn("@UUID@C_BPartner:old-uuid");
		when(data.getNewValue()).thenReturn("@UUID@C_BPartner:new-uuid");
		doCallRealMethod().when(data).toXmlNode(eq(parent), any(Document.class));

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element xml = (Element) data.toXmlNode(parent, document);

		assertEquals("C_BPartner_ID", xml.getAttribute("Column"));
		assertEquals("2893", xml.getAttribute("AD_Column_ID"));
		assertEquals("C_BPartner", xml.getAttribute("oldReferenceTable"));
		assertEquals("old-uuid", xml.getAttribute("oldReferenceUUID"));
		assertEquals("C_BPartner", xml.getAttribute("referenceTable"));
		assertEquals("new-uuid", xml.getAttribute("referenceUUID"));
		assertTrue(xml.getTextContent().isEmpty());
	}

	@Test
	void preservesLegacyNumericValuesWithoutUuidAttributes() throws Exception {
		MColumn column = mock(MColumn.class);
		when(column.getColumnName()).thenReturn("C_BPartner_ID");

		MMigrationStep parent = mock(MMigrationStep.class);
		when(parent.getAction()).thenReturn(MMigrationStep.ACTION_Update);

		MMigrationData data = mock(MMigrationData.class);
		when(data.getAD_Column()).thenReturn(column);
		when(data.getAD_Column_ID()).thenReturn(2893);
		when(data.getOldValue()).thenReturn("1000001");
		when(data.getNewValue()).thenReturn("1000002");
		doCallRealMethod().when(data).toXmlNode(eq(parent), any(Document.class));

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element xml = (Element) data.toXmlNode(parent, document);

		assertEquals("1000001", xml.getAttribute("oldValue"));
		assertEquals("1000002", xml.getTextContent());
		assertFalse(xml.hasAttribute("oldReferenceUUID"));
		assertFalse(xml.hasAttribute("referenceUUID"));
	}
}
