package ica.oose.padel.backend.services.dto;

public class RegisterPlayerDTO {
    String alias;
    int ranking;

    //Constructor has to be present for the rest service
    public RegisterPlayerDTO() {
    }

    public RegisterPlayerDTO(String alias, int ranking) {
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
