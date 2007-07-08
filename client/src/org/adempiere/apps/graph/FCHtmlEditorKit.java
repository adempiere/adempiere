package org.adempiere.apps.graph;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import java.awt.*;
import java.awt.image.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class FCHtmlEditorKit extends HTMLEditorKit {
	public ViewFactory getViewFactory() {
		if (defaultFactory == null) {
			defaultFactory = new FCHtmlFactory(super.getViewFactory());
		}
		return defaultFactory;
	}

	private static class FCHtmlFactory implements ViewFactory {
		public FCHtmlFactory(ViewFactory factory) {
			oldFactory = factory;
		}
		public View create(Element elem) {
			View result;
			result = oldFactory.create(elem);
			if (result instanceof ImageView) {
				String src = (String)elem.getAttributes().
				getAttribute(HTML.Attribute.SRC);
				if ("res:".equals(src.substring(0, 4))) {
					result = new NewImageView(elem);
				}
			}
			return result;
		}
		private static class NewImageView extends ImageView {
			Element elem;
			public NewImageView(Element elem) {
				super(elem);
				this.elem=elem;
			}
			public Image getImage() {
				//return smile image
				//java.awt.Toolkit.getDefaultToolkit().getImage(getImageURL()).flush();
				//if (smileImage == null) {
					String src = (String)elem.getAttributes().
					getAttribute(HTML.Attribute.SRC);
					//System.out.println("img load: " + src.substring(4));
					URL url = getClass().getClassLoader().
					getResource(src.substring(4));
					//getResource("at/freecom/apps/images/freecom.gif");
					//getResource("javax/swing/text/html/icons/image-delayed.gif");
					if (url == null) return null;
					smileImage  =  Toolkit.getDefaultToolkit().getImage(url);
					if (smileImage==null) return null;
					//forcing image to load synchronously
					ImageIcon ii = new ImageIcon();
					ii.setImage(smileImage);
				//}
				return smileImage;
			}
			public URL getImageURL() {
				// here we return url to some image. It might be any
				// existing image.  we need to move ImageView to the
				// state where it thinks that image was loaded.
				// ImageView is calling getImage to get image for
				// measurement and painting when image was loaded
				if (false) {
					return getClass().getClassLoader().
					getResource("javax/swing/text/html/icons/image-delayed.gif");
				} else {
				String src = (String)elem.getAttributes().
				getAttribute(HTML.Attribute.SRC);
				//System.out.println("img load: " + src.substring(4));
				URL url = getClass().getClassLoader().
				getResource(src.substring(4));
				if (url != null) {
					System.out.println("load image: " + url);
					return url;
				}
				}
				return null;
			}
			private static Image smileImage = null;
		}

		private ViewFactory oldFactory;
	}
	private static ViewFactory defaultFactory = null;
}