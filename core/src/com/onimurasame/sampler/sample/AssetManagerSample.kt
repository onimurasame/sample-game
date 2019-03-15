package com.onimurasame.sampler.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Logger
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.clearScreen
import com.onimurasame.sampler.util.logger
import com.onimurasame.sampler.util.use

class AssetManagerSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<AssetManagerSample>()

        private const val BACKGROUND_BLUE = "raw/background-blue.png"
        private const val GREEN_CIRCLE = "raw/circle-green.png"
        private const val RED_CIRCLE = "raw/circle-red.png"
        private const val FONT = "fonts/oswald-32.fnt"
    }


    private lateinit var assetManager : AssetManager
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var background: Texture
    private lateinit var greenCircle: Texture
    private lateinit var redCircle: Texture
    private lateinit var font: BitmapFont

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        assetManager = AssetManager()
        assetManager.logger.level = Logger.DEBUG

        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()

        assetManager.load(BACKGROUND_BLUE, Texture::class.java)
        assetManager.load(GREEN_CIRCLE, Texture::class.java)
        assetManager.load(RED_CIRCLE, Texture::class.java)
        assetManager.load(FONT, BitmapFont::class.java)

        //Blocks until all resources are loaded into memory
        assetManager.finishLoading()

        background = assetManager[BACKGROUND_BLUE]
        greenCircle = assetManager[GREEN_CIRCLE]
        redCircle = assetManager[RED_CIRCLE]
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
            font.draw(batch, "Asset Manager Sample", 500f, 50f)
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