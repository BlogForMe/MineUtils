package com.comm.util.openlib.rxretrofit.xiangx;

import com.comm.util.openlib.rxretrofit.xiangx.bean.ResponseResult;
import com.comm.util.openlib.rxretrofit.xiangx.bean.SuccessBean;
import io.reactivex.Observable;


public class LoginEngine {

    public static Observable<ResponseResult> login(String name, String pwd){

        ResponseResult responseResult = new ResponseResult();
        if ("john".equals(name)&&"1234".equals(pwd)){
            SuccessBean successBean = new SuccessBean();
            successBean.setId(45674565);
            successBean.setName("john登录成功");
            responseResult.setData(successBean);
            responseResult.setCode(200);
            responseResult.setMessage("john登录成功");
        }else {
            responseResult.setData(null);
            responseResult.setCode(404);
            responseResult.setMessage("error");
        }
        return Observable.just(responseResult);
    }
}
