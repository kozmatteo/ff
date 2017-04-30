package pl.kozmatteo.finance.report;

import org.springframework.stereotype.Service;
import pl.kozmatteo.finance.support.Specification;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TransactionReportingService {
  private final TransactionRepository transactionRepository;

  public TransactionReportingService(final TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public TransactionReport buildReport() {
    return new TransactionReport(transactionRepository.findAll());
  }

  public TransactionReport buildReport(final Specification<Transaction> specification) {
    List<Transaction> filteredExpenses = transactionRepository.findAll().stream()
                                                              .filter(specification::isSatisfiedBy)
                                                              .collect(toList());
    return new TransactionReport(filteredExpenses);
  }
}
