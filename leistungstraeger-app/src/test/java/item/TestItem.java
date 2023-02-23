package item;

/**
 * Class for testing purposes only, to be removed as soon as actual implementation exists.
 */
public class TestItem extends AbstractItem {

    @Deprecated
    public TestItem(String name) {
        super(name);
    }

    public TestItem(String name, double health, double damage, double attack, double speed, double defence) {
        super(name, health, damage, attack, speed, defence);
    }
}
