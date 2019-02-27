package com.onimurasame.sampler.sample

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.onimurasame.sampler.common.SampleBase
import com.onimurasame.sampler.util.clearScreen
import com.onimurasame.sampler.util.use

class GdxGeneratedSample : SampleBase() {

    lateinit var batch: SpriteBatch
    lateinit var img: Texture

    override fun create() {
        this.batch = SpriteBatch()
        this.img = Texture("badlogic.jpg")
    }

    override fun render() {
        clearScreen()

        /*batch.begin()
        batch.draw(img, 0f, 0f)
        batch.end()*/

        batch.use { batch.draw(img, 0f, 0f) }
    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
    }


}