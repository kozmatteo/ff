package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.domain.Money;

import java.util.List;

public class ExpenditureReport {
  private final List<Money> expenditures;

  public ExpenditureReport(final List<Money> expenditures) {
    this.expenditures = expenditures;
  }

  public int count() {
    return expenditures.size();
  }

  public Money sum() {
    return expenditures.stream().reduce(Money::plus).orElseGet(() -> Money.of(0));
  }
}
