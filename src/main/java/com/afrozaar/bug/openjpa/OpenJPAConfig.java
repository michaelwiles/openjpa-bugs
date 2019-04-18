package com.afrozaar.bug.openjpa;

import com.afrozaar.util.openjpa.OpenJpaVendorAdapter;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

@Configuration
public class OpenJPAConfig extends JpaBaseConfiguration {

    protected OpenJPAConfig(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManager,
                            ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
        properties.setShowSql(true);
        properties.setGenerateDdl(true);
        properties.getProperties().put("openjpa.Log", "slf4j");
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        OpenJpaVendorAdapter openJpaVendorAdapter = new OpenJpaVendorAdapter();
        return openJpaVendorAdapter;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {

        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("openjpa.Log", "slf4j");
        hashMap.put("openjpa.ConnectionFactoryProperties", "PrintParameters=true");
        return hashMap;
    }
}
