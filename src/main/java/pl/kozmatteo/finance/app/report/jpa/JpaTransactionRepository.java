package pl.kozmatteo.finance.app.report.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.kozmatteo.finance.transactions.Transaction;

public interface JpaTransactionRepository extends JpaRepository<Transaction, Long>,
    JpaSpecificationExecutor {
}
