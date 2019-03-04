package com.onimurasame.sampler.sample

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.clearScreen
import com.onimurasame.sampler.util.logger

class ShapeRendererSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ShapeRendererSample>()

        private const val WORLD_WIDTH = 40f
        private const val WORLD_HEIGHT = 20f
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer: ShapeRenderer

    private var drawGrid = true
    private var drawCircles = true
    private var drawRectangles = true
    private var drawPoints = true

    override fun create() {
        camera = OrthographicCamera()
        viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        renderer = ShapeRenderer()

        Gdx.input.inputProcessor = this
    }

    override fun render() {
        clearScreen()
        renderer.projectionMatrix = camera.combined

        if (drawGrid) {
            drawGrid()
        }

        if (drawCircles) {
            drawCircles()
        }

        if (drawRectangles) {
            drawRectangles()
        }

        if (drawPoints) {
            drawPoints()
        }

    }

    private fun drawGrid() {
        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.color = Color.WHITE

        val worldWidth = WORLD_WIDTH.toInt()
        val worldHeight = WORLD_HEIGHT.toInt()

        // Horizontal Lines

        for (y in -worldHeight until worldHeight) {
            renderer.line(-worldHeight.toFloat(), y.toFloat(), worldWidth.toFloat(), y.toFloat())
        }

        for (x in -worldWidth until worldWidth) {
            renderer.line(x.toFloat(), -worldWidth.toFloat(), x.toFloat(), worldWidth.toFloat())
        }

        renderer.end()

        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.rectLine(0f, -worldWidth.toFloat(), 0f, worldWidth.toFloat(), 0.2f, Color.RED, Color.RED)
        renderer.rectLine(-worldHeight.toFloat(), 0f, worldHeight.toFloat(), 0f, 0.2f, Color.BLUE, Color.BLUE)

        renderer.end()

        /*batch.use {
            batch.color = Color.WHITE
            font.data.setScale(0.035f)
            font.draw(batch, "0:0", 0f, 0f)
            font.draw(batch, "-$worldWidth:0", -worldWidth.toFloat()/2, 0f)
            font.draw(batch, "$worldWidth:0", worldWidth.toFloat()/2, 0f)
            font.draw(batch, "0:$worldHeight", 0f, worldHeight.toFloat()/2)
            font.draw(batch, "0:-$worldHeight", 0f, -worldHeight.toFloat()/2)
        }*/

    }

    fun drawCircles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.color = Color.GREEN

        renderer.circle(2f, 2f, 2f, 30)
        renderer.circle(-5f, -5f, 1f)

        renderer.end()

    }

    fun drawRectangles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.color = Color.BLUE

        renderer.rect(-8f, 4f, 4f, 2f)
        renderer.rect(-11f, 3f, 1f, 5f)

        renderer.end()
    }

    fun drawPoints() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.color = Color.MAGENTA

        renderer.point(-5f, 0f, 0f)
        renderer.point(5f, -3f, 0f)
        renderer.point(8f, 6f, 1f)
        renderer.end()

        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.x(-10f, 0f, 0.25f)
        renderer.end()
    }

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.G -> drawGrid = !drawGrid
            Input.Keys.C -> drawCircles = !drawCircles
            Input.Keys.R -> drawRectangles = !drawRectangles
            Input.Keys.P -> drawPoints = !drawPoints
        }

        return true
    }

    override fun resize(width: Int, height: Int) {
        // Not centering camera
        viewport.update(width, height)
    }

    override fun dispose() {
        renderer.dispose()
    }

}