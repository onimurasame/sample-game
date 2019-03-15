package com.onimurasame.sampler.common

import com.onimurasame.sampler.sample.*

object SampleInfos {

    private val allSamples = arrayListOf<SampleInfo>(
            sampleBase<ApplicationListenerSample>(),
            sampleBase<GdxGeneratedSample>(),
            sampleBase<InputListeningSample>(),
            sampleBase<InputPollingSample>(),
            sampleBase<ModuleInfoSample>(),
            sampleBase<MultiplexerSample>(),
            sampleBase<ReflectionSample>(),
            sampleBase<OrthographicCameraSample>(),
            sampleBase<ViewportSample>(),
            sampleBase<SpriteBatchSample>(),
            sampleBase<ShapeRendererSample>(),
            sampleBase<BitmapFontSample>(),
            sampleBase<PoolingSample>(),
            sampleBase<AssetManagerSample>()

    )

    fun getSampleNames(): Array<String> = allSamples.associateBy { it.name }.keys.toTypedArray()

    fun find(name: String) = allSamples.find { it.name == name }

}