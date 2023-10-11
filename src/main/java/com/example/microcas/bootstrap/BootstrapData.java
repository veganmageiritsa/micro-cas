package com.example.microcas.bootstrap;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.microcas.domain.Customer;

@Profile("dev")
@Component
public class BootstrapData implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(BootstrapData.class);
    private final CassandraOperations cassandraOperations;
    
    private final CqlSession cqlSession;
    
    public BootstrapData(final CassandraOperations cassandraOperations,
                          final CqlSession cqlSession) {
        this.cassandraOperations = cassandraOperations;
        this.cqlSession = cqlSession;
        
    }
    
    @Override
    public void run(final String... args) throws Exception {
    
        CassandraOperations template = new CassandraTemplate(cqlSession);
        Customer customer = new Customer();
        customer.setCustomerId(UUID.randomUUID());
        customer.setName("John Dode");
        customer.setEmail("geo@gmail.com");
        customer.setMobileNumber("6937940392");
        Customer jonDoe = template.insert(customer);
         log.info("Customer: {} ", jonDoe );
        UUID customerId = template.selectOne(Query.query(Criteria.where("customer_id").is(jonDoe.getCustomerId())), Customer.class).getCustomerId();
        log.info("customer : {}", customerId);
        Customer update = template.update(jonDoe);
        log.info("Customer: {} ", update );
        
//        cqlSession.close();
    }
    
}
