package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.trans.model.Expense;

import java.time.LocalDate;

class DateExpenseFilter implements ExpenseFilter {
  private final LocalDate date;

  public DateExpenseFilter(final LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean test(final Expense expense) {
    return expense.getDate().isEqual(date);
  }
}
