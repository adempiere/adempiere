/**
 * 
 */
package org.adempiere.webui.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Center;
import org.zkoss.zul.East;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.West;

/**
 * @author teo_sarca
 *
 */
public class Borderlayout extends org.zkoss.zul.Borderlayout
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6976820221945897268L;
	
	public Borderlayout()
	{
		super();
	}
	
	public Borderlayout appendNorth(Component c)
	{
		North north = getNorth();
		if (north == null)
		{
			north = new North();
			this.appendChild(north);
		}
		north.appendChild(c);
		return this;
	}
	public Borderlayout appendWest(Component c)
	{
		West west = getWest();
		if (west == null)
		{
			west = new West();
			this.appendChild(west);
		}
		west.appendChild(c);
		return this;
	}
	public Borderlayout appendSouth(Component c)
	{
		South south = getSouth();
		if (south == null)
		{
			south = new South();
			this.appendChild(south);
		}
		south.appendChild(c);
		return this;
	}
	public Borderlayout appendEast(Component c)
	{
		East east = getEast();
		if (east == null)
		{
			east = new East();
			this.appendChild(east);
		}
		east.appendChild(c);
		return this;
	}
	public Borderlayout appendCenter(Component c)
	{
		Center center = getCenter();
		if (center == null)
		{
			center = new Center();
			this.appendChild(center);
		}
		center.appendChild(c);
		return this;
	}
}
