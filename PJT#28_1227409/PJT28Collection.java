import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PJT28Collection {

    public static void main(String[] args) {

        int year = 2024;
        int month = 12;

        Map<Integer,Integer> days = new HashMap<>();

        Arrays.asList(1,3,5,7,8,10,12)
        .forEach(m -> days.put(m, 31));

        Arrays.asList(4,6,9,11)
        .forEach(m -> days.put(m, 30));

        boolean feb = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        days.put(2, feb ? 29 : 28);

        System.out.println(days.get(month)+"days for " + year + "-" + month);
        
    }
    
}
