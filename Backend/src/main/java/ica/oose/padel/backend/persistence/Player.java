package ica.oose.padel.backend.persistence;

import javax.persistence.*;
import java.util.List;

@Entity(name = "player")
public class Player {

    @Id
    private String alias;

    private int ranking;

    @OneToMany
    private List<Game> games;

    public Player() {}

    public Player(String alias, int ranking) {
        this.alias = alias;
        this.ranking = ranking;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

}
