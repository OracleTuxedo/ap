package mti.com.telegram.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FIELD {
    Kind kind();

    int length();

    FieldType type();

    TrimType trim();
}