package Game;
import java.util.Random;

public class Thief extends Character {
    Random random = new Random();
    public double luck = 1.25;

    public Thief(String name) {
        super(name, 85, 15, 7);
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void levelUp() {
        super.levelUp();
        damage = (int)(damage * 1.7);
        health = (int)(health * 1.2);
        defense = (int)(defense * 1.5);
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
    }

    private int getRandomNumber() {
        int min = 7;
        int max = 30;
        return (int) (Math.random() * (max - min) + min);
    }

    @Override
    public int useSkill() {
        System.out.println(name + " brandished a knife!");
        if (luck*random.nextInt(100)+1 >= 90) {
            return damage * getRandomNumber();
        }   else {
            System.out.println("Just missed!");
            return 0;
        }
    }
}
