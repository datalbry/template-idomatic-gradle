/*
 *
 */
plugins {
    id("datalbry.repository")
    jacoco
    java
}

/*
 *
 */
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

/*
 *
 */
tasks.withType<AbstractArchiveTask> {
    archiveBaseName.set("datalbry-${this.project.name}")
    archiveVersion.set(this.project.version.toString())
}

/*
 *
 */
tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

/*
 *
 */
tasks.withType<Test> {
    useJUnitPlatform()
}
