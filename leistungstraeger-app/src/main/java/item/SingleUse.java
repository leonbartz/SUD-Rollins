package item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If an annotated {@class AbstractItem} is used, the character object which interacts with the item gets a
 * permanent alteration to their modifiers, after which the AbstractItem object is removed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SingleUse {}
