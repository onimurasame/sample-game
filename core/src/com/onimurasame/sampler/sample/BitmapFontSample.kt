package com.onimurasame.sampler.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.clearScreen
import com.onimurasame.sampler.util.logger
import com.onimurasame.sampler.util.toInternalFile
import com.onimurasame.sampler.util.use

class BitmapFontSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<BitmapFontSample>()

        private const val WIDTH = 1080f // World units, 1 un == 1 pixel
        private const val HEIGHT = 720f
    }

    lateinit var camera: OrthographicCamera
    lateinit var viewPort: Viewport
    lateinit var batch: SpriteBatch
    lateinit var effectFont: BitmapFont
    lateinit var uiFont: BitmapFont

    private val layout = GlyphLayout()

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("create()")

        camera = OrthographicCamera()
        viewPort = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()
        effectFont = BitmapFont("fonts/effect_font_32.fnt".toInternalFile())
        uiFont = BitmapFont("fonts/ui_font_32.fnt".toInternalFile())
        uiFont.data.markupEnabled = true
    }

    override fun resize(width: Int, height: Int) {
        viewPort.update(width, height, true)
    }

    override fun render() {
        clearScreen()

        batch.projectionMatrix = camera.combined

        batch.use { draw() }
    }

    private fun draw() {
        val text1 = "USING BITMAP FONT"
        effectFont.draw(batch, text1, 20f, HEIGHT, 100f, Align.right, true)

        val text2 = "[#FF0000]BITMAP [GREEN]FONTS [YELLOW]ARE [BLUE]KEWL"
        layout.setText(uiFont, text2)

        uiFont.draw(batch, layout, (WIDTH - layout.width) / 2f, (HEIGHT - layout.height) / 2f)
    }


    override fun dispose() {
        batch.dispose()
        effectFont.dispose()
        uiFont.dispose()
    }


}