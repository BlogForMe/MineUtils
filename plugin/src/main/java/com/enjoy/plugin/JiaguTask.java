package com.enjoy.plugin;

import java.io.File;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

/**
 * ClassName:      JiaguTask
 * Description:    Description
 * Author:         zh
 * CreateDate:     2022/7/26 2:47 PM
 * UpdateUser:     zh
 * UpdateDate:     2022/7/26 2:47 PM
 * UpdateRemark:   Modify the description
 */
public class JiaguTask extends DefaultTask {
    private File apk;
    private JiaguExt jiaguExt;

    @Inject
    public JiaguTask(File apk, JiaguExt jiaguExt) {
        this.apk = apk;
        this.jiaguExt = jiaguExt;
        setGroup("jiagu");
    }

    public JiaguTask(File apk) {
        this.apk = apk;
    }

    public JiaguTask(JiaguExt jiaguExt) {
        this.jiaguExt = jiaguExt;
    }

    @TaskAction
    public void a() {
        String userName = jiaguExt.userName;
        String password = jiaguExt.password;
        System.out.println("username " + userName + " password " + password);
    }
}
