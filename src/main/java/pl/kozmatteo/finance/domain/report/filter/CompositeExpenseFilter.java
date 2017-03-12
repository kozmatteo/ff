package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.trans.model.Expense;

import java.util.Collection;

class CompositeExpenseFilter implements ExpenseFilter {
  private final Collection<ExpenseFilter> filters;

  public CompositeExpenseFilter(final Collection<ExpenseFilter> filters) {
    this.filters = filters;
  }

  @Override
  public boolean test(final Expense expense) {
    return filters.stream().allMatch(f -> f.test(expense));
  }
}
