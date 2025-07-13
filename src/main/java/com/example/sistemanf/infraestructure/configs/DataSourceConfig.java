package com.example.sistemanf.infraestructure.configs;

import com.example.sistemanf.datasources.EmpresaDataSource;
import com.example.sistemanf.datasources.RequesterDataSource;
import com.example.sistemanf.datasources.TokenDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.infraestructure.persistence.jpa.repos.EmpresaRepoJpaImpl;
import com.example.sistemanf.infraestructure.persistence.jpa.repos.UsuarioRepoJpaImpl;
import com.example.sistemanf.infraestructure.services.RequesterServiceImpl;
import com.example.sistemanf.infraestructure.services.TokenServiceJwtImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public EmpresaDataSource empresaDataSource() {
        return new EmpresaRepoJpaImpl();
    }

    @Bean
    public UsuarioDataSource usuarioDataSource() {
        return new UsuarioRepoJpaImpl();
    }

    @Bean
    public TokenDataSource tokenDataSource() {
        return new TokenServiceJwtImpl();
    }

    @Bean
    public RequesterDataSource requesterDataSource() {
        return new RequesterServiceImpl();
    }
}
