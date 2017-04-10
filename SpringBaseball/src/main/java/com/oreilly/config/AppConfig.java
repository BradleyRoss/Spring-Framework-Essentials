package com.oreilly.config;

import com.oreilly.entities.Game;
import com.oreilly.entities.BaseballGame;
import com.oreilly.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.text.NumberFormat;
import java.util.List;
// import java.util.Set;

@Configuration
@ComponentScan(basePackages = "com.oreilly")
@EnableAspectJAutoProxy
public class AppConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private List<Team> teams;

    @Bean
    public NumberFormat nf() {
        return NumberFormat.getCurrencyInstance();
    }

    @Bean
    @Scope("prototype")
    public Game game() {
        BaseballGame baseballGame = new BaseballGame(teams.get(0),teams.get(1));
        baseballGame.setDataSource(dataSource);
        return baseballGame;
    }
}
