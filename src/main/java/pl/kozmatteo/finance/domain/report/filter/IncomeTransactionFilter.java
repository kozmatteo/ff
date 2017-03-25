package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.trans.model.Transaction;

public class IncomeTransactionFilter implements TransactionFilter {

  @Override
  public boolean test(final Transaction transaction) {
    return transaction.getAmount().compareTo(Money.of(0)) > 0;
  }
}
