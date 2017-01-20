package tr.com.innova.payflex.campaignCriteria.drlGenerator.helpers;

public class DroolsHelper {

    public static boolean firstArrayContainsSecond(Object first, Object second) {
        String[] firstArray = ((String) first).split(",");
        String[] secondArray = ((String) second).split(",");

        for (String aFirstArray : firstArray) {
            for (String aSecondArray : secondArray) {
                if (aFirstArray.equals(aSecondArray))
                    return true;
            }
        }
        return false;
    }

    public static boolean isDealerChildOfOrSame(Object fromEvent, String fromRule) {
        String[] eventLocations = ((String) fromEvent).split(",");
        String[] ruleLocations = fromRule.split(",");

        for (String eventLocation : eventLocations) {
            for (String ruleLocation : ruleLocations) {
                // System.out.println(eventLocations[i].toString() + "-" +
                // ruleLocations[j].toString());
                if (eventLocation.equals(ruleLocation))
                    return true;
            }
        }
        return false;
    }

    public static boolean isExcluded(Object strExcludedCampaign, String currentCampaign) {
        if (strExcludedCampaign == null)
            return false;

        String[] excludedCampaigns = ((String) strExcludedCampaign).split(",");
        for (String excludedCampaign : excludedCampaigns) {
            if (excludedCampaign.equals(currentCampaign))
                return true;
        }
        return false;
    }

    public static String getDate(Object date) {
        return ((String) date).substring(10);
    }
}
