# Pay selection line: payment rule and bank account inheritance

Feature: close the five CodeRabbit findings reported on adempiere/adempiere#4490
(branch `hotfix/3.9.4.002-4313`) with regression tests observed RED first, then the
fixes, then GREEN. The High security finding is kept as a separate product decision.

## Specs

- **S1** Retrieve the CodeRabbit review observations of public repo `adempiere/adempiere`
  PR #4490 (branch `hotfix/3.9.4.002-4313`) and turn each one into a test.
  Observed review body: 4 inline comments + 1 summary, 238 lines, stored at
  `/tmp/pr4490/rabbit_inline.json`.
- **S2** Write RED regression tests for each of the 5 CodeRabbit findings, before the
  fixes. Findings as reported:
  - F1 `CalloutPaySelection.java:320` (Minor) — "Grid saves null via `set_ValueNoCheck`,
    model fallback never applies"
  - F2 `CalloutPaySelection.java:344` (Major) — "Stale bank account when new partner has none"
  - F3 `CalloutPaySelection.java:344` order (Major) — "Same issue in `order()` callout"
  - F4 `MPaySelectionLine.java:150` (Minor) — "`getByPartner` passes `trxName=null`"
  - F5 `ValidatePaySelectionLineFromInvoice.scala:362` (Minor) — "Test doesn't actually
    exercise the copy"
- **S3** Observe RED for each new scenario — run the suite and capture the expected
  failure per finding.
- **S4** Implement the fixes in `CalloutPaySelection.java` (`invoice()` / `order()`) and
  `MPaySelectionLine.java` (`setInvoice()` / `setOrder()`).
- **S5** Observe GREEN and keep the pre-existing spec green
  (`ValidatePaySelectionLineFromInvoice`).
- **S6** Show the changes (final diff).
- **S7** Report the High security concern (auto-assigned partner account without
  filtering `IsACH` / `BPBankAcctUse`) as a separate product decision, not fixed here.

## Tasks

| ID | Spec | Route | Commit |
|----|------|-------|--------|
| T1 | S1 | parent inline (`gh` API) | n/a (evidence only) |
| T2 | S2 | parent inline (test authoring needs full review context) | `test:` slice |
| T3 | S3 | parent inline (`/tmp/pr4313/run_spec.sh`) | `test:` slice |
| T4 | S4 | parent inline (2 files, one behaviour unit) | `fix:` slice |
| T5 | S5 | parent inline | `fix:` slice |
| T6 | S6 | parent inline | `fix:` slice |
| T7 | S7 | parent report | n/a (decision open) |

### Slice boundaries

Two reviewable slices on `hotfix/3.9.4.002-4313`, on top of `48dbaac3b`. They are
referenced by subject: amending a commit to record its own hash rewrites that hash, so
`git log --oneline` is the source of truth for the current ids.

1. `test: pin the five pay selection review findings observed red` — the regression spec
   alone, no behaviour change, observed red.
2. `fix: resolve the payment rule and clear a foreign bank account` — the production
   change, 49 authored lines across `CalloutPaySelection.java` and
   `MPaySelectionLine.java`, plus this feature document.

The branch itself carries 9290 authored lines in 139 commits against `origin/develop`.
That is the pre-existing shape of PR #4490 and is not restructured here; this feature
adds two commits. Push, PR update and merge stay the user's decision.

## Log

- **L1** Original request (paraphrased from the session; the verbatim prompt is not
  retained in the compacted context): retrieve the CodeRabbit review observations from
  public repo `adempiere/adempiere` PR #4490, write RED regression tests for each of the
  5 findings, then implement the fixes and show the changes (RED → GREEN cycle).
- **L2** Environment: work happens in the git worktree
  `.worktrees/adempiere-pr-4313`; Postgres at `localhost:5432` (`adempiere`/`adempiere`/
  `adempiere`); `ADEMPIERE_APPS_TYPE=standalone` is required for the DB connection; Scala
  3.3.1 + ScalaTest 3.2.10; JDK Azul Zulu 17.0.18.
