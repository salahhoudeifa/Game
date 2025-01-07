package Game;

public class Score {
    private int kills;
    private int totalExp;
    private int highestDmg;

    public Score(int kills, int totalExp, int highestDmg) {
        this.kills = kills;
        this.totalExp = totalExp;
        this.highestDmg = highestDmg;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void incrementKills() {
        this.kills++;
    }

    public int getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(int totalExp) {
        this.totalExp = totalExp;
    }

    public int getHighestDmg() {
        return highestDmg;
    }

    public void setHighestDmg(int highestDmg) {
        this.highestDmg = highestDmg;
    }
}
