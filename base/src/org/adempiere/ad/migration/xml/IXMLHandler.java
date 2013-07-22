package org.adempiere.ad.migration.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface IXMLHandler<T>
{
	/**
	 * Creates and returns an {@link Node} based on given record
	 * 
	 * @param document
	 * @param record
	 * @return create XML node
	 */
	Node toXmlNode(Document document, T record);

	/**
	 * Updates given record from XML element.
	 * 
	 * @param record
	 * @param element
	 * @return true if something was imported; false if method did not import anything
	 */
	boolean fromXmlNode(T record, Element element);

}
