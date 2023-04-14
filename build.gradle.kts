import fr.brouillard.oss.gradle.plugins.JGitverPluginExtensionBranchPolicy
import fr.brouillard.oss.jgitver.Version

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.jgitver)
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
        jvmTarget = libs.versions.jvm.get()
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
    policy(closureOf<JGitverPluginExtensionBranchPolicy> {
        // remove branch name from version for all branches
        pattern = "(.*)"
        transformations = listOf("IGNORE")
    })
}

project.afterEvaluate {
    val versionList = this.version.toString().split("-")
    if (2 != versionList.size) {
        // Expected like 1.1.0-123
        throw InvalidUserDataException("Invalid version format: ${this.version}")
    }

    val (semVer, distance) = versionList

    this.version = if ("0" == distance) {
        // 1.1.0-0 -> 1.1.0
        semVer
    } else {
        // 1.1.0-123 -> 1.1.1-SNAPSHOT
        "${Version.parse(semVer).incrementPatch()}-SNAPSHOT"
    }
}
