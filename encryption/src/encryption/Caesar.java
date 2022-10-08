package encryption;

public class Caesar {
    public void encrypt(int key1,String text) {
        char a[] = text.toCharArray();
        for (int i = 0; i < a .length; i++) {
            if (a[i] >= 'a' && 'z' >= a[i]) {
                char miWen = (char) ((int) 'a' + ((int) a[i] - (int) 'a' + key1) % 26);
                System.out.print(miWen);
            } else if (a[i] >= 'A' && a[i] <= 'Z') {
                char miWen = (char) ((int) 'A' + ((int) a[i] - (int) 'A' + key1) % 26);
                System.out.print(miWen);
            } else if (a[i] >= '0' && a[i] <= '9') {
                char miWen = (char) ((int) '0' + ((int) a[i] - (int) '0' + key1) % 10);
                System.out.print(miWen);
            } else {
                System.out.print(a[i]);
            }
        }
    }

    public void jieMi(int key2,String code) {
        char a[] = code.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 'a' && 'z' >= a[i]) {
                int num = ((int) a[i] - (int) 'a' - key2) % 26;
                if(num<0){
                    num += 26;
                }
                char c = (char)(num + 97);
                System.out.print(c);
            } else if (a[i] >= 'A' && a[i] <= 'Z') {
                int num = ((int) a[i] - (int) 'A' - key2) % 26;
                if(num<0){
                    num += 26;
                }
                char yuanWen = (char)(num + 65);
                System.out.print(yuanWen);
            } else if (a[i] >= '0' && a[i] <= '9') {
                int num = ((int) a[i] - (int) '0' - key2) % 10;
                if(num<0){
                    num += 10;
                }
                char yuanWen = (char)(num + 48);
                System.out.print(yuanWen);
            } else {
                System.out.print(a[i]);
            }
        }
    }
}
