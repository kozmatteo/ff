package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.trans.model.Expense;

import java.util.List;

public class ExpenditureReport {
  private final List<Expense> expenditures;
  private final String category;

  public ExpenditureReport(final List<Expense> expenditures, final String category) {
    this.expenditures = expenditures;
    this.category = category;
  }

  public int count() {
    return expenditures.size();
  }

  public Money sum() {
    return expenditures.stream()
                       .map(Expense::getAmount)
                       .reduce(Money::plus).orElseGet(() -> Money.of(0));
  }

  public String getCategory() {
    return category;
  }
}
