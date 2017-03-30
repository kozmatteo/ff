package pl.kozmatteo.finance.report;

import java.util.List;

public interface TransactionRepository {
  List<Transaction> findAll();
}
