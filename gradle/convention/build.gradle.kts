plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("gradle.plugin.com.google.cloud.tools:jib-gradle-plugin:2.7.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
