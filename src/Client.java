import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rootState = scanner.next();
        String strGoalState = scanner.next();
        scanner.close();

        long startTime = System.currentTimeMillis();
        BestFirstSearch.search(rootState,strGoalState);

        long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("** Tempo de execução:\t" + totalTime + " ms");
    }
}
