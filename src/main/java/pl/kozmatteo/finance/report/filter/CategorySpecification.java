package pl.kozmatteo.finance.report.filter;

import pl.kozmatteo.finance.report.Transaction;
import pl.kozmatteo.finance.support.Specification;

import java.util.Objects;

class CategorySpecification implements Specification<Transaction> {

  private final String category;

  public CategorySpecification(final String category) {
    this.category = category;
  }

  @Override
  public boolean isSatisfiedBy(final Transaction transaction) {
    return Objects.equals(transaction.getCategory(), category);
  }
}
