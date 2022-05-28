package com.comm.util.dagger.login;

/**
 * Created by A on 2018/1/24.
 */

public interface ILoginModel {
    boolean login(String username, String password);
    boolean checkUserName(String username);
    boolean checkPassword(String password);
}
