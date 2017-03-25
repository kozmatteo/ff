package pl.kozmatteo.finance.domain.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.domain.Money;
import pl.kozmatteo.finance.domain.report.filter.TransactionFilter;
import pl.kozmatteo.finance.trans.model.Transaction;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.kozmatteo.finance.domain.report.filter.TransactionFilterBuilder.aTransactionFilter;
import static pl.kozmatteo.finance.domain.report.filter.TransactionFilterBuilder.anExpenseFilter;
import static pl.kozmatteo.finance.trans.model.Transaction.expenseOf;
import static pl.kozmatteo.finance.trans.model.Transaction.incomeOf;

/**
 * todo grupowanie po kategoriach / dacie
 */
class TransactionReportingServiceTest {

  private TransactionReportingService transactionReportingService;
  private TransactionRepository transactionRepository;

  @BeforeEach
  void setUp() {
    transactionRepository = mock(TransactionRepository.class);
    transactionReportingService = new TransactionReportingService(transactionRepository);
  }

  @Test
  void findNoneTransactionsWhenEmpty() {
    // given
    when(transactionRepository.findAll()).thenReturn(emptyList());
    // when
    TransactionReport transactionReport = transactionReportingService.buildReport();
    // then
    assertEquals(0, transactionReport.count());
  }

  @Test
  void findExactlyOneTransaction() {
    // given
    when(transactionRepository.findAll()).thenReturn(singletonList(
        expenseOf(Money.of(1.44))));

    // when
    TransactionReport transactionReport = transactionReportingService
        .buildReport();

    // then
    assertAll(() -> {
      assertEquals(1, transactionReport.count(), "There should be one transaction in report");
      assertEquals(Money.of(1.44)
                        .negative(), transactionReport.sum(), "Sum is equal value of expenditure");
    });
  }

  @Test
  void findExpendituresInCategory() {
    // given
    List<Transaction> transactions = asList(
        expenseOf(Money.of(1.44)),
        expenseOf(Money.of(2.08)).category("category"),
        expenseOf(Money.of(1.66)).category("category"));
    when(transactionRepository.findAll()).thenReturn(transactions);
    TransactionFilter reportFilter = anExpenseFilter()
        .withinCategory("category")
        .build();

    // when
    TransactionReport transactionReport = transactionReportingService.buildReport(reportFilter);

    // then
    assertAll(() -> {
      assertEquals(2, transactionReport.count());
      assertEquals(Money.of(3.74).negative(), transactionReport.sum());
      assertTrue(transactionReport.getTransactions().stream().allMatch(e -> "category".equals(e
          .getCategory())));
    });
  }

  @Test
  void findTransactionsFromToday() {
    // given
    List<Transaction> transactions = asList(
        expenseOf(Money.of(1.44)).onDate(LocalDate.now().minusMonths(1)),
        expenseOf(Money.of(1.44)).onDate(LocalDate.now().minusDays(1)).category("category"),
        incomeOf(Money.of(1.44)).category("category"),
        expenseOf(Money.of(1.66)).category("category"));
    when(transactionRepository.findAll()).thenReturn(transactions);
    TransactionFilter reportFilter = aTransactionFilter()
        .onDate(LocalDate.now())
        .build();

    // when
    TransactionReport transactionReport = transactionReportingService.buildReport(reportFilter);

    // then
    assertAll(() -> {
      assertEquals(2, transactionReport.count());
      assertEquals(Money.of(-0.22), transactionReport.sum());
      assertTrue(transactionReport.getTransactions()
                                  .stream()
                                  .allMatch(e -> LocalDate.now().isEqual(e.getDate())));
    });
  }
}