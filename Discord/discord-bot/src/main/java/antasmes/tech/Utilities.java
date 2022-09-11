package antasmes.tech;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

public class Utilities {

    public static void printArray(Object[] array) {
        // prints an array
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(String.format("%s, ", array[i]));
        }
        System.out.println("]");
    }

    public static void printList(List<?> list) {
        // prints a list
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(String.format("%s, \n", list.get(i)));
        }
        System.out.println("]");
    }

    public static int getRandomNumber(int min, int max) {
        // returns a random number between min and max
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static Boolean isIn(Object sample, Object[] array) {
        // checks if value is in array
        try {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(sample)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static int getFirstFreeIndex(Object[] array) {
        // returns the last null index in the array
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static Timestamp getCurrentTime() {
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());

        return timeStamp;
    }

    public static String[] trimArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
        return array;
    }
}
