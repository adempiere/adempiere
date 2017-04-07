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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.logging.Level;

import org.compiere.util.CLogger;

/**
 * 
 * @author hengsin
 *
 */
public class SwapFile
{
	private static final CLogger log = CLogger.getCLogger(SwapFile.class);
	
	private final File swapFile;
	private RandomAccessFile randomAccessFile;
	private final int blockSize;
	private final int minBlockToGrow;
	private final LinkedList<Long> freeBlocks;
	
	
	/**
	 * Creates a swap file.
	 * 
	 * The file name is generated automatically.
	 * 
	 * @param prefix the swap file prefix
	 * @param blockSize the size of the blocks allocated by the swap file
	 * @param minBlockToGrow the minimum number of blocks by which the swap file grows when full
	 */
	public SwapFile(String prefix, int blockSize, int minBlockToGrow)
	{
		try
		{			
			swapFile = File.createTempFile(prefix, ".swap");
			if (log.isLoggable(Level.INFO))
			{
				log.info("Creating swap file " + swapFile.getPath());
			}
			swapFile.deleteOnExit();
			
			this.blockSize = blockSize;
			this.minBlockToGrow = minBlockToGrow;
			freeBlocks = new LinkedList<Long>();			
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * open for read write
	 */
	public synchronized void open() {
		try {
			randomAccessFile = new RandomAccessFile(swapFile, "rw");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * close and release file handle
	 */
	public synchronized void close() {
		if (randomAccessFile != null) {
			try {
				randomAccessFile.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Allocates an area in the swap file and writes data in it.
	 * 
	 * @param data the data for which to allocate an area in the file
	 * @return a handle to the allocated area
	 * @throws IOException
	 */
	public SwapFileSegment write(byte[] data) throws IOException
	{
		verifyOpen();
		int blockCount = (data.length - 1) / blockSize + 1;
		long[] offsets = allocateFreeBlocks(blockCount);
		int lastBlockSize = (data.length - 1) % blockSize + 1;
		SwapFileSegment segment = new SwapFileSegment(offsets, lastBlockSize);
		for (int i = 0; i < blockCount; ++i)
		{
			int dataSize = i < blockCount - 1 ? blockSize : lastBlockSize;
			int dataOffset = i * blockSize;			
			write(data, dataSize, dataOffset, offsets[i]);
		}
		
		return segment;
	}


	private synchronized void write(byte[] data, int dataSize, int dataOffset, long fileOffset) throws IOException
	{
		randomAccessFile.seek(fileOffset);
		randomAccessFile.write(data, dataOffset, dataSize);
	}

	
	/**
	 * Reads all the data from an allocated area.
	 * 
	 * @param segment the allocated segment
	 * @return the whole data saved in an allocated area
	 * @throws IOException
	 */
	public byte[] read(SwapFileSegment segment) throws IOException
	{
		verifyOpen();
		long[] offsets = segment.getOffsets();
		int totalLength = (offsets.length - 1) * blockSize + segment.getLastBlockSize();
		byte[] data = new byte[totalLength];
		
		for (int i = 0; i < offsets.length; ++i)
		{
			int dataOffset = i * blockSize;
			int dataLength = i < offsets.length - 1 ? blockSize : segment.getLastBlockSize();
			read(data, dataOffset, dataLength, offsets[i]);
		}
		
		return data;
	}


	private synchronized void read(byte[] data, int dataOffset, int dataLength, long fileOffset) throws IOException
	{		
		randomAccessFile.seek(fileOffset);
		randomAccessFile.readFully(data, dataOffset, dataLength);
	}
	
	
	/**
	 * Frees an allocated area.
	 * 
	 * @param segment the allocated segment
	 */
	public void free(SwapFileSegment segment)
	{
		verifyOpen();
		freeBlocks(segment.getOffsets());
	}

	private void verifyOpen() {
		if (randomAccessFile == null) {
			throw new RuntimeException("Swap file not open for read write access");
		}
	}
	
	
	/**
	 * Closes and deletes the swap file.
	 */
	public void dispose()
	{
		synchronized (this)
		{
			if (swapFile.exists())
			{
				if (log.isLoggable(Level.INFO))
				{
					log.info("Disposing swap file " + swapFile.getPath());
				}

				try
				{
					close();
				}
				catch (Exception e)
				{
					log.warning("Not able to close swap file " + swapFile.getPath());
				}

				if (!swapFile.delete())
				{
					log.warning("Not able to delete swap file " + swapFile.getPath());
				}
			}
		}
	}


	protected void finalize() throws Throwable //NOSONAR
	{
		dispose();
		super.finalize();
	}


	private synchronized long[] allocateFreeBlocks(int blockCount) throws IOException
	{
		int growCount = blockCount - freeBlocks.size();
		if (growCount > 0)
		{
			if (growCount < minBlockToGrow)
			{
				growCount = minBlockToGrow;
			}
			
			long length = randomAccessFile.length();
			long newLength = length + growCount * blockSize;
			if (log.isLoggable(Level.INFO))
			{
				log.info("Growing swap file " + swapFile.getPath() + " with " + growCount + " blocks x " + blockSize + " bytes to size " + newLength);
			}
			randomAccessFile.setLength(newLength);

			for (int i = 0; i < growCount; ++i)
			{
				freeBlocks.addLast(length + i * blockSize);
			}
		}
		
		long[] offsets = new long[blockCount];
		for (int i = 0; i < blockCount; i++)
		{
			offsets[i] = freeBlocks.pollFirst();
		}
		return offsets;
	}


	private synchronized void freeBlocks(long []offsets)
	{
		for (int i = offsets.length - 1; i >= 0; --i)
		{
			freeBlocks.addFirst(offsets[i]);
		}
	}	
}
