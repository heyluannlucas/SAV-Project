package Utilitarios;

import java.util.Random;

public class RandomList {
    public RandomList() {
    }

    private static int[] generateRandomList(int count) {
        int[] list = new int[count];
        Random random = new Random();

        for(int i = 0; i < count; ++i) {
            list[i] = random.nextInt(100 );
        }

        return list;
    }

    public static int[] getRandomList(int count) {
        return generateRandomList(count);
    }
}
