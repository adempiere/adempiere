@Title	Wrap Security Procedures
@Rem $Id: access.bat,v 1.3 2001/11/19 14:26:47 jjanke Exp $

@Echo	------
@Echo	Wrap
@Echo	------

wrap iname=context_header
wrap iname=context_body

@Echo	------
@Echo	Create
@Echo	------
@sqlplus adempiere/adempiere @Wrap

@pause
