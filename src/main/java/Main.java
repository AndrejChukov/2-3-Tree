import java.util.Random;

// Эксперимент
public class Main {
    public static void main(String[] args) {
        Tree23 tree = new Tree23();
        Random random = new Random();
        int n = 10000;
        int[] data = new int[n];

        // Создаем массив
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(100000);
        }

        // Вставка
        long totalInsertTime = 0;
        long totalInsertOps = 0;
        for (int x : data) {
            long start = System.nanoTime();
            tree.operations = 0;
            tree.insert(x);
            totalInsertTime += (System.nanoTime() - start);
            totalInsertOps += tree.operations;
        }

        // Поиск (100 элементов)
        long totalSearchTime = 0;
        long totalSearchOps = 0;
        for (int i = 0; i < 100; i++) {
            int target = data[random.nextInt(n)];
            long start = System.nanoTime();
            tree.operations = 0;
            tree.find(target);
            totalSearchTime += (System.nanoTime() - start);
            totalSearchOps += tree.operations;
        }

        // Удаление (1000 элементов)
        long totalDeleteTime = 0;
        long totalDeleteOps = 0;
        for (int i = 0; i < 1000; i++) {
            int target = data[random.nextInt(n)];
            long start = System.nanoTime();
            tree.operations = 0;
            tree.delete(target);
            totalDeleteTime += (System.nanoTime() - start);
            totalDeleteOps += tree.operations;
        }

        int avgTotalInsertOps = (int) (totalInsertOps / n);
        int avgTotalSearchOps = (int) (totalSearchOps / 100);
        int avgTotalDeleteOps = (int) (totalDeleteOps / 1000);
        int avgTotalInsertTime = (int) (totalInsertTime / n);
        int avgTotalSearchTime = (int) (totalSearchTime / 100);
        int avgTotalDeleteTime = (int )(totalDeleteTime / 1000);

        System.out.println("Средняя вставка: " + avgTotalInsertOps + " операций, " + avgTotalInsertTime + " нс");
        System.out.println("Средний поиск: " + avgTotalSearchOps + " операций, " + avgTotalSearchTime + " нс");
        System.out.println("Среднее удаление: " + avgTotalDeleteOps + " операций, " + avgTotalDeleteTime + " нс");
    }
}