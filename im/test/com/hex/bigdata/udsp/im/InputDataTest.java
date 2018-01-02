package com.hex.bigdata.udsp.im;

public class InputDataTest {

    public static void main(String[] args) {
        InputDataThread thread1 = new InputDataThread("S01_FZRMX_20160630.TXT");
        thread1.start();
    }

}
