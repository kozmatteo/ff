package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.trans.model.Transaction;

import java.util.List;

public class TransactionReport {
  private final List<Transaction> transactions;

  public TransactionReport(final List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public int count() {
    return transactions.size();
  }

  public Money sum() {
    return transactions.stream()
                       .map(Transaction::getAmount)
                       .reduce(Money::plus)
                       .orElseGet(() -> Money.of(0));
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }
}
