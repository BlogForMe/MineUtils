package com.comm.util.socket.mina;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import timber.log.Timber;

public class MinaCustomThread extends Thread {

	private final String writeData;
	private IoSession session = null;
	private IoConnector connector = null;
	public MinaCustomThread(String writeData) {
		this.writeData = writeData;
	}

	@Override
	public void run() {
		super.run();
		// TODO Auto-generated method stub]
		System.out.println("客户端链接开始...");
		connector = new NioSocketConnector();
		// 设置链接超时时间
		connector.setConnectTimeoutMillis(10000);
		connector.getFilterChain().addLast( "logger", new LoggingFilter() );
		connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory(
			StandardCharsets.UTF_8)));

		connector.setHandler(new DeafaultHandler());
		//设置默认连接远程服务器的IP地址和端口
		connector.setDefaultRemoteAddress(new InetSocketAddress("dev.casanubeserver.com",6667));

		//开始连接
		try {
			ConnectFuture future = connector.connect();
			future.awaitUninterruptibly();// 等待连接创建完成
			session = future.getSession();// 获得session
			//判断是否连接服务器成功
			if (session != null && session.isConnected()) {
				session.write(writeData+"\n");
			} else {
				System.out.println("写数据失败");
			}
		} catch (Exception e) {
			System.out.println("客户端链接异常...");
		}

		// 监听客户端是否断线
		connector.addListener(new IoListener() {
			@Override
			public void sessionDestroyed(IoSession arg0) throws Exception {
				// TODO Auto-generated method stub
				super.sessionDestroyed(arg0);
				try {
					int failCount = 0;
					while (true) {
						Thread.sleep(5000);
						System.out.println(((InetSocketAddress) connector.getDefaultRemoteAddress()).getAddress()
								.getHostAddress());
						ConnectFuture future = connector.connect();
						future.awaitUninterruptibly();// 等待连接创建完成
						session = future.getSession();// 获得session
//						if (session != null && session.isConnected()) {
//							session.write("JYT20190100000033\n");
//							break;
//						} else {
//							System.out.println("断线重连失败---->" + failCount + "次");
//						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});

		Timber.i("writeData	"+writeData);
		for (;;){
			try {
				Thread.sleep(5000);
				if (session.isConnected()){
					session.write(writeData+"\n");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



}