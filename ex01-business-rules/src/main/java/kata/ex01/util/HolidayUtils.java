package kata.ex01.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kawasima
 */
public class HolidayUtils {
    private static final Pattern DTSTART_PTN = Pattern.compile("^DTSTART;VALUE=DATE:(\\d{8})$");
    private static final Pattern SUMMARY_PTN = Pattern.compile("^SUMMARY:(.+)$");
    private static final EnumSet HOLIDAYS_OF_WEEK = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    private static final Map<LocalDate, String> holidays = new HashMap<>();
    static {
        try {
            URL url = new URL("https://calendar.google.com/calendar/ical/ja.japanese%23holiday%40group.v.calendar.google.com/public/basic.ics");
            try (InputStream in = url.openConnection().getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line;
                String name = null;
                LocalDate date = null;

                DateTimeFormatter ptn = DateTimeFormatter.ofPattern("yyyyMMdd");
                while ((line = reader.readLine()) != null) {
                    if ("BEGIN:VEVENT".equals(line)) {
                        name = null;
                        date = null;
                    } else if (line.startsWith("DTSTART;VALUE=DATE")) {
                        Matcher dtstartMatcher = DTSTART_PTN.matcher(line);
                        if (dtstartMatcher.matches()) {
                            date = LocalDate.parse(dtstartMatcher.group(1), ptn);
                        }
                    } else if (line.startsWith("SUMMARY:")) {
                        Matcher summaryMatcher = SUMMARY_PTN.matcher(line);
                        if (summaryMatcher.matches()) {
                            name = summaryMatcher.group(1);
                        }
                    } else if ("END:VEVENT".equals(line)) {
                        holidays.put(date, name);
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean isHoliday (LocalDate d) {
        return HOLIDAYS_OF_WEEK.contains(d.getDayOfWeek()) || holidays.containsKey(d);
    }

    public static void printHolidays() {
        holidays.entrySet().stream()
                .sorted(Comparator.comparing(x -> x.getKey()))
                .forEach(x -> System.out.println(x.getKey() + ": " + x.getValue()));
    }

}
