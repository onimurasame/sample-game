package com.onimurasame.sampler.common

import com.onimurasame.sampler.sample.*

object SampleInfos {

    private val allSamples = arrayListOf<SampleInfo>(
            SampleInfo(ApplicationListenerSample::class.java),
            SampleInfo(GdxGeneratedSample::class.java),
            SampleInfo(InputListeningSample::class.java),
            SampleInfo(InputPollingSample::class.java),
            SampleInfo(ModuleInfoSample::class.java),
            SampleInfo(MultiplexerSample::class.java),
            SampleInfo(ReflectionSample::class.java),
            SampleInfo(OrthographicCameraSample::class.java),
            SampleInfo(ViewportSample::class.java),
            SampleInfo(SpriteBatchSample::class.java),
            SampleInfo(ShapeRendererSample::class.java),
            SampleInfo(BitmapFontSample::class.java)

    )

    fun getSampleNames(): Array<String> = allSamples.associateBy { it.name }.keys.toTypedArray()

    fun find(name: String) = allSamples.find { it.name == name }

}