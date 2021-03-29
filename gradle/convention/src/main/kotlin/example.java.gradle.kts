@file:Suppress("UnstableApiUsage")

plugins {
    /*
     * The example.java.gradle.kts is relying on the example.repository.gradle.kts
     */
    id("example.repository")
    /*
     * Apply JaCoCo for coverage support for our tests
     */
    jacoco
    java
}

/**
 * Setup the Java toolchain
 */
configure<JavaPluginExtension> {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of("1.11"))
        vendor.set(JvmVendorSpec.ADOPTOPENJDK)
    }
}

/*
 * The dependencies section here is meant for adding basic dependencies such as
 * jUnit5
 *
 * Sometimes it makes sense to get rid of the section, as every single (sub-)module may define all
 * of its dependencies on its own.
 */
dependencies {
    val testImplementation by configurations
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")

    val testRuntimeOnly by configurations
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

/*
 * We preconfigure all AbstractArchiveTasks so the archive name is being prefixed.
 * Typically one is using the company name to prefix the archives
 */
tasks.withType<AbstractArchiveTask> {
    archiveBaseName.set("example-${this.project.name}")
    archiveVersion.set(this.project.version.toString())
}

/*
 * Enables jUnit5 as the default test platform
 */
tasks.withType<Test> {
    useJUnitPlatform()
}
