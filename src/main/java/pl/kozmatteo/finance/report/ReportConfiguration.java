package pl.kozmatteo.finance.report;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pl.kozmatteo.finance.report")
@EntityScan("pl.kozmatteo.finance.report")
public class ReportConfiguration {
}
