package ica.oose.padel.backend.services.dto;

public class PlayerDTO {
    private String alias;
    private int ranking;

    //Constructor has to be present for the rest service
    public PlayerDTO() {
    }

    //constructor with parameter alias
    public PlayerDTO(String alias) {
        this.alias = alias;
    }

    //constructor with parameters alias and ranking
    public PlayerDTO(String alias, int ranking) {
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
}
