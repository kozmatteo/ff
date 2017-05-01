package pl.kozmatteo.finance.transactions.specification;

import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Transaction;

import java.util.Objects;

public class CategorySpecification implements Specification<Transaction> {

  private final String category;

  public CategorySpecification(final String category) {
    this.category = category;
  }

  @Override
  public boolean isSatisfiedBy(final Transaction transaction) {
    return Objects.equals(transaction.getCategory(), category);
  }
}
