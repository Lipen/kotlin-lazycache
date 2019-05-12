@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.lipen.lazycache

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LazyCache {
    private val properties: MutableList<LazyCachedValue<*>> = mutableListOf()

    fun subscribe(property: LazyCachedValue<*>) {
        properties.add(property)
    }

    fun <T> subscribe(init: () -> T): LazyCachedValue<T> = LazyCachedValue(init).also { subscribe(it) }

    operator fun <T> invoke(init: () -> T): LazyCachedValue<T> = subscribe(init)

    fun invalidate() {
        // println("Invalidating LazyCache...")
        properties.forEach { it.invalidate() }
    }
}

class LazyCachedValue<out T>(
    private val init: () -> T
) : ReadOnlyProperty<Any, T> {
    private var cached: Option<T> = None

    fun invalidate() {
        // println("Invalidating LazyCachedValue...")
        cached = None
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        when (val c = cached) {
            is None -> init().also { cached = Some(it) }
            // .also { println("Calculated ${property.name} = $it...") }
            is Some<T> -> c.value
            // .also { println("Returning already stored ${property.name} = ${it}...") }
        }
}
