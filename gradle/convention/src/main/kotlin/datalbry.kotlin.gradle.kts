/*
 *
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    /*
     * We are using Kotlin (JVM) so we can rely on the Java-convention which already provides some convenience
     * for our Kotlin convention.
     *
     * Note:
     * The `java` plugin is also present on the classpath - if you really (for unknown reasons) don't want it
     * to be enabled, just copy everything from within the java-convention and remove the id("datalbry.java") here
     */
    id("datalbry.java")

    /*
     * The explicit version is already provided by Gradle (6.8.1 is providing Kotlin 1.4.20).
     * Are usual experience is to stick to that particular version, as most IDEs will run into issues when
     * having multiple Kotlin versions in Classpath (and as Gradle already requires Kotlin 1.4.20,
     * another version should be avoided)
     */
    kotlin("jvm")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
