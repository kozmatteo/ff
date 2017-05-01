package pl.kozmatteo.finance.transactions.specification;

import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Money;
import pl.kozmatteo.finance.transactions.Transaction;

public class ExpenseSpecification implements Specification<Transaction> {

  @Override
  public boolean isSatisfiedBy(final Transaction transaction) {
    return transaction.getAmount().compareTo(Money.of(0)) < 0;
  }
}
