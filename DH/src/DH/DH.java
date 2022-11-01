package DH;

import java.util.HashSet;
import java.util.Random;

public class DH {
    //判断 n是素数
    boolean isPrime(int n) {
        int prime = 2;   //从模2开始判断
        while(prime < n){
            if(n % prime == 0){
                break;
            } else {
                prime++;
            }
        }
        if(prime >= n){
            return true;
        }
        return false;
    }
    //计算 a，b最大公因子
    int GCD(int a, int b) {
        if (a > b && a % b == 0){
            return b;
        }else {
            return GCD(a, (a % b));
        }

    }
    //计算 b^n mod m .
    Double ExpMod(int b, int n, int m) {
        Double result;
        result = Math.pow(b, n) % m;
        return result;
    }
    //判断 g是模p乘的生成元。
    boolean isPrimeRoot(int g, int p) {
        double n;
        HashSet<Double> hashSet = new HashSet<>();
        for(int j = 1; j < p; j++) {
            n = ExpMod(g, j, p);
            hashSet.add(n);
        }
        if(hashSet.size() == p - 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DH dh = new DH();
        Random random = new Random();
        int prime;
        int g = 0;
        double X, Y, x, y;
        do {
            //随机生成一个素数
            do {
                prime = random.nextInt(100);
            } while ( !dh.isPrime(prime) );
            //得到一个生成元
            for (int i = 0; i < prime; i++) {
                if (dh.isPrimeRoot(i, prime)) {
                    g = i;
                    break;
                }
            }
        } while ( g == 0);
        System.out.println("随机生成素数 P 为 " + prime);
        System.out.println(" P 的一个生成元为 " + g);
        System.out.println();
        //模拟A
        x = random.nextInt(prime);
        System.out.println("A随机生成了 x = " + x);
        X = Math.pow(g, x);
        System.out.println("经计算得 X = " + X);
        System.out.println();
        //模拟B
        y = random.nextInt(prime);
        System.out.println("B随机生成了 y = " + y);
        Y = Math.pow(g, y % prime);
        System.out.println("经计算得 Y = " + Y);
        System.out.println();
        System.out.println(" Y^x = " + Math.pow(Y, x));
        System.out.println(" X^y = " + Math.pow(X, y));
        if (Math.pow(Y, x) == Math.pow(X, y)) {
            System.out.println("算法验证正确。");
        } else {
            System.out.println("算法验证错误。");
        }
    }
}
