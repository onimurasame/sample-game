package com.onimurasame.sampler.sample

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.reflect.ClassReflection
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.clearScreen
import com.onimurasame.sampler.util.logger
import com.onimurasame.sampler.util.toInternalFile
import com.onimurasame.sampler.util.use

class ReflectionSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ReflectionSample>()
    }

    lateinit var camera: OrthographicCamera
    lateinit var viewPort: Viewport
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("create()")

        camera = OrthographicCamera()
        viewPort = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())

        debugReflection<ReflectionSample>()
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
        // Mouse or Touch X and Y coordinates
        val mouseX = Gdx.input.x
        val mouseY = Gdx.input.y

        val leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT)
        val rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT)

        val wPressed = Gdx.input.isKeyPressed(Input.Keys.W)
        val sPressed = Gdx.input.isKeyPressed(Input.Keys.S)
        val aPressed = Gdx.input.isKeyPressed(Input.Keys.A)
        val dPressed = Gdx.input.isKeyPressed(Input.Keys.D)

        val leftPressedString = if (leftPressed) "Left Button Pressed" else "Left button Not Pressed"
        val rightPressedString = if (rightPressed) "Right Button Pressed" else "Right button Not Pressed"

        font.draw(batch, "Mouse X = $mouseX, Y = $mouseY", 20f, 720f - 20f)
        font.draw(batch, leftPressedString, 20f, 720f - 50f)
        font.draw(batch, rightPressedString, 20f, 720f - 80f)
        font.draw(batch, if (wPressed) "W Is pressed" else "W Is NOT pressed", 20f, 720f - 110f)
        font.draw(batch, if (sPressed) "S Is pressed" else "S Is NOT pressed", 20f, 720f - 140f)
        font.draw(batch, if (aPressed) "A Is pressed" else "A Is NOT pressed", 20f, 720f - 170f)
        font.draw(batch, if (dPressed) "D Is pressed" else "D Is NOT pressed", 20f, 720f - 200f)


    }


    override fun dispose() {
        batch.dispose()
        font.dispose()
    }

    private inline fun  <reified T : Any> debugReflection() {
        val fields = ClassReflection.getDeclaredFields(T::class.java)
        val methods = ClassReflection.getDeclaredMethods(T::class.java)

        log.debug("Reflecting class = ${T::class.java.simpleName}")
        log.debug("fieldCount = ${fields.size}")

        for(field in fields) {
            log.debug("name = ${field.name} type = ${field.type}")
        }

        methods.forEach { log.debug("name = ${it.name} parameterCount = ${it.parameterTypes.size}") }
    }


}