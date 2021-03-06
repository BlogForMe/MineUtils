package com.comm.util.socket.mina;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import android.content.Context;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import timber.log.Timber;

public class ConnectionManager {
    public static final String BROADCAST_ACTION = " com.jonzhou.mineutils.socket.mina";
    public static String MESSAGE = "message";
    private final WeakReference<Context> mContext;
    private ConnectionConfig mConfig;
    private NioSocketConnector mConnector;
    private IoSession mIoSession;
    private InetSocketAddress mAddress;


    public ConnectionManager(ConnectionConfig config) {
        this.mConfig = config;
        this.mContext = new WeakReference<Context>(config.getContext());
        init();
    }

    private void init() {
        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
        mConnector = new NioSocketConnector();
        mConnector.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());
//        mConnector.getFilterChain().addLast("logging", new LoggingFilter());
        ObjectSerializationCodecFactory objectSize = new ObjectSerializationCodecFactory();
        objectSize.setDecoderMaxObjectSize(Integer.MAX_VALUE);
        objectSize.setEncoderMaxObjectSize(Integer.MAX_VALUE);
//        mConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(objectSize));
        mConnector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory(
            StandardCharsets.UTF_8)));

        mConnector.setHandler(new DeafaultHandler(mConfig.getContext()));
        mConnector.setDefaultRemoteAddress(mAddress);
        Timber.i("ConnectionThread ??????");


//        NioSocketConnector mConnector = new NioSocketConnector();
        //??????????????????????????????
//        mConnector.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new FrameCodecFactory()));
        //???????????????
        KeepAliveFilter heartFilter = new KeepAliveFilter(new HeartBeatMessageFactory());
        //??? 5 ???????????????????????????
        heartFilter.setRequestInterval(60);
        //????????????????????? 10s
        heartFilter.setRequestTimeout(60);
        // ??????????????????
//        mConnector.getFilterChain().addLast("heartbeat", heartFilter);
        Timber.i("======????????????");
        mConnector.addListener(new IoListener() {
            @Override
            public void sessionDestroyed(IoSession ioSession) throws Exception {
                super.sessionDestroyed(ioSession);
                if (mConnector!=null){
                    Timber.e("sessionDestroyed");
                    reConnect();
                }
            }
        });
    }

    private void reConnect(){
        try {
            for (; ; ) {
                Thread.sleep(60000);
                 if (connect()){
                    break;
                 }
            }
        } catch (InterruptedException ex) {
            Timber.e("???????????????????????????,10??????????????????:" + ex.getMessage());
            reConnect();
        }
    }

    /**
     * ?????????????????????????????????
     *
     * @return
     */
    public boolean connect() {
        Timber.i(" connect socket????????????");
        try {
            ConnectFuture future = mConnector.connect();
            future.awaitUninterruptibly();
            mIoSession = future.getSession();
            if (mIoSession.isConnected()) {
                Timber.i("????????????[" + mConnector.getDefaultRemoteAddress().getHostName() + ":" + mConnector.getDefaultRemoteAddress().getPort() + "]??????");
            }
            SessionManager.getInstance().setSession(mIoSession);
            Thread.sleep(20000);
        } catch (Exception e) {
            Timber.e(" connect socket???????????? ????????????");
            return false;
        }
        return mIoSession != null;
    }

    /**
     * ????????????
     */
    public void disConnect() {
        mConnector.dispose();
        mConnector = null;
        mIoSession = null;
        mAddress = null;
        mConfig = null;
    }

    private static class DeafaultHandler extends IoHandlerAdapter {
        private final Context mContext;

        public DeafaultHandler(Context mContext) {
            this.mContext = mContext;
        }

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
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
            //????????????session??????????????????session manager??????,????????????????????????????????????
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
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
}
