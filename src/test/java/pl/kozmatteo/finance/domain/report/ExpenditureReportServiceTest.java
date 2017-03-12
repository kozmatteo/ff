package pl.kozmatteo.finance.domain.report;

import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.trans.model.Expense;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO filtrowanie po kategoriach
 * TODO filtrowanie po datach
 * todo grupowanie po kategoriach
 * TODO sortowanie
 */
class ExpenditureReportServiceTest {

  @Test
  void findNoneExpendituresWhenEmpty() {
    ExpenditureReport expenditureReport = new ExpenditureReportService(emptyList()).buildReport
        ("category");
    assertEquals(0, expenditureReport.count());
  }

  @Test
  void findExactlyOneExpenditure() {
    // given
    List<Expense> expenditures = singletonList(new Expense(Money.of(1.44)));
    ExpenditureReportService expenditureReportService = new ExpenditureReportService(expenditures);

    // when
    ExpenditureReport expenditureReport = expenditureReportService.buildReport();

    // then
    assertAll(() -> {
      assertEquals(1, expenditureReport.count(), "There should be one transaction in report");
      assertEquals(Money.of(1.44), expenditureReport.sum(), "Sum is equal value of expenditure");
      assertEquals(null, expenditureReport.getCategory());
    });
  }

  @Test
  void findExpendituresInCategory() {
    // given
    List<Expense> expenditures = asList(
        new Expense(Money.of(1.44)),
        new Expense(Money.of(2.08), "category"),
        new Expense(Money.of(1.66), "category"));
    ExpenditureReportService expenditureReportService = new ExpenditureReportService(expenditures);

    // when
    ExpenditureReport expenditureReport = expenditureReportService.buildReport("category");

    // then
    assertAll(() -> {
      assertEquals(2, expenditureReport.count());
      assertEquals(Money.of(3.74), expenditureReport.sum());
      assertEquals("category", expenditureReport.getCategory());
    });

  }
}