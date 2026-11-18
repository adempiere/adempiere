# invoiceOpen Java/SQL Divergence Analysis

## Summary

MInvoice.getOpenAmt() (Java) does not implement payment schedule logic that
exists in C_Invoice_Open.sql (SQL).

## SQL Behavior (lines 82-105)

When p_C_InvoicePaySchedule_ID > 0:
1. Iterate payment schedules in DueDate order
2. For each schedule before the target: subtract DueAmt from remaining paid
3. For target schedule: return (DueAmt * MultiplierCM) - remaining

When p_C_InvoicePaySchedule_ID is null:
1. Return GrandTotal - PaidAmt

## Java Behavior (lines 1219-1245)

Always returns GrandTotal - AllocatedAmt regardless of payment schedule.

## Impact

Split-payment invoices with C_InvoicePaySchedule records will show incorrect
open amounts per schedule in Java.

## Resolution

Port SQL payment schedule logic to Java in InvoiceFunctions.invoiceOpen().
