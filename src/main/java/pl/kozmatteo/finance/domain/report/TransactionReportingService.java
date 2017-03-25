package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.domain.report.filter.TransactionFilter;
import pl.kozmatteo.finance.trans.model.Transaction;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TransactionReportingService {
  private final TransactionRepository transactionRepository;

  public TransactionReportingService(final TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public TransactionReport buildReport() {
    return new TransactionReport(transactionRepository.findAll());
  }

  public TransactionReport buildReport(final TransactionFilter filter) {
    List<Transaction> filteredExpenses = transactionRepository.findAll().stream()
                                                              .filter(filter)
                                                              .collect(toList());
    return new TransactionReport(filteredExpenses);
  }
}
