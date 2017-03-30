package pl.kozmatteo.finance.report.filter;

import pl.kozmatteo.finance.report.Money;
import pl.kozmatteo.finance.report.Transaction;

public class ExpenseTransactionFilter implements TransactionFilter {

  @Override
  public boolean test(final Transaction transaction) {
    return transaction.getAmount().compareTo(Money.of(0)) < 0;
  }
}
