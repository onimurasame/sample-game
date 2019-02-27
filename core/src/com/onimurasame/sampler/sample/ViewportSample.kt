package com.onimurasame.sampler.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ArrayMap
import com.badlogic.gdx.utils.viewport.*
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.*

class ViewportSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ViewportSample>()

        private const val WORLD_WIDTH = 1080f // world units 1 World Unit = 1 pixel
        private const val WORLD_HEIGHT = 720f // world units
    }

    lateinit var camera: OrthographicCamera
    lateinit var currentViewport: Viewport
    lateinit var batch: SpriteBatch
    lateinit var texture: Texture
    lateinit var font: BitmapFont

    val viewports = ArrayMap<String, Viewport>()
    var currentViewportIndex = -1
    var currentViewportName = ""

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("create()")

        camera = OrthographicCamera()
        batch = SpriteBatch()
        texture = Texture("raw/level-bg.png".toInternalFile())
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())

        createViewports()
        selectNextViewport()

        Gdx.input.inputProcessor = this

    }

    private fun selectNextViewport() {
        currentViewportIndex = (currentViewportIndex + 1) % viewports.size

        currentViewport = viewports.getValueAt(currentViewportIndex)
        resize(Gdx.graphics.width, Gdx.graphics.height)

        currentViewportName = viewports.getKeyAt(currentViewportIndex)

        log.debug("Selected viewport = $currentViewportName")
    }

    private fun createViewports() {
        viewports.put(StretchViewport::class.java.simpleName, StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera))
        viewports.put(FitViewport::class.java.simpleName, FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera))
        viewports.put(FillViewport::class.java.simpleName, FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera))
        viewports.put(ScreenViewport::class.java.simpleName, ScreenViewport(camera))
        viewports.put(ExtendViewport::class.java.simpleName, ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera))
    }

    override fun resize(width: Int, height: Int) {
        currentViewport.update(width, height, true)
    }

    override fun render() {
        clearScreen()
        batch.projectionMatrix = camera.combined

        /*batch.begin()

        draw()

        batch.end()*/

        batch.use { draw() }
    }

    private fun queryInput() {

    }

    private fun draw() {
        batch.draw(texture, 0f, 0f, WORLD_WIDTH, WORLD_HEIGHT)
        font.draw(batch, currentViewportName, 50f, 100f)
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        selectNextViewport()
        return false
    }


    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }


}