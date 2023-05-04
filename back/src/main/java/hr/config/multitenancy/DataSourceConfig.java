package hr.config.multitenancy;

import hr.config.multitenancy.tenantDetails.Tenant1Details;
import hr.config.multitenancy.tenantDetails.Tenant2Details;
import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.list.entity.ListEntity;
import hr.mvc.project.entity.ProjectEntity;
import hr.mvc.role.entity.RoleEntity;
import hr.mvc.task.entity.TaskEntity;
import hr.mvc.tasklist.entity.TaskListEntity;
import hr.mvc.tasklistitem.entity.TaskListItemEntity;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "hr.mvc",
        transactionManagerRef = "transactionManager",
        entityManagerFactoryRef = "entityManagerFactory"
)
@EnableTransactionManagement
public class DataSourceConfig {
    @Autowired
    private Tenant1Details tenant1;

    @Autowired
    private Tenant2Details tenant2;


    @Bean
    @Primary
    @Autowired
    public DataSource dataSource(){
        DataSourceRouting routing = new DataSourceRouting();
        routing.initDataSource(tenant1DataSource(), tenant2DataSource());
        return routing;
    }

    public DataSource tenant1DataSource(){
        DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setUsername(tenant1.getUsername());
        dataSource.setUrl(tenant1.getUrl());
        dataSource.setDriverClassName(tenant1.getDriver());
        return dataSource;
    }

    public DataSource tenant2DataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(tenant2.getUrl());
        dataSource.setUsername(tenant2.getUsername());
        dataSource.setDriverClassName(tenant2.getDriver());
        return dataSource;
    }


    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource())
                .packages(EmployeeEntity.class, ProjectEntity.class, RoleEntity.class, ListEntity.class, TaskEntity.class)
                .build();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(
            @Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {

        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

}
