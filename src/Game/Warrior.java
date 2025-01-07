package Game;

public class Warrior extends Character {

    public Warrior(String name) {
        super(name, 100, 20, 15);
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void levelUp() {
        super.levelUp();
        damage = (int)(damage * 1.5);
        health = (int)(health * 1.3);
        defense = (int)(defense * 1.2);
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
    }

    public int getRandomNumber() {
        int min = 15;
        int max = 20;
        return (int) (Math.random() * (max - min) + min);
    }

    @Override
    public int useSkill() {
        System.out.printf(name + "delivers a devastating blow!");
        return damage * 2;
    }

}