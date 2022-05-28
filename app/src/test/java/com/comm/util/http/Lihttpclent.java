package com.comm.util.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Lihttpclent {
    private final String innersite;
    private final StringBuilder mbuild = new StringBuilder();

    public Lihttpclent(String site) {
        this.innersite = site;
    }


    public String excute() {

        Thread getthread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket mSocket = new Socket(
                            InetAddress.getByName(innersite),
                            80);
                    BufferedReader br = new BufferedReader(new
                            InputStreamReader(
                            mSocket.getInputStream(), StandardCharsets.UTF_8));
                    BufferedWriter bf = new BufferedWriter((new OutputStreamWriter(
                            mSocket.getOutputStream())));
                    StringBuffer requestHeader = new StringBuffer();
                    requestHeader
                            .append("GET " + "/"
                                    + " HTTP/1.1\n")
                            .append("HOST:" + innersite + "\n")
                            //.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n")
                            .append("Accept-Encoding:gzip, deflate, sdch\n")
                            .append("Accept-Language:zh-CN,zh;q=0.8\n")
                            .append("Cache-Control:no-cache\n")
                            .append("User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36\n")
                            .append("Encoding:UTF-8\n")
                            .append("Connection:keep-alive" + "\n")
                            .append("\n");

                    bf.write(requestHeader.toString());
                    bf.flush();
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        mbuild.append(line);
                        mbuild.append("\r\n");
                        // Log.i(TAG,line);
                    }
                    System.out.println(mbuild.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        getthread.start();
        try {
            getthread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mbuild.toString();
    }
}