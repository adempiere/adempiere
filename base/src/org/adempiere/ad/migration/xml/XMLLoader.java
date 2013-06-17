package org.adempiere.ad.migration.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.POWrapper;
import org.adempiere.util.Services;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLLoader
{
	private final transient CLogger logger = CLogger.getCLogger(getClass());

	private final String fileName;
	private final Properties ctx;

	private InputStream inputStream = null;

	private List<Object> objects;

	public XMLLoader(String fileName)
	{
		this.fileName = fileName;
		this.ctx = Env.getCtx();
	}

	public XMLLoader(InputStream is)
	{
		this.fileName = null;
		this.inputStream = is;
		this.ctx = Env.getCtx();
	}

	public void load(String trxName)
	{
		Trx.run(trxName, new TrxRunnable()
		{

			@Override
			public void run(String innerTrxName)
			{
				try
				{
					load0(innerTrxName);
				}
				catch (RuntimeException e)
				{
					throw e;
				}
				catch (Exception e)
				{
					throw new AdempiereException(e);
				}
			}
		});
	}

	private void load0(String trxName) throws ParserConfigurationException, SAXException, IOException
	{
		this.objects = new ArrayList<Object>();

		final IXMLHandlerFactory factory = Services.get(IXMLHandlerFactory.class);

		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);

		final DocumentBuilder builder = dbf.newDocumentBuilder();
		final InputSource source = getInputSource();

		final Document doc = builder.parse(source);
		final NodeList nodes = doc.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++)
		{
			if (!(nodes.item(i) instanceof Element))
			{
				continue;
			}

			final Element element = (Element)nodes.item(i);
			final String name = element.getLocalName();
			final Class<Object> beanClass = factory.getBeanClassByNodeName(name);
			if (beanClass == null)
			{
				addLog("Error: node is not handled: " + name);
				continue;
			}

			final IXMLHandler<Object> converter = factory.getHandler(beanClass);

			final Object bean = POWrapper.create(ctx, beanClass, trxName);
			if (converter.fromXmlNode(bean, element))
			{
				POWrapper.save(bean); // make sure is saved
				objects.add(bean);
			}

		}

	}

	private InputSource getInputSource()
	{
		if (fileName != null)
		{
			return new InputSource(fileName);
		}
		else if (inputStream != null)
		{
			return new InputSource(inputStream);
		}
		else
		{
			throw new AdempiereException("Cannot identify source");
		}
	}

	private void addLog(String msg)
	{
		logger.info(msg);
	}

	public List<Object> getObjects()
	{
		if (objects == null)
		{
			return new ArrayList<Object>();
		}
		return new ArrayList<Object>(this.objects);
	}
}
