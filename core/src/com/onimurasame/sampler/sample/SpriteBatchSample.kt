package com.onimurasame.sampler.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.clearScreen
import com.onimurasame.sampler.util.logger
import com.onimurasame.sampler.util.toInternalFile
import com.onimurasame.sampler.util.use

class SpriteBatchSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<SpriteBatchSample>()
    }

    lateinit var camera: OrthographicCamera
    lateinit var viewPort: Viewport
    lateinit var batch: SpriteBatch
    lateinit var texture: Texture

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("create()")

        camera = OrthographicCamera()
        viewPort = FitViewport(10.8f, 7.2f, camera)
        batch = SpriteBatch()
        texture = Texture("raw/character.png".toInternalFile())
    }

    override fun resize(width: Int, height: Int) {
        viewPort.update(width, height, true)
    }

    override fun render() {
        clearScreen()

        batch.projectionMatrix = camera.combined

        /*batch.begin()

        draw()

        batch.end()*/
        batch.use { draw() }
    }

    private fun draw() {
        val width = 1f
        val height = 1f

        batch.draw(texture,
                1f, 1f,
                width /2f, height / 2f,
                width, height,
                1f, 1f,
                0f,
                0,0,
                texture.width, texture.height,
                false, false)

        batch.draw(texture,
                4f, 2f,
                width /2f, height / 2f,
                width, height,
                2f, 2f,
                0f,
                0,0,
                texture.width, texture.height,
                false, false)

        val oldColor = Color.WHITE
        batch.color = Color.GREEN

        batch.draw(texture,
                8f, 1f,
                width /2f, height / 2f,
                width, height,
                2f, 2f,
                0f,
                0,0,
                texture.width, texture.height,
                false, false)

        batch.color = oldColor
    }


    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }


}