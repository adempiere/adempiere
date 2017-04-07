/******************************************************************************
 * Copyright (C) 2012 Heng Sin Low                                            *
 * Copyright (C) 2012 Trek Global                 							  *
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
package org.compiere.print.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MSysConfig;
import org.compiere.util.CLogger;

/**
 * 
 * @author hengsin
 *
 */
public class SerializableMatrixImpl<T extends Serializable> implements SerializableMatrix<T> {
	/** default 4k block size **/
	private static final int DEFAULT_BLOCK_SIZE = 4*1024;

	/** Default to start swapping after 2k row **/
	private static final int DEFAULT_SWAP_MAX_ROWS = 2000;
	
	/** set to 0 or smaller to disable swap file usage **/
	private static final String REPORT_SWAP_MAX_ROWS = "REPORT_SWAP_MAX_ROWS";

	private static final CLogger log = CLogger.getCLogger(SerializableMatrixImpl.class);
	
	/** Data Structure rows			*/
	private ArrayList<List<T>>	m_rows = new ArrayList<List<T>>();
	/**	Current Row					*/
	private int m_currentRow = -1;
	
	private int m_pageSize = 0;
	
	private int m_size = 0;
	
	private Page currentPage = null;
	
	private List<SwapFileSegment> segments = new ArrayList<SwapFileSegment>();
	
	private List<Page> pages = new ArrayList<Page>();
	
	private SwapFile swapFile;
	private String prefix;
	
	public SerializableMatrixImpl(String name) {
		this.prefix = name;
		int pageSize = MSysConfig.getIntValue(REPORT_SWAP_MAX_ROWS, DEFAULT_SWAP_MAX_ROWS);
		if (pageSize <= 0) {
			m_pageSize = Integer.MAX_VALUE;
		} else {
			m_pageSize = pageSize;
		}
	}
		
	/* (non-Javadoc)
	 * @see org.compiere.print.util.SerializableDataTable#addRow(java.util.ArrayList)
	 */
	@Override
	public void addRow(List<T> data) {
		m_size++;		
		if (currentPage == null) {
			currentPage = new Page();
			pages.add(currentPage);
			currentPage.first = 0;
			currentPage.last = 0;
			currentPage.size = 1;
			currentPage.pageNo = pages.size() - 1;
			
			m_rows.add(data);
			m_currentRow = 0;			
		} else {
			Page lastPage = pages.get(pages.size() - 1);
			if (lastPage.size == m_pageSize) {
				pageout(currentPage);			
				currentPage = new Page();
				pages.add(currentPage);
				currentPage.pageNo = pages.size() - 1;
				currentPage.first = lastPage.last + 1;
				currentPage.last = currentPage.first;
				currentPage.size = 1;
				
				m_rows = new ArrayList<List<T>>();
				m_rows.add(data);
				m_currentRow = currentPage.first;
			} else {
				if (currentPage.pageNo != lastPage.pageNo) {
					pageout(currentPage);
					pagein(lastPage.pageNo);
				}
				m_rows.add(data);
				currentPage.last++;
				currentPage.size++;
				m_currentRow = currentPage.last;
			}
		}
	}	
	
	/* (non-Javadoc)
	 * @see org.compiere.print.util.SerializableDataTable#setRowIndex(int)
	 */
	@Override
	public boolean setRowIndex (int row) {
		if (row < 0 || row >= m_size)
			return false;
		
		if (row >= currentPage.first && row <= currentPage.last) {
			m_currentRow = row;
			return true;
		} else {
			Page tmp = currentPage;
			for(Page page : pages) {
				if (row >= page.first && row <= page.last) {					
					currentPage = page;
					pageout(tmp);
					pagein(currentPage.pageNo);
					m_currentRow = row;
					return true;
				}
			}
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.compiere.print.util.SerializableDataTable#setRowNext()
	 */
	@Override
	public boolean setRowNext()
	{
		return setRowIndex(m_currentRow+1);
	}	//	setRowNext
	
	/* (non-Javadoc)
	 * @see org.compiere.print.util.SerializableDataTable#getRowCount()
	 */
	@Override
	public int getRowCount()
	{
		return m_size;
	}	//	getRowCount

	/* (non-Javadoc)
	 * @see org.compiere.print.util.SerializableDataTable#getRowIndex()
	 */
	@Override
	public int getRowIndex()
	{
		return m_currentRow;
	}	//	getRowIndex
	
	/* (non-Javadoc)
	 * @see org.compiere.print.util.SerializableDataTable#getRowData()
	 */
	@Override
	public List<T> getRowData()
	{
		return m_rows.isEmpty() ? null : m_rows.get(m_currentRow - currentPage.first);
	}
	
	@Override
	public void setRowData(List<T> data) {
		if (currentPage != null) {
			int index = m_currentRow - currentPage.first; 
			if (index < m_rows.size()) {
				m_rows.set(index, data);
			}
		}
	}
	
	private void pageout(Page currentPage) {
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		try {
			ObjectOutputStream ous = new ObjectOutputStream(bas);
			ous.writeObject(m_rows);
			if (swapFile == null) {
				swapFile = new SwapFile(makePrefix(prefix), DEFAULT_BLOCK_SIZE, 2);
			}
			swapFile.open();
			SwapFileSegment segment = swapFile.write(bas.toByteArray());
			if (currentPage.pageNo < segments.size())
				segments.set(currentPage.pageNo, segment);
			else
				segments.add(segment);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			if (swapFile != null)
				swapFile.close();
		}		
	}

	private void pagein(int index) {
		SwapFileSegment segment = segments.get(index);
		try {
			swapFile.open();
			byte[] data = swapFile.read(segment);
			swapFile.free(segment);
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
			ArrayList<List<T>> rows = (ArrayList<List<T>>) ois.readObject();
			this.m_rows = rows;
			currentPage = pages.get(index);
			m_currentRow = currentPage.first;
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			swapFile.close();
		}
	}
	
	private String makePrefix(String name) {
		StringBuffer prefix = new StringBuffer();
		char[] nameArray = name.toCharArray();
		for (char ch : nameArray) {
			if (Character.isLetterOrDigit(ch)) {
				prefix.append(ch);
			} else {
				prefix.append("_");
			}
		}
		return prefix.toString();
	}
	
	class Page {
		protected int pageNo;
		protected int first;
		protected int last;
		protected int size;
	}	
}
