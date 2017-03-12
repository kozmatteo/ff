package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.trans.model.Expense;

import java.util.function.Predicate;

public interface ExpenseFilter extends Predicate<Expense> {
  @Override
  boolean test(Expense expense);
}
