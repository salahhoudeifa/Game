package Game;

abstract class Character {
    protected String name;
    protected int health;
    protected int damage;
    protected int defense;
    protected int experience;
    protected int level;

    public Character(String name, int health, int damage, int defense) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.experience = 0;
        this.level = 1;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void levelUp() {
        if (experience >= 100) {
            level += 1;
            experience = 0;
            System.out.println(name + "is now level " + level);
        }
    }

    public void takeDamage(int damage) {
        damage = (int)(damage * 100)/(defense + 100);
        health -= damage;
    }

    abstract int useSkill();
}