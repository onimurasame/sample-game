package com.onimurasame.sampler.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.logger

class ApplicationListenerSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ApplicationListenerSample>()
    }

    private var renderInterrupted = true

    /**
     * Used to initialize game and load resources
     */
    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("create()")
    }

    /**
     * Use to handle new setting of screen side
     */
    override fun resize(width: Int, height: Int) {
        log.debug("resize()")
    }

    /**
     * Used to update and render the game elements called *usually* 60 times per second
     */
    override fun render() {
        if (renderInterrupted) {
            log.debug("render()")
            renderInterrupted = false
        }
    }

    /**
     * Used to save game state when it loses focus, which doesn't involve the actual gameplay
     * being paused unless the developer wants to pause it
     */
    override fun pause() {
        log.debug("pause()")
        renderInterrupted = true
    }

    /**
     * Used to handle the game coming back from being paused and restores games state
     */
    override fun resume() {
        log.debug("resume()")
        renderInterrupted = true
    }


    /**
     * Used to free resources and clean up
     */
    override fun dispose() {
        log.debug("dispose()")
    }

}