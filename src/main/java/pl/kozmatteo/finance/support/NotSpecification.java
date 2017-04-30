package pl.kozmatteo.finance.support;

public class NotSpecification<T> implements Specification<T> {
  private final Specification<T> specification;

  public NotSpecification(final Specification<T> specification) {
    this.specification =
        specification;
  }

  @Override
  public boolean isSatisfiedBy(final T candidate) {
    return !specification.isSatisfiedBy(candidate);
  }
}
