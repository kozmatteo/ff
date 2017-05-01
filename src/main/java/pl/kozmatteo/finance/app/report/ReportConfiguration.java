package pl.kozmatteo.finance.app.report;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.kozmatteo.finance.app.report.jpa.JpaTransactionRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = {JpaTransactionRepository.class})
@EntityScan("pl.kozmatteo.finance.transactions")
public class ReportConfiguration {
}
