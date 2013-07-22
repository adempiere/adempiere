package org.adempiere.ad.modelvalidator;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.ModelValidator;

/**
 * Factory class which is able to create and bind(register) annotated model validators
 * 
 * @author tsa
 * 
 */
public class AnnotatedModelValidatorFactory
{
	private static final AnnotatedModelValidatorFactory instance = new AnnotatedModelValidatorFactory();

	public static AnnotatedModelValidatorFactory get()
	{
		return instance;
	}

	/**
	 * Creates a {@link ModelValidator} object for given annotated class.
	 * 
	 * This method is not checking if the annotatedObject was already registered.
	 * 
	 * @param annotatedObject
	 * @return {@link ModelValidator} or null if the given object is not a valid annotated model validator or it has no pointcuts
	 * @throws AdempiereException
	 *             if annotations were not correctly used
	 */
	public ModelValidator createModelValidator(Object annotatedObject)
	{
		return createAnnotatedModelValidator(annotatedObject);
	}

	private final AnnotatedModelValidator createAnnotatedModelValidator(Object annotatedObject)
	{
		final AnnotatedModelValidator validator = new AnnotatedModelValidator(annotatedObject);
		if (!validator.hasPointcuts())
		{
			return null;
		}

		return validator;
	}
}
