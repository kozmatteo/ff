package pl.kozmatteo.finance.support;

public interface Specification<T> {
  Specification DEFAULT = candidate -> true;

  boolean isSatisfiedBy(T candidate);

  default Specification<T> and(Specification<T> other) {
    return new AndSpecification<>(this, other);
  }

  default Specification<T> or(Specification<T> other) {
    return new OrSpecification<>(this, other);
  }

  default Specification<T> not() {
    return new NotSpecification<>(this);
  }
}
