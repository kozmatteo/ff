package pl.kozmatteo.finance.report.filter;

import pl.kozmatteo.finance.report.Transaction;

import java.time.LocalDate;

class DateTransactionFilter implements TransactionFilter {
  private final LocalDate date;

  public DateTransactionFilter(final LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean test(final Transaction transaction) {
    return transaction.getDate().isEqual(date);
  }
}
