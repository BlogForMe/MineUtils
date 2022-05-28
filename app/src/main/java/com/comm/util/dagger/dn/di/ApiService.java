/**
 * ClassName:      ApiService
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/8/29 7:25 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/8/29 7:25 PM
 * UpdateRemark:   Modify the description
 */

package com.comm.util.dagger.dn.di;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/user/info")
    Call<String> requestInfo();
}
