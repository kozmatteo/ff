package pl.kozmatteo.finance.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kozmatteo.finance.report.TransactionReport;
import pl.kozmatteo.finance.report.TransactionReportingService;

@RestController
public class Ctrl {

  @Autowired
  private TransactionReportingService reportingService;

  @GetMapping("/tt")
  public TransactionReport test() {
    return reportingService.buildReport();
  }
}
