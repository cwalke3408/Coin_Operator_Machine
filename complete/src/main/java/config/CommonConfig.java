package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class CommonConfig {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
