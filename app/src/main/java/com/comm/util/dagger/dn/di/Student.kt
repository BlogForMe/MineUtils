/**
 * ClassName:      Sudent
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/9/25 6:56 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/9/25 6:56 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di

class Student constructor(val name: String?) {
    constructor() : this(null)
    override fun toString(): String {
        return "Student(name=$name)"
    }
}