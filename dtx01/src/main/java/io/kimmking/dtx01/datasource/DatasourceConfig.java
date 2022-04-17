package io.kimmking.dtx01.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatasourceConfig {

    @Bean(name="writeDatasource")
    @ConfigurationProperties("spring.write.datasource")
    public DataSource writeDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="readDatasource")
    @ConfigurationProperties("spring.read.datasource")
    public DataSource readDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @DependsOn({"writeDatasource","readDatasource"})
    @Bean(name="customRoutingDatasource")
    public CustomRoutingDatasource customRoutingDatasource(@Qualifier("writeDatasource") DataSource writeDatasource, @Qualifier("readDatasource") DataSource readDatasource) {
        CustomRoutingDatasource ds = new CustomRoutingDatasource();
        ds.setDefaultTargetDataSource(writeDatasource);
        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put("write", writeDatasource);
        datasourceMap.put("read", readDatasource);
        ds.setTargetDataSources(datasourceMap);
        return ds;
    }

}
