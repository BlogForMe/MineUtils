package com.comm.util.bean;

/**
 * @author : John
 * @date : 2018/7/19
 */
public class BaseMeta {

    /**
     * meta : {"describe":"系统操作成功","statusCode":"0"}
     */

    private MetaBean meta;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public static class MetaBean {
        /**
         * describe : 系统操作成功
         * statusCode : 0
         */

        private String describe;
        private int statusCode;

        public String getDescribe() {
            return describe;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }
}
