/**
 * Copyright (C) 2003-2018, http://www.klst.com/
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Created by eugen.hanussek@klst.com https://github.com/homebeaver
 */
package org.adempiere.pdf.viewer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.compiere.util.CLogger;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 * PDF Viewer using icepdf
 */
public class PDFViewerBean extends JPanel {

	private static final long serialVersionUID = -365936659584244L;
	private static CLogger log	= CLogger.getCLogger(PDFViewerBean.class);
	
    // build a component controller
	private final SwingController controller = new SwingController();
	private final SwingViewBuilder factory = new SwingViewBuilder(controller);
	private final JPanel viewerComponentPanel = factory.buildViewerPanel();

    private final JScrollPane center = new JScrollPane(viewerComponentPanel); 
//    private final JLabel pageCountLabel = new JLabel("00");

//    private int currentPage = 1;
    private String filename = null;
	private File tmpFile = null;

    public PDFViewerBean() {
    	
    	controller.setIsEmbeddedComponent(true);
    	this.add(center);

    }
    

    public void loadPDF(String filename) {
        this.filename = filename;
        try {
            // try openning a PDF
        	controller.closeDocument();
            controller.openDocument(filename);
            int numPages = controller.getPageTree().getNumberOfPages();
            int pageNum = controller.getCurrentPageNumber();
            log.info("@@@EUG ------------------------- numPages="+numPages + " pageNum="+pageNum);
//            pageCountLabel.setText(pageNum + " ");
//            setCurrentPage(1);
            controller.showPage(0);
//            log.info("@@@EUG ------------------------- DocumentViewToolMode="+controller.getDocumentViewToolMode() + " DISPLAY_TOOL_NONE="+DocumentViewModelImpl.DISPLAY_TOOL_NONE);
//            //controller.setPageFitMode(DocumentViewController.PAGE_FIT_ACTUAL_SIZE, true);
//            controller.setPageFitMode(DocumentViewController.PAGE_FIT_ACTUAL_SIZE, false);
//            //controller.setDisplayTool(DocumentViewModelImpl.DISPLAY_TOOL_NONE);
//            controller.setDisplayTool(controller.getDocumentViewToolMode());
//            //center.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

//    public int getCurrentPage() {
//        return currentPage;
//    }
    
    public void clearDocument() {
    	controller.closeDocument();
    	if (tmpFile != null) {
    		tmpFile.delete();
    		tmpFile = null;
    	}
    }
    

    @Deprecated
    public void setScale(int percent) {
//        int step;
//        for (step = 0; step < zoomFactors.length - 1; step++) {
//            if (zoomFactors[step] * 100 >= percent) {
//                break;
//            }
//        }
//    	setScaleStep(step);
    }
    
    public void loadPDF(InputStream is) {
    	if (tmpFile != null) {
    		tmpFile.delete();
    	}
    	
        try {
            tmpFile = File.createTempFile("adempiere", ".pdf");
            tmpFile.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        try {
			final OutputStream os = new FileOutputStream(tmpFile);
            try {
                final byte[] buffer = new byte[32768];
                for (int read; (read = is.read(buffer)) != -1; ) {
                    os.write(buffer, 0, read);
                }
            } catch (IOException e) {
				e.printStackTrace();
			} finally {
                os.close();
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loadPDF(tmpFile.getAbsolutePath());
    }

	protected void finalize() throws Throwable {
    	if (tmpFile != null) {
    		tmpFile.delete();
    	}
    	controller.closeDocument();
	}
}
