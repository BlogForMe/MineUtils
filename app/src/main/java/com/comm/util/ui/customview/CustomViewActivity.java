package com.comm.util.ui.customview;

import java.util.ArrayList;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.ui.customview.gcssloop.PathView;
import com.comm.util.ui.customview.gcssloop.PieData;
import com.comm.util.ui.customview.gcssloop.PieView;

public class CustomViewActivity extends AppCompatActivity {

    private PieView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
//        Electrocardiogram electrocardiogram
//                = (Electrocardiogram)findViewById(R.id.electrocardiogram);
//        electrocardiogram.startDraw();

        /**
         *http://www.gcssloop.com/customview/Canvas_Convert
         */
//        view = new PieView(this);  // view 块状图
//        initCustom();
//        PathFinish view = new PathFinish(this);

//        CanvasView view = new CanvasView(this);


//        PictureView view = new PictureView(this);


//        CheckView view = new CheckView(this);

//        MyTextView view = new MyTextView(this);

        PathView view = new PathView(this);

//        RadarView view = new RadarView(this);  //雷达

//        Bezier view = new Bezier(this);

//        Bezier2 view = new Bezier2(this);

//        Bezier3 view = new Bezier3(this);

        setContentView(view);

//        String datxt = "[{\"drug_name\":\"感冒消炎片\",\"drug_num\":\"48片\",\"frequency\":\"每日1次\",\"num\":\"1包\",\"pharmacy_type\":\"皮下注射\"}]";

//        String datxt = "[{\"drug_name\":\"参茸卫生丸\",\"drug_num\":\"9g*10丸/盒\",\"frequency\":\"每日1次\",\"num\":\"1粒\",\"pharmacy_type\":\"皮下注射\"}]";
//        List<RecipeBean> reList = new Gson().fromJson(datxt, new TypeToken<List<RecipeBean>>() {
//        }.getType());

//        List<RecipeBean> reList = null;
//        try {
//            reList = RecipeBean.analysisChild(datxt);
//            System.out.println(reList);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    private void initCustom() {
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        view.setData(datas);
    }
}
