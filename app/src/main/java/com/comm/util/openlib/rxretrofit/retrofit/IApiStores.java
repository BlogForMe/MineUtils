package com.comm.util.openlib.rxretrofit.retrofit;

import java.util.List;
import java.util.Map;

import com.comm.util.bean.BaseCount;
import com.comm.util.bean.ConfigEnv;
import com.comm.util.bean.SigleField;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author : John
 * @date : 2018/7/15
 */
public interface IApiStores {


    @POST("csn_hospital_APIServer/RestService/Call/BloodSugarFoot/MeasureDataSearch")
    Observable<ResponseBody> measureDataSearch(@Body RequestBody requestBody);

    @POST("dotctts")
    Observable<ResponseBody> getTTs(@QueryMap Map<String,Object> map);

    @POST("/casanube-config/Api/Call/ConfigRouter/GetAppConfig")
    Observable<BaseCount<SigleField>> getAppConfig(@Body RequestBody requestBody);   @POST("/casanube-config/Api/Call/ConfigRouter/GetAppConfig")
    Observable<Response<BaseCount<SigleField>>> getAppConfigSJ(@Body RequestBody requestBody);

    @POST("/casanube-config/Api/Call/DeployUnit/GetUnitList")
    Observable<BaseCount<List<ConfigEnv>>> getUnitList();

    @POST("Measure/TemperatureInfoInsert")
    Observable<BaseCount> temperatureInfoInsert(@Body RequestBody requestBody);

    @GET("user/{userid}/share_articles/{pageid}/json")
    Call<List<SharedListBean>> getSharedList(@Path("userid")int it,@Path("pageid") int pageid);
}
