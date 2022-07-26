package com.great.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project

//https://www.bilibili.com/video/BV1dp4y1e7W4?p=4
class JiaGuPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val jiagu = project.extensions.create("jiagu", JiaGuExt::class.java)

        project.afterEvaluate(object : Action<Project> {
            override fun execute(project: Project) {
                project.extensions.getByName("android")
                //能够动态的获取当前的(debug/release)的apk文件
                val android = project.extensions.getByType(AppExtension::class.java)
                android.applicationVariants.all(object : Action<ApplicationVariant> {
                    override fun execute(applicationVariant: ApplicationVariant) {
                        val name = applicationVariant.name
                        applicationVariant.outputs
                    }
                })
            }
        })
    }
}