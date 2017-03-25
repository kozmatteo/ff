package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.trans.model.Transaction;

import java.util.List;

public interface TransactionRepository {
  List<Transaction> findAll();
}
