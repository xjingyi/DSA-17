
public class EditDistance {

    public static int minEditDist(String a, String b) {

        return editting(a,b,-1,-1);
    }

    public static int editting(String a, String b,int m, int n) {
        if (m == a.length()-1){
            return b.length()-1-n;
        }
        if (n == b.length()-1){
            return a.length()-1-m;
        }
        if (a.charAt(m+1) == b.charAt(n+1)){
            return editting(a,  b, m+1 , n+1);
        }
        return (1+Math.min(editting(a,  b, m+1,n+1),Math.min(editting(a,  b, m+1,n),editting(a,  b, m,n+1))));
    }

}
/*
code source from geeksforgeeks*/