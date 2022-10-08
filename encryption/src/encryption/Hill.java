package encryption;

import java.util.ArrayList;
import java.util.Scanner;

public class Hill {

    private int m;

    public void encrypt()
    {
        String ciphertext = "";
        System.out.print("请输入加密密钥矩阵的阶数:");
        Scanner scan = new Scanner(System.in);
        m = scan.nextInt();
        int[][] k = new int[m][m];
        System.out.println("请输入密钥矩阵的值:");
        Scanner scan1 = new Scanner(System.in);
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < m; j++)
            {
                System.out.print("请输入 k[" + i + "][" + j + "]: ");
                k[i][j] = scan1.nextInt();
            }
        }

        System.out.print("请输入明文: ");
        Scanner scan2 = new Scanner(System.in);
        String plaintext = scan2.nextLine();
        char[] plaintextCharArray = plaintext.replaceAll(" ", "").toUpperCase().toCharArray();

        ArrayList<Character> list = new ArrayList<Character>();
        for(int i = 0; i < plaintextCharArray.length; i++) list.add(plaintextCharArray[i]);

        while(list.size() % m != 0) list.add('x');

        System.out.print("Hill加密密文为: ");
        for(int i = 0; i < list.size(); i = i + m)
        {
            char[] p = new char[m];
            char[] c = new char[m];
            for(int j = 0; j < m; j++) {
                p[j] = list.get(i + j);
            }
            for(int j = 0; j < m; j++) {
                int t;
                int sum = 0;
                for(int l = 0; l < m; l++){
                    t = k[j][l] * (p[l] - 65);
                    sum = t + sum;
                }
                c[j] = (char) (sum % 26 + 65);
                ciphertext += c[j];
            }
        }
        char[] a = ciphertext.toCharArray();
        for(int i = 0; i < ciphertext.length(); i++) {
            System.out.print(a[i]);
            if((i + 1) % m == 0){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
