package pl.kozmatteo.finance.trans.model;

import pl.kozmatteo.finance.domain.Money;

public class Expense {
  private Money amount;
  private String category;

  public Expense(final Money amount) {
    this(amount, null);
  }

  public Expense(final Money amount, final String category) {
    this.amount = amount;
    this.category = category;
  }

  public Money getAmount() {
    return amount;
  }

  public String getCategory() {
    return category;
  }
}
