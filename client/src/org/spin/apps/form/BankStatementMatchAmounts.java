/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 *************************************************************************************/
package org.spin.apps.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Amount distribution for the Bank Statement Matcher.
 *
 * It is pure arithmetic, without any dependency on PO / Env / DB, so it can be unit tested.
 * Every amount is signed: positive means income (receipt) and negative means outcome (payment).
 *
 * The result always keeps the accounting invariants:
 * <li>The sum of TrxAmt equals the payment amount (or the sum of payments on the reverse match)</li>
 * <li>Per line StmtAmt = TrxAmt + ChargeAmt</li>
 * <li>On the reverse match the sum of StmtAmt equals the bank statement line amount</li>
 *
 * @author Carlos Parada, cparada@erpya.com , http://www.erpya.com
 */
public class BankStatementMatchAmounts {

	/**
	 * Amounts calculated for one line of the match
	 */
	public static class LineAmount {
		/**	Position of the source amount on the list given to the distribution	*/
		private final int index;
		/**	Amount applied to the ADempiere payment	*/
		private final BigDecimal trxAmt;
		/**	Difference taken by the charge account	*/
		private final BigDecimal chargeAmt;
		/**	Amount moved by the bank	*/
		private final BigDecimal stmtAmt;

		private LineAmount(int index, BigDecimal trxAmt, BigDecimal chargeAmt, BigDecimal stmtAmt) {
			this.index = index;
			this.trxAmt = trxAmt;
			this.chargeAmt = chargeAmt;
			this.stmtAmt = stmtAmt;
		}

		public int getIndex() {
			return index;
		}

		public BigDecimal getTrxAmt() {
			return trxAmt;
		}

		public BigDecimal getChargeAmt() {
			return chargeAmt;
		}

		public BigDecimal getStmtAmt() {
			return stmtAmt;
		}

		@Override
		public String toString() {
			return "LineAmount[index=" + index + ", TrxAmt=" + trxAmt + ", ChargeAmt=" + chargeAmt + ", StmtAmt=" + stmtAmt + "]";
		}
	}

	/**
	 * Distribute a single payment over N imported bank statement lines.
	 * The result is returned on processing order, from the biggest to the smallest line.
	 * @param payAmt signed payment amount
	 * @param lineAmounts signed amounts of the imported lines
	 * @param mixed true when income and outcome are mixed on the selection
	 * @return one entry per line, referencing its position on lineAmounts
	 */
	public static List<LineAmount> distributeToPayment(BigDecimal payAmt, List<BigDecimal> lineAmounts, boolean mixed) {
		List<Integer> order = sortedIndexes(lineAmounts);
		if (mixed) {
			return distributeMixedToPayment(payAmt, lineAmounts, order);
		}
		List<LineAmount> result = new ArrayList<LineAmount>();
		//	Homogeneous selection, the payment covers as much of each line as possible
		BigDecimal remainingPayment = payAmt.abs();
		for (int index : order) {
			BigDecimal lineTrxAmt = amountAt(lineAmounts, index);
			BigDecimal lineTrxAmtAbs = lineTrxAmt.abs();
			BigDecimal covered = remainingPayment.min(lineTrxAmtAbs).max(BigDecimal.ZERO);
			BigDecimal lineCharge = lineTrxAmtAbs.subtract(covered);
			remainingPayment = remainingPayment.subtract(covered);
			BigDecimal signum = BigDecimal.valueOf(lineTrxAmt.signum());
			result.add(new LineAmount(index, covered.multiply(signum), lineCharge.multiply(signum), lineTrxAmt));
		}
		//	The payment applies more than what the bank moved, the smallest line takes the remainder
		BigDecimal difference = remainingPayment;
		if (difference.compareTo(BigDecimal.ZERO) != 0 && !result.isEmpty()) {
			LineAmount last = result.get(result.size() - 1);
			BigDecimal originalTrxAmt = amountAt(lineAmounts, last.getIndex());
			BigDecimal payDifference = difference.multiply(BigDecimal.valueOf(originalTrxAmt.signum()));
			BigDecimal trxAmt = last.getTrxAmt().add(payDifference);
			//	StmtAmt = TrxAmt + ChargeAmt, the charge closes against the original line amount
			result.set(result.size() - 1,
					new LineAmount(last.getIndex(), trxAmt, originalTrxAmt.subtract(trxAmt), originalTrxAmt));
		}
		return result;
	}

