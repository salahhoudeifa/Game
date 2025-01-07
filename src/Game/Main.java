package Game;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        boolean running = true;

        System.out.println("Enter the name of your character: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("Welcome to the game!");
        System.out.println("Your name is " + name);

        Game.Character warrior = new Warrior(name);
        Game.Character mage = new Mage(name);
        Game.Character thief = new Thief(name);

        Game.Score score = new Score(0,0,0);

        System.out.println("""
                Choose your class:
                1. Warrior
                2. Mage
                3. Thief\s""");

        int choice = getUserChoice(sc, 1, 3, "Invalid choice, try again");
        Game.Character player;

        switch (choice) {
            case 1:
                player = warrior;
                break;
            case 2:
                player = mage;
                break;
            case 3:
                player = thief;
                break;
            default:
                System.out.println("Invalid choice. Picked Warrior as default.");
                player = warrior;
        }

        int extraDefense = (int)(Math.random() * 10);

        System.out.println("----------------------------------");

        while(running) {
            String[] enemies = {"Goblin", "Orc", "Skeleton", "Elf"};

            int enemyHealth = rand.nextInt(100);
            int enemyDamage = rand.nextInt(10-5) + 10;

            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("----------------------------------");
            System.out.println("A(n) " + enemy + " approaches!");

            while(enemyHealth > 0) {

                int criticalHit = rand.nextInt(100);

                System.out.println(enemy + "'s Health: " + enemyHealth);
                System.out.println(name + "'s Health: " + player.health);
                System.out.println("""
                        --------------------
                        What will you do?
                        1. Attack
                        2. Defend
                        3. Use skill
                        4. Wait
                        --------------------
                        \s""");
                choice = getUserChoice(sc, 1, 4, "Invalid choice, try again");
                switch (choice) {
                    case 1:
                        System.out.println(name + " attacks!");
                        if (criticalHit >= 90) {
                            System.out.println("Critical Hit!");
                            int criticalDamage = (int)(player.damage * 2.5);
                            enemyHealth -= criticalDamage;
                            System.out.println(criticalDamage + " damage!");
                            if (score.getHighestDmg() < criticalDamage) {
                                score.setHighestDmg(criticalDamage);
                            }
                            break;
                        } else {
                            enemyHealth -= player.damage;
                            System.out.println(player.damage + " damage to " + enemy);
                            if (score.getHighestDmg() < player.damage) {
                                score.setHighestDmg(player.damage);
                            }
                            break;
                        }
                    case 2:
                        System.out.println(name + " braced themselves!");
                        player.defense += extraDefense;
                        break;
                    case 3:
                        int skillDamage = player.useSkill();
                        if (skillDamage > 0) {
                            enemyHealth -= skillDamage;
                            System.out.println(skillDamage + " damage to " + enemy);
                        }
                        if (score.getHighestDmg() < skillDamage) {
                            score.setHighestDmg(skillDamage);
                        }
                        break;
                    case 4:
                        System.out.println(name + " waited their turn out!");
                        break;
                }

                if (enemyHealth <= 0) {
                    System.out.println("Victory!");
                    score.incrementKills();
                    int expDrop = rand.nextInt(40);
                    player.experience += expDrop;
                    score.setTotalExp(score.getTotalExp() + expDrop);
                    System.out.println(name + " gained " + expDrop + " exp.");
                    if (player.experience >= 100) {
                        player.levelUp();
                    }
                    break;
                }

                System.out.println("The " + enemy + " attacks!");
                player.takeDamage(enemyDamage);
                System.out.println(enemyDamage + " damage to " + name);
                player.defense -= extraDefense;
                if (!player.isAlive()) {
                    System.out.println("Game over!");
                    System.out.println("-----------------------------");
                    System.out.println("Enemies slain: " + score.getKills());
                    System.out.println("Total experience gained: " + score.getTotalExp());
                    System.out.println("Highest damage dealt: " + score.getHighestDmg());
                    System.out.println("-----------------------------");
                    running = false;
                    break;
                }
            }
        }
        sc.close();
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