package com.hex.bigdata.udsp.im;

import com.hex.kafka.KafkaProducerClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Kafka生产者示例
 *
 * @author K0183
 */
public class InputDataThread extends Thread {
    public String fileName;
    private static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat yyyyMMdd_HH_mm_ss = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

    public InputDataThread(String fileName) {
        this.fileName = fileName;
    }

    private KafkaProducerClient kpClient = new KafkaProducerClient("kafka.producer.properties");

    public void run() {
        String msg;

        Socket server = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String message = null;
        S01_FZRMX s01_fzrmx = null;
        try {
            while (true) {
                InputStream input = InputDataThread.class.getClassLoader().getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while ((msg = reader.readLine()) != null) {
                    System.out.println(msg);
                    String[] strs = msg.split("\\|", -1);
                    strs[15] = yyyy_MM_dd_HH_mm_ss.format(new Date());
                    s01_fzrmx = new S01_FZRMX(//
                            strs[0].trim(),//
                            strs[1].trim(),//
                            strs[2].trim(),//
                            strs[3].trim(),//
                            strs[4].trim(),//
                            strs[5].trim(),//
                            strs[6].trim(),//
                            strs[7].trim(),//
                            strs[8].trim(),//
                            strs[9].trim(),//
                            strs[10].trim(),//
                            strs[11].trim(),//
                            strs[12].trim(),//
                            strs[13].trim(),//
                            strs[14].trim(),//
                            strs[15].trim()//
                    );
                    message = s01_fzrmx.toJsonStr();
                    System.out.println("JSON数据===>" + message);
                    kpClient.send(message);
                    Thread.sleep(1000);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println("Input Stop!!!");
    }
}
