/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on 25-Jul-2005 by alok
 *
 */
package org.posterita.struts.core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;
import org.compiere.model.MPayment;

import org.posterita.core.EmailValidator;
import org.posterita.core.JulianDate;
import org.posterita.core.Range;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.FormattingException;
import org.posterita.formatter.Formatter;



/**
 * An abstract base class for ActionForms that adds support for automatic formatting
 * and unformatting of string values and for the transfer of the resulting values
 * between itself and the given bean. The <code>populate()</code> method provides
 * and entry point to this functionality, while the <code>keysToSkip</code> method allows
 * subclasses to specify fields that should not be populated.
 * <p>
 * 
 * Additional methods are provided to allow subclasses to override formatting defaults. 
 * The <code>DefaultString()</code> method allows callers to specify overrides for the default
 * string used to represent a <code>null</code> for a given property. Similarly, 
 * the <code>setFormatterype()</code> method allows callers to specify a
 * formatting type other than the default for a given property.
 * <p>
 * Developers can also specify validation rules by invoking <code>addRange()</code>
 * to add a range validation or <code>addRequiredFields()</code> to add required field validations
 * for an array of property names.
 *  
 */
public class BaseForm extends ActionForm
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, String> defaultStringMap = new HashMap<String, String>();
    private Map<String, Object> formatMap = new HashMap<String, Object>();
    MessageResources resources = null;
    
    public final static int TO_OBJECT = 0;
    public final static int TO_STRING = 1;
    public final static int MIN_LENGTH = 6;
    
    public final static String REQUIRED = "required";
    public final static String RANGE = "range";
    public final static String STRING_NUMBER="stringNumber";
    public final static String EMAIL_VALID = "emailValid";
    public final static String MIN_LENGTH_VALID = "minLengthValid";
    public final static String MATCH_FIELDS = "matchFields";
    public final static String DATE_FIELDS_VALID = "dateValid";
    public final static String IMAGE_FILE_EXT_VALID = "imageFileValid";
    public static final String YEAR_VALID = "yearValid";
    public static final String CREDITCARD_VALID = "creditcardValid";
    public static final String CVV_VALID = "cvvValid";
    public static final String EXPIRY_DATE_VALID = "expiryDateValid";
	private static final String ACCOUNT_VALID = "accountValid";
    private int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    private int currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1;    
    
    private transient Object bean;
    private Map<String, Object> validationMap = new HashMap<String, Object>();
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        /*
         * This is where you need to get the country specific details.
         */
        
        resources = (MessageResources)request.getAttribute(Globals.MESSAGES_KEY);
        
        ActionErrors errors = populate(this.bean, TO_OBJECT);
        return errors;
        
    }
    
    public ActionErrors populate(Object vo)
    {
        return populate(vo,TO_STRING);
    }
    
    /*
     * field1 does not contain the value of the field but contains the name of the field.
     */
    public void addMatchFields(String key, String otherFieldName)
    {
        addValidationRule(key, MATCH_FIELDS, otherFieldName);
    }
    
    public void addDateFieldsValidation(String key, String otherKey)
    {
        addValidationRule(key, DATE_FIELDS_VALID, otherKey);
    }	

    public void addCreditCardFieldsValidation(String key)
    {
        addValidationRule(key, CREDITCARD_VALID, Boolean.TRUE);
    }	
    
    public void addAccountFieldValidation(String key)
    {
        addValidationRule(key, ACCOUNT_VALID, Boolean.TRUE);
    }    
    
    public void addCVVFieldsValidation(String key)
    {
        addValidationRule(key, CVV_VALID, Boolean.TRUE);
    }	

    public void addCreditCardExpiryDateFieldsValidation(String key)
    {
        addValidationRule(key, EXPIRY_DATE_VALID, Boolean.TRUE);
    }	
    
    @SuppressWarnings("unchecked")
	public void addValidationRule(String key, String rule, Object value)
    {
        Map<String, Object> values =(Map) validationMap.get(key);
        if (values == null)
        {
            values = new HashMap<String, Object>();
            validationMap.put(key,values);
        }
        
        values.put(rule, value);
    }
    
    
    public void addRequiredFields(String[] keys)
    {
        for (int i=0; i< keys.length; i++)
        {
            addValidationRule(keys[i],REQUIRED, Boolean.TRUE);
        }
    }

    
    public void addRange(String key, Comparable min, Comparable max)
    {
        Range range =  new Range(min,max);
        addValidationRule(key,RANGE, range);
    }
    
    public void addEmailValidation(String key)
    {
        addValidationRule(key,EMAIL_VALID,Boolean.TRUE);
    }
    
    public void addYearValidation(String key)
    {
        addValidationRule(key, YEAR_VALID, Boolean.TRUE );
    }
    
    public void addValidateCustomerNumber(String key)
    {
        addValidationRule(key,STRING_NUMBER,Boolean.TRUE);
    }
    
    
    public void addImageFileExtensionValidation(String key)
    {
        addValidationRule(key, IMAGE_FILE_EXT_VALID, Boolean.TRUE);
    }
    
    public void addMinLengthValidation(String key, Integer min, Integer max)
    {
        Range range = new Range(min, max);
        addValidationRule(key,MIN_LENGTH_VALID,range);
    }
    
    public Object getBean() throws ApplicationException
    {
        return bean;
    }
    
    public void setBean(Object bean)
    {
        this.bean = bean;
    }
    
    
    
    public ActionErrors populate(Object vo, int mode)
    {
        String errorMsg = "Unable to format values from bean: "+ vo;
        
        //Object source = (mode == TO_STRING ? bean : this);
        Object target = (mode == TO_STRING ? vo : this);
        
        
        Map valueMap = mapRepresentation(target, mode);
        
        ActionErrors errors = new ActionErrors();
        
        
        if(mode == TO_STRING)
        {
            setBean(vo);
        }
        
        Iterator keyIter = valueMap.keySet().iterator();
        
        while(keyIter.hasNext())
        {
            String currKey = (String) keyIter.next();
            Object  currValue = valueMap.get(currKey);
            
            try
            {
            	ActionErrors currErrors = populateProperty(vo,currKey, currValue, mode, valueMap);
                errors.add(currErrors);
                
            }
            catch(InstantiationException ie)
            {
                throw new FormattingException(errorMsg, ie);
            }
            catch(IllegalAccessException iae)
            {
                throw new FormattingException(errorMsg, iae);				
            }
            catch(InvocationTargetException ite)
            {
                throw new FormattingException(errorMsg,ite);
            }
            catch(NoSuchMethodException nsme)
            {
                //throw new FormattingException(errorMsg,nsme);
                // There can be some assymetry in the form and the bean so do not treat this as an exception.
            }
        }
        
        return errors;
    }
    
    /**
     * @param target
     * @param mode
     * @return
     */
    public Map mapRepresentation(Object vo, int mode)
    {
        String errorMsg = "Unable to format values from bean: "+ vo;
        
        
        Map valueMap = null;
        
        try
        {
            valueMap = PropertyUtils.describe(vo);
        }
        catch(IllegalAccessException iae)
        {
            throw new FormattingException(errorMsg, iae);
        }
        catch(InvocationTargetException ite)
        {
            throw new FormattingException(errorMsg, ite);
        }
        catch(NoSuchMethodException nsme)
        {
            throw new FormattingException(errorMsg,nsme);
        }
        
        Iterator keyIter = keysToSkip().iterator();
        
        while(keyIter.hasNext())
        {
            String key = (String) keyIter.next();
            valueMap.remove(key);
        }
        
        return valueMap;
    }
    
    protected Object convert(Class type, String key, Object obj, int mode)
    throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NumberFormatException
    {
        Object convertedObj = null;
        Formatter formatter = getFormatter(key, type);
        
        try
        {
            switch(mode)
            {				
            case TO_OBJECT:
                convertedObj = formatter.unformat(obj);
                break;
            case TO_STRING:
                if (obj == null)
                    convertedObj = (String) defaultStringMap.get(key);
                else
                    convertedObj = formatter.format(obj);
                
                /**
                 * This let's the toDate being converted into endDay, endMonth and endYear
                 * note that the String here are sensitive.
                 */
                
                if (key.equals("toDate"))
				{
					JulianDate dateValue = (JulianDate) obj;
					if (dateValue != null)
					{
						PropertyUtils.setSimpleProperty(this, "endDay", ""+dateValue.getDay());
						PropertyUtils.setSimpleProperty(this, "endMonth", ""+dateValue.getMonth());
						PropertyUtils.setSimpleProperty(this, "endYear", ""+dateValue.getYear());
					}
					else
					{
						PropertyUtils.setSimpleProperty(this, "endDay", "");
						PropertyUtils.setSimpleProperty(this, "endMonth", "");
						PropertyUtils.setSimpleProperty(this, "endYear", "");
					}
										
				}
				if (key.equals("fromDate"))
				{
					JulianDate dateValue = (JulianDate) obj;
					if (dateValue != null)
					{
						PropertyUtils.setSimpleProperty(this, "startDay", ""+dateValue.getDay());
						PropertyUtils.setSimpleProperty(this, "startMonth", ""+dateValue.getMonth());
						PropertyUtils.setSimpleProperty(this, "startYear", ""+dateValue.getYear());
					}
					else
					{
						PropertyUtils.setSimpleProperty(this, "startDay", "");
						PropertyUtils.setSimpleProperty(this, "startMonth", "");
						PropertyUtils.setSimpleProperty(this, "startYear", "");
					}
				}
                
                break;
                
            default:
                throw new RuntimeException("Unknown mode:" + mode);
            
            }
        }
        catch(FormattingException e)
        {
            e.setFormatter(formatter);
            throw e;
        }
        
        return convertedObj;
    }
    
    
    protected Object getKeyStringValue(String key, int mode)  
    {
        Object target = (mode == TO_STRING ? this.bean : this);
        Map valueMap = mapRepresentation(target, mode);
        
        Object currValue = valueMap.get(key);
		
		return currValue;
	        
   }
    
    
    protected ActionErrors populateProperty(Object vo, String key, Object obj, int mode, Map valueMap)
    throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
    {
    	
        Object target = (mode == TO_STRING? this : vo);
        Class type = PropertyUtils.getPropertyType(vo, key);
        ActionErrors errors = new ActionErrors();
        
        Object value = null;
        
        if (mode == TO_STRING)
        {
            value = convert(type, key, obj, mode);
        }
        else
        {
            try
            {
                String errorKey = null;

                value = convert(type, key, obj, mode);
                
                errorKey = validateRequired(key, obj);
                
                if (errorKey == null)
                    errorKey = validateRange(key, value);
                if (errorKey == null)				
                    errorKey = validateEmail(key,value);
                if (errorKey == null)
                    errorKey = validateLength(key, obj);
                if (errorKey == null)
                    errorKey = validateImageFileExtension(key, obj);
                if (errorKey == null)
                    errorKey = validateMatchFields(key,value, vo, valueMap);
                if (errorKey == null)
                    errorKey = validateCustomerNumber(key, value);
                if (errorKey == null)
					errorKey = validateMatchFields(key,value, vo, valueMap);
                if (errorKey == null)
					errorKey = validateDateFields(key,value, vo, valueMap);
                if (errorKey == null)
					errorKey = validateCreditCardExpiryDateFields(key,value, vo, valueMap);                
                if (errorKey !=null)
                    errors.add(key, new ActionMessage(errorKey,key));

                //This part is placed here because validateCreditCardField method may return an errorKey
                //As we are reusing the same variable to store an error message
                //the error is added above in the ActionMessage and then variable errorKey is reused here 
				errorKey = validateCVVField(key,value, vo, valueMap);
                if (errorKey !=null)
                    errors.add(key, new ActionMessage(errorKey,key));

				errorKey = validateAccountField(key,value, vo, valueMap);
                if (errorKey !=null)
                    errors.add(key, new ActionMessage(errorKey,key));

				errorKey = validateCreditCardField(key,value, vo, valueMap);
                if (errorKey !=null)
                    errors.add(key, new ActionMessage(errorKey,key));

                
            }
            catch(FormattingException e)
            {
                String errorKey = e.getFormatter().getErrorKey();
                errors.add(key, new ActionMessage(errorKey, key));
            }
            catch (NumberFormatException nfe)
            {
                String errorKey;
                
                if (resources.getMessage("error.numberformatexception."+key) == null)		
                    errorKey = "error.numberformatexception";
                else
                    errorKey = "error.numberformatexception."+key;
                
                errors.add(key, new ActionMessage(errorKey,key));
            }
            
        }
        
        PropertyUtils.setSimpleProperty(target, key, value);
        
        return errors;
        
    }
    
    private String validateAccountField(String key, Object value, Object vo, Map valueMap)
	{
		Map rules = (Map) validationMap.get(key);
		
		if (rules == null)
			return null;
		
		if (!rules.containsKey(ACCOUNT_VALID))
			return null;
		
		if (value == null)
			return null;
		
		
		if (value.equals(MPayment.TENDERTYPE_CreditCard))
		{
			String accountName = (String) valueMap.get("accountName");
			
			if (accountName == null || accountName.equals(""))
				return "error.required.accountName";
		}
				
		return null;


	}

	private String validateCreditCardExpiryDateFields(String key, Object value, Object vo, Map valueMap)
	{
		Map rules = (Map) validationMap.get(key);
		
		if (rules == null)
			return null;
		
		if (!rules.containsKey(EXPIRY_DATE_VALID))
			return null;
		
		if (value == null)
			return null;
		
		
		if (value.equals(MPayment.TENDERTYPE_CreditCard))
		{
			String creditCardExpYear = (String) valueMap.get("creditCardExpYear");
			
			if (creditCardExpYear == null || creditCardExpYear.equals(""))
				return "error.required.creditCardExpYear";

			if (Integer.parseInt(creditCardExpYear) == currentYear)
			{	
			
			
				String creditCardExpMonth = (String) valueMap.get("creditCardExpMonth");
	
				if (creditCardExpMonth == null || creditCardExpMonth.equals(""))
					return "error.required.creditCardExpMonth";
				
				if (Integer.parseInt(creditCardExpMonth) < currentMonth)
					return "error.creditCardExpMonth";
			}
			
		}
				
		return null;

	}

	private String validateCustomerNumber(String key, Object value)
    {
        
        Map rules = (Map) validationMap.get(key);
        
        if(rules == null)
            return null;
        
        Boolean bool = (Boolean) rules.get(STRING_NUMBER);
        
        if (bool == null)
        {
            return null;
        }
        
        String str = (String) value;

        boolean isNumber = true;
        try 
        {
           // Double numb = new Double(str);
        }
        catch(NumberFormatException e)
        {
            isNumber = false;
        }

        
        if (!isNumber)
        {
	      
          if (resources.getMessage("error.customer.id.numberformat."+key) == null)		
	          return "error.customer.id.numberformat";
	      else
	          return "error.customer.id.numberformat."+key;		
        }
        else
        
        if (str.length()!=13)
        {
	      
          if (resources.getMessage("error.customer.id.length."+key) == null)		
	          return "error.customer.id.length";
	      else
	          return "error.customer.id.length."+key;		
        }
        else
            return null;
        
    }
    
    
    private String validateRange(String key, Object value)
    {
        
        Map rules = (Map) validationMap.get(key);
        
        if(rules == null)
            return null;
        
        Range range = (Range) rules.get(RANGE);
        
        if (range == null)
        {
            return null;
        }
        
        if(!range.isInRange((Comparable) value))
        {
            if (resources.getMessage("error.range."+key) == null)		
                return "error.range";
            else
                return "error.range."+key;					
        }			
        else
            return null;
    }
    
    private String validateEmail(String key, Object value)
    {
        Map rules = (Map) validationMap.get(key);
        
        if(rules == null)
            return null;
        
        Boolean email_valid = (Boolean) rules.get(EMAIL_VALID);
        
        if (email_valid == null)
            return null;
        
        String email = (String) value;
        if(!EmailValidator.isValidEmail(email))
            return "error.email";
        else
            return null;
        
    }
    
    private String validateImageFileExtension(String key, Object value)
    {
        Map rules = (Map) validationMap.get(key);
        
        if (rules == null)
            return null;
        
        Boolean imageFile_valid = (Boolean) rules.get(IMAGE_FILE_EXT_VALID);
        
        if (imageFile_valid == null)
            return null;
        
        //String imageFile = (String) value;
        
        /*		if(!FileExtensionChecker.isValidFileExtention(imageFile))
         return "error.fileNotSupportedException";
         else*/
        return null;
        
    }	
    
    private String validateMatchFields(String key, Object value, Object vo, Map valueMap) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
    {
        Map rules = (Map) validationMap.get(key);
        
        if((rules == null))
            return null;
        
        if (!rules.containsKey(MATCH_FIELDS))
            return null;
        
        String otherField = (String) rules.get(MATCH_FIELDS);
        
        Class typeOfOtherField = PropertyUtils.getPropertyType(vo, otherField);
        String currOtherFieldValue = (String) valueMap.get(otherField);
        
        if (otherField == null)
        {
            return null;
        }
        else
        {
            Object obj = convert(typeOfOtherField, otherField, currOtherFieldValue, TO_OBJECT);
            if(!value.equals(obj))
            {
                if (resources.getMessage("error.match."+key) == null)		
                    return "error.match";
                else
                    return "error.match."+key;					
            }
            else
                return null;
            
        }
    }
    
    private String validateLength(String key, Object value)
    {
        Map rules = (Map) validationMap.get(key);
        
        if(rules == null)
            return null;
        
        Range range = (Range) rules.get(MIN_LENGTH_VALID);
        
        if (range == null)
            return null;
        
        String str = (String) value;
        Integer len = Integer.valueOf(str.length());
        
        if(!range.isInRange(len))
        {
            if (resources.getMessage("error.minlength."+key) == null)		
                return "error.minlength";
            else 
                return "error.minlength."+key;
        }
        else
            return null;
        
    }
    
