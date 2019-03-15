package com.onimurasame.sampler.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Logger
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.clearScreen
import com.onimurasame.sampler.util.logger
import com.onimurasame.sampler.util.use

class TextureAtlasSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<TextureAtlasSample>()

        private const val BACKGROUND_BLUE = "background-blue"
        private const val GREEN_CIRCLE = "circle-green"
        private const val RED_CIRCLE = "circle-red"
        private const val FONT = "oswald-32.fnt"
        private const val ATLAS = "images/atlasSample.sample"
    }


    private lateinit var assetManager : AssetManager
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var background: TextureRegion
    private lateinit var greenCircle: TextureRegion
    private lateinit var redCircle: TextureRegion
    private lateinit var font: BitmapFont

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        assetManager = AssetManager()
        assetManager.logger.level = Logger.DEBUG

        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()

        assetManager.load(ATLAS, TextureAtlas::class.java)
        assetManager.load(FONT, BitmapFont::class.java)

        //Blocks until all resources are loaded into memory
        assetManager.finishLoading()


        val atlas: TextureAtlas = assetManager[ATLAS]
        background = atlas.findRegion(BACKGROUND_BLUE)
        greenCircle = atlas.findRegion(GREEN_CIRCLE)
        redCircle = atlas.findRegion(RED_CIRCLE)
        font = assetManager[FONT]
    }

    override fun render() {
        clearScreen()
        viewport.apply()
        batch.projectionMatrix = camera.combined

        batch.use {
            batch.draw(background, 0f, 0f)
            batch.draw(greenCircle, 50f, 50f)
            batch.draw(redCircle, 200f, 200f)
            font.draw(batch, "Texture Atlas Sample", 500f, 50f)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()

    }
}