package item;

import org.junit.jupiter.api.Assertions;
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
                sut.getModifierByIdentifier(ModifierIdentifier.HEALTH),
                item0.getModifierByIdentififer(ModifierIdentifier.HEALTH));

        //multiple puts
        sut.putItems(List.of(item1, item2));
        assertEquals(sut.getModifierByIdentifier(ModifierIdentifier.DAMAGE), -4);
    }

    @Test
    void test_itemStash_remove_items_from_inventory() {
        //given
        //when
        //then

    }

    @Test
    void test_itemStash_updates_modifier() {
        //given
        //when
        //then

    }
}