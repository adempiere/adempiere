package org.adempiere.util.collections;

/**
 * Performs some predicate which returns true or false based on the input object. Predicate instances can be used to implement queries or to do filtering.
 * 
 */
// this interface is based on org.apache.commons.collections.Predicate
public interface Predicate<T>
{
	/**
	 * Returns true if the input object matches this predicate.
	 * 
	 * @return true if the input object matches this predicate, else returns false
	 */
	boolean evaluate(T value);
}
