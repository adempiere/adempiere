package org.compiere.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.ecs.Printable;
import org.apache.ecs.MultiPartElement;


/**
 *  Load html-src (text) stored in JAR, e.g. to load a style-sheet
 */
public class StoredHtmlSrc extends MultiPartElement implements Printable {
	
	private static final long serialVersionUID = 50303119083373138L;
	
	/**	Logger					*/
	protected static Logger 	log = Logger.getLogger(StoredHtmlSrc.class.getName());
	
	
	/**
	 *  Load html-src (text) stored in JAR, e.g. to load a style-sheet
	 *  @param elementType e.g. elementType=STYLE
	 *  @param srcLocation package/filename in SRC e.g. org/compiere/util/standard.css
	 *  todo if needed: also write for SinglePartElement and StringElement
	 */
	public StoredHtmlSrc(String elementType, String srcLocation) {
		this.setElementType(elementType);
		
		URL url = getClass().getClassLoader().getResource(srcLocation);
		if (url==null) {
			log.warning("failed to load html-src: " + srcLocation);
			return;
		}			
		InputStreamReader ins;
		try {
			ins = new InputStreamReader(url.openStream());
			BufferedReader bufferedReader = new BufferedReader( ins );
			String cssLine;
			String result="";
			while ((cssLine = bufferedReader.readLine()) != null) 
				result+=cssLine;
			this.setTagText(result);
		} catch (IOException e1) {
			log.warning("failed to load html-src: " + srcLocation);
		}
	}
}
