package lesson;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {
    public static void main(String[] args) {
        //密钥
        byte[] des_key = new byte[8];

        //明文
        byte[] des_input = new byte[8];

        System.out.println("The default plaintext is 1111111111111111");
        System.out.println("The default key is 1111111111111111");

        //默认明文为1111111111111111
        //默认密钥为1111111111111111
        for (int i = 0; i < 8; i++) {
            des_key[i] = 0x11;
            des_input[i] = 0x11;
        }

        //加密后的密文为F40379AB9E0EC533
        byte[] des_output = encrypt(des_key, des_input);

        //输出加密结果
        System.out.println("The cipher text is " + byteArrayToHex(des_output));

        System.out.println("------------------------------------------");

        BitsArray key = new BitsArray(des_key);
        BitsArray input = new BitsArray(des_input);
        BitsArray output = new BitsArray(des_output);


        //分别计算改变1~64位时的平均改变位数并输出
        for (int bits = 1; bits <= 64; bits++) {
            int count = 0; //记录总改变的位数的数量

            //总共进行十次测试
            for (int j = 0; j < 10; j++) {
                //利用Set中元素不能重复的特性随机得到要修改位所在的位置
                HashSet<Integer> hs = new HashSet<Integer>();
                for (int i = 0; i < bits; i++) {
                    while (hs.size() == i) hs.add((int) (Math.random() * 64));
                }

                //将元素放进ArrayList以供调用
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i : hs) list.add(i);

                //克隆一份原明文的备份
                BitsArray inputCopy = input.clone();

                //对明文进行指定位数的修改
                for (int i = 0; i < bits; i++) {
                    int pos = list.get(i); //要修改的位的位置

                    if (input.toString().charAt(pos) == '0') input.setOne(pos);
                    else if (input.toString().charAt(pos) == '1') input.setZero(pos);
                }

                //将修改后的明文输出为byte数组
                des_input = input.toByteArray();

                //使用修改后的明文和原来的密钥进行加密运算，得到新的密文byte数组
                byte[] des_newOutput = encrypt(des_key, des_input);

                //将新的密文byte数组转化为位串对象
                BitsArray newOutput = new BitsArray(des_newOutput);

                //与原来输出的密文的位串进行异或操作
                newOutput.xor(output);

                //计算异或之后位串中1的个数，即为改变的位数
                count += newOutput.OnesCount();

                //重置已被修改的明文为原明文
                input = inputCopy;
            }

            System.out.println("明文改变位数 = " + bits + ". 输出密文位数平均改变 " + ((double) count / 10));
        }

        for (int bits = 1; bits <= 64; bits++) {
            int count = 0; //记录总改变的位数的数量

            //总共进行十次测试
            for (int j = 0; j < 10; j++) {
                //利用Set中元素不能重复的特性随机得到要修改位所在的位置
                HashSet<Integer> hs = new HashSet<Integer>();
                for (int i = 0; i < bits; i++) {
                    while (hs.size() == i) hs.add((int) (Math.random() * 64));
                }

                //将元素放进ArrayList以供调用
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i : hs) list.add(i);

                //克隆一份原密钥的备份
                BitsArray keyCopy = key.clone();

                //对密钥进行指定位数的修改
                for (int i = 0; i < bits; i++) {
                    int pos = list.get(i); //要修改的位的位置

                    if (key.toString().charAt(pos) == '0') key.setOne(pos);
                    else if (key.toString().charAt(pos) == '1') key.setZero(pos);
                }

                //将修改后的密钥输出为byte数组
                des_key = key.toByteArray();

                //使用修改后的密钥和原来的明文进行加密运算，得到新的密文byte数组
                byte[] des_newOutput = encrypt(des_key, des_input);

                //将新的密文byte数组转化为位串对象
                BitsArray newOutput = new BitsArray(des_newOutput);

                //与原来输出的密文的位串进行异或操作
                newOutput.xor(output);

                //计算异或之后位串中1的个数，即为改变的位数
                count += newOutput.OnesCount();

                //重置已被修改的密钥为原密钥
                key = keyCopy;
            }

            System.out.println("密钥改变位数 = " + bits + ". 输出密文位数平均改变 " + ((double) count / 10));
        }

    }

    public static String byteArrayToHex(byte[] bs) {
        StringBuilder res = new StringBuilder();

        for (byte b : bs) res.append(String.format("%02X", (b & 0xff)));

        return res.toString();
    }

    public static byte[] encrypt(byte[] des_key, byte[] des_input) {
        //DES加密算法
        Cipher des = null;

        //加密后的输出
        byte[] des_output = null;

        //创建DES密钥
        SecretKey secretKey = new SecretKeySpec(des_key, "DES");

        //创建DES密码算法对象，指定电码本模式和无填充方式
        try {
            des = Cipher.getInstance("DES/ECB/NoPadding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        //初始化DES算法
        try {
            des.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        //加密
        try {
            des_output = des.doFinal(des_input);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return des_output;
    }
}

class BitsArray {
    private String str;

    BitsArray(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) sb.append("0");
        str = sb.toString();
    }

    BitsArray(byte[] bs) {
        fromByteArray(bs);
    }

    int length() {
        return str.length();
    }

    void fromByteArray(byte[] bs) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < bs.length; i++) {
            int length = Long.toString(bs[i] & 0xff, 2).length();
            String str = "";
            if (length < 8) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < 8 - length; j++) sb.append("0");
                str = sb.toString();
            }
            result.append(str + Long.toString(bs[i] & 0xff, 2));
        }
        str = result.toString();
    }

    byte[] toByteArray() {
        String[] temp = new String[8];
        int pos = 0;
        for (int i = 0; i < 8; i++) {
            temp[i] = str.substring(pos, pos + 8);
            pos += 8;
        }

        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) b[i] = Long.valueOf(temp[i], 2).byteValue();

        return b;
    }

    void xor(BitsArray other) {
        String otherStr = other.toString();
        char[] cs = str.toCharArray();
        for (int i = 0; i < otherStr.length(); i++) {
            if (str.charAt(i) == otherStr.charAt(i)) cs[i] = '0';
            else cs[i] = '1';
        }
        str = Arrays.toString(cs).replaceAll("[\\[\\]\\s,]", "");
    }

    int OnesCount() {
        int count = 0;
        for (int i = 0; i < str.length(); i++) if (str.charAt(i) == '1') count++;

        return count;
    }

    @Override
    protected BitsArray clone() {
        byte[] bs = toByteArray();
        BitsArray bitsArray = new BitsArray(bs);

        return bitsArray;
    }

    void setOne(int index) {
        char[] cs = str.toCharArray();
        cs[index] = '1';
        str = Arrays.toString(cs).replaceAll("[\\[\\]\\s,]", "");
    }

    void setZero(int index) {
        char[] cs = str.toCharArray();
        cs[index] = '0';
        str = Arrays.toString(cs).replaceAll("[\\[\\]\\s,]", "");
    }

    void set(int index, int value) {
        char[] cs = str.toCharArray();
        cs[index] = (char) ('0' + value);
        str = Arrays.toString(cs).replaceAll("[\\[\\]\\s,]", "");
    }

    @Override
    public String toString() {
        return str.toString();
    }
}

