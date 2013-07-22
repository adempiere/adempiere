package de.metas.adempiere.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to indicate that a method result should be cached using the simple {@link CacheAsp}
 * implementation.
 * 
 * <p>
 * Note: the relation between this annotation and {@link CacheAsp} is configured in a <code>jboss-aop.xml</code> file.
 * 
 * @author ts
 * @see CacheIgnore
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.CONSTRUCTOR })
public @interface Cached
{

	/**
	 * Optional array of properties whose values are also used when creating the hash key
	 * 
	 * @return
	 */
	public String[] keyProperties() default {};

	/**
	 * If true, the actual instance, whose method is cached, is not included in the caching key
	 * @return
	 */
	public boolean ignoreInstance() default false;

	/**
	 * Optional cache name. Generally you can use the table name of the object that is cached (if applies)
	 * @return
	 */
	public String cacheName() default "";

}
