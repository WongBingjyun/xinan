package encryption;

import java.util.Scanner;

public class Caesar {
    public void encrypt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入明文：");
        String text = scanner.nextLine();
        System.out.print("请输入密钥(数字)：");
        int key = scanner.nextInt();
        System.out.print("密文为：");
        char plaintext[] = text.toCharArray();
        char ciphertext;
        String ciphertext1 = "";
        for (int i = 0; i < plaintext.length; i++) {
            if (plaintext[i] >= 'a' && 'z' >= plaintext[i]) {
                ciphertext = (char) ((int) 'a' + ((int) plaintext[i] - (int) 'a' + key) % 26);
                System.out.print(ciphertext);
                ciphertext1 += ciphertext;
            } else if (plaintext[i] >= 'A' && plaintext[i] <= 'Z') {
                ciphertext = (char) ((int) 'A' + ((int) plaintext[i] - (int) 'A' + key) % 26);
                System.out.print(ciphertext);
                ciphertext1 += ciphertext;
            } else if (plaintext[i] >= '0' && plaintext[i] <= '9') {
                ciphertext = (char) ((int) '0' + ((int) plaintext[i] - (int) '0' + key) % 10);
                System.out.print(ciphertext);
                ciphertext1 += ciphertext;
            } else {
                System.out.print(plaintext[i]);
                ciphertext1 += plaintext[i];
            }
        }
        System.out.println();
        Count count;
        System.out.println("明文字符统计：");
        count = new Count(text);
        System.out.println("密文字符统计：");
        count = new Count(ciphertext1);
    }

    public void decrypt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入密文：");
        String text = scanner.nextLine();
        System.out.print("请输入密钥(数字)：");
        int key = scanner.nextInt();
        System.out.print("明文为：");
        char ciphertext[] = text.toCharArray();
        char plaintext;
        for (int i = 0; i < ciphertext.length; i++) {
            if (ciphertext[i] >= 'a' && 'z' >= ciphertext[i]) {
                int num = ((int) ciphertext[i] - (int) 'a' - key) % 26;
                if(num<0){
                    num += 26;
                }
                plaintext = (char)(num + 97);
                System.out.print(plaintext);
            } else if (ciphertext[i] >= 'A' && ciphertext[i] <= 'Z') {
                int num = ((int) ciphertext[i] - (int) 'A' - key) % 26;
                if(num<0){
                    num += 26;
                }
                plaintext = (char)(num + 65);
                System.out.print(plaintext);
            } else if (ciphertext[i] >= '0' && ciphertext[i] <= '9') {
                int num = ((int) ciphertext[i] - (int) '0' - key) % 10;
                if(num<0){
                    num += 10;
                }
                plaintext = (char)(num + 48);
                System.out.print(plaintext);
            } else {
                System.out.print(ciphertext[i]);
            }
        }
        System.out.println();
    }
}
