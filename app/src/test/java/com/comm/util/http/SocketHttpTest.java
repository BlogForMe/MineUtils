package com.comm.util.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import org.junit.Test;

public class SocketHttpTest {
    final String cUrl = "http://api.caiyunapp.com/v2.5/dHBDzW0QKYTPZBmP/weather.json?adcode=330106";
    final String weatherUrl = "http://restapi.amap.com/v3/weather/weatherInfo?city=%E9%95%BF%E6%B2%99&key=13cb58f5884f9749287abbead9c658f2";


    @Test
    public void testLittleClient() {
        new Lihttpclent(cUrl).excute();
    }

    @Test
    public void testGetHttp() throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(cUrl);
                System.out.println("协议是 " + url.getProtocol());
                System.out.println("文件名是 " + url.getFile());
                System.out.println("主机是 " + url.getHost());
                System.out.println("路径是 " + url.getPath());
                System.out.println();

                StringBuffer protocol = new StringBuffer();
                //请求行
                protocol.append("GET ");
                protocol.append(url.getPath());
                protocol.append("?");
                protocol.append(url.getQuery());
                protocol.append(" ");
                protocol.append("HTTP/1.1");
                protocol.append("\r\n");

                // http请求头
                protocol.append("Host:");
                protocol.append(url.getHost());
                protocol.append("\r\n");

                //空行
                protocol.append("\r\n");


                //post请求体 get没有
                System.out.println("发送的http报文: \n" + protocol);

                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(url.getHost(), 80));

                //获得输入输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                bufferedWriter.write(protocol.toString());
                bufferedWriter.flush();

                StringBuilder stringBuilder = new StringBuilder();


                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line)
                            .append("\r\n");

                }
                System.out.println(stringBuilder);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        thread.start();
        thread.join();

    }


    @Test
    public void testPostHttp() {

        try {
            URL url = new URL(cUrl);
            System.out.println("协议是 " + url.getProtocol());
            System.out.println("文件名是 " + url.getFile());
            System.out.println("主机是 " + url.getHost());
            System.out.println("路径是 " + url.getPath());
            System.out.println("参数 : " + url.getQuery());
            StringBuffer protocol = new StringBuffer();
            //请求行
            protocol.append("POST ");
            protocol.append(url.getPath());
            protocol.append(" ");
            protocol.append("HTTP/1.1");
            protocol.append("\r\n");

            // http请求头
            protocol.append("Host:");
            protocol.append(url.getHost());
            protocol.append("\r\n");

            protocol.append("Content-Length: 60\r\n");
            protocol.append("Content-Type: application/x-www-form-urlencoded\r\n");


            //空行
            protocol.append("\r\n"); // 必须用空行分隔请求体


            //post请求体 get没有
//                protocol.append("city="+ URLEncoder.encode("长沙","UTF-8") +"&key=13cb58f5884f9749287abbead9c658f2");
            protocol.append(url.getQuery());
            System.out.println("发送的http报文: \n" + protocol);

            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(url.getHost(), 80));

            //获得输入输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bufferedWriter.write(protocol.toString());
            bufferedWriter.flush();

            StringBuilder stringBuilder = new StringBuilder();


            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line)
                        .append("\r\n");

            }
            System.out.println(stringBuilder);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
