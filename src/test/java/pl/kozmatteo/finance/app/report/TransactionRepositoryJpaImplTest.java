package pl.kozmatteo.finance.app.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kozmatteo.finance.app.report.jpa.JpaTransactionRepository;
import pl.kozmatteo.finance.support.Specification;
import pl.kozmatteo.finance.transactions.Money;
import pl.kozmatteo.finance.transactions.Transaction;
import pl.kozmatteo.finance.transactions.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.kozmatteo.finance.transactions.Transaction.expenseOf;
import static pl.kozmatteo.finance.transactions.Transaction.incomeOf;
import static pl.kozmatteo.finance.transactions.specification.TransactionSpecificationBuilder
    .aTransactionFilter;
import static pl.kozmatteo.finance.transactions.specification.TransactionSpecificationBuilder
    .anExpenseFilter;

class TransactionRepositoryJpaImplTest {
  private JpaTransactionRepository repositoryStub;
  private TransactionRepository sut;

  @BeforeEach
  void setUp() {
    repositoryStub = mock(JpaTransactionRepository.class);
    sut = new TransactionRepositoryJpaImpl(repositoryStub);
  }

  @Test
  void findExpendituresInCategory() {
    // given
    List<Transaction> transactions = asList(
        expenseOf(Money.of(0.66)).category("another"),
        expenseOf(Money.of(2.08)).category("category"),
        expenseOf(Money.of(1.66)).category("category"));
    when(repositoryStub.findAll()).thenReturn(transactions);
    Specification<Transaction> reportFilter = anExpenseFilter()
        .withinCategory("category")
        .build();

    // when
    List<Transaction> transactionList = sut.findAll(reportFilter);

    // then
    assertAll(() -> {
      assertEquals(2, transactionList.size());
      assertTrue(transactionList.stream().allMatch(e -> "category".equals(e.getCategory())));
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
    when(repositoryStub.findAll()).thenReturn(transactions);
    Specification<Transaction> reportFilter = aTransactionFilter()
        .onDate(LocalDate.now())
        .build();

    // when
    List<Transaction> transactionList = sut.findAll(reportFilter);

    // then
    assertAll(() -> {
      assertEquals(2, transactionList.size());
      assertTrue(transactionList.stream().allMatch(e -> LocalDate.now().isEqual(e.getDate())));
    });
  }
}