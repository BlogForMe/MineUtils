package com.comm.util.dagger.demo1;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class A01SimpleActivity extends AppCompatActivity {


//    @Inject
//    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a01_simple);
//        DaggerA01SimpleComponent.builder()
//                .a01SimpleModule(new A01SimpleModule(this))
//                .build().inject(this);
    }


    public void btStudent(View v) {

//        String stu = student.toString();

//        Timber.d("输出"+stu);
    }
}
