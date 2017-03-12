package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.trans.model.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class ExpenditureReportService {
  private final List<Expense> expenditures;

  public ExpenditureReportService(final List<Expense> expenditures) {
    this.expenditures = expenditures;
  }

  public ExpenditureReport buildReport() {
    return buildReport((String) null);
  }

  public ExpenditureReport buildReport(final String category) {
    List<Expense> filteredExpenses = expenditures.stream()
                                                 .filter(e -> Objects.equals(e.getCategory(),
                                                     category))
                                                 .collect(toList());
    return new ExpenditureReport(filteredExpenses);
  }

  public ExpenditureReport buildReport(final LocalDate expenseDate) {
    List<Expense> filteredExpenses = expenditures.stream()
                                                 .filter(e -> expenseDate.isEqual(e.getDate()))
                                                 .collect(toList());
    return new ExpenditureReport(filteredExpenses);
  }
}
