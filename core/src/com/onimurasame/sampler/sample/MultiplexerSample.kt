package com.onimurasame.sampler.sample

import com.badlogic.gdx.*
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.logger

class MultiplexerSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<MultiplexerSample>()
    }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        val firstInputProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                log.debug("first keyDown keycode = $keycode")
                return true
            }

            override fun keyUp(keycode: Int): Boolean {
                log.debug("first keyUp keycode = $keycode")
                return false
            }
        }

        val secondInputProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                log.debug("first keyDown keycode = $keycode")
                return true
            }

            override fun keyUp(keycode: Int): Boolean {
                log.debug("first keyUp keycode = $keycode")
                return false
            }
        }

        val multiplexer = InputMultiplexer(firstInputProcessor, secondInputProcessor)

        Gdx.input.inputProcessor = multiplexer

    }

}