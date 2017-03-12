package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.domain.report.filter.ExpenseFilter;
import pl.kozmatteo.finance.trans.model.Expense;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExpenditureReportService {
  private final List<Expense> expenditures;

  public ExpenditureReportService(final List<Expense> expenditures) {
    this.expenditures = expenditures;
  }

  public ExpenditureReport buildReport() {
    return new ExpenditureReport(expenditures);
  }

  public ExpenditureReport buildReport(final ExpenseFilter filter) {
    List<Expense> filteredExpenses = expenditures.stream()
                                                 .filter(filter)
                                                 .collect(toList());
    return new ExpenditureReport(filteredExpenses);
  }
}
