package com.onimurasame.sampler.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.logger

class ModuleInfoSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ModuleInfoSample>()
    }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("App = ${Gdx.app}")
        log.debug("Audio = ${Gdx.audio}")
        log.debug("Input = ${Gdx.input}")
        log.debug("Files = ${Gdx.files}")
        log.debug("Net = ${Gdx.net}")
        log.debug("Graphics = ${Gdx.graphics}")

    }

}