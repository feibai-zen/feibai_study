package com.feibai.study.demos.good_practice;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个Id自增起存在的问题：进程重启后，REQUEST_ID会重新1开始递增
 */
public class IdGenerator {

    private static final AtomicInteger REQUEST_ID = new AtomicInteger(1);
    private static final String POD_NAME = System.getenv("HOSTNAME");

    public String getAuthId() {
        return getRequestId("AUTHENTICATE");
    }

    public String getOnDemandMatrixVolId() {
        return getRequestId("ON_DEMAND_MATRIX_VOL");
    }

    public static long getUniqueId() {

        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    private String getRequestId(String context) {
        return String.format("%s-%s-%s", context, REQUEST_ID.getAndIncrement(), POD_NAME);
    }

}
