package org.adempiere.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * List that can contain weak elements
 * 
 * @author Teo Sarca
 */
public class WeakList<T> extends AbstractList<T>
{

	private final ReferenceQueue<T> queue = new ReferenceQueue<T>();
	private final List<ListEntry> list = new ArrayList<ListEntry>();
	private final Object sync = new Object();

	public boolean add(T o, boolean weak)
	{
		expungeStaleEntries();
		return list.add(new ListEntry(o, weak));
	}

	@Override
	public boolean add(T o)
	{
		return add(o, true);
	}

	@Override
	public T get(int i)
	{
		expungeStaleEntries();
		ListEntry e = list.get(i);
		return e.get();
	}

	@Override
	public int size()
	{
		expungeStaleEntries();
		return list.size();
	}

	public T remove(int index)
	{
		ListEntry e = list.remove(index);
		return e == null ? null : e.get();
	}

	public List<T> hardList()
	{
		expungeStaleEntries();
		List<T> result = new ArrayList<T>();
		synchronized (sync)
		{
			for (int i = 0; i < size(); i++)
			{
				T tmp = get(i);
				if (tmp != null)
					result.add(tmp);
			}
		}

		return result;
	}

	public Iterator<T> iterator()
	{
		return hardList().iterator();
	}

	/**
	 * Expunge stale entries from the list.
	 */
	@SuppressWarnings("unchecked")
	private void expungeStaleEntries()
	{
		synchronized (sync)
		{
			ListWeakReference r;
			while ((r = (ListWeakReference)queue.poll()) != null)
			{
				ListEntry le = r.getListEntry();
				int i = list.indexOf(le);
				if (i != -1)
				{
					list.remove(i);
					// System.out.println("Expuge " + le + " (index=" + i + ")");
				}
			}
		}
	}

	private class ListWeakReference extends WeakReference<T>
	{
		private final ListEntry parent;

		public ListWeakReference(T value, ListEntry parent)
		{
			super(value, queue);
			this.parent = parent;
		}

		public ListEntry getListEntry()
		{
			return this.parent;
		}

	}

	private class ListEntry
	{
		final T value;
		final ListWeakReference weakValue;
		final boolean isWeak;

		public ListEntry(T value, boolean isWeak)
		{
			if (isWeak)
			{
				this.value = null;
				this.weakValue = new ListWeakReference(value, this);
				this.isWeak = true;
			}
			else
			{
				this.value = value;
				this.weakValue = null;
				this.isWeak = false;
			}
		}

		public boolean isAvailable()
		{
			if (isWeak)
				return weakValue.get() != null;
			else
				return true;
		}

		public T get()
		{
			if (isWeak)
				return weakValue.get();
			else
				return value;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			return false;
		}

		public String toString()
		{
			if (isAvailable())
			{
				return "<GARBAGED>";
			}
			else
			{
				return String.valueOf(this.get());
			}
		}
	}
}
