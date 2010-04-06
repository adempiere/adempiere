package org.compiere.pos;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;

import org.compiere.minigrid.MiniTable;

public class PosTable extends MiniTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7884238751207398699L;

	public PosTable() {
		
		super();
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);
		setMultiSelection(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setRowHeight(30);
		setAutoResize(false);
	}
	
	public void growScrollbars() {
		// fatter scroll bars
		Container p = getParent();
		if (p instanceof JViewport) {
			Container gp = p.getParent();
			if (gp instanceof JScrollPane) {
				JScrollPane scrollPane = (JScrollPane) gp;             
				scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(30,0));
				scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
			}
		}
	}

}
