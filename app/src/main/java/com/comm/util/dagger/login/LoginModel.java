package com.comm.util.dagger.login;

import javax.inject.Inject;

/**
 * Created by A on 2018/1/24.
 *   https://github.com/jessieeeee/DaggerDemo
 */


public class LoginModel implements ILoginModel {

    @Inject
    public LoginModel() {
    }

    @Override
    public boolean login(String username, String password) {
        return "admin".equals(username) && "123456".equals(password);
    }

    @Override
    public boolean checkUserName(String username) {
        return "admin".equals(username);
    }

    @Override
    public boolean checkPassword(String password) {
        return "123456".equals(password);
    }
}
