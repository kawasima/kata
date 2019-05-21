package kata.ex01.util;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.*;
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

    private static boolean isCacheAvailable(File cacheFile) {
        Instant now = Instant.now();

        return cacheFile.exists() && (
                LocalDateTime.ofEpochSecond(cacheFile.lastModified() / 1000, (int)((cacheFile.lastModified() % 1000) * 1000), ZoneId.systemDefault().getRules().getOffset(now)).getYear()
                        == LocalDateTime.ofInstant(now, ZoneId.systemDefault()).getYear());
    }

    private static OutputStream createOutputStream(File file) throws FileNotFoundException {
        if (isCacheAvailable(file)) {
            return new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    // doNothing
                }
            };
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            return new FileOutputStream(file);

        }
    }

    static {
        try {
            File cacheFile = new File("target/basic.ics");
            URL url = cacheFile.exists()? cacheFile.toURI().toURL() : new URL("https://calendar.google.com/calendar/ical/ja.japanese%23holiday%40group.v.calendar.google.com/public/basic.ics");

            try (InputStream in = url.openConnection().getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(createOutputStream(cacheFile), StandardCharsets.UTF_8))) {
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
                    writer.append(line).append("\n");
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
