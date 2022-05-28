package com.comm.util

import org.junit.Assert
import org.junit.Test
import kotlin.math.PI
import kotlin.math.sin

/**
 * 三角函数计算
 */
class TrigonometricTest {

    @Test
    fun  cacalulteTest(){
        println(PI)
        println(sin(PI/6))
    }

    @Test
    fun forTest(){
//        var i=0
//        for (angel in 135 .. 405 step 7.5){
//            println(angel)
//            i++;
//        }
//        println(i)


        var i=0
        var  angel=135.0
        while (angel<405){
            println(angel)
            angel+=7.5
            i++
        }
        println(i)
        Assert.assertEquals(36,i)
//        Assert.assertEquals(35,i)
    }

}