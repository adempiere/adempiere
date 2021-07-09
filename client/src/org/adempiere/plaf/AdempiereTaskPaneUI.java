package org.adempiere.plaf;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComponentUI;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.plaf.basic.BasicTaskPaneUI;

public class AdempiereTaskPaneUI extends BasicTaskPaneUI {
	public static ComponentUI createUI(JComponent c) {
		return new AdempiereTaskPaneUI();
	}

	protected Border createPaneBorder() {
		return new AdempierePaneBorder();
	}

	/**
	 * Overriden to paint the background of the component but keeping the
	 * rounded corners.
	 */
	public void update(Graphics g, JComponent c) {
		if (c.isOpaque()) {
			g.setColor(new ColorUIResource(251, 248, 241));
			g.fillRect(0, 0, c.getWidth(), c.getHeight());
			g.setColor(new ColorUIResource(251, 248, 241));
			g.fillRect(0, getRoundHeight(), c.getWidth(), c.getHeight()
					- getRoundHeight());
		}
		paint(g, c);
	}

	/**
	 * The border of the taskpane group paints the "text", the "icon", the
	 * "expanded" status and the "special" type.
	 * 
	 */
	class AdempierePaneBorder extends PaneBorder {

		protected void paintTitleBackground(JXTaskPane group, Graphics g) {
			if (group.isSpecial()) {
				g.setColor(new ColorUIResource(251, 248, 241));
				g.fillRoundRect(0, 0, group.getWidth(), getRoundHeight() * 2,
						getRoundHeight(), getRoundHeight());
				g.fillRect(0, getRoundHeight(), group.getWidth(), getTitleHeight(group)
						- getRoundHeight());
			} else {
				Paint oldPaint = ((Graphics2D) g).getPaint();
				GradientPaint gradient = new GradientPaint(0f, 0f, // group.getWidth()
						// / 2,
						new ColorUIResource(241, 239, 222), 0f, // group.getWidth(),
						getTitleHeight(group), new ColorUIResource(251, 248, 241));

				((Graphics2D) g).setRenderingHint(
						RenderingHints.KEY_COLOR_RENDERING,
						RenderingHints.VALUE_COLOR_RENDER_QUALITY);
				((Graphics2D) g).setRenderingHint(
						RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING,
						RenderingHints.VALUE_RENDER_QUALITY);
				((Graphics2D) g).setPaint(gradient);

				g.fillRoundRect(0, 0, group.getWidth(), getRoundHeight() * 2,
						getRoundHeight(), getRoundHeight());
				g.fillRect(0, getRoundHeight(), group.getWidth(), getTitleHeight(group)
						- getRoundHeight());
				((Graphics2D) g).setPaint(oldPaint);
			}

			Rectangle oldRect = g.getClipBounds();
			g.setClip(0, 0, group.getWidth(), getTitleHeight(group));
			g.setColor(borderColor);
			g.drawRoundRect(0, 0, group.getWidth() - 1, getTitleHeight(group)
					+ getRoundHeight(), getRoundHeight(), getRoundHeight());
			g.drawLine(0, getTitleHeight(group) - 1, group.getWidth(), getTitleHeight(group) - 1);
			g.setClip(oldRect);
		}

		protected void paintExpandedControls(JXTaskPane group, Graphics g,
				int x, int y, int width, int height) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			paintOvalAroundControls(group, g, x, y, width, height);
			g.setColor(getPaintColor(group));
			paintChevronControls(group, g, x, y, width, height);

			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF);
		}

		@Override
		protected boolean isMouseOverBorder() {
			return true;
		}

	}

}
