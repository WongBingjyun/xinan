package Xor;

import java.util.Scanner;

public class Xor {
    private byte[] bytes;
    private int l;

    public Xor(String key) {
        bytes = key.getBytes();
        l = bytes.length;
    }

    public String encrypt(String plaintext) {
        byte[] by = plaintext.getBytes();
        byte[] by1 = new byte[by.length];
        for (int i = 0; i < by.length; i++) {
            by1[i] = (byte) (by[i] ^ bytes[i % l]);
        }
        return new String(by1);
    }

    public String decrypt(String cipherText) {
        byte[] by = cipherText.getBytes();
        byte[] by1 = new byte[by.length];
        for (int i = 0; i < by.length; i++) {
            by1[i] = (byte) (by[i] ^ bytes[i % l]);
        }
        return new String(by1);
    }

    public static void main(String[] args) {
        Xor crypto;
        String key;
        int choose;
        do {
            System.out.println("请选择操作：");
            System.out.println("1.加密    2.解密    3.退出");
            Scanner sc = new Scanner(System.in);
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("请输入明文：");
                    String plaintext = sc.next();
                    System.out.println("请输入密钥：");
                    key = sc.next();

                    crypto = new Xor(key);
                    String cipherText1 = crypto.encrypt(plaintext);
                    System.out.println("异或加密后密文如下：");
                    System.out.println(cipherText1);
                    break;
                case 2:
                    System.out.println("请输入密文：");
                    String cipherText = sc.next();
                    System.out.println("请输入密钥：");
                    key = sc.next();

                    crypto = new Xor(key);
                    String plaintext1 = crypto.decrypt(cipherText);
                    System.out.println("异或解密后密文如下：");
                    System.out.println(plaintext1);
                    break;
                default:
                    break;
            }
        } while (choose!=3);
    }
}