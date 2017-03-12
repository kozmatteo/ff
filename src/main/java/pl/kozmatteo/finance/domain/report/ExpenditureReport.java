package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.trans.model.Expense;

import java.util.List;

public class ExpenditureReport {
  private final List<Expense> expenditures;

  public ExpenditureReport(final List<Expense> expenditures) {
    this.expenditures = expenditures;
  }

  public int count() {
    return expenditures.size();
  }

  public Money sum() {
    return expenditures.stream()
                       .map(Expense::getAmount)
                       .reduce(Money::plus).orElseGet(() -> Money.of(0));
  }

  public List<Expense> getExpenses() {
    return expenditures;
  }
}
