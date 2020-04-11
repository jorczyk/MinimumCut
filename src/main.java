import algorithm.impl.RandomizedContractionSolver;

public class main {

    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\Piotr\\IdeaProjects\\graph\\resources\\kargerMinCut.txt";
        RandomizedContractionSolver solver = new RandomizedContractionSolver(inputFilePath);
        System.out.println(solver.calculateMinCut());
    }
}
