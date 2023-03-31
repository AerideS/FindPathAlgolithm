class Dijkstra {
    private int n;
    private int[][] weight;
    private String[] saveRoute;
    private String[] vertex = {"진주", "사천", "산청", "마산", "창원"};

    public Dijkstra(int n, int[][] matrix) {
        super();
        this.n = n;
        this.weight = matrix;
        saveRoute = new String[n];
    }

    public void showMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(weight[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int stringToInt(String s) {
        int x = 0;
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == s) x = i;
        }
        return x;
    }

    public void algorithm(String a, String b) {
        boolean[] visited = new boolean[n];
        int distance[] = new int[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        int x = stringToInt(a);
        distance[x] = 0;
        visited[x] = true;
        saveRoute[x] = vertex[x];

        for (int i = 0; i < n; i++) {
            if (!visited[i] && weight[x][i] != 0) {
                distance[i] = weight[x][i];
                saveRoute[i] = vertex[x];
            }
        }
        for(int i = 0; i < n; i++) { System.out.print(distance[i] + " ");}
        System.out.println();

        for(int i = 0; i < n-1; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minVertex = -1;
            for(int j = 0; j < n; j++) {
                if(!visited[j] && distance[j]!=Integer.MAX_VALUE) {
                    if(distance[j] < minDistance) {
                        minDistance = distance[j];
                        minVertex = j;
                    }
                }
            }
            visited[minVertex] = true;
            for(int j = 0; j < n; j++) {
                if(!visited[j] && weight[minVertex][j] != Integer.MAX_VALUE) {
                    if(distance[j] > distance[minVertex]+weight[minVertex][j]) {
                        distance[j] = distance[minVertex]+weight[minVertex][j];
                        saveRoute[j] = vertex[minVertex];
                    }
                }
            }
        }

        for(int i = 0; i < n; i++ ) {
            System.out.println("시작 도시 " + a + "부터 꼭지점 " + vertex[i] + "까지의 거리 : " + distance[i]);
        }

        System.out.println("======================================");
/*
        for(int i = 0; i < n; i++) {
            String route = "";
            System.out.println("시작 도시 " + a + "부터 도시 " + vertex[i] + "까지의 경로");
            int index = i;
            while (true) {
                if ( saveRoute[index] == vertex[index]) break;
                route += " " + saveRoute[index];
                index = stringToInt(saveRoute[index]);
            }
            StringBuilder sb = new StringBuilder(route);
            System.out.println(sb.reverse() + " " + vertex[i]);

            System.out.println();

        }
*/
        String route = "";
        String prev = b;
        while(true) {
            route += vertex[stringToInt(prev)];
            route += " ";
            prev = saveRoute[stringToInt(prev)];
            if(prev == a) {
                route += prev;
                break;
            }
        }
        String[] strArray = route.split(" ");

        System.out.println("도시 " + a + "부터 도시 " + b + " 의 경로");
        for(int i = strArray.length-1; i>=0; i--){
            System.out.print(strArray[i] + " ");
        }

    }
}




public class Main {

    public static void main(String[] args) {

        int[][] matrixGN =new int[][] {
                {0, 24-12, 39, 56, Integer.MAX_VALUE},
                {24-12, 0, Integer.MAX_VALUE, 74-37, 85-38},
                {39, Integer.MAX_VALUE, 0, 85, 96},
                {56, 74-37, 85, 0, 14},
                {Integer.MAX_VALUE, 85-38, 96, 14, 0}
        };

        Dijkstra d = new Dijkstra(5, matrixGN);
        d.algorithm("진주", "창원");
    }
}