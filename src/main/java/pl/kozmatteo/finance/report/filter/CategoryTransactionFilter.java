package pl.kozmatteo.finance.report.filter;

import pl.kozmatteo.finance.report.Transaction;

import java.util.Objects;

class CategoryTransactionFilter implements TransactionFilter {

  private final String category;

  public CategoryTransactionFilter(final String category) {
    this.category = category;
  }

  @Override
  public boolean test(final Transaction transaction) {
    return Objects.equals(transaction.getCategory(), category);
  }
}
