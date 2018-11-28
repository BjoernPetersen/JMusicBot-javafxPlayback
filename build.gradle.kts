import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Version.KOTLIN
    idea

    id("com.github.spotbugs") version Version.SPOTBUGS

    id("com.github.johnrengelman.shadow") version Version.SHADOW_JAR
}

group = "com.github.bjoernpetersen"
version = "0.12.0-SNAPSHOT"

repositories {
    jcenter()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

idea {
    module {
        isDownloadJavadoc = true
    }
}

spotbugs {
    isIgnoreFailures = true
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    "compileKotlin"(KotlinCompile::class) {
        kotlinOptions.jvmTarget = "1.8"
    }

    "compileTestKotlin"(KotlinCompile::class) {
        kotlinOptions.jvmTarget = "1.8"
    }

    "test"(Test::class) {
        useJUnitPlatform()
    }
}

configurations.all {
    // TODO remove or comment out when MusicBot refactor is released
    resolutionStrategy.cacheChangingModulesFor(1, TimeUnit.MINUTES)
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(
        group = "io.github.microutils",
        name = "kotlin-logging",
        version = Version.KOTLIN_LOGGING)
    compileOnly(
        group = "com.github.bjoernpetersen",
        name = "musicbot",
        version = Version.MUSICBOT) {
        isChanging = true
    }

    testImplementation(
        group = "org.junit.jupiter",
        name = "junit-jupiter-api",
        version = Version.JUNIT)
    testImplementation(
        group = "org.junit.jupiter",
        name = "junit-jupiter-engine",
        version = Version.JUNIT)
}
