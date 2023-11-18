import java.util.*;
public class GetRoot {
    private static ArrayList<ArrayList<Integer>>ListaAdiacenta=new ArrayList<ArrayList<Integer>>();
    private static ArrayList<ArrayList<Integer>>ListaAdiacentaNoerientat=new ArrayList<ArrayList<Integer>>();

    ArrayList<ArrayList<Integer>>getListaAdiacenta()
    {
        return ListaAdiacenta;
    }
    private static Vector<Vector<Integer>>AdjencyMatrix;

    void createListaAdiacenta()
    {
        AdjencyMatrix=Frame.AdjencyMatrix();
        if(AdjencyMatrix.size()==0)
        {
            return;
        }
        ListaAdiacenta.clear();
        ListaAdiacentaNoerientat.clear();
        for(int i=0;i<AdjencyMatrix.size();i++)
        {
            ArrayList<Integer>list=new ArrayList<Integer>();
            for(int j=0;j<AdjencyMatrix.size();j++)
            {
                if(AdjencyMatrix.get(i).get(j)==1)
                {
                    list.add(j);
                }
            }
            ListaAdiacenta.add(list);
        }
        //copy lista adiacenta in lista adiacenta noerientat
        for(int i=0;i<ListaAdiacenta.size();i++)
        {
            ArrayList<Integer>list=new ArrayList<Integer>();
            for(int j=0;j<ListaAdiacenta.get(i).size();j++)
            {
                list.add(ListaAdiacenta.get(i).get(j));
            }
            ListaAdiacentaNoerientat.add(list);
        }
        for(int i=0;i<ListaAdiacentaNoerientat.size();i++)
        {
            for(int j=0;j<ListaAdiacentaNoerientat.get(i).size();j++)
            {
                if(!ListaAdiacentaNoerientat.get(ListaAdiacentaNoerientat.get(i).get(j)).contains(i))
                {
                    ListaAdiacentaNoerientat.get(ListaAdiacentaNoerientat.get(i).get(j)).add(i);
                }
            }
        }
    }
    boolean isCiclic() {
        int V = ListaAdiacenta.size();
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            int j;
            while (!stack.isEmpty()) {
                j = stack.peek();
                if (!visited[j]) {
                    visited[j] = true;
                    recStack[j] = true;
                }
                boolean isDone = true;
                for (int k = 0; k < ListaAdiacenta.get(j).size(); k++) {
                    if (!visited[ListaAdiacenta.get(j).get(k)]) {
                        stack.push(ListaAdiacenta.get(j).get(k));
                        isDone = false;
                        break;
                    } else if (recStack[ListaAdiacenta.get(j).get(k)]) {
                        return true;
                    }
                }
                if (isDone) {
                    recStack[stack.pop()] = false;
                }
            }

        }
        return false;
    }
    // Funcție care verifică dacă un graf este quasi-tare conex
    boolean isQuasiStronglyConnected() {
        int V = ListaAdiacenta.size();
        boolean[] visited = new boolean[V];
        // Parcurgem graful ca graf neorientat
        DFS(0, visited, false);
        // Verificăm dacă toate nodurile au fost vizitate
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        // Resetăm vectorul de vizitare
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        // Parcurgem graful ca graf orientat
        DFS(0, visited, true);
        // Verificăm dacă toate nodurile au fost vizitate
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        // Dacă am ajuns aici, înseamnă că graful este quasi-tare conex
        return true;
    }

    // Funcție care parcurge graful în adâncime folosind o stivă
    void DFS(int v, boolean[] visited, boolean directed) {
        // Creăm o stivă și adăugăm nodul sursă
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        // Parcurgem graful până când stiva este goală
        while (!stack.isEmpty()) {
            // Scoatem un nod din stivă și îl marcăm ca vizitat
            v = stack.pop();
            visited[v] = true;
            // Parcurgem toți vecinii nodului curent
            for (int u : ListaAdiacentaNoerientat.get(v)) {
                // Dacă vecinul nu este vizitat, îl adăugăm în stivă
                if (!visited[u]) {
                    stack.push(u);
                }
                // Dacă graful este orientat, adăugăm și muchiile inversate în stivă
                if (directed) {
                    for (int w : ListaAdiacenta.get(u)) {
                        if (w == v && !visited[w]) {
                            stack.push(w);
                        }
                    }
                }
            }
        }
    }




    // Funcție care determină rădăcina arborelui
    int findRoot() {
        int V = ListaAdiacenta.size();
        int rootCount = 0;
        // Vector care reține părintele fiecărui nod
        int[] parent = new int[V];
        // Inițializăm vectorul cu -1, care înseamnă că nodul nu are părinte
        for (int i = 0; i < V; i++) {
            parent[i] = -1;
        }
        // Parcurgem toate muchiile arborelui
        for (int v = 0; v < V; v++) {
            for (int u : ListaAdiacenta.get(v)) {
                // Actualizăm părintele nodului u cu nodul v
                parent[u] = v;
            }
        }

        for (int i = 0; i < V; i++) {
            if (parent[i] == -1) {
                ++rootCount;
            }
        }

        if (rootCount != 1) {
            return -1;
        }

        // Parcurgem vectorul de părinți și căutăm nodul care are valoarea -1
        for (int i = 0; i < V; i++) {
            if (parent[i] == -1) {
                // Acesta este rădăcina arborelui
                return i;
            }
        }
        // Dacă nu am găsit niciun nod cu valoarea -1, înseamnă că graful nu este un arbore
        return -1;
    }


    void printListaAdiacenta()
    {
        for(int i=0;i<ListaAdiacenta.size();i++)
        {
            System.out.print((i+1)+": ");
            for(int j=0;j<ListaAdiacenta.get(i).size();j++)
            {
                System.out.print((ListaAdiacenta.get(i).get(j)+1)+" ");
            }
            System.out.println();
        }
    }
}
