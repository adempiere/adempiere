 /******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *																		      *
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.modelvalidator;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.ModelValidator;

/**
 * Factory class which is able to create and bind(register) annotated model validators
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
