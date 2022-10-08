package encryption;

public class Count {


    public Count(String str) {
        char chs[] = str.toCharArray();
        int count = 0;
        for (char ch = 0; ch < 127; ch++) {
            count = 0;
            for (int i = 0; i < chs.length; i++) {
                if (ch == chs[i])
                    count++;
            }
            if (count != 0) {
                System.out.print(ch + ":" + count + "个" + "  ");
            }
        }
        System.out.println();
    }
}
