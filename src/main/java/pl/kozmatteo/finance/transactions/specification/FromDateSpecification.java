package pl.kozmatteo.finance.transactions.specification;

import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Transaction;

import java.time.LocalDate;

public class FromDateSpecification implements Specification<Transaction> {
  private final LocalDate fromDate;

  public FromDateSpecification(final LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  @Override
  public boolean isSatisfiedBy(final Transaction candidate) {
    return !candidate.getDate().isBefore(fromDate);
  }
}
