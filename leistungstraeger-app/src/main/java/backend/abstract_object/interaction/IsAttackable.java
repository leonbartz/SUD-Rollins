package backend.abstract_object.interaction;

public interface IsAttackable {

    int getHealth();

    /**
     * In case we need something like armour
     */
    double getArmour();

    /**
     * Allows this object to be attacked, reduces damage by the amount of armour
     * @param damage - unmodified damage dealt to this object
     */
    void beAttacked(double damage);
}
