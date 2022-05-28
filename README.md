Android常用内容


日志过滤
^(?!.*(eglMakeCurrent|OpenGLRenderer|ARouter|glUtilsParamSize|CubicBezierInterpolator)).*$


 //日志去时间工具
  FileReader fis = new FileReader("/Users/john/Desktop/IMG/RAW.txt");
        BufferedReader bufferedReader = new BufferedReader(fis);
        String line = null;
        while ((line=bufferedReader.readLine()) != null) {
            String[] rawData = line.split("com.comm.util");
            if (rawData.length>1){
                System.out.println(rawData[1]);
            }

      }