import fr.brouillard.oss.gradle.plugins.JGitverPluginExtensionBranchPolicy
import fr.brouillard.oss.jgitver.Strategies
import io.github.z4kn4fein.semver.Inc
import io.github.z4kn4fein.semver.inc
import io.github.z4kn4fein.semver.toVersion

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.jgitver)
}

buildscript {
    dependencies {
        classpath(libs.bundles.buildscript.classpath)
    }
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

tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.test {
    useJUnitPlatform()
}

val dockerHubUsername: String? by project
val dockerHubPassword: String? by project
val withLatest: String? by project

if (dockerHubUsername.isNullOrBlank().not()) {
    tasks.bootBuildImage {
        imageName.set("$dockerHubUsername/${project.name}:${project.version}")

        if (null != withLatest) {
            tags.set(listOf("$dockerHubUsername/${project.name}:latest"))
        }

        if (dockerHubPassword.isNullOrBlank().not()) {
            publish.set(true)
            docker {
                publishRegistry {
                    username.set(dockerHubUsername)
                    password.set(dockerHubPassword)
                }
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

jgitver {
    strategy = Strategies.PATTERN
    regexVersionTag = "(.+)"
    versionPattern = "\${meta.BASE_TAG}+\${meta.COMMIT_DISTANCE}+\${meta.DIRTY}"
    policy(closureOf<JGitverPluginExtensionBranchPolicy> {
        // remove branch name from version for all branches
        pattern = "(.*)"
        transformations = listOf("IGNORE")
    })
}

project.afterEvaluate {
    // Expected like 1.1.0+123+false | 1.1.0-rc.20+0+true
    val versionList = this.version.toString().split("+")
    if (3 != versionList.size) {
        throw InvalidUserDataException("Invalid version format: ${this.version}")
    }

    val (tag, distance, isDirtyStr) = versionList
    val semVer = tag.toVersion()

    this.version = if (isDirtyStr.toBoolean() || "0" != distance) {
        if (semVer.isPreRelease) {
            // 1.1.0-rc.20+123+false | 1.1.0-rc.20+0+true -> 1.1.0-rc.21
            semVer.inc(Inc.PRE_RELEASE).copy(buildMetadata = "SNAPSHOT")
        } else {
            // 1.1.0+123+false | 1.1.0+0+true -> 1.1.1
            semVer.inc(Inc.PATCH).copy(buildMetadata = "SNAPSHOT")
        }
    } else {
        // 1.1.0+0+false -> 1.1.0
        // 1.1.0-rc.20+0+false -> 1.1.0-rc.20
        semVer.toString()
    }
}
