package haru.model;

import haru.exception.HaruException;
import haru.exception.InvalidTimeException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

public class TaskTime implements Serializable {
    private static final List<DateTimeFormatter> FORMATS = List.of(
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    );

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH);

    private LocalDateTime time;

    public TaskTime(String alias, String strTime) throws HaruException {
        for (DateTimeFormatter fmt : FORMATS) {
            try {
                this.time = LocalDateTime.parse(strTime, fmt);
                return;
            } catch (DateTimeParseException e) {
            }
        }
        throw new InvalidTimeException(alias);
    }

    @Override
    public String toString() {
        return this.time.format(OUTPUT_FORMAT);
    }
}