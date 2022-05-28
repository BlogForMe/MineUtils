package com.comm.util.socket.mina;

import android.content.Context;

/**
 * @author : John
 * @date : 2018/11/3
 */
public class ConnectionConfig {
    private Context context;
    private String ip;
    private int port;
    private int readBufferSize;
    private long connectionTimeout;
    private String sn;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getSn() {
        return sn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public void setReadBufferSize(int readBufferSize) {
        this.readBufferSize = readBufferSize;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public static class Builder {
        private final Context context;
        private String ip = "10.0.0.7";
        private int port = 6667;
        private int readBufferSize = 10240;
        private long connectionTimeout = 10000;
        private String sn;

        public Builder(Context context) {
            this.context = context;
        }

        public String getIp() {
            return ip;
        }

        public Builder setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder setSn(String sn) {
            this.sn = sn;
            return this;
        }

        public int getPort() {
            return port;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public int getReadBufferSize() {
            return readBufferSize;
        }

        public Builder setReadBufferSize(int readBufferSize) {
            this.readBufferSize = readBufferSize;
            return this;
        }

        public long getConnectionTimeout() {
            return connectionTimeout;
        }

        public Builder setConnectionTimeout(long connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public void applyConfig(ConnectionConfig config) {
            config.context = this.context;
            config.ip = this.ip;
            config.port = this.port;
            config.readBufferSize = this.readBufferSize;
            config.connectionTimeout = this.connectionTimeout;
            config.sn = this.sn;
        }

        public ConnectionConfig builder() {
            ConnectionConfig config = new ConnectionConfig();
            applyConfig(config);
            return config;
        }
    }
}
