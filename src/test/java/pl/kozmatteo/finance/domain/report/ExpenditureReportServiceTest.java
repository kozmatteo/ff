package pl.kozmatteo.finance.domain.report;

import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.domain.Money;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO filtrowanie kategoriach
 * todo grupowanie po kategoriach
 * TODO sortowanie
 */
class ExpenditureReportServiceTest {

  @Test
  void findNoneExpendituresWhenEmpty() {
    ExpenditureReport expenditureReport = new ExpenditureReportService(emptyList()).buildReport();
    assertEquals(0, expenditureReport.count());
  }

  @Test
  void findExactlyOneExpenditure() {
    // given
    List<Money> expenditures = singletonList(Money.of(1.44));
    ExpenditureReportService expenditureReportService = new ExpenditureReportService(expenditures);

    // when
    ExpenditureReport expenditureReport = expenditureReportService.buildReport();

    // then
    assertAll(() -> {
      assertEquals(1, expenditureReport.count(), "There should be one transaction in report");
      assertEquals(Money.of(1.44), expenditureReport.sum(), "Sum is equal value of expenditure");
    });
  }

  @Test
  void findManyExpenditures() {
    // given
    List<Money> expenditures = asList(Money.of(1.44), Money.of(2.08));
    ExpenditureReportService expenditureReportService = new ExpenditureReportService(expenditures);

    // when
    ExpenditureReport expenditureReport = expenditureReportService.buildReport();

    // then
    assertAll(() -> {
      assertEquals(2, expenditureReport.count(), "There should be 2 transactions in report");
      assertEquals(Money.of(3.52), expenditureReport.sum());
    });
  }
}