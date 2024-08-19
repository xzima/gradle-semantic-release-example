import com.palantir.gradle.gitversion.VersionDetails
import groovy.lang.Closure
import org.gradle.jvm.toolchain.internal.DefaultJavaLanguageVersion
import org.springframework.boot.buildpack.platform.docker.type.ImageName
import org.springframework.boot.buildpack.platform.docker.type.ImageReference
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import java.util.*

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.git.version)
}

val versionDetails: Closure<VersionDetails> by extra
version = project.version.takeUnless { Project.DEFAULT_VERSION == it } ?: versionDetails().run {
    val base = "$branchName.$gitHash".substringAfter("/")
    if (isCleanTag) return@run base
    "$base+SNAPSHOT"
}

group = "com.github.xzima"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.implementation)
    developmentOnly(libs.bundles.development)
    testImplementation(libs.bundles.test)
}

kotlin {
    jvmToolchain {
        val sdkProps = file(".sdkmanrc").reader().use {
            Properties().apply { load(it) }
        }
        val sdkDistribution = sdkProps["java"]?.toString()
            ?: throw RuntimeException("jdk not present in .sdkmanrc")

        languageVersion.set(DefaultJavaLanguageVersion.fromFullVersion(sdkDistribution))
    }
}

tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("genDocs") {
}


tasks.bootBuildImage {
    customizeBootBuildImage(this)
}

fun customizeBootBuildImage(task: BootBuildImage) {
    val imageName: String? by project
    if (imageName.isNullOrBlank()) return
    // Configure image name with version tag
    val imageReference = ImageReference.of(imageName)
    task.imageName.set(imageReference.toString())
    // Add latest tag
    val withLatest: String? by project
    if (null != withLatest) {
        task.tags.set(listOf(ImageReference.of(ImageName.of(imageReference.name), "latest").toString()))
    }
    // Configure docker publish
    val dockerHubUsername: String? by project
    val dockerHubPassword: String? by project
    if (dockerHubUsername.isNullOrBlank() || dockerHubPassword.isNullOrBlank()) return
    task.apply {
        publish.set(true)
        docker {
            publishRegistry {
                username.set(dockerHubUsername)
                password.set(dockerHubPassword)
            }
        }
    }
}
