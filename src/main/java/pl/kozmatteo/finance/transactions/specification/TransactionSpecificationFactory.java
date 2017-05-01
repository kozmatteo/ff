package pl.kozmatteo.finance.transactions.specification;

import org.springframework.stereotype.Component;
import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Transaction;

import java.time.LocalDate;

@Component
public class TransactionSpecificationFactory {

  public Specification<Transaction> createSpecification(final String category,
                                                        final LocalDate fromDate,
                                                        final LocalDate toDate,
                                                        final String type) {
    @SuppressWarnings("unchecked")
    Specification<Transaction> transactionSpecification = Specification.DEFAULT;
    if (category != null) {
      transactionSpecification = transactionSpecification.and(new CategorySpecification(category));
    }
    if (fromDate != null) {
      transactionSpecification = transactionSpecification.and(new FromDateSpecification(fromDate));
    }
    if (toDate != null) {
      transactionSpecification = transactionSpecification.and(new UntilDateSpecification(toDate));
    }
    switch (type.toLowerCase()) {
      case "income":
        transactionSpecification = transactionSpecification.and(new IncomeSpecification());
        break;
      case "expense":
        transactionSpecification = transactionSpecification.and(new ExpenseSpecification());
        break;
      default:
    }
    return transactionSpecification;
  }
}