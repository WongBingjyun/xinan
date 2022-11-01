package RSA;

import java.util.Scanner;

public class RSA {
    //判断是否是素数
    public static boolean isPrime(long n) {
        boolean b = true;
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                b = false;
                break;
            } else
                b = true;
        }
        return b;
    }

    //计算欧拉数
    public static long Euler(long p, long q) {
        return (p - 1) * (q - 1);
    }

    //欧几里得算法求两数的最大公因数---a>b
    static long gcd(long a, long b) {
        if (a < b) {
            long t = a;
            a = b;
            b = t;
        }
        if (a % b == 0) return b;
        else return gcd(b, a % b);
    }

    //求模反元素d（私钥）
    public static long Key(long e, long euler) {
        long key = 1;
        while ((key * e) % euler != 1) {
            key++;
        }
        return key;
    }

    //递归求n次方
    public static long power(long a, long n) {
        long r = 1;
        if (n == 0) r = 1;
        else {
            r = a * power(a, n - 1);
        }
        return r;
    }

    public static long prime(long n) {
        long p = 0;
        for (int i = 3; i < n/2; i = i + 2) {
            if (n % i == 0) {
                if (isPrime(i) && isPrime(n/i)) {
                    p = i;
                }
            }
        }
        return p;
    }

    //加密
    public static long encryption(long msg, long e, long n) {
        return power(msg, e) % n;
    }

    //解密
    public static long decryption(long c, long key, long n) {
        return power(c, key) % n;
    }

    public static void main(String[] args) {
        System.out.println("--------RSA--------");
        System.out.println("请选择需求： 1. 获取私有密钥   2. 加密   3. 解密");
        Scanner scanner = new Scanner(System.in);
        long n, e, p, euler, d, m, c;
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                System.out.print("请输入n:");
                n = scanner.nextLong();
                System.out.print("请输入e:");
                e = scanner.nextLong();
                p = prime(n);
                //计算p、q的欧拉数
                euler = Euler(p, n/p);
                System.out.println("Euler(p,q) = " + euler);
                //求出模反元素（私钥）
                d = Key(e, euler);
                System.out.println("d = " + d);
                break;

            case 2:
                System.out.print("请输入n:");
                n = scanner.nextLong();
                System.out.print("请输入e:");
                e = scanner.nextLong();
                p = prime(n);
                //计算p、q的欧拉数
                euler = Euler(p, n/p);
                System.out.println("Euler(p,q) = " + euler);
                //求出模反元素（私钥）
                d = Key(e, euler);
                System.out.println("d = " + d);
                //加密过程
                System.out.print("输入明文：");
                m = scanner.nextLong();
                c = encryption(m, e, p * (n/p));//密文
                System.out.println("加密后的密文：" + c);
                break;

            case 3:
                System.out.print("请输入n:");
                n = scanner.nextLong();
                System.out.print("请输入e:");
                e = scanner.nextLong();
                p = prime(n);
                //计算p、q的欧拉数
                euler = Euler(p, n/p);
                System.out.println("Euler(p,q) = " + euler);
                //求出模反元素（私钥）
                d = Key(e, euler);
                System.out.println("d = " + d);
                System.out.print("输入密文：");
                c = scanner.nextLong();
                m = decryption(c, d, p * (n/p));
                System.out.println("解密后的明文：" + m);
                break;
            default:
                break;
        }
    }
}

