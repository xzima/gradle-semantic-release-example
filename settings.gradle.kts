pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    
}
rootProject.name = "gradle-semantic-release-example"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.4.0")
}
