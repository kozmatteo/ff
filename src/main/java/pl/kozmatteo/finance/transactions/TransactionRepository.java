package pl.kozmatteo.finance.transactions;

import pl.kozmatteo.finance.support.Specification;

import java.util.List;

public interface TransactionRepository {
  List<Transaction> findAll();

  List<Transaction> findAll(Specification<Transaction> transactionSpecification);
}
