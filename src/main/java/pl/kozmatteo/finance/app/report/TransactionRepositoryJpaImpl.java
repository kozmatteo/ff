package pl.kozmatteo.finance.app.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.kozmatteo.finance.app.report.jpa.JpaTransactionRepository;
import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Transaction;
import pl.kozmatteo.finance.transactions.TransactionRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Primary
@Component
public class TransactionRepositoryJpaImpl implements TransactionRepository {
  private final JpaTransactionRepository transactionJpaRepository;

  @Autowired
  public TransactionRepositoryJpaImpl(JpaTransactionRepository transactionJpaRepository) {
    this.transactionJpaRepository = transactionJpaRepository;
  }

  @Override
  public List<Transaction> findAll() {
    return transactionJpaRepository.findAll();
  }

  @Override
  public List<Transaction> findAll(final Specification<Transaction> transactionSpecification) {
    return findAll()
        .stream()
        .filter(transactionSpecification::isSatisfiedBy)
        .collect(toList());
  }
}
