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
  Tracked in the spec by an `ignore()` scenario so it cannot be silently regressed.
- **S8** Add negative guards proving the narrowing did not over-clear: when the partner
  owns accounts, `invoice()` / `setInvoice()` / `setOrder()` must still resolve one.

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
| T8 | S8 | parent inline (guards requested by verification) | `test:` slice |

### Slice boundaries

Two reviewable slices on `hotfix/3.9.4.002-4313`, on top of `48dbaac3b`. They are
referenced by subject: amending a commit to record its own hash rewrites that hash, so
`git log --oneline` is the source of truth for the current ids.

1. `test: pin the five pay selection review findings observed red` — the regression spec
   alone, no behaviour change, observed red.
2. `fix: resolve the payment rule and clear a foreign bank account` — the production
   change, 49 authored lines across `CalloutPaySelection.java` and
   `MPaySelectionLine.java`, plus this feature document.

The branch itself carries 138 commits against `origin/develop` (`git rev-list --count`),
80 of them non-merge, and `git diff --shortstat origin/develop...HEAD` reports 97 files,
8243 insertions and 1063 deletions. That count is against a single merge base and the
branch has several, so the number moves with the base chosen; it is the pre-existing shape
of PR #4490 and is not restructured here. This feature adds two commits. Push, PR update
and merge stay the user's decision.

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
- **L10** Independent verification (`gentle-ai-verify`) reproduced S2-S5 as MET, including
  seven single-mutation probes (each fix removed one at a time against `HEAD~1` classes:
  F1 -> 1 failure, F2 callout -> 1, F2 model -> 1, F3 callout -> 1, F3 model -> 1, F4 ->
  exactly 2, F5 -> exactly 2) and bytecode proof that `order()` holds zero `getByPartner`
  references, so it clears and never auto-assigns. It confirmed the High finding stays open
  (`isach|bpbankacctuse` absent from the fix diff) and that no database row leaked.
- **L11** Runner caveat found by verification: `/tmp/pr4313/run_spec.sh` ends with
  `echo "SUITE_EXIT=$?"`, so the shell exit of the authorized command is always 0 even on
  RED runs. The meaningful code is the `SUITE_EXIT=` line. This must not be carried into CI
  as a pass signal without reading that line.
- **L12** Unrelated defect surfaced while writing the guards, deliberately NOT fixed here:
  `MBPBankAccount.getByPartner` (`base/src/org/compiere/model/MBPBankAccount.java:55-66`)
  sets no `ORDER BY`, so `banks.get(0)` / `stream().findFirst()` resolve an arbitrary row
  when a partner owns several accounts. Four other payment sites do the same
  (`MPaySelectionCheck.java:517,528`, `PaymentExportList.java:425,454`,
  `MHRPaySelectionCheck.java:484,491`). Adding an `ORDER BY` would change which account
  every one of them pays, which is exactly the product decision of S7, so the guards assert
  the resolved account belongs to the partner rather than pinning a particular id.
- **L13** Root cause of the flaky fixture, found by writing the N1 guards (this is the
  reason the earlier RED was weaker than it looked): `AdempiereTestCase` wraps the **whole
  suite** in one transaction (`BeforeAndAfterAll`, `trxName` created once at trait level,
  rolled back only in `afterAll`). So an account saved by one scenario stays visible to all
  later scenarios. Combined with `MBPBankAccount.getByPartner` setting no `ORDER BY`, a
  helper resolving "a partner owning an account" by `C_BPartner_ID` started returning
  partners whose account existed **only in the transaction**: invisible to the callout
  (which reads with `trx=null`), and ambiguous once the partner owned two accounts.
  Observed directly: `F4 setOrder` failed `1000037 did not equal 1000038` (two accounts in
  trx, arbitrary winner), and `N1` failed `[] was empty` (the account was in trx, not
  committed).
  Fix: `committedAccountPartnerIds` is snapshotted in `beforeAll` before any scenario
  writes, `vendorWithAccount` / `vendorWithoutAccount` are anchored to that snapshot, and
  every scenario asserting a specific account is resolved, kept, or cleared takes
  `partnerWithNoAccountYet`, a partner owning no account committed **or** in transaction.
  No scenario outcome may depend on execution order.
- **L14** Re-observed RED with the order-safe fixtures, against `HEAD~1` production classes
  compiled into a scratch overlay (`/tmp/pr4313/mut_recheck.sh`, hardlink farm with the two
  class files broken so javac never writes through): `succeeded 6, failed 7, ignored 1`,
  `SUITE_EXIT=1` — the same 7 findings as L5, now order-independent. The 3 N1 guards pass
  there by design: `HEAD~1` never cleared an account, so it could not over-clear.
  GREEN with the fix: `succeeded 13, failed 0, ignored 1`.
- **L15** Lesson about mutation probes: the first over-clearing probe reported
  `succeeded 13, failed 0`, which would have read as "the N1 guards cannot fail". It was
  worthless: the `perl` substitution matched nothing, so the suite ran against unmutated
  code. A probe must assert its own mutation before it trusts its result.
  `/tmp/pr4313/mut_overclear.sh` now aborts with `MUTATION_NOT_APPLIED` unless
  `grep -c 'banks.get(0)'` is 0 and `setC_BP_BankAccount_ID(0)` appears 3 times, and it
  re-checks the compiled bytecode for `get(0)` references afterwards.
- **L16** Falsifiability of the N1 guards, proven with an auto-verified mutation probe
  (`/tmp/pr4313/mut_overclear.sh`): over-clear the fix so every resolved account becomes
  "clear" instead of "keep". The probe refuses to run unless its own mutation is present
  (`diff` shows exactly 4 removed `banks.get(0)` keeps and 4 added clears, `grep` finds 0
  left, and `javap` finds 0 `get(0)` references in the mutant bytecode). Result:
  `succeeded 8, failed 5` — the three N1 guards fail plus both F4 scenarios, exactly as
  expected when the code over-clears. Without the self-check the same probe had reported
  13/13 against unmutated code.
- **L17** Coverage gap left open, not fixed here: `client/src/org/compiere/apps/panel/order/
  PaySelect.java` and `base/src/org/compiere/process/PSCreateFromOrder.java:61` call the
  changed overloads and have no tests; `MPaySelectionCheck.java:514` still copies
  `paySelectionLine.getC_BP_BankAccount_ID()` without re-validating that the account belongs
  to the check partner. That is a pre-existing model-level gap: the fixes stop stale values
  at the entry points, they do not add a guard at the point of payment.
