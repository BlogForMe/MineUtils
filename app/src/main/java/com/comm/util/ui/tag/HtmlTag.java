package com.comm.util.ui.tag;

import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Spanned;
import android.util.ArrayMap;
import androidx.annotation.ColorInt;
import org.xml.sax.Attributes;

public abstract class HtmlTag {

    private static final Map<String, Integer> sColorNameMap;

    static {
        sColorNameMap = new ArrayMap<String, Integer>();
        sColorNameMap.put("black", Color.BLACK);
        sColorNameMap.put("darkgray", Color.DKGRAY);
        sColorNameMap.put("gray", Color.GRAY);
        sColorNameMap.put("lightgray", Color.LTGRAY);
        sColorNameMap.put("white", Color.WHITE);
        sColorNameMap.put("red", Color.RED);
        sColorNameMap.put("green", Color.GREEN);
        sColorNameMap.put("blue", Color.BLUE);
        sColorNameMap.put("yellow", Color.YELLOW);
        sColorNameMap.put("cyan", Color.CYAN);
        sColorNameMap.put("magenta", Color.MAGENTA);
        sColorNameMap.put("aqua", 0xFF00FFFF);
        sColorNameMap.put("fuchsia", 0xFFFF00FF);
        sColorNameMap.put("darkgrey", Color.DKGRAY);
        sColorNameMap.put("grey", Color.GRAY);
        sColorNameMap.put("lightgrey", Color.LTGRAY);
        sColorNameMap.put("lime", 0xFF00FF00);
        sColorNameMap.put("maroon", 0xFF800000);
        sColorNameMap.put("navy", 0xFF000080);
        sColorNameMap.put("olive", 0xFF808000);
        sColorNameMap.put("purple", 0xFF800080);
        sColorNameMap.put("silver", 0xFFC0C0C0);
        sColorNameMap.put("teal", 0xFF008080);
        sColorNameMap.put("white", Color.WHITE);
        sColorNameMap.put("transparent", Color.TRANSPARENT);

    }

    private final Context context;

    public HtmlTag(Context context) {
        this.context = context;
    }

    @ColorInt
    public static   int getHtmlColor(String colorString){

        if(sColorNameMap.containsKey(colorString.toLowerCase())){
            Integer colorInt = sColorNameMap.get(colorString);
            if(colorInt!=null) return colorInt;
        }

        return parseHtmlColor(colorString.toLowerCase());
    }

    @ColorInt
    public static int parseHtmlColor( String colorString) {

        if (colorString.charAt(0) == '#') {
            if(colorString.length()==4){
                StringBuilder sb = new StringBuilder("#");
                for (int i=1;i<colorString.length();i++){
                    char c = colorString.charAt(i);
                    sb.append(c).append(c);
                }
                colorString  = sb.toString();
            }
            long color = Long.parseLong(colorString.substring(1), 16);
            if (colorString.length() == 7) {
                // Set the alpha value
                color |= 0x00000000ff000000;
            } else if (colorString.length() == 9) {

                int alpha = Integer.parseInt(colorString.substring(1,3),16) ;
                int red = Integer.parseInt(colorString.substring(3,5),16);
                int green = Integer.parseInt(colorString.substring(5,7),16);
                int blue = Integer.parseInt(colorString.substring(7,8),16);
                color = Color.argb(alpha,red,green,blue);
            }else{
                throw new IllegalArgumentException("Unknown color");
            }
            return (int)color;
        }
        else if(colorString.startsWith("rgb(") || colorString.startsWith("rgba(") && colorString.endsWith(")"))
        {
            colorString = colorString.substring(colorString.indexOf("("),colorString.indexOf(")"));
            colorString = colorString.replaceAll(" ","");
            String[] colorArray = colorString.split(",");
            if(colorArray.length==3){
                return Color.argb(255,Integer.parseInt(colorArray[0]),Integer.parseInt(colorArray[1]),Integer.parseInt(colorArray[2]));
            }
            else if (colorArray.length==4){
                return Color.argb(Integer.parseInt(colorArray[3]),Integer.parseInt(colorArray[0]),Integer.parseInt(colorArray[1]),Integer.parseInt(colorArray[2]));
            }

        }
        throw new IllegalArgumentException("Unknown color");
    }

    public static <T> T getLast(Spanned text, Class<T> kind) {

        T[] objs = text.getSpans(0, text.length(), kind);
        if (objs.length == 0) {
            return null;
        } else {
            return objs[objs.length - 1];
        }
    }

    public Context getContext() {
        return context;
    }

    public abstract void startHandleTag(Editable text, Attributes attributes);  //开始解析
    public abstract void endHandleTag(Editable text);  //结束解析


}
