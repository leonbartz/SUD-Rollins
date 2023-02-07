package object.interaction;

public interface IsAttackable {

    int getHealth();

    /**
     * In case we need something like armour
     */
    double getArmour();

    void attack(double damage);
}
