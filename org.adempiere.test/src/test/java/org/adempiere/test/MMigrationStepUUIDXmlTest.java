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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilderFactory;

import org.compiere.model.MColumn;
import org.compiere.model.MMigrationData;
import org.compiere.model.MMigrationStep;
import org.compiere.model.MTable;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Verifies that migration step XML retains legacy IDs and exports UUID
 * identity together with every column of a composite key.
 *
 * @author Carlos Parada, cparada@erpya.com, ERPyA https://erpya.com
 */
class MMigrationStepUUIDXmlTest implements UnitTestTag {

	@Test
	void exportsUuidIdentityAndBothCompositeKeyValues() throws Exception {
		MMigrationStep step = migrationStep("AD_Field_Trl", 1000001,
				"record-uuid",
				migrationData("AD_Field_ID", 100, "@UUID@AD_Field:parent-uuid"),
				migrationData("AD_Language", 101, "es_MX"),
				migrationData("UUID", 102, "record-uuid"));

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element xml = (Element) step.toXmlNode(document);
		Element po = (Element) xml.getElementsByTagName("PO").item(0);
		NodeList data = po.getElementsByTagName("Data");

		assertEquals("record-uuid", po.getAttribute("Record_UUID"));
		assertEquals(3, data.getLength());
		Element parentKey = (Element) data.item(0);
		assertEquals("AD_Field_ID", parentKey.getAttribute("Column"));
		assertEquals("AD_Field", parentKey.getAttribute("referenceTable"));
		assertEquals("parent-uuid", parentKey.getAttribute("referenceUUID"));
		Element languageKey = (Element) data.item(1);
		assertEquals("AD_Language", languageKey.getAttribute("Column"));
		assertEquals("es_MX", languageKey.getTextContent());
		assertEquals("record-uuid", ((Element)data.item(2)).getTextContent());
	}

	@Test
	void exportsLegacyRecordIdWithoutUuidIdentity() throws Exception {
		MMigrationStep step = migrationStep("AD_Field_Trl", 1000001, null,
				migrationData("AD_Language", 101, "es_MX"),
				migrationData("UUID", 102, "legacy-data-uuid"));

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element xml = (Element) step.toXmlNode(document);
		Element po = (Element) xml.getElementsByTagName("PO").item(0);

		assertEquals("1000001", po.getAttribute("Record_ID"));
		assertFalse(po.hasAttribute("Record_UUID"));
		assertEquals(2, po.getElementsByTagName("Data").getLength());
		Method getRecordUUID = MMigrationStep.class.getDeclaredMethod("getRecordUUID", boolean.class);
		getRecordUUID.setAccessible(true);
		assertNull(getRecordUUID.invoke(step, false));
	}

	@Test
	void retainsExplicitUuidIdentityAfterStepReload() throws Exception {
		MMigrationStep step = migrationStep("AD_Field", 1000001, null,
				migrationData("UUID", 102, "record-uuid"));
		when(step.getSQLStatement()).thenReturn("@Record_UUID@record-uuid");
		Method getRecordUUID = MMigrationStep.class.getDeclaredMethod("getRecordUUID", boolean.class);
		getRecordUUID.setAccessible(true);
		assertEquals("record-uuid", getRecordUUID.invoke(step, false));
	}

	@Test
	void usesPreviousUuidToFindAnUpdatedRecord() throws Exception {
		MColumn column = mock(MColumn.class);
		when(column.getColumnName()).thenReturn("UUID");
		MMigrationData datum = mock(MMigrationData.class);
		when(datum.getAD_Column()).thenReturn(column);
		when(datum.getOldValue()).thenReturn("previous-uuid");
		MMigrationStep step = migrationStep("AD_Field", 1000001, "new-uuid", datum);
		Method getRecordUUID = MMigrationStep.class.getDeclaredMethod("getRecordUUID", boolean.class);
		getRecordUUID.setAccessible(true);

		assertEquals("previous-uuid", getRecordUUID.invoke(step, true));
		assertEquals("new-uuid", getRecordUUID.invoke(step, false));
	}

	private static MMigrationStep migrationStep(String tableName, int recordId,
			String recordUUID, MMigrationData... data) throws Exception {
		MTable table = mock(MTable.class);
		when(table.getTableName()).thenReturn(tableName);
		MMigrationStep step = mock(MMigrationStep.class);
		when(step.getStepType()).thenReturn(MMigrationStep.STEPTYPE_ApplicationDictionary);
		when(step.getAction()).thenReturn(MMigrationStep.ACTION_Insert);
		when(step.getAD_Table()).thenReturn(table);
		when(step.getRecord_ID()).thenReturn(recordId);
		when(step.getSeqNo()).thenReturn(10);
		Field dataField = MMigrationStep.class.getDeclaredField("m_migrationData");
		dataField.setAccessible(true);
		dataField.set(step, Arrays.asList(data));
		if (recordUUID != null) {
			Field uuidField = MMigrationStep.class.getDeclaredField("m_recordUUID");
			uuidField.setAccessible(true);
			uuidField.set(step, recordUUID);
		}
		doCallRealMethod().when(step).toXmlNode(any(Document.class));
		return step;
	}

	private static MMigrationData migrationData(String columnName, int columnId,
			String value) {
		MColumn column = mock(MColumn.class);
		when(column.getColumnName()).thenReturn(columnName);
		MMigrationData data = mock(MMigrationData.class);
		when(data.getAD_Column()).thenReturn(column);
		when(data.getAD_Column_ID()).thenReturn(columnId);
		when(data.getNewValue()).thenReturn(value);
		doCallRealMethod().when(data).toXmlNode(any(MMigrationStep.class), any(Document.class));
		return data;
	}
}
