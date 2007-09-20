package org.posterita.core.utils;

public class NumberUtil
{
	public static boolean isSquareNumber(int x)
	{
		return isSquareNumber((double)x);
	}
	
	public static boolean isSquareNumber(double x)
	{
		double num = Math.sqrt(x);
		int iNum = (int)num;
		
		if((double)iNum == num)
			return true;
		else
			return false;
	}
}
