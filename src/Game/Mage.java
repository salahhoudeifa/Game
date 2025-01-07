package Game;

import java.util.Scanner;
import java.util.Random;

public class Mage extends Character {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public int mana = 200;

    public Mage(String name) {
        super(name, 70, 10, 10);
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void levelUp() {
        super.levelUp();
        damage = (int)(damage * 1.1);
        health = (int)(health * 1.6);
        defense = (int)(defense * 1.4);
        mana += 100;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
    }

    private int getRandomNumber() {
        int min = 3;
        int max = 10;
        return (int) ((max - min) / Math.random());
    }

    @Override
    @SuppressWarnings("UnnecessaryLocalVariable")
    public int useSkill() {
        System.out.println("""
                Pick a spell to cast:
                1. Fireball --- 20 mana
                2. Thunderbolt --- 35 mana
                3. Heal --- 50 mana
                """);
        int choice = getUserChoice(scanner, 1, 3, "Invalid choice, try again");
        switch (choice) {
            case 1:
                if (mana < 20) {
                    System.out.println("Not enough mana!");
                    return 0;
                } else {
                mana -= 20;
                System.out.println(name + " casted Fireball!");
                int fireDamage = (int)(damage * 1.5) * 2;
                return fireDamage;
                }
            case 2:
                if (mana < 35) {
                    System.out.println("Not enough mana!");
                    return 0;
                } else {
                    mana -= 35;
                    int accuracy = random.nextInt(4);
                    if (accuracy == 2) {
                        System.out.println(name + " casted Thunderbolt!");
                        int thunderDamage = (int)(damage * 1.5) * 3;
                        return thunderDamage;
                    } else {
                        System.out.println("Just missed!");
                        return 0;
                    }
                }
            case 3:
                if (mana < 50) {
                    System.out.println("Not enough mana!");
                    return 0;
                } else if (health >= 70) {
                    System.out.println("Health is already full!");
                    return 0;
                } else {
                    mana -= 50;
                    System.out.println(name + " casted Heal!");
                    health += getRandomNumber();
                    return 0;
                }

        }
        scanner.close();
        return 0;
    }

    private static int getUserChoice(Scanner sc, int min, int max, String errorMessage) {
        int choice = -1;
        while (true) {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= min && choice <= max) {
                    break;
                }
            } else {
                sc.next(); // Clear invalid input
            }
            System.out.println(errorMessage);
        }
        return choice;
    }
}
