package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.trans.model.Expense;

import java.util.Objects;

class CategoryExpenseFilter implements ExpenseFilter {

  private final String category;

  public CategoryExpenseFilter(final String category) {
    this.category = category;
  }

  @Override
  public boolean test(final Expense expense) {
    return Objects.equals(expense.getCategory(), category);
  }
}
