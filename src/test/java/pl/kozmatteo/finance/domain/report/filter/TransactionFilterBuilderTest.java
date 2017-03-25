package pl.kozmatteo.finance.domain.report.filter;

import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.trans.model.Transaction;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.kozmatteo.finance.domain.report.filter.TransactionFilterBuilder.aTransactionFilter;
import static pl.kozmatteo.finance.trans.model.Transaction.expenseOf;

class TransactionFilterBuilderTest {
  @Test
  void emptyFilterAlwaysReturnTrue() {
    TransactionFilter filterBuilder = aTransactionFilter().build();
    Transaction expense = expenseOf(Money.of(1));

    assertTrue(filterBuilder.test(expense));
  }

  @Test
  void differentFilterCanBeApplied() {
    TransactionFilter filterBuilder = aTransactionFilter()
        .withinCategory("category")
        .onDate(now())
        .build();

    Transaction expense1 = expenseOf(Money.of(1.44)).category("category");
    Transaction expense2 = expenseOf(Money.of(2)).category("category2");
    Transaction expense3 = expenseOf(Money.of(2.5)).onDate(now().minusDays(1)).category("category");

    assertAll(() -> {
      assertTrue(filterBuilder.test(expense1));
      assertFalse(filterBuilder.test(expense2));
      assertFalse(filterBuilder.test(expense3));
    });
  }

  @Test
  void whenSameFilterIsUsedMultipleTimesUseLastValue() {
    TransactionFilter filterBuilder = aTransactionFilter()
        .withinCategory("category2")
        .withinCategory("category")
        .build();

    Transaction expense1 = expenseOf(Money.of(1.44)).category("category");
    Transaction expense2 = expenseOf(Money.of(2)).category("category2");

    assertAll(() -> {
      assertTrue(filterBuilder.test(expense1));
      assertFalse(filterBuilder.test(expense2));
    });
  }

}