@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

group = "com.github.xzima"
if (!project.hasProperty("release")) {
    version = "${version}-SNAPSHOT"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.implementation)
    developmentOnly(libs.bundles.development)
    testImplementation(libs.bundles.test)
}

tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = libs.versions.jvm.get()
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.bootBuildImage {
    if (!project.hasProperty("release")) {
        throw InvalidUserDataException("bootBuildImage must be run with -Prelease property")
    }

    imageName.set(project.property("release").toString())
    tags.set(listOf("${imageName.get()}:${project.version}"))

    if (project.hasProperty("releaseToken")) {
        publish.set(true)
        docker{
            publishRegistry {
                username.set("alexzima")
                password.set(project.property("releaseToken").toString())
            }
        }
    }
}

kotlin {
    jvmToolchain {
        vendor.set(JvmVendorSpec.BELLSOFT)
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvm.get()))
    }
}
