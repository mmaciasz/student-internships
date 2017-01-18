package pl.com.pollub.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maciek on 2016-10-23.
 */
public enum UserType {

    /** Student */
    STUDENT,

    /** Pracownik uczelni */
    TUTOR,

    /** Pracownik firmy */
    FIRM_EMPLOYEE,

    /** Administrator */
    ADMIN,
    ;

    public static String[] getAllValues() {
        String[] strings = {};
        for (UserType userType : UserType.values()) {
            strings = Arrays.copyOf(strings, strings.length + 1);
            strings[strings.length - 1] = userType.name();
        }
        return strings;
    }

}
