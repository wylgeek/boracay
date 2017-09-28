package com.hex.bigdata.udsp.elsearch;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class ElSearchRestTest {
    static RestClient restClient = RestClient.builder(
            new HttpHost("192.168.125.101", 9200,"http" ),
            new HttpHost("192.168.125.102", 9200, "http"),
            new HttpHost("192.168.125.103", 9200, "http")).build();

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";

    private static final String CONTEXT_TYPE_TEXT_JSON = "text/json";

    public static void main(String[] args) throws IOException {
        //verifyClusterExist();
        FileToES("C:\\Users\\tomnic\\Desktop\\subline\\learn\\elasticsearch\\elasticsearch_data.txt","UTF-8");
    }

    /**
     * 单条插入
     */
    public static void FileToES(){
        NStringEntity stringEntity = new NStringEntity("{\"acct_no\" : \"1000002\",\"acct_name\" : \"王熊\",\"age\" : 28,\"acct_about\" : \"I love to go bike\",\"acct_zcrq\":\"2016-06-22\",\"acct_zcsj\":\"2016-06-22 09:21:22\",\"acct_interests\": [ \"sports\", \"music\" ]}", ContentType.APPLICATION_JSON);
        Response response = null;
        try {
            response = restClient.performRequest("PUT","/megacorp/employee/2", Collections.<String, String>emptyMap(),stringEntity);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 从文件中读取插入
     * @param filePath
     * @param charset
     */
    public static void FileToES(String filePath,String charset){
        File theFile = new File(filePath);
        LineIterator iterator = null;
        int i = 4;
        String endpoint="/megacorp/employee/";
        try {
            iterator = FileUtils.lineIterator(theFile, charset);
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                NStringEntity stringEntity = new NStringEntity(line,ContentType.APPLICATION_JSON);
                Response response = restClient.performRequest("PUT",endpoint+i,Collections.<String, String>emptyMap(),stringEntity);
                System.out.println(EntityUtils.toString(response.getEntity()));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (iterator!=null){
                LineIterator.closeQuietly(iterator);
                try {
                    restClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 验证es集群是否搭建成功
     */
    public static void verifyClusterExist(){
        Response response = null;
        try {
            response = restClient.performRequest("GET", "/", Collections.singletonMap("pretty", "true"));
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
