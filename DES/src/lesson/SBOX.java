package lesson;

public class SBOX {
    // S盒，每个S盒Si是4x16的置换表，6位 -> 4位
    final static int[][][] S_BOX ={
            {
                    {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                    {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                    {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                    {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
            },
            {
                    {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                    {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                    {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                    {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}
            },
            {
                    {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                    {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                    {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                    {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}
            },
            {
                    {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                    {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                    {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                    {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
            },
            {
                    {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                    {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                    {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                    {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
            },
            {
                    {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                    {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
                    {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                    {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
            },
            {
                    {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                    {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                    {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                    {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
            },
            {
                    {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                    {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                    {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                    {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
            }
    };

    public void sbox() {
        for(int output = 0; output < 16; output++) {
            System.out.print("输出为 " + Integer.toBinaryString(output) + " 的可能输入: ");
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 16; j++){
                    if(S_BOX[0][i][j] == output){
                        Binary(i, j);
                    }
                }
            }
            System.out.println();
        }
    }

    public void Binary(int a, int b) {
        if(a == 0) {
            if(b == 0) {
                System.out.print("000000  ");
            }
            if(b == 1) {
                System.out.print("000010  ");
            }
            if(b == 2) {
                System.out.print("000100  ");
            }
            if(b == 3) {
                System.out.print("000110  ");
            }
            if(b == 4) {
                System.out.print("001000  ");
            }
            if(b == 5) {
                System.out.print("001010  ");
            }
            if(b == 6) {
                System.out.print("001100  ");
            }
            if(b == 7) {
                System.out.print("001110  ");
            }
            if(b == 8) {
                System.out.print("010000  ");
            }
            if(b == 9) {
                System.out.print("010010  ");
            }
            if(b == 10) {
                System.out.print("010100  ");
            }
            if(b == 11) {
                System.out.print("010110  ");
            }
            if(b == 12) {
                System.out.print("011000  ");
            }
            if(b == 13) {
                System.out.print("011010  ");
            }
            if(b == 14) {
                System.out.print("011100  ");
            }
            if(b == 15) {
                System.out.print("011110  ");
            }
        }
        if(a == 1) {
            if(b == 0) {
                System.out.print("000001  ");
            }
            if(b == 1) {
                System.out.print("000011  ");
            }
            if(b == 2) {
                System.out.print("000101  ");
            }
            if(b == 3) {
                System.out.print("000111  ");
            }
            if(b == 4) {
                System.out.print("001001  ");
            }
            if(b == 5) {
                System.out.print("001011  ");
            }
            if(b == 6) {
                System.out.print("001101  ");
            }
            if(b == 7) {
                System.out.print("001111  ");
            }
            if(b == 8) {
                System.out.print("010001  ");
            }
            if(b == 9) {
                System.out.print("010011  ");
            }
            if(b == 10) {
                System.out.print("010101  ");
            }
            if(b == 11) {
                System.out.print("010111  ");
            }
            if(b == 12) {
                System.out.print("011001  ");
            }
            if(b == 13) {
                System.out.print("011011  ");
            }
            if(b == 14) {
                System.out.print("011101  ");
            }
            if(b == 15) {
                System.out.print("011111  ");
            }
        }
        if(a == 2) {
            if(b == 0) {
                System.out.print("100000  ");
            }
            if(b == 1) {
                System.out.print("100010  ");
            }
            if(b == 2) {
                System.out.print("100100  ");
            }
            if(b == 3) {
                System.out.print("100110  ");
            }
            if(b == 4) {
                System.out.print("101000  ");
            }
            if(b == 5) {
                System.out.print("101010  ");
            }
            if(b == 6) {
                System.out.print("101100  ");
            }
            if(b == 7) {
                System.out.print("101110  ");
            }
            if(b == 8) {
                System.out.print("110000  ");
            }
            if(b == 9) {
                System.out.print("110010  ");
            }
            if(b == 10) {
                System.out.print("110100  ");
            }
            if(b == 11) {
                System.out.print("110110  ");
            }
            if(b == 12) {
                System.out.print("111000  ");
            }
            if(b == 13) {
                System.out.print("111010  ");
            }
            if(b == 14) {
                System.out.print("111100  ");
            }
            if(b == 15) {
                System.out.print("111110  ");
            }
        }
        if(a == 3) {
            if(b == 0) {
                System.out.print("100001  ");
            }
            if(b == 1) {
                System.out.print("100011  ");
            }
            if(b == 2) {
                System.out.print("100101  ");
            }
            if(b == 3) {
                System.out.print("100111  ");
            }
            if(b == 4) {
                System.out.print("101001  ");
            }
            if(b == 5) {
                System.out.print("101011  ");
            }
            if(b == 6) {
                System.out.print("101101  ");
            }
            if(b == 7) {
                System.out.print("101111  ");
            }
            if(b == 8) {
                System.out.print("110001  ");
            }
            if(b == 9) {
                System.out.print("110011  ");
            }
            if(b == 10) {
                System.out.print("110101  ");
            }
            if(b == 11) {
                System.out.print("110111  ");
            }
            if(b == 12) {
                System.out.print("111001  ");
            }
            if(b == 13) {
                System.out.print("111011  ");
            }
            if(b == 14) {
                System.out.print("111101  ");
            }
            if(b == 15) {
                System.out.print("111111  ");
            }
        }
    }


    public static void main(String[] args) throws Exception {
        SBOX des = new SBOX();
        des.sbox();
    }
}

