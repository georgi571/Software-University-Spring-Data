package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Email {
    String message() default "Invalid email format";

    class EmailValidator {
        private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([.\\-_][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.-]?[a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$";

        private static final Pattern regex = Pattern.compile(EMAIL_PATTERN);

        public static boolean isValid(String email) {
            if (email == null) {
                return false;
            }

            Matcher matcher = regex.matcher(email);
            return matcher.matches();
        }
    }
}
