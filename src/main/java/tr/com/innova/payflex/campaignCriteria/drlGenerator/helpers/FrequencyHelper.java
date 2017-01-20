package tr.com.innova.payflex.campaignCriteria.drlGenerator.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FrequencyHelper {

    static String PATTERN = "yyyy-MM-dd HH:mm:ss";
    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static boolean checkFrequencyForPastEvents(Object strNow, String period, List<HashMap<String, Object>> pastEvents, int minCount, int maxCount) throws IllegalArgumentException {
        LocalDateTime now = LocalDateTime.parse(strNow.toString(), FORMATTER);

        String[] operator = period.split("-");
        if (operator.length != 2)
            throw new IllegalArgumentException("Use - as a delimeter and use one only");

        int value = Integer.parseInt(operator[0]);
        String type = operator[1];
        switch (type) {
            case "MC":
                return checkMonthlyFrequency(now, value, pastEvents, minCount, maxCount);
            default:
                throw new IllegalArgumentException(type);
        }
    }

    public static boolean checkFrequencyForBenefitLimit(Object strNow, String period, List<HashMap<String, Object>> pastBenefits, int limitValue) throws IllegalArgumentException {
        LocalDateTime now = LocalDateTime.parse(strNow.toString(), FORMATTER);
        //System.out.println(now);

        switch (period) {
            case "MC":
                LocalDate startDate = LocalDate.of(now.getYear(), now.getMonth().getValue(), 1);
                int size = pastBenefits
                        .stream()
                        .filter(p -> LocalDate.parse(p.get("Date").toString(), FORMATTER).isAfter(startDate)
                                || LocalDate.parse(p.get("Date").toString(), FORMATTER).equals(startDate))
                        .collect(Collectors.toList())
                        .size();
                //System.out.println("Size : " + size + " LimitValue : " + limitValue);
                return size < limitValue;
            default:
                throw new IllegalArgumentException(period);
        }
    }

    private static boolean checkMonthlyFrequency(LocalDateTime now, int months, List<HashMap<String, Object>> pastEvents, int minCount, int maxCount) {
        //System.out.println(now);
        months = months + 1;

        List<LocalDate> dates = new ArrayList<LocalDate>();

        LocalDate ldate = LocalDate.of(now.getYear(), now.getMonth().getValue(), 1);

        for (int i = 0; i < months; i++) {
            dates.add(LocalDate.of(ldate.getYear(), ldate.getMonth(), ldate.getDayOfMonth()));
            ldate = ldate.minusMonths(1);
        }

        //System.out.println("Every Month Min : " + minCount + " Max : " + maxCount);
        for (int i = months - 1; i >= 0; i--) {
            if (i == 0)
                continue;

            LocalDate startDate = dates.get(i);
            LocalDate endDate = dates.get(i - 1);
            int size = pastEvents
                    .stream()
                    .filter(p -> LocalDate.parse(p.get("Date").toString(), FORMATTER).isAfter(startDate)
                            || LocalDate.parse(p.get("Date").toString(), FORMATTER).equals(startDate))
                    .filter(p -> LocalDate.parse(p.get("Date").toString(), FORMATTER).isBefore(endDate))
                    .collect(Collectors.toList())
                    .size();

            //System.out.println(dates.get(i) + " | " + dates.get(i-1) + " | " + size);

            if (size < minCount || size > maxCount)
                return false;
        }

        return true;
    }
}