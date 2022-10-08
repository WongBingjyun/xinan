package encryption;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int encryption;
        do {
            System.out.println("请选择密码算法：");
            System.out.println("1.Caesar      2.Playfair     3.Hill     4.退出");
            Scanner scanner = new Scanner(System.in);
            encryption = scanner.nextInt();
            switch (encryption) {
                case 1:
                    Caesar caesar = new Caesar();
                    System.out.println("请选择：");
                    System.out.println("1.加密     2.解密");
                    int choose = scanner.nextInt();
                    switch (choose) {
                        case 1:
                            caesar.encrypt();
                            break;
                        case 2:
                            caesar.decrypt();
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    Playfair playfair = new Playfair();
                    playfair.encrypt();
                    break;
                case 3:
                    Hill hill = new Hill();
                    hill.encrypt();
                    break;
                default:
                    break;
            }
        } while (encryption != 4);
    }
}
