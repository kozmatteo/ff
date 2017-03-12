package pl.kozmatteo.finance.domain.report;

import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.domain.report.filter.ExpenseFilter;
import pl.kozmatteo.finance.trans.model.Expense;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.kozmatteo.finance.domain.report.filter.ExpenseFilterBuilder.anExpenseFilter;

/**
 * todo grupowanie po kategoriach
 * TODO sortowanie
 */
class ExpenditureReportServiceTest {

  @Test
  void findNoneExpendituresWhenEmpty() {
    ExpenditureReport expenditureReport = createService(emptyList()).buildReport();
    assertEquals(0, expenditureReport.count());
  }

  private ExpenditureReportService createService(final List<Expense> expenditures) {
    return new ExpenditureReportService(expenditures);
  }

  @Test
  void findExactlyOneExpenditure() {
    // given
    List<Expense> expenditures = singletonList(new Expense(Money.of(1.44)));
    ExpenditureReportService expenditureReportService = createService(expenditures);

    // when
    ExpenditureReport expenditureReport = expenditureReportService.buildReport();

    // then
    assertAll(() -> {
      assertEquals(1, expenditureReport.count(), "There should be one transaction in report");
      assertEquals(Money.of(1.44), expenditureReport.sum(), "Sum is equal value of expenditure");
    });
  }

  @Test
  void findExpendituresInCategory() {
    // given
    List<Expense> expenditures = asList(
        new Expense(Money.of(1.44)),
        new Expense(Money.of(2.08), "category"),
        new Expense(Money.of(1.66), "category"));
    ExpenditureReportService expenditureReportService = createService(expenditures);
    ExpenseFilter reportFilter = anExpenseFilter()
        .withinCategory("category")
        .build();

    // when
    ExpenditureReport expenditureReport = expenditureReportService.buildReport(reportFilter);

    // then
    assertAll(() -> {
      assertEquals(2, expenditureReport.count());
      assertEquals(Money.of(3.74), expenditureReport.sum());
      assertTrue(expenditureReport.getExpenses().stream().allMatch(e -> "category".equals(e
          .getCategory())));
    });
  }

  @Test
  void findExpendituresFromToday() {
    // given
    List<Expense> expenditures = asList(
        new Expense(Money.of(1.44), LocalDate.now().minusMonths(1)),
        new Expense(Money.of(2.08), LocalDate.now().minusDays(1), "category"),
        new Expense(Money.of(1.66), "category"));
    ExpenditureReportService expenditureReportService = createService(expenditures);
    ExpenseFilter reportFilter = anExpenseFilter()
        .onDate(LocalDate.now())
        .build();

    // when
    ExpenditureReport expenditureReport = expenditureReportService.buildReport(reportFilter);

    // then
    assertAll(() -> {
      assertEquals(1, expenditureReport.count());
      assertEquals(Money.of(1.66), expenditureReport.sum());
      assertTrue(expenditureReport.getExpenses()
                                  .stream()
                                  .allMatch(e -> LocalDate.now().isEqual(e.getDate())));
    });
  }
}