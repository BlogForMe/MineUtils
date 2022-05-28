package com.comm.util.ui;


import java.util.Random;

public class Test {

    public static void main(String[] args) {
//        if(args.length != 2)
//        {
//            System.out.println("Usage: java test userid  wavefile");
//            //test 1001  wav/upload.wav
//            return;
//        }
//
//        String userid = "1570582225" /*args[0]*/; //1001
//        String uploadfile = "C:\\JYZN\\tts.txt" /*args[1]*/; //wav/upload.wav
////        String uploadfile = "语音转文字" /*args[1]*/; //wav/upload.wav
//
//        List<String> list  = new ArrayList<String>();  //创建一个空list.
//        list.add(uploadfile);  //要上传的文件名,可自行添加.
//
//        try {
//            //定义数据分隔符
//            String TWO_HYPHENS = "--";
//            String BOUNDARY = "*****";
//            String END = "\r\n";
//            String CHARSET = "UTF-8";
//
//            //上传语音
////            URL url = new URL("http://192.168.1.223:3334/dotctts");
//            URL url = new URL("http://125.77.202.194:3334/dotctts");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//            conn.setRequestProperty("Charsert", "UTF-8");
//            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
//            conn.setReadTimeout(300000);
//
//            String token = "1234567890";
//            //System.out.println(token);
//
//            HashMap<String, String> param = null;
//            param = new HashMap<String, String>();
//            param.put("userid", userid); //必选字段
//            param.put("token", token); //必选字段
//
//            OutputStream out = new DataOutputStream(conn.getOutputStream());
//            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
//
//            StringBuilder sb = new StringBuilder();
//            for (Map.Entry<String, String> entry : param.entrySet()) {
//                sb.append(TWO_HYPHENS);
//                sb.append(BOUNDARY);
//                sb.append(END);
//                sb.append("Content-Disposition: form-data; name=\""
//                        + entry.getKey() + "\"" + END);
//                sb.append("Content-Type: text/plain; charset=" + CHARSET + END);
//                sb.append("Content-Transfer-Encoding: 8bit" + END);
//                sb.append(END);
//                sb.append(entry.getValue());
//                sb.append(END);
//            }
//
//            int leng = list.size();
//            for(int i=0;i<leng;i++){
//                String fname = list.get(i);
//                File file = new File(fname);
//
//                sb.append("--");
//                sb.append(BOUNDARY);
//                sb.append("\r\n");
//                sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName() + "\"\r\n");
//                sb.append("Content-Type:application/octet-stream\r\n\r\n");
//
//                byte[] data = sb.toString().getBytes();
//                out.write(data);
//                DataInputStream in = new DataInputStream(new FileInputStream(file));
//                int bytes = 0;
//                byte[] bufferOut = new byte[1024];
//                while ((bytes = in.read(bufferOut)) != -1) {
//                    out.write(bufferOut, 0, bytes);
//                }
//                out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
//                in.close();
//            }
//            out.write(end_data);
//            out.flush();
//            out.close();
//
//            // 定义BufferedReader输入流来读取URL的响应
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//        } catch (Exception e) {
//            System.out.println("发送POST请求出现异常！" + e);
//            e.printStackTrace();
//        }


        while (true){
            try {
                Thread.sleep(2000);
                int randomTxt = new Random().nextInt(100);
                System.out.println(randomTxt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
