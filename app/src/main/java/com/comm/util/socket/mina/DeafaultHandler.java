package com.comm.util.socket.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import timber.log.Timber;

public   class DeafaultHandler extends IoHandlerAdapter {
//    private Context mContext;
//
//    public DeafaultHandler(Context mContext) {
//        this.mContext = mContext;
//    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        Timber.e("exceptionCaught   " + cause);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        Timber.i("messageSent() " + message);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        //将我们的session保存到我们的session manager类中,从而可以发送消息到服务器
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        Timber.i("sessionClosed()  ");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        Timber.i("messageReceived()"+message);
//            if (mContext != null) {
//                Intent intent = new Intent(BROADCAST_ACTION);
//                intent.putExtra(MESSAGE, String.valueOf(message));
//                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
//            }
    }
}