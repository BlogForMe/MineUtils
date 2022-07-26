package com.enjoy.plugin;

import java.io.File;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

class JiaguPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        final JiaguExt jiagu = project.getExtensions().create("jiagu", JiaguExt.class);
        //回调, 在gradle配置完成之后回调，在解析完build.gradle之后回调
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(final Project project) {
                //能够动态的获取到当前(debug/release)的apk文件
                AppExtension android = project.getExtensions().getByType(AppExtension.class);
                // 得到一个集合 [debug,release]
                android.getApplicationVariants().all(new Action<ApplicationVariant>() {

                    @Override
                    public void execute(ApplicationVariant applicationVariant) {
                        //debug/release
                        final String name = applicationVariant.getName();
                        applicationVariant.getOutputs().all(new Action<BaseVariantOutput>() {
                            @Override
                            public void execute(BaseVariantOutput baseVariantOutput) {
                                //Apk文件
                                File outputFile = baseVariantOutput.getOutputFile();
                                project.getTasks().create("jiagu" + name, JiaguTask.class,
                                    outputFile, jiagu);
                            }
                        });
                    }
                });
            }
        });
    }

    //AppExtension android = project.extensions.android
    //        android.applicationVariants.all {
    //    ApplicationVariant variant ->
    //    //对应变体(debug/release)的签名配置
    //    SigningConfig signingConfig = variant.signingConfig
    //    variant.outputs.all {
    //        BaseVariantOutput output->
    //        //输出的apk文件
    //        File apk = output.outputFile
    //        //创建加固任务  jiag  Debug
    //        JiaguTask jiaguTask = project.tasks.create("jiagu${variant.baseName.capitalize()}",
    //        JiaguTask)
    //        jiaguTask.jiagu = jiagu
    //        jiaguTask.signingConfig = signingConfig
    //        jiaguTask.apk = apk
    //    }
}