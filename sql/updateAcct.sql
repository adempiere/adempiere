  IF(tabla = 224 ) THEN--GL_Journal --listo
         UPDATE GL_Journal SET C_ConversionType_ID = new_ConversionType, DateDoc = new_Date1, DateAcct = new_DateAcct WHERE GL_Journal_ID = record;
    END IF;
	IF(tabla = 318 ) THEN--C_Invoice --listo
         UPDATE C_Invoice SET C_ConversionType_ID = new_ConversionType, DateInvoiced = new_Date1, DateAcct = new_DateAcct, User1_ID = new_User1, C_Activity_ID = new_activity, C_Campaign_ID = new_Campaign WHERE C_Invoice_ID = record;
    END IF;
	IF(tabla = 319 ) THEN-- M_InOut --listo
         UPDATE M_InOut SET MovementDate = new_Date1, DateAcct = new_DateAcct, C_Activity_ID = new_activity, User1_ID = new_User1, C_Campaign_ID = new_Campaign WHERE M_InOut_ID = record;
    END IF;
	IF(tabla = 321 ) THEN--M_Inventory --listo
         UPDATE M_Inventory SET MovementDate = new_Date1, User1_ID = new_User1, C_Activity_ID = new_activity, C_Campaign_ID = new_Campaign WHERE M_Inventory_ID = record;
    END IF;
	IF(tabla = 323 ) THEN--M_Movement --listo
         UPDATE M_Movement SET MovementDate = new_Date1, User1_ID = new_User1, C_Activity_ID = new_activity, C_Campaign_ID = new_Campaign WHERE M_Movement_ID = record;
    END IF;
	IF(tabla = 335 ) THEN--C_Payment
         UPDATE C_Payment SET DateTrx = new_Date1, DateAcct = new_DateAcct, C_ConversionType_ID = new_ConversionType, User1_ID = new_User1, C_Activity_ID = new_activity, C_Campaign_ID = new_Campaign WHERE C_Payment_ID = record;
    END IF;
	IF(tabla = 392 ) THEN--C_BankStatement --bien 
         UPDATE C_BankStatement SET statementlinedate = new_Date1 WHERE C_BankStatement_ID = record;
    END IF;
	IF(tabla = 407 ) THEN--C_Cash
        UPDATE C_Cash SET DateAcct = new_DateAcct, DateAcct = new_Date1, User1_ID = new_User1, C_Activity_ID = new_activity WHERE C_Cash_ID = record;
    END IF;
	IF(tabla = 472 ) THEN--M_MatchInv
        UPDATE M_MatchInv SET DateTrx = new_Date1, DateAcct = new_DateAcct WHERE M_MatchInv_ID = record;
    END IF;
	IF(tabla = 735) THEN--C_AllocationHdr
        UPDATE C_AllocationHdr SET DateTrx = new_Date1, DateAcct = new_DateAcct WHERE C_AllocationHdr_ID = record;
    END IF; 