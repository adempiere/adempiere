/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.pdf.viewer;

import java.io.InputStream;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

/**
 * PDF Viewer using icepdf.
 * 
 * @author eugen.hanussek@klst.com https://github.com/homebeaver
 */
public class PDFViewerBean extends JPanel {

	private static final long serialVersionUID = -365936659584244L;
//	private static CLogger log	= CLogger.getCLogger(PDFViewerBean.class);
	
    PropertiesManager properties = new PropertiesManager(System.getProperties()
    		, ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
    
    // build a component controller
	private final SwingController controller = new SwingController();
	private final SwingViewBuilder factory;
	private final JPanel viewerComponentPanel;
	private final JScrollPane pdfPane;
	
    public PDFViewerBean() {
    	
    	// while testing I detect that FitWidthButton-action runs out of memory - maybe there is a bug in icepdf
    	// so I do not show the fit-Buttons
    	properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FIT, false);
    	properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ANNOTATION, false); // not used
    	
    	controller.setIsEmbeddedComponent(true);
    	factory = new SwingViewBuilder(controller, properties);
    	
        // add interactive mouse link annotation support via callback
//        controller.getDocumentViewController().setAnnotationCallback(
//                new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));
    	viewerComponentPanel = factory.buildViewerPanel();
    	pdfPane = new JScrollPane(viewerComponentPanel);
    	this.add(pdfPane);

    }
    

    public void clearDocument() {
    	controller.closeDocument();
    }
    

    @Deprecated
    // only for compatibility
    public void setScale(int percent) {

    }
    
    /**
     * Opens a Document via the specified InputStream
     * @param inputStream InputStream containing a valid PDF document.
     * @param description When in the GUI for describing this document.
     * @param pathOrURL   Either a file path, or file name, or URL, describing the
     *                    origin of the PDF file. This is typically null. If non-null, it is
     *                    used to populate the default file name in the File..Save a Copy
     *                    dialog summoned in saveFile()
     */
    public void loadPDF(InputStream is) {
    	
    	String description = "";
    	controller.openDocument(is, description, null);
    	
    }

	protected void finalize() throws Throwable {
    	controller.closeDocument();
	}
}
