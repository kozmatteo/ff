package pl.kozmatteo.finance.domain.report;

import pl.kozmatteo.finance.domain.Money;

import java.util.List;

public class ExpenditureReportService {
  private final List<Money> expenditures;

  public ExpenditureReportService(final List<Money> expenditures) {
    this.expenditures = expenditures;
  }

  public ExpenditureReport buildReport() {
    return new ExpenditureReport(expenditures);
  }
}
