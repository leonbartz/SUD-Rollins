package core.item;

import core.item.modifier.ModifierIdentifier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemStashTest {

    @Test
    void test_itemStash_put_items_into_inventory() {
        final TestItem item0 = new TestItem("Test", 10, 10, 10, 10, 10);
        final TestItem item1 = new TestItem("Test", 41, 0, 0, 0, 0);
        final TestItem item2 = new TestItem("Test", 5, -14, 0, -20, 0);
        final ItemStash sut = new ItemStash();

        //single put
        sut.putItem(item0);
        assertEquals(
                sut.getValueForModifier(ModifierIdentifier.HEALTH),
                item0.getModifierByIdentifier(ModifierIdentifier.HEALTH));

        //multiple puts
        sut.putItems(List.of(item1, item2));
        assertEquals(sut.getValueForModifier(ModifierIdentifier.DAMAGE), -4);
    }

    @Test
    void test_itemStash_remove_items_from_inventory() {
        final TestItem item0 = new TestItem("Test", 10, 10, 10, 10, 10);
        final TestItem item1 = new TestItem("Test", 41, 0, 0, 0, 0);
        final TestItem item2 = new TestItem("Test", 5, -14, 0, -20, 0);
        final ItemStash sut = new ItemStash(item0, item1, item2);

        //single remove
        sut.removeItem(item0.getId());
        assertEquals(item1.getModifierByIdentifier(ModifierIdentifier.HEALTH)
                        + item2.getModifierByIdentifier(ModifierIdentifier.HEALTH),
                sut.getValueForModifier(ModifierIdentifier.HEALTH));

        //remove rest
        sut.removeAllItems();
        assertEquals(0, sut.getValueForModifier(ModifierIdentifier.ATTACK));
    }
}