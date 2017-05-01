package pl.kozmatteo.finance.app.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Money;
import pl.kozmatteo.finance.transactions.TransactionReport;
import pl.kozmatteo.finance.transactions.TransactionRepository;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.kozmatteo.finance.transactions.Transaction.expenseOf;

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
    // when
    TransactionReport transactionReport = transactionReportingService.buildReport();
    // then
    assertEquals(0, transactionReport.count());
  }

  @Test
  void findExactlyManyTransactions() {
    // given
    when(transactionRepository.findAll(any(Specification.class)))
        .thenReturn(asList(expenseOf(Money.of(1.44)), expenseOf(Money.of(2.55))));

    // when
    TransactionReport transactionReport = transactionReportingService.buildReport();

    // then
    assertAll(() -> {
      assertEquals(2, transactionReport.count(), "There should be one transaction in report");
      assertEquals(Money.of(3.99)
                        .negative(), transactionReport.sum(), "Sum is equal value of expenditure");
    });
  }
}