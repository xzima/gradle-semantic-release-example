import com.github.vlsi.gradle.dsl.configureEach
import com.github.vlsi.gradle.license.GatherLicenseTask
import com.github.vlsi.gradle.license.VerifyLicenseCompatibilityTask
import com.github.vlsi.gradle.license.api.SimpleLicense
import com.github.vlsi.gradle.license.api.SpdxLicense
import com.github.vlsi.gradle.license.api.SpdxLicenseException
import com.github.vlsi.gradle.properties.dsl.props
import com.github.vlsi.gradle.release.Apache2LicenseRenderer
import com.github.vlsi.gradle.release.ArtifactType
import com.github.vlsi.gradle.release.AsfLicenseCategory
import com.github.vlsi.gradle.release.dsl.dependencyLicenses
import com.github.vlsi.gradle.release.dsl.licensesCopySpec
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
    alias(libs.plugins.spotless)
    alias(libs.plugins.vlsi.extensions)
    alias(libs.plugins.vlsi.license)
    alias(libs.plugins.vlsi.release) apply false
}

val versionDetails: Closure<VersionDetails> by extra
version = project.version.takeUnless { Project.DEFAULT_VERSION == it } ?: versionDetails().run {
    val base = "$branchName.$gitHash".substringAfter("/")
    if (isCleanTag) return@run base
    "$base+SNAPSHOT"
}

group = "com.github.xzima"
val author = "Alex Zima"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.implementation)
    developmentOnly(libs.bundles.development)
    testImplementation(libs.bundles.test)
}

spotless {
    ratchetFrom = "origin/master"
    val yearPlaceholder = "\$YEAR"
    kotlin {
        licenseHeader(
            """
            /**
             * Copyright $yearPlaceholder $author
             *
             * Licensed under the Apache License, Version 2.0 (the "License");
             * you may not use this file except in compliance with the License.
             * You may obtain a copy of the License at
             *
             *     https://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software
             * distributed under the License is distributed on an "AS IS" BASIS,
             * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
             * See the License for the specific language governing permissions and
             * limitations under the License.
             */
            """.trimIndent()
        )
    }
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

    compilerOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.test {
    useJUnitPlatform()
}

val genDocs by tasks.registering(Test::class) {
}


tasks.bootBuildImage {
    customizeBootBuildImage(this)
}

fun customizeBootBuildImage(task: BootBuildImage) {
    val imageName by project.props.string
    if (imageName.isBlank()) return
    // Configure image name with version tag
    val imageReference = ImageReference.of(imageName)
    task.imageName.set(imageReference.toString())
    // Add latest tag
    val withLatest by project.props.bool
    if (withLatest) {
        task.tags.set(listOf(ImageReference.of(ImageName.of(imageReference.name), "latest").toString()))
    }
    // Configure docker publish
    val dockerHubUsername by project.props.string
    val dockerHubPassword by project.props.string
    if (dockerHubUsername.isBlank() || dockerHubPassword.isBlank()) return
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

val gatherLicense by tasks.registering(GatherLicenseTask::class) {
    // используем зависимости runtimeClasspath
    configuration(project.configurations.runtimeClasspath)

    // игнорируем отсутствие файла лицензии для следующих лицензий
    ignoreMissingLicenseFor.addAll(
        SpdxLicense.EPL_1_0 or SpdxLicense.LGPL_3_0_only,
    )

    // уточняем лецензии для следующих зависимостей
    overrideLicense("jakarta.annotation:jakarta.annotation-api") {
        expectedLicense = SpdxLicense.EPL_2_0 and SimpleLicense(
            "GPL2 w/ CPE",
            uri("https://www.gnu.org/software/classpath/license.html")
        )
        val gpl2WithCpEx = SpdxLicense.GPL_2_0_only with SpdxLicenseException.Classpath_exception_2_0
        effectiveLicense = SpdxLicense.EPL_2_0 and gpl2WithCpEx
    }
    overrideLicense("ch.qos.logback:logback-classic") {
        // https://github.com/qos-ch/logback/blob/master/LICENSE.txt
        expectedLicense = SpdxLicense.EPL_1_0 and SpdxLicense.LGPL_3_0_only
        effectiveLicense = SpdxLicense.EPL_1_0 or SpdxLicense.LGPL_3_0_only
    }
    overrideLicense("ch.qos.logback:logback-core") {
        // https://github.com/qos-ch/logback/blob/master/LICENSE.txt
        expectedLicense = SpdxLicense.EPL_1_0 and SpdxLicense.LGPL_3_0_only
        effectiveLicense = SpdxLicense.EPL_1_0 or SpdxLicense.LGPL_3_0_only
    }
}

val verifyLicenses by tasks.registering(VerifyLicenseCompatibilityTask::class) {
    metadata.from(gatherLicense) // берем метаданные из задачи gatherLicense

    // Правила для проверки лецензий зависимостей
    allow(SpdxLicense.EPL_2_0) {
        because("EPL-2.0 is fine in our projects")
    }
    // License category
    // See https://www.apache.org/legal/resolved.html
    allow(AsfLicenseCategory.A) {
        because("The ASF category A is allowed")
    }
    reject(AsfLicenseCategory.X) {
        because("The ASF category X is forbidden")
    }
}

val renderLicense by tasks.registering(Apache2LicenseRenderer::class) {
    dependsOn(verifyLicenses) // проверка должна вызываться до генерации
    metadata.from(gatherLicense) // берем метаданные из задачи gatherLicense
    artifactType.set(ArtifactType.BINARY) // влияет на перечень допустимых лицензий
    mainLicenseFile.set(file("LICENSE")) // базовый файл лицензии, который будет расширяться
}

val renderLicenseCopySpec = licensesCopySpec(renderLicense)

tasks.configureEach<Jar> {
    manifest {
        // see default in org.springframework.boot.gradle.tasks.bundling.BootArchiveSupport
        // attributes["Implementation-Title"] = "PostgreSQL JDBC Driver"
        // attributes["Implementation-Version"] = project.version
        // attributes["Bundle-Copyright"] = "Copyright (c) 2003-2020, $author"
        attributes["Bundle-License"] = SpdxLicense.Apache_2_0.id
        attributes["Implementation-Vendor"] = author
    }
    into("META-INF") {
        dependencyLicenses(renderLicenseCopySpec)
    }
}
