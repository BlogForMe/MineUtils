package com.comm.util.openlib.rxretrofit.lambda;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 *  https://github.com/xitu/gold-miner/blob/master/TODO/war-learning-curve-rx-java-2-java-8-stream-android-rxjava2-hell-part4.md
 */
public class LambdaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);




        final List<String> list = new LinkedList<>();
        list.add("H");
        list.add("e");
        list.add("l");
        list.add("l");
        list.add("o");
        list.add(" ");
        list.add("W");
        list.add("o");
        list.add("r");
        list.add("l");
        list.add("d");
        findViewById(R.id.bt_foreach).setOnClickListener(v->{
//            list.stream()
//                    .filter(s-> !s.equals("l"))
//                    .forEach(integer-> Timber.i(" java8    "+integer));

//            Observable.fromIterable(list)
//                    .filter(s-> !s.equals("l"))
//                    .forEach(s-> Timber.i("Android     " + s));
        });


        Integer[] data = {1,2,3,4};
        findViewById(R.id.bt_array_sqr).setOnClickListener(v->{
            Observable.fromArray(data)
                    .map(value->value*value)
                    .map(value->Integer.toString(value))
                    .forEach(value->{
                        Timber.i("fromArray     " + value);
                    });
        });
    }
}
