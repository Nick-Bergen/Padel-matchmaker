package ica.oose.padel.backend;

import ica.oose.padel.backend.services.*;
import ica.oose.padel.backend.services.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PlayerService playerService() {
        return new PlayerService();
    }

    @Bean
    public ClubService clubService() {
        return new ClubService();
    }

    @Bean
    public FieldService fieldService() {
        return new FieldService();
    }

    @Bean
    public GameService gameService() { return new GameService();}

    @Bean
    public InviteService inviteService() {return new InviteService();}

}
