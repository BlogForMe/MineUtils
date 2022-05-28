package com.comm.util.socket.mina;

import org.apache.mina.core.session.IoSession;

/**
 * @author : John
 * @date : 2018/11/3
 */
public class SessionManager {
    private static SessionManager mInstance = null;


    private IoSession mSession;

    public static SessionManager getInstance() {
        if (mInstance == null) {
            synchronized (SessionManager.class) {
                if (mInstance == null) {
                    mInstance = new SessionManager();
                }
            }
        }
        return mInstance;
    }

    public void setSession(IoSession mSession) {
        this.mSession = mSession;
    }

    public void writeToServer(Object msg) {
        if (mSession != null) {
            mSession.write(msg);
        }
    }

    public void closeSession() {
        if (mSession != null) {
            mSession.closeOnFlush();
        }
    }

    public void removeSession() {
        this.mSession = null;
    }
}
