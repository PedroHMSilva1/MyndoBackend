package com.boardhub.BoardPi.configurations;

import com.boardhub.BoardPi.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {
    @Autowired
    private DbService dbService;
    @Bean
    public boolean init() throws Exception {
        try{
            dbService.criarDadosMySQL();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
