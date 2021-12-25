import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class LogicTasks {
    public static void main(String[] args) {
        GeneratedData generatedBoxesInfo = getTwelveBoxesWithOneNonNormalBox();
        int position = getNonNormalBox(generatedBoxesInfo.getRandomBoxes());
        System.out.println(position == generatedBoxesInfo.getCorrectBoxPosition());

        for (int i = 0; i < 10000000; i++) {
            testThisShit();
        }
    }

    /* Входные параметры для примера теста
     * тут всегда отличается 7 коробка */
    private static GeneratedData getTwelveBoxesWithOneNonNormalBox() {
        Box[] randomBoxes = new Box[12];
        for (int i = 0; i < 12; i++) {
            randomBoxes[i] = new Box(5);
        }
        randomBoxes[7] = new Box(3);
        return new GeneratedData(randomBoxes, 7);
    }

    private static void testThisShit(){
        Random random = new Random();
        int boxPos = random.nextInt(12);
        System.out.println(boxPos);

        Box[] boxes = new Box[12];
        for (int i = 0; i < 12; i++) {
            boxes[i] = new Box(5);
        }

        if(random.nextBoolean()){
            boxes[boxPos].value += 3;
        } else {
            boxes[boxPos].value -= 3;
        }

        if(boxPos != getNonNormalBox(boxes)){
            System.out.println("YOU ARE IDIOT!");
        }
    }

    /* Ваша задача - реализовать этот метод.
       getNonNormalBox должен вернуть позицию коробки в массиве, отличающейся по value от остальных одинаковых коробок.
     */
    private static int getNonNormalBox(Box[] boxes) {
        Box[] normalTriplet = null;

        for (int i = 0; i < 12; i += 6) {
            Box[] left = Arrays.copyOfRange(boxes, i, i + 3);
            Box[] right = Arrays.copyOfRange(boxes, i + 3, i + 6);

            int compare = Box.comparing(left, right);
            if (compare == 0) {
                normalTriplet = left;
                continue;
            }

            if (Objects.isNull(normalTriplet)) {
                normalTriplet = Arrays.copyOfRange(boxes, 9, 12);
            }

            Box[] nonNormalTriplet;
            int nonNormalTripletStartIndex;
            if (Box.comparing(left, normalTriplet) == 0) {
                nonNormalTriplet = right;
                nonNormalTripletStartIndex = i + 3;
            } else {
                nonNormalTriplet = left;
                nonNormalTripletStartIndex = i;
            }

            if (Box.comparing(nonNormalTriplet[0], nonNormalTriplet[1]) == 0) {
                return nonNormalTripletStartIndex + 2;
            } else {
                return Box.comparing(nonNormalTriplet[0], nonNormalTriplet[2]) == 0 ? nonNormalTripletStartIndex + 1 : nonNormalTripletStartIndex;
            }
        }

        return 0;
    }
}

