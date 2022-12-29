package br.com.erudio.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
  
  @Autowired
  private DBConfig dBConfig;
  
  @Bean
  public DataSource getDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.postgresql.Driver");
    dataSourceBuilder.url(dBConfig.getUrl()); //jdbc:postgresql://localhost:5432/rest-with-spring-boot
    dataSourceBuilder.username(dBConfig.getUser());
    dataSourceBuilder.password(dBConfig.getPassword());
    return dataSourceBuilder.build();
  }
}
