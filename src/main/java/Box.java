import java.util.*;

public class Box{
    private static int counter = 0;
    public Integer value;

    public Box(Integer value) {
        this.value = value;
    }

    public static int comparing(Box first, Box second) {
        counter++;
        if (first.value.equals(second.value)) {
            return 0;
        }
        return first.value - second.value > 0 ? 1 : -1;
    }

    public static int comparing(Box[] first, Box[] second) {
        counter++;
        Integer firstSum = Arrays.stream(first).mapToInt(o -> o.value).sum();
        Integer secondSum = Arrays.stream(second).mapToInt(o -> o.value).sum();
        if (firstSum.equals(secondSum)) {
            return 0;
        }
        return firstSum - secondSum > 0 ? 1 : -1;
    }

    public static int getCounter() {
        return counter;
    }
}