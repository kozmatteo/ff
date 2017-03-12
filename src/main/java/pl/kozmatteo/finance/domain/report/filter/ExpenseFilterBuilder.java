package pl.kozmatteo.finance.domain.report.filter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseFilterBuilder {
  private List<ExpenseFilter> filters = new ArrayList<>();

  public static ExpenseFilterBuilder anExpenseFilter() {
    return new ExpenseFilterBuilder();
  }

  public ExpenseFilterBuilder onDate(LocalDate date) {
    ExpenseFilter filter = new DateExpenseFilter(date);
    addFilter(filter);
    return this;
  }

  private void addFilter(final ExpenseFilter filter) {
    filters.removeIf(f -> f.getClass().equals(filter.getClass()));
    filters.add(filter);
  }

  public ExpenseFilter build() {
    if (!filters.isEmpty()) {
      return new CompositeExpenseFilter(filters);
    }
    return expense -> true;
  }

  public ExpenseFilterBuilder withinCategory(final String category) {
    CategoryExpenseFilter filter = new CategoryExpenseFilter(category);
    addFilter(filter);
    return this;
  }
}
