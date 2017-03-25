package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.trans.model.Transaction;

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
