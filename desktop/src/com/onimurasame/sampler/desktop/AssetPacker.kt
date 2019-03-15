package com.onimurasame.sampler.desktop

import com.badlogic.gdx.tools.texturepacker.TexturePacker

object AssetPacker {

    const val DRAW_DEBUG_OUTLINE = true
    const val RAW_ASSETS_PATH = "desktop/assets-raw"
    const val ASSETS_PATH = "core/assets"

}

fun main(args: Array<String>) {
    val settings = TexturePacker.Settings().apply {
        debug = AssetPacker.DRAW_DEBUG_OUTLINE
        maxWidth = 2048
        maxHeight = 2048
    }

    TexturePacker.process(settings, "${AssetPacker.RAW_ASSETS_PATH}/images", "${AssetPacker.ASSETS_PATH}/images", "atlasSample")
}
