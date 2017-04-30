package pl.kozmatteo.finance.report.filter;

import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.report.Money;
import pl.kozmatteo.finance.report.Transaction;
import pl.kozmatteo.finance.support.Specification;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.kozmatteo.finance.report.Transaction.expenseOf;
import static pl.kozmatteo.finance.report.filter.TransactionSpecificationBuilder.aTransactionFilter;

class TransactionSpecificationBuilderTest {
  @Test
  void emptyFilterAlwaysReturnTrue() {
    Specification<Transaction> filterBuilder = aTransactionFilter().build();
    Transaction expense = expenseOf(Money.of(1));

    assertTrue(filterBuilder.isSatisfiedBy(expense));
  }

  @Test
  void differentFilterCanBeApplied() {
    Specification<Transaction> filterBuilder = aTransactionFilter()
        .withinCategory("category")
        .onDate(now())
        .build();

    Transaction expense1 = expenseOf(Money.of(1.44)).category("category");
    Transaction expense2 = expenseOf(Money.of(2)).category("category2");
    Transaction expense3 = expenseOf(Money.of(2.5)).onDate(now().minusDays(1)).category("category");

    assertAll(() -> {
      assertTrue(filterBuilder.isSatisfiedBy(expense1));
      assertFalse(filterBuilder.isSatisfiedBy(expense2));
      assertFalse(filterBuilder.isSatisfiedBy(expense3));
    });
  }

  @Test
  void whenSameFilterIsUsedMultipleTimesUseLastValue() {
    Specification<Transaction> filterBuilder = aTransactionFilter()
        .withinCategory("category2")
        .withinCategory("category")
        .build();

    Transaction expense1 = expenseOf(Money.of(1.44)).category("category");
    Transaction expense2 = expenseOf(Money.of(2)).category("category2");

    assertAll(() -> {
      assertTrue(filterBuilder.isSatisfiedBy(expense1));
      assertFalse(filterBuilder.isSatisfiedBy(expense2));
    });
  }

}