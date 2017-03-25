package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.trans.model.Transaction;

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
