package corp.ny.com.rufus.database.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    int intSize() default 0;
    int varCharSize() default 42;
    boolean nullable() default false;
    String[] check() default {};
    boolean primary() default false;
    boolean increment() default false;
    boolean unique() default false;
    String defaultString() default "";
    int defaultInt() default 0;
    boolean signed() default false;
}
