package pl.com.pollub.util;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 * Created by Maciek on 2017-01-08.
 */
public class AcademicYearResolver {

    private static final LocalDate ACADEMIC_YEAR_START_DT = LocalDate.of(Year.now().getValue(), Month.OCTOBER.getValue(), 1);

    private static final String ACADEMIC_YEAR_PATTERN = "{0}/{1}";

    public static String computeAcademicYear(LocalDate startDt) {

        Integer year1;
        Integer year2;

        if(ACADEMIC_YEAR_START_DT.isEqual(startDt) || ACADEMIC_YEAR_START_DT.isBefore(startDt)) {
            year1 = startDt.getYear();
            year2 = year1 + 1;
        } else {
            year2 = startDt.getYear();
            year1 = year2 - 1;
        }

        String academicYear = MessageFormat.format(ACADEMIC_YEAR_PATTERN, year1.toString(), year2.toString());
        return academicYear;
    }
}
