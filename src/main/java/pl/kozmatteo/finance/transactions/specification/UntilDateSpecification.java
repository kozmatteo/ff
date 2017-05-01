package pl.kozmatteo.finance.transactions.specification;

import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Transaction;

import java.time.LocalDate;

public class UntilDateSpecification implements Specification<Transaction> {
  private final LocalDate fromDate;

  public UntilDateSpecification(final LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  @Override
  public boolean isSatisfiedBy(final Transaction candidate) {
    return !candidate.getDate().isAfter(fromDate);
  }
}
