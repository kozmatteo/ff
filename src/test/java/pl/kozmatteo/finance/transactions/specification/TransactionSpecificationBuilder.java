package pl.kozmatteo.finance.transactions.specification;

import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionSpecificationBuilder {
  private List<Specification<Transaction>> filters = new ArrayList<>();

  public static TransactionSpecificationBuilder aTransactionFilter() {
    return new TransactionSpecificationBuilder();
  }

  public static TransactionSpecificationBuilder anExpenseFilter() {
    TransactionSpecificationBuilder specificationBuilder = new TransactionSpecificationBuilder();
    specificationBuilder.addFilter(new ExpenseSpecification());
    return specificationBuilder;
  }

  private void addFilter(final Specification<Transaction> filter) {
    filters.removeIf(f -> f.getClass().equals(filter.getClass()));
    filters.add(filter);
  }

  public static TransactionSpecificationBuilder anIncomeFilter() {
    TransactionSpecificationBuilder specificationBuilder = new TransactionSpecificationBuilder();
    specificationBuilder.addFilter(new IncomeSpecification());
    return specificationBuilder;
  }

  public TransactionSpecificationBuilder onDate(LocalDate date) {
    Specification<Transaction> filter = new DateSpecification(date);
    addFilter(filter);
    return this;
  }

  public Specification<Transaction> build() {
    if (!filters.isEmpty()) {
      return filters.stream().reduce(Specification::and).orElse(candidate -> true);
    }
    return expense -> true;
  }

  public TransactionSpecificationBuilder withinCategory(final String category) {
    addFilter(new CategorySpecification(category));
    return this;
  }
}
