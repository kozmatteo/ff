package pl.kozmatteo.finance.report.filter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionFilterBuilder {
  private List<TransactionFilter> filters = new ArrayList<>();

  public static TransactionFilterBuilder aTransactionFilter() {
    return new TransactionFilterBuilder();
  }

  public static TransactionFilterBuilder anExpenseFilter() {
    TransactionFilterBuilder transactionFilterBuilder = new TransactionFilterBuilder();
    transactionFilterBuilder.addFilter(new ExpenseTransactionFilter());
    return transactionFilterBuilder;
  }

  private void addFilter(final TransactionFilter filter) {
    filters.removeIf(f -> f.getClass().equals(filter.getClass()));
    filters.add(filter);
  }

  public static TransactionFilterBuilder anIncomeFilter() {
    TransactionFilterBuilder transactionFilterBuilder = new TransactionFilterBuilder();
    transactionFilterBuilder.addFilter(new IncomeTransactionFilter());
    return transactionFilterBuilder;
  }

  public TransactionFilterBuilder onDate(LocalDate date) {
    TransactionFilter filter = new DateTransactionFilter(date);
    addFilter(filter);
    return this;
  }

  public TransactionFilter build() {
    if (!filters.isEmpty()) {
      return new CompositeTransactionFilter(filters);
    }
    return expense -> true;
  }

  public TransactionFilterBuilder withinCategory(final String category) {
    CategoryTransactionFilter filter = new CategoryTransactionFilter(category);
    addFilter(filter);
    return this;
  }
}
