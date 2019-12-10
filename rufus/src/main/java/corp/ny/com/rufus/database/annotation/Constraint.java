package corp.ny.com.rufus.database.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Constraint {
    public java.lang.String references();
    public java.lang.String onTable() ;
    public java.lang.String onUpdate() default "CASCADE";
    public java.lang.String onDelete() default "CASCADE";
}
