package com.comm.util.audio.tts;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SharedData {
    //语速
    public static float voice_speed=0.5f;
    //音调
    public static float voice_pitch=1.0f;
    //
    public static Map<String,Locale> languageList=new HashMap<String, Locale>();
}
