package org.adempiere.webui.component;

public class GridFactory {

	public static Grid newGridLayout() {
		Grid grid = new Grid();
		grid.makeNoStrip();
		return grid;
	}
}