//    private String validateYear(String key, Object obj)
//    {
//        Map rules = (Map) validationMap.get(key);
//        
//        if (rules == null)
//            return null;
//        
//        Boolean year_valid = (Boolean) rules.get(YEAR_VALID);
//        
//        String year = (String) obj;
//        
//        if (year.length()!= 4)
//            return "error.year.length.invalid";
//        
//        return null;
//        
//        
//    }
    
    private String validateRequired(String key, Object obj)
    {
        // This was added because of the String[]
        if (obj!=null && !String.class.isAssignableFrom(obj.getClass()))
        {
            return null;
        }
        
        String value = (String) obj;
        Map rules = (Map) validationMap.get(key);
        
        if(rules == null)
            return null;
        
        Boolean required = (Boolean) rules.get(REQUIRED);
        
        if (required == null)
            return null;
        
        boolean isRequired = required.booleanValue();
        boolean isBlank =(value == null || value.trim().equals(""));
        
        if(isRequired && isBlank)
        {
            if (resources.getMessage("error.required."+key) == null)		
                return "error.required";
            else
                return "error.required."+key;
        }
        
        else
            return null;
    }

    private String validateCreditCardField(String key, Object value, Object vo, Map valueMap) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		Map rules = (Map) validationMap.get(key);
		
		if (rules == null)
			return null;
		
		if (!rules.containsKey(CREDITCARD_VALID))
			return null;
		
		if (value == null)
			return null;
		
		if (value.equals(MPayment.TENDERTYPE_CreditCard))
		{
			String creditCardNumber = (String) valueMap.get("creditCardNumber");

			if (creditCardNumber == null || creditCardNumber.equals(""))
				return "error.required.creditCardNumber";
			
		}
				
		return null;
		
	}	
    
    private String validateCVVField(String key, Object value, Object vo, Map valueMap) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		Map rules = (Map) validationMap.get(key);
		
		if (rules == null)
			return null;
		
		if (!rules.containsKey(CVV_VALID))
			return null;
		
		if (value == null)
			return null;
		
		if (value.equals(MPayment.TENDERTYPE_CreditCard))
		{
			String cvv = (String) valueMap.get("cvv");

			if (cvv == null || cvv.equals(""))
				return "error.required.cvv";
			
		}
				
		return null;
		
	}	
    
    private String validateDateFields(String key, Object value, Object vo, Map valueMap) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		Map rules = (Map) validationMap.get(key);
		
		if (rules == null)
			return null;
		
		if (!rules.containsKey(DATE_FIELDS_VALID))
			return null;
		
		String otherKey = (String) rules.get(DATE_FIELDS_VALID);
		
		String otherValue = (String) valueMap.get(otherKey);
		
		if (value == null && otherValue == null)
			return null;
		
		if (value == null)
			return "error.date.prior.today";
		
		if (otherValue == null)
			return "error.date.prior.today";
				
		Class otherType = PropertyUtils.getPropertyType(vo, otherKey);
				

		JulianDate toDate = (JulianDate) convert(otherType, otherKey, otherValue, TO_OBJECT);

		JulianDate fromDate = (JulianDate) value;
		
		Range range = new Range(JulianDate.getToday(), toDate);
		
		if (!range.isInRange(fromDate))
			return "error.date.prior.today";
		else
			return null;
		
	}	
    
    
    protected Formatter getFormatter(String key, Class type)
    {
        Formatter formatType = (Formatter) formatMap.get(key);
        
        if (formatType == null)
        {
            return Formatter.getFormatter(type);
        }
        
        return formatType;
    }
    
    public void setDefaultString(String key, String value)
    {
        defaultStringMap.put(key,value);
    }
    /*
     * This is where you can override the way things are formatted.
     */
    public void setFormatterType(String key, Class type)
    {
        if(!Formatter.class.isAssignableFrom(type))
            throw new FormattingException(type + "must be a Formatter");
        
        formatMap.put(key,type);
    }
    
    protected Map mapRepresentation(Object bean)
    {
        String errorMsg = "Unable to format values from bean: " + bean;
        Map valueMap = null;
        
        try
        {
            valueMap = PropertyUtils.describe(bean);
        }
        catch(IllegalAccessException iae)
        {
            throw new FormattingException(errorMsg,iae);		
        }
        catch(InvocationTargetException ite)
        {
            throw new FormattingException(errorMsg,ite);
        }
        catch(NoSuchMethodException nsme)
        {
            throw new FormattingException(errorMsg,nsme);
        }
        
        Iterator keyIter = keysToSkip().iterator();
        
        while(keyIter.hasNext())
        {
            String key = (String) keyIter.next();
            valueMap.remove(key);
        }
        return valueMap;
    }
    
    protected ArrayList keysToSkip()
    {
        ArrayList<String> keysToSkip =  new ArrayList<String>();
        
        // Don't popluate "class" field inherited from Object
        
        keysToSkip.add("class");
        keysToSkip.add("servletWrapper");
        keysToSkip.add("multipartRequestHandler");
        keysToSkip.add("bean");
        keysToSkip.add("properties");
        keysToSkip.add("checkBoxLists");
//        keysToSkip.add("file");
        keysToSkip.add("minValue");
        keysToSkip.add("maxValue");

        
        return keysToSkip;
    }
    
    
    //
    protected String action;
    protected String input;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
    
    
    
}
