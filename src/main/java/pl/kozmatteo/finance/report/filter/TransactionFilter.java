package pl.kozmatteo.finance.report.filter;

import pl.kozmatteo.finance.report.Transaction;

import java.util.function.Predicate;

public interface TransactionFilter extends Predicate<Transaction> {
  @Override
  boolean test(Transaction transaction);
}
