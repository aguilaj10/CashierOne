plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

repositories {
    mavenCentral()
}

val detekt by configurations.creating

val detektTask = tasks.register<JavaExec>("detekt") {
    mainClass.set("io.gitlab.arturbosch.detekt.cli.Main")
    classpath = detekt

    val input = projectDir
    val config = "$projectDir/detekt.yml"
    val exclude = ".*/build/.*,.*/resources/.*"
    val params = listOf("-i", input, "-c", config, "-ex", exclude)

    args(params)
}

dependencies {
    detekt(libs.detekt.cli)
}