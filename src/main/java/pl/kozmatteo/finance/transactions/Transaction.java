package pl.kozmatteo.finance.transactions;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "t_trans")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_SEQ")
  @SequenceGenerator(sequenceName = "trans_seq", allocationSize = 1, name = "TRANSACTION_SEQ")
  @Column(name = "transaction_id")
  private Long transactionId;

  private LocalDate date = LocalDate.now();

  @Embedded
  private Money amount;

  private String category;

  private Transaction() {
  }

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

  public Long getTransactionId() {
    return transactionId;
  }
}
