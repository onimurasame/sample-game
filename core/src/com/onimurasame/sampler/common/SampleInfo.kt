package com.onimurasame.sampler.common

class SampleInfo(val clazz: Class<out SampleBase> ) {

    val name: String = clazz.simpleName
}

inline fun <reified T : SampleBase> sampleBase() : SampleInfo = SampleInfo(T::class.java)