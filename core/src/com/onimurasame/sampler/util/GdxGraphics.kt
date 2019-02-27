package com.onimurasame.sampler.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch

@JvmOverloads
fun clearScreen(color: Color = Color.BLACK) = clearScreen(color.r, color.g, color.b, color.a)

fun clearScreen(red: Float, green: Float, blue: Float, alpha: Float) {
    Gdx.gl.glClearColor(red,green, blue, alpha)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
}

inline fun Batch.use (action: () -> Unit) {
    begin()
    action()
    end()
}