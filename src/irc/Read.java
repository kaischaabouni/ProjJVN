package irc;
import java.lang.annotation.*;
//Is the annotation available at execution time
@Retention(RetentionPolicy.RUNTIME)
//Annotation associated with a type (Classe, interface)
@Target(ElementType.METHOD)

public @interface Read {

}
