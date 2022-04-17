package io.kimmking.dtx01.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

public class CustomRoutingDatasource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DatasourceHolder.get();
        if(StringUtils.isEmpty(key)){
            return "write";
        }
        return key;
    }
}
