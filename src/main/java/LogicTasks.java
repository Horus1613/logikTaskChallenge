import java.util.Arrays;
import java.util.Random;

public class LogicTasks {
    public static void main(String[] args) {
        GeneratedData generatedBoxesInfo = getTwelveBoxesWithOneNonNormalBox();
        int position = getNonNormalBox(generatedBoxesInfo.getRandomBoxes());
        System.out.println(position == generatedBoxesInfo.getCorrectBoxPosition());

        for (int i = 0; i < 1000000; i++) {
            testThisShit();
        }
    }

    /* Входные параметры для примера теста
     * тут всегда отличается 7 коробка */
    private static GeneratedData getTwelveBoxesWithOneNonNormalBox() {
        int pos = 8;
        Box[] randomBoxes = new Box[12];
        for (int i = 0; i < 12; i++) {
            randomBoxes[i] = new Box(5);
        }
        randomBoxes[pos] = new Box(8);
        return new GeneratedData(randomBoxes, pos);
    }

    private static void testThisShit() {
        Random random = new Random();
        int boxPos = random.nextInt(12);

        Box[] boxes = new Box[12];
        for (int i = 0; i < 12; i++) {
            boxes[i] = new Box(5);
        }

        if (random.nextBoolean()) {
            boxes[boxPos].value += 3;
        } else {
            boxes[boxPos].value -= 3;
        }

        if (boxPos != getNonNormalBox(boxes)) {
            System.out.println("YOU ARE IDIOT!");
        }
    }


    /* Ваша задача - реализовать этот метод.
       getNonNormalBox должен вернуть позицию коробки в массиве, отличающейся по value от остальных одинаковых коробок.
     */
    private static int getNonNormalBox(Box[] boxes) {
        Box[] first = Arrays.copyOfRange(boxes, 0, 4);
        Box[] second = Arrays.copyOfRange(boxes, 4, 8);

        int compare = Box.comparing(first, second);

        if (compare == 0) {
            Box[] normal = prepareTriplet(boxes, 0, 1, 2);
            Box[] bad = prepareTriplet(boxes, 8, 9, 10);
            int tripletCompare = Box.comparing(bad, normal);

            if (tripletCompare == 0) {
                return 11;
            }

            return resolveTriplet(boxes, 8, 9, 10, tripletCompare);
        }

        Box[] left = Arrays.copyOfRange(boxes, 0, 3);
        Box[] normal = Arrays.copyOfRange(boxes,8,11);
        int leftRightCompare = Box.comparing(left, Arrays.copyOfRange(boxes, 3, 6));
        if(leftRightCompare == 0){
            return resolvePair(boxes,6,7,11);
        }

        if(Box.comparing(left, normal)==0){
            return resolveTriplet(boxes, 3, 4, 5, (-1)*leftRightCompare);
        } else {
            return resolveTriplet(boxes, 0, 1, 2, leftRightCompare);
        }

    }

    private static int resolveTriplet(Box[] boxes, int first, int second, int third, int tripletDiffDirect) {
        int compare = Box.comparing(boxes[first], boxes[second]);
        if (compare == 0) {
            return third;
        }

        return compare == tripletDiffDirect ? first : second;
    }

    private static int resolvePair(Box[] boxes, int first, int second, int normal){
        return Box.comparing(boxes[first], boxes[normal]) == 0
                ? second
                : first;
    }

    private static Box[] prepareTriplet(Box[] boxes, int first, int second, int third) {
        return new Box[]{boxes[first], boxes[second], boxes[third]};
    }
}

