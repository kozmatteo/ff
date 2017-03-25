package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.trans.model.Transaction;

import java.util.Collection;

class CompositeTransactionFilter implements TransactionFilter {
  private final Collection<TransactionFilter> filters;

  public CompositeTransactionFilter(final Collection<TransactionFilter> filters) {
    this.filters = filters;
  }

  @Override
  public boolean test(final Transaction transaction) {
    return filters.stream().allMatch(f -> f.test(transaction));
  }
}
