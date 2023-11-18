import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class FilePrint {
    private File file;
    public FilePrint(Vector<Vector<Integer>> adjencyMatrix) {
        try {
            file = new File("src/adjencyMatrix.txt");
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < adjencyMatrix.size(); i++) {
                for (int j = 0; j < adjencyMatrix.size(); j++) {
                    printWriter.print(adjencyMatrix.get(i).get(j) + " ");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}
