package pl.kozmatteo.finance.trans.model;

import pl.kozmatteo.finance.domain.Money;

import java.time.LocalDate;

public class Expense {
  private final LocalDate date;
  private Money amount;
  private String category;

  public Expense(final Money amount) {
    this(amount, null, null);
  }

  public Expense(final Money amount, final LocalDate date, final String category) {
    this.amount = amount;
    if (date == null) {
      this.date = LocalDate.now();
    } else {
      this.date = date;
    }
    this.category = category;
  }

  public Expense(final Money amount, final String category) {
    this(amount, null, category);
  }

  public Expense(final Money amount, final LocalDate date) {
    this(amount, date, null);
  }

  public Money getAmount() {
    return amount;
  }

  public String getCategory() {
    return category;
  }

  public LocalDate getDate() {
    return date;
  }
}
