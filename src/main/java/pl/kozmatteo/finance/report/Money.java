package pl.kozmatteo.finance.report;

import java.math.BigDecimal;
import java.util.Objects;

public class Money implements Comparable<Money> {
  private final BigDecimal amount;

  public Money(final BigDecimal amount) {
    this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
  }

  public static Money of(final double amount) {
    return new Money(BigDecimal.valueOf(amount));
  }

  public Money plus(final Money money) {
    return new Money(amount.add(money.amount));
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return Objects.equals(amount, money.amount);
  }

  @Override
  public String toString() {
    return "Money{" +
        "amount=" + amount +
        '}';
  }

  public Money negative() {
    BigDecimal negative = BigDecimal.ZERO.subtract(amount.abs());
    return new Money(negative);
  }

  @Override
  public int compareTo(final Money o) {
    return amount.compareTo(o.amount);
  }
}
