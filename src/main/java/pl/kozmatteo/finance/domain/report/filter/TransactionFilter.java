package pl.kozmatteo.finance.domain.report.filter;

import pl.kozmatteo.finance.trans.model.Transaction;

import java.util.function.Predicate;

public interface TransactionFilter extends Predicate<Transaction> {
  @Override
  boolean test(Transaction transaction);
}
