import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    int N = 1000000;
    long[]s={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997};
    long[] divs = new long[N+1];
    int[][] a = new int[2][N+1];

    public static void main(String[] args) throws Exception {
        long st = System.currentTimeMillis();
        new Main().run();
        long fin = System.currentTimeMillis();
        System.out.println(fin-st);
    }

    private void run() throws FileNotFoundException {
        divs[1] = 1; divs[2] = 2; divs[3] = 2;
        for (int i = 4; i <=N ; i++) {
            divs[i] = kd(i);
        }
        int count = 0;
        for (int i = 2; i < divs.length; i++) {
            long d = divs[i];
            if (check(d)) {
                count = (count + 1) % 9;
                if (count % 9 == 0) {
                    System.out.println(i);
//                    System.out.println(i + " " + a[0][i] + " " + a[1][i]);
                }
            }
        }
    }

    //TODO СМотреть здесь оптимизация - взять степени множителей из a[0][d] и a[1][d] ?
    boolean check(long d) {
        if (d==-1) return false;
        for (int i = 0; i < s.length; i++) {
            if (d % s[i] == 0) {
                long r = d/s[i];
                int found = Arrays.binarySearch(s,i+1,s.length, r);
                if (found>i) return true;
            }
        }
        return false;
    }

    private long kd(int n) {
        long nd = n;
        int z = 0;
        int t = 0;
        int[] n1 = new int[2];
        int[] k = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            if (nd % s[i] == 0) {
                t++;
                if (t > 2) return -1; // больше 2 нет смысла продолжать
                while (nd % s[i] == 0) {
                    nd /= s[i];
                    k[i]++;
                }
                a[t - 1][n] = k[i];
                n1[t-1] = k[i];
            }
            if (nd==1) break;
        }
        if (nd>1) z = 1;
//        long res = 1;
//        for (int i = 0; i < k.length; i++) {
//            res *= (k[i]+1);
//        }
        int res = (n1[0]+1)*(n1[1]+1);
        return res * (z+1);
    }

}
