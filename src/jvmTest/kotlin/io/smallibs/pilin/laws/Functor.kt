package io.smallibs.pilin.laws

import io.smallibs.pilin.control.Functor
import io.smallibs.pilin.core.Fun
import io.smallibs.pilin.core.Fun.Infix.compose
import io.smallibs.pilin.core.Fun.id
import io.smallibs.pilin.module.open
import io.smallibs.pilin.type.App

object Functor {

    suspend fun <F, A> Functor.API<F>.`map id = id`(
        x: App<F, A>
    ): Boolean =
        open(this.infix) {
            val id: suspend (A) -> A = Fun::id
            id map (x) == id(x)
        }

    suspend fun <F, A, B, C> Functor.API<F>.`map (f compose g) = map f compose map g`(
        f: suspend (B) -> C,
        g: suspend (A) -> B,
        x: App<F, A>
    ): Boolean =
        open(this.infix) {
            (f compose g) map x == (map(f) compose map(g))(x)
        }


}