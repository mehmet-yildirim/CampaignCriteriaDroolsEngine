package tr.com.innova.payflex.campaignCriteria.drlGenerator.helpers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DroolsDateHelper {

    static String PATTERN = "yyyy-MM-dd HH:mm:ss";
    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static boolean FirstIsGreaterThanOrEqualToSecond(Object firstDate, String secondDate) {
        return LocalDateTime.parse(firstDate.toString(), FORMATTER).isAfter(LocalDateTime.parse(secondDate, FORMATTER))
                || LocalDateTime.parse(firstDate.toString(), FORMATTER).isEqual(LocalDateTime.parse(secondDate, FORMATTER));
    }

    public static boolean firstIsRelativelyBeforeTimestamp(Object firstDate, String relativeValue, Object timeStamp) throws IllegalArgumentException {
        LocalDateTime condition = LocalDateTime.parse(firstDate.toString(), FORMATTER);
        LocalDateTime now = LocalDateTime.parse(timeStamp.toString(), FORMATTER);

        String[] operator = relativeValue.split("-");
        if (operator.length > 2)
            throw new IllegalArgumentException("Use - as a delimeter and use one only");

        int value = Integer.parseInt(operator[0]);
        String type = operator[1];
        switch (type) {
            case "M":
                return now.minusMonths(value).isAfter(condition);

            default:
                throw new IllegalArgumentException(type);
        }
    }

    public static boolean firstIsInTheLastPeriod(Object firstDate, String relativeValue, Object timeStamp) throws IllegalArgumentException {
        LocalDateTime condition = LocalDateTime.parse(firstDate.toString(), FORMATTER);
        LocalDateTime now = LocalDateTime.parse(timeStamp.toString(), FORMATTER);

        String[] operator = relativeValue.split("-");
        if (operator.length > 2)
            throw new IllegalArgumentException("Use - as a delimeter and use one only");

        int value = Integer.parseInt(operator[0]);
        String type = operator[1];
        switch (type) {
            case "M":
                return now.minusMonths(value).isBefore(condition);

            default:
                throw new IllegalArgumentException(type);
        }
    }

    public static boolean afterTimeOrEqualTo(Object now, String criteriaTime) {
        String[] arrNow = now.toString().substring(11, 16).split(":");
        String[] arrCriteriaTime = criteriaTime.split(":");

        LocalTime time1 = LocalTime.of(Integer.parseInt(arrNow[0]), Integer.parseInt(arrNow[1]));
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrNow[0]));
        cal1.set(Calendar.MINUTE, Integer.parseInt(arrNow[1]));

        LocalTime time2 = LocalTime.of(Integer.parseInt(arrCriteriaTime[0]), Integer.parseInt(arrCriteriaTime[1]));
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrCriteriaTime[0]));
        cal2.set(Calendar.MINUTE, Integer.parseInt(arrCriteriaTime[1]));

        return (time1.isAfter(time2) || time1.equals(time2));
    }

    public static boolean beforeTimeOrEqualTo(Object now, String criteriaTime) {
        String[] arrNow = now.toString().substring(11, 16).split(":");
        String[] arrCriteriaTime = criteriaTime.split(":");

        LocalTime time1 = LocalTime.of(Integer.parseInt(arrNow[0]), Integer.parseInt(arrNow[1]));
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrNow[0]));
        cal1.set(Calendar.MINUTE, Integer.parseInt(arrNow[1]));

        LocalTime time2 = LocalTime.of(Integer.parseInt(arrCriteriaTime[0]), Integer.parseInt(arrCriteriaTime[1]));
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrCriteriaTime[0]));
        cal2.set(Calendar.MINUTE, Integer.parseInt(arrCriteriaTime[1]));

        return (time1.isBefore(time2) || time1.equals(time2));
    }

    public static boolean FirstIsLessThanOrEqualToSecond(Object firstDate, String secondDate) {
        return LocalDateTime.parse(firstDate.toString(), FORMATTER).isBefore(LocalDateTime.parse(secondDate, FORMATTER))
                || LocalDateTime.parse(firstDate.toString(), FORMATTER).isEqual(LocalDateTime.parse(secondDate, FORMATTER));
    }

    public static boolean firstIsAfterSecond(Object firstDate, String secondDate) {
        return LocalDateTime.parse(firstDate.toString(), FORMATTER).isAfter(LocalDateTime.parse(secondDate, FORMATTER));
    }

    public static boolean firstIsBeforeSecond(Object firstDate, String secondDate) {
        return LocalDateTime.parse(firstDate.toString(), FORMATTER).isBefore(LocalDateTime.parse(secondDate, FORMATTER));
    }

    public static boolean IsDayOfYear(Object firstDate, Object secondDate) {
        LocalDateTime firstDay = LocalDateTime.parse(firstDate.toString(), FORMATTER);
        LocalDateTime secondDay = LocalDateTime.parse(secondDate.toString(), FORMATTER);

        return (firstDay.getDayOfYear() == secondDay.getDayOfYear());
    }

    public static List<HashMap<String, Object>> applySameDayLimit(List<HashMap<String, Object>> events, int limit) {

        Map<String, Long> groupedByCalendarDay = events.stream()
                .collect(Collectors.groupingBy(x -> x.get("Date").toString().substring(0, 10), Collectors.counting()));

        Map<String, Long> filtered = groupedByCalendarDay
                .entrySet()
                .stream()
                .filter(m -> m.getValue() > limit)
                .collect(Collectors.toMap(map -> (String) map.getKey(), map -> (Long) map.getValue()));

        //System.out.println(filtered);
        //System.out.println(events);

        for (Map.Entry<String, Long> entry : filtered.entrySet()) {
            GetFirstN(events, entry.getKey(), limit);
        }

        //System.out.println(events);

        return events;
    }

    private static void GetFirstN(List<HashMap<String, Object>> events, String dateToApply, int limit) {
        List<HashMap<String, Object>> subList = events
                .stream()
                .filter(e -> e.get("Date").toString().substring(0, 10).equals(dateToApply))
                .collect(Collectors.toList());

        Collections.sort(subList, (a, b) -> LocalDateTime.parse(a.get("Date").toString(), FORMATTER)
                .compareTo(LocalDateTime.parse(b.get("Date").toString(), FORMATTER)));
        subList = subList.subList(limit, subList.size());
        for (int i = 0; i < subList.size(); i++) {
            HashMap<String, Object> tmp = subList.get(i);
            events.removeIf(e -> e.get("Date").equals(tmp.get("Date")));
            //System.out.println(tmp.get("Date"));
        }
    }
}
