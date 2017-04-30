package pl.kozmatteo.finance.report.filter;

import pl.kozmatteo.finance.report.Money;
import pl.kozmatteo.finance.report.Transaction;
import pl.kozmatteo.finance.support.Specification;

public class IncomeSpecification implements Specification<Transaction> {

  @Override
  public boolean isSatisfiedBy(final Transaction transaction) {
    return transaction.getAmount().compareTo(Money.of(0)) > 0;
  }
}
