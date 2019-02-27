package com.onimurasame.sampler.util

import com.badlogic.gdx.utils.Logger

inline fun <reified T : Any> logger() = Logger(T::class.java.simpleName, Logger.DEBUG)