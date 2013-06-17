/**
 * 
 */
package org.adempiere.util;

/**
 * @author tsa
 *
 */
public interface Accessor extends TypedAccessor<Object>
{
	@Override
	public Object getValue(Object o);
}
