CREATE UNIQUE INDEX HR_Job_Value
   ON HR_Job (AD_CLient_ID, Value);

CREATE UNIQUE INDEX HR_Department_Value
   ON HR_Department (AD_CLient_ID, Value);
   
CREATE UNIQUE INDEX HR_Contract_Value
   ON HR_Contract (AD_CLient_ID, Value);
   