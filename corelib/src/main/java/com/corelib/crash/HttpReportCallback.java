package com.corelib.crash;

import java.io.File;

/**
 * @author: charles
 * @date: 2016-06-17 16:32
 */
public interface HttpReportCallback {

    void uploadException2remote(File file);
}
