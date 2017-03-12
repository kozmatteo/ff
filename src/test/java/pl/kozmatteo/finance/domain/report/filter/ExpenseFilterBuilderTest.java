package pl.kozmatteo.finance.domain.report.filter;

import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.trans.model.Expense;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.kozmatteo.finance.domain.report.filter.ExpenseFilterBuilder.anExpenseFilter;

class ExpenseFilterBuilderTest {
  @Test
  void emptyFilterAlwaysReturnTrue() {
    ExpenseFilter filterBuilder = anExpenseFilter().build();
    Expense expense = new Expense(Money.of(1));

    assertTrue(filterBuilder.test(expense));
  }

  @Test
  void differentFilterCanBeApplied() {
    ExpenseFilter filterBuilder = anExpenseFilter()
        .withinCategory("category")
        .onDate(LocalDate.now())
        .build();

    Expense expense1 = new Expense(Money.of(1.44), "category");
    Expense expense2 = new Expense(Money.of(2), "category2");
    Expense expense3 = new Expense(Money.of(2.5), LocalDate.now().minusDays(1), "category");

    assertAll(() -> {
      assertTrue(filterBuilder.test(expense1));
      assertFalse(filterBuilder.test(expense2));
      assertFalse(filterBuilder.test(expense3));
    });
  }

  @Test
  void whenSameFilterIsUsedMultipleTimesUseLastValue() {
    ExpenseFilter filterBuilder = anExpenseFilter()
        .withinCategory("category2")
        .withinCategory("category")
        .build();

    Expense expense1 = new Expense(Money.of(1.44), "category");
    Expense expense2 = new Expense(Money.of(2), "category2");

    assertAll(() -> {
      assertTrue(filterBuilder.test(expense1));
      assertFalse(filterBuilder.test(expense2));
    });
  }

}