package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Password {
    String message() default "Invalid password format";

    class PasswordValidator {
        private static final int MIN_LENGTH = 6;
        private static final int MAX_LENGTH = 50;

        private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+<>?]).+$";

        private static final Pattern regex = Pattern.compile(PASSWORD_PATTERN);

        public static boolean isValid(String password) {
            if (password == null || password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
                return false;
            }

            Matcher matcher = regex.matcher(password);
            return matcher.matches();
        }
    }
}
