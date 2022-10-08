package encryption;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Playfair {

    private char[][] matrix = new char[5][5];
    private String key;

    private int[] search(char c) {
        int[] pos = {5, 5};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        if (pos[0] == 5 && pos[1] == 5) {
            c -= 1;
            return search(c);
        }

        return pos;
    }

    public void encrypt() {

        System.out.print("请输入密钥（只包含英文字母的字符串）: ");
        Scanner scanner = new Scanner(System.in);
        key = scanner.nextLine();
        char[] k = key.toUpperCase().toCharArray();

        char[] alphabet = new char[26];
        for (char c = 'A', i = 0; i < 25; c++, i++) {
            alphabet[i] = c;
            if (alphabet[i] == 'I') {
                c++;
            }
        }

        LinkedHashSet<Character> set = new LinkedHashSet<Character>();
        for (int i = 0; i < k.length; i++) {
            set.add(k[i]);
        }
        for (int i = 0; i < 25; i++) {
            set.add(alphabet[i]);
            if (set.size() == 25) break;
        }
        Character[] array = set.toArray(new Character[set.size()]);
        int l = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = array[l];
                l++;
            }
        }

        System.out.print("请输入明文: ");
        String plaintext = scanner.nextLine();
        char[] plaintext1 = plaintext.replaceAll(" ", "").toUpperCase().toCharArray();
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < plaintext1.length; i++) {
            list.add(plaintext1[i]);
        }
        for (int i = 0; i < plaintext1.length; i = i + 2) {
            if (i + 1 == plaintext1.length) {
                break;
            }
            if (list.get(i) == list.get(i + 1)) {
                list.add(i + 1, 'K');
            }
        }
        if (list.size() % 2 != 0) {
            list.add('K');
        }

        String ciphertext = "";
        System.out.print("Playfair加密密文为: ");
        for (int i = 0; i < list.size(); i = i + 2) {
            char fir = list.get(i);
            char sec = list.get(i + 1);

            int[] pos1 = search(fir);
            int[] pos2 = search(sec);

            int l1 = pos1[0];
            int l2 = pos2[0];
            int r1 = pos1[1];
            int r2 = pos2[1];

            if (l1 == l2) {
                System.out.print(matrix[l1][(r1 + 1) % 5] + "" + matrix[l2][(r2 + 1) % 5] + " ");
                ciphertext = ciphertext + matrix[l1][(r1 + 1) % 5] + matrix[l2][(r2 + 1) % 5];
            } else if (r1 == r2) {
                System.out.print(matrix[(l1 + 1) % 5][r1] + "" + matrix[(l2 + 1) % 5][r2] + " ");
                ciphertext = ciphertext + matrix[(l1 + 1) % 5][r1] + matrix[(l2 + 1) % 5][r2];
            } else {
                System.out.print(matrix[l1][r2] + "" + matrix[l2][r1] + " ");
                ciphertext = ciphertext + matrix[l1][r2] + matrix[l2][r1];
            }
        }
        System.out.println();
        Count count;
        System.out.println("明文字符统计：");
        count = new Count(plaintext);
        System.out.println("密文字符统计：");
        count = new Count(ciphertext);
    }
}