- **L3** Classpath defects found while standing up the runner, both fixed in
  `/tmp/pr4313/run_spec.sh`:
  - `glassfish.jar` on the classpath makes `Ini.getAdempiereHome` start CORBA and die
    with `ClassNotFoundException: org.omg.PortableInterceptor.ClientRequestInterceptor`
    on JDK 17. Java compilation keeps the full jar set; the Scala/test classpath excludes it.
  - `tools/lib/legacy/wms-support-legacy-1.0.0.jar` ships 2022 copies of
    `org.eevolution.model.MWM*` that make the unqualified Scala imports in
    `org.eevolution.dsl` ambiguous. Only `freight-support-legacy-*.jar` is kept, the same
    rule `build.xml` applies.
- **L4** Fixture corrections, established by probing the real database and models rather
  than assuming (this is what makes each RED attributable to a bug):
  - This client keeps exactly **one** active bank account (`C_BP_BankAccount_ID=100`,
    partner `117`), and partner `117` is **not** a vendor. `vendorWithAccount` therefore
    promotes the partner to vendor inside the rolled back transaction.
  - `PO.set_Value` throws `IllegalArgumentException: PaymentRule is mandatory.` so the
    CodeRabbit-suggested `set_ValueOfColumn(..., null)` is not executable. The grid path
    that actually reaches the column is `GridTable.dataSavePO` → `PO.set_ValueNoCheck`
    (`base/src/org/compiere/model/GridTable.java:2174`). Tests use a `GridSavedLine`
    exposing `set_ValueNoCheck`.
  - A new `MPaySelectionLine` already carries `"S"` (Check) from the dictionary default,
    so the null pre-copy state of F5 is written through the grid path.
  - `C_Order` has no committed order of a partner without a bank account, so F3/F4 own
    their purchase order inside the transaction (`DocBaseType='API'`/`'POO'`, not
    `'AP Invoice'`).
- **L5** RED observed (`Tests: succeeded 3, failed 7`): the 7 failures are F1-1, F2-1,
  F2-2, F3-1, F3-2, F4-1, F4-2 — all real. The 3 that pass are the F1 mechanism proof
  and both F5 pins, which pin behaviour that already works.
- **L6** Fix scope decision: `order()` **clears** the bank account when the partner
  changes and does not auto-assign one. Auto-assignment without filtering `IsACH` /
  `BPBankAcctUse` is the High finding (S7) and stays a product decision, so this fix
  narrows behaviour instead of widening it. Where auto-assignment already existed
  (`invoice()`, `setInvoice()`, `setOrder()`) it is kept and the empty case now clears.
- **L7** GREEN observed after the fixes:
  - `ValidatePaySelectionLineDocumentDefaults` — `Tests: succeeded 10, failed 0`.
  - `ValidatePaySelectionLineFromInvoice` (the spec that shipped with the PR) —
    `Tests: succeeded 6, failed 0`, so the fixes did not regress it.
  - `javac` over all 4204 non-ui/zk sources: 0 errors.
- **L8** Why F1 is fixed in the callout and F4 in the model: `GridTab` exposes no
  transaction name (`base/src/org/compiere/model/GridTab.java` has no `getTrxName`), so
  the callout cannot read the invoice in the transaction that owns it and must resolve the
  null itself. `MPaySelectionLine` does have `get_TrxName()`, so there the correct fix is
  to pass it to `MBPBankAccount.getByPartner(ctx, partnerId, null, get_TrxName())`.
- **L9** `setC_BP_BankAccount_ID(0)` is the clearing path: `X_C_PaySelectionLine` maps any
  value below 1 to a null column, so the model fix stores NULL, matching the callout's
  `mTab.setValue("C_BP_BankAccount_ID", null)`.
