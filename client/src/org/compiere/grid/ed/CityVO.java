/**
 * 
 */
package org.compiere.grid.ed;


/**
 * @author Teo Sarca , www.arhipac.ro
 *
 */
public class CityVO
{
	public final int C_City_ID;
	public final String CityName;
	public final int C_Region_ID;
	public final String RegionName;
	public CityVO(int city_ID, String cityName, int region_ID, String regionName)
	{
		super();
		C_City_ID = city_ID;
		CityName = cityName;
		C_Region_ID = region_ID;
		RegionName = regionName;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + C_City_ID;
		result = prime * result + C_Region_ID;
		result = prime * result + ((CityName == null) ? 0 : CityName.hashCode());
		result = prime * result + ((RegionName == null) ? 0 : RegionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityVO other = (CityVO) obj;
		if (C_City_ID != other.C_City_ID)
			return false;
		if (C_Region_ID != other.C_Region_ID)
			return false;
		if (CityName == null)
		{
			if (other.CityName != null)
				return false;
		}
		else if (!CityName.equals(other.CityName))
			return false;
		if (RegionName == null)
		{
			if (other.RegionName != null)
				return false;
		}
		else if (!RegionName.equals(other.RegionName))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if (this.CityName != null)
		{
			sb.append(this.CityName);
		}
		if (this.RegionName != null)
		{
			sb.append(" (").append(this.RegionName).append(")");
		}
		return sb.toString();
	}

}