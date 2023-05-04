package hr.config.multitenancy;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceRouting extends AbstractRoutingDataSource {



    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContextHolder.getTenantContext();
    }


    public void initDataSource(DataSource tenant1Datasource,
                               DataSource tenant2DataSource){
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(TenantEnum.tenant1, tenant1Datasource);
        dataSourceMap.put(TenantEnum.tenant2, tenant2DataSource);
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(tenant2DataSource);
    }
}