	/**
	 * Distribute a single payment over N imported lines when income and outcome are mixed.
	 * Every line keeps its own signed amount, so the bank statement is not distorted, and the
	 * whole difference is taken by the biggest line.
	 * @param payAmt signed payment amount
	 * @param lineAmounts signed amounts of the imported lines
	 * @param order line indexes sorted by absolute amount, descending
	 * @return one entry per line
	 */
	private static List<LineAmount> distributeMixedToPayment(BigDecimal payAmt, List<BigDecimal> lineAmounts, List<Integer> order) {
		BigDecimal importedTotal = BigDecimal.ZERO;
		for (BigDecimal lineAmount : lineAmounts) {
			importedTotal = importedTotal.add(lineAmount != null ? lineAmount : BigDecimal.ZERO);
		}
		//	Difference between what the bank says and what was really paid
		BigDecimal difference = importedTotal.subtract(payAmt);
		List<LineAmount> result = new ArrayList<LineAmount>();
		boolean isFirst = true;
		for (int index : order) {
			BigDecimal lineTrxAmt = amountAt(lineAmounts, index);
			if (isFirst && difference.compareTo(BigDecimal.ZERO) != 0) {
				result.add(new LineAmount(index, lineTrxAmt.subtract(difference), difference, lineTrxAmt));
			} else {
				result.add(new LineAmount(index, lineTrxAmt, BigDecimal.ZERO, lineTrxAmt));
			}
			isFirst = false;
		}
		return result;
	}

	/**
	 * Distribute a single imported bank statement line over N payments, splitting it on N lines.
	 * The result is returned on processing order, the last entry is the one that takes the
	 * pending statement amount, so the sum of StmtAmt always matches the bank statement.
	 * @param importedAmt signed amount of the imported line
	 * @param payAmounts signed payment amounts
	 * @param mixed true when income and outcome are mixed on the selection
	 * @return one entry per payment, referencing its position on payAmounts
	 */
	public static List<LineAmount> distributeToPayments(BigDecimal importedAmt, List<BigDecimal> payAmounts, boolean mixed) {
		int signum = importedAmt.signum() != 0 ? importedAmt.signum() : 1;
		//	Statement amount pending to be distributed (signed)
		BigDecimal remainingStmtAmt = importedAmt;
		List<Integer> order = sortedIndexes(payAmounts);
		List<LineAmount> result = new ArrayList<LineAmount>();
		for (int position = 0; position < order.size(); position++) {
			int index = order.get(position);
			boolean isLastPayment = position == order.size() - 1;
			BigDecimal payAmt = amountAt(payAmounts, index);
			//	TrxAmt always matches the ADempiere payment amount
			BigDecimal lineTrxAmt = mixed
					? payAmt
					: payAmt.abs().multiply(BigDecimal.valueOf(signum));
			//	Intermediate lines take the payment amount as is, the last one takes the pending
			//	statement amount so the sum of all StmtAmt matches the bank statement amount
			BigDecimal lineStmtAmt = isLastPayment ? remainingStmtAmt : lineTrxAmt;
			remainingStmtAmt = remainingStmtAmt.subtract(lineStmtAmt);
			//	StmtAmt = TrxAmt + ChargeAmt, so the difference goes to the charge
			result.add(new LineAmount(index, lineTrxAmt, lineStmtAmt.subtract(lineTrxAmt), lineStmtAmt));
		}
		return result;
	}

	/**
	 * Get the amount indexes sorted by absolute value, descending
	 * @param amounts
	 * @return
	 */
	private static List<Integer> sortedIndexes(List<BigDecimal> amounts) {
		List<Integer> indexes = new ArrayList<Integer>();
		for (int index = 0; index < amounts.size(); index++) {
			indexes.add(index);
		}
		indexes.sort(Comparator.comparing((Integer index) -> amountAt(amounts, index).abs()).reversed());
		return indexes;
	}

	/**
	 * Null safe amount access
	 * @param amounts
	 * @param index
	 * @return
	 */
	private static BigDecimal amountAt(List<BigDecimal> amounts, int index) {
		BigDecimal amount = amounts.get(index);
		return amount != null ? amount : BigDecimal.ZERO;
	}
}
