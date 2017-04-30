package pl.kozmatteo.finance.report.filter;

import pl.kozmatteo.finance.report.Transaction;
import pl.kozmatteo.finance.support.Specification;

import java.time.LocalDate;

class DateSpecification implements Specification<Transaction> {
  private final LocalDate date;

  public DateSpecification(final LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean isSatisfiedBy(final Transaction transaction) {
    return transaction.getDate().isEqual(date);
  }
}
