public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        MyHashMap<String,Integer> maps = new MyHashMap<>();
        int count = 0;
        for (int i =0;i<points.length;i++){
            int[] temp = points[i];
            for (int j = 0 ; j<points.length ; j++){
                if (j!=i){
                int[] tempp = points[j];
                double x = (temp[0]-tempp[0])*(temp[0]-tempp[0]);
                double y = (temp[1]-tempp[1])*(temp[1]-tempp[1]);
                double edge = Math.sqrt(x+y);
                System.out.println("First "+temp[0] +" Second "+ tempp[0]+" Result "+ " x " +x+" y "+ y +" edge "+edge);
                String edgeS = Double.toString(edge);
                if (maps.containsKey(edgeS)){
                    maps.put(edgeS, maps.get(edgeS)+1);}
                else{
                        maps.put(edgeS,1);
                    }
              }
            }
            for (int value:maps.values()){
                System.out.println(" Value "+value);
                if (value>1){
                    count = count+(Boomerang.factorial(value)/2/Boomerang.factorial((value)-2));
                }
            }
            maps.clear();
        }
        return count*2;
    }

    private static int getSquaredDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx * dx + dy * dy;

    }

    public static int factorial(int n){
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = result * i;
            }
            return result;
    }
}
