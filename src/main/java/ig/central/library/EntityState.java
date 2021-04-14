package ig.central.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * This annotation is created to represent transient attribute which will be
 * used to hold state of an entity
 * 
 * NEW_ENTITY
 * 
 * EXISTING_ENTITY
 * 
 * UPDATED_ENTITY
 * 
 * DELETED_ENTITY
 * 
 * @author Jai Kishan
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EntityState {
	public String value() default "";
}
