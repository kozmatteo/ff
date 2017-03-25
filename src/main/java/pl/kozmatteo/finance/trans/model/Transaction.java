package pl.kozmatteo.finance.trans.model;

import pl.kozmatteo.finance.domain.Money;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
  private LocalDate date = LocalDate.now();
  private Money amount;
  private String category;

  public Transaction(final LocalDate date, final String category, final Money amount) {
    if (date == null) {
      this.date = LocalDate.now();
    } else {
      this.date = date;
    }
    this.category = category;
    this.amount = amount;
  }

  public Transaction(final Money amount) {
    this.amount = amount;
  }

  public static Transaction expenseOf(final Money amount) {
    return new Transaction(amount.negative());
  }

  public static Transaction incomeOf(final Money amount) {
    return new Transaction(amount);
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

  public Transaction category(final String category) {
    this.category = category;
    return this;
  }

  public Transaction onDate(final LocalDate date) {
    Objects.requireNonNull(date);
    this.date = date;
    return this;
  }
}
