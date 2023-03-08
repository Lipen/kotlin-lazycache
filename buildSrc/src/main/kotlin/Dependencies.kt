@file:Suppress("PublicApiImplicitType")

object Versions {
    const val kotlin = "1.8.10"
    const val ktlint = "11.3.1"
    const val gradle_versions = "0.46.0"
    const val jgitver = "0.10.0-rc03"
    const val junit = "5.9.2"
}

object Libs {
    const val junit_jupiter_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junit_jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
}
