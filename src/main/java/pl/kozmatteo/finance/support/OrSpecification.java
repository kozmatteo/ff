package pl.kozmatteo.finance.support;

public class OrSpecification<T> implements Specification<T> {
  private final Specification<T> left;
  private final Specification<T> right;

  public OrSpecification(final Specification<T> left, final Specification<T>
      right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean isSatisfiedBy(final T candidate) {
    return left.isSatisfiedBy(candidate) || right.isSatisfiedBy(candidate);
  }
}
