package pl.kozmatteo.finance.app.report;

import org.springframework.stereotype.Service;
import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Transaction;
import pl.kozmatteo.finance.transactions.TransactionReport;
import pl.kozmatteo.finance.transactions.TransactionRepository;

import java.util.List;

@Service
public class TransactionReportingService {
  private final TransactionRepository transactionRepository;

  public TransactionReportingService(final TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public TransactionReport buildReport() {
    return buildReport(Specification.DEFAULT);
  }

  public TransactionReport buildReport(final Specification<Transaction> specification) {
    List<Transaction> filteredExpenses = transactionRepository.findAll(specification);
    return new TransactionReport(filteredExpenses);
  }
}
