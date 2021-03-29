@file:Suppress("UnstableApiUsage")

/*
* The maven-publish convention contains
*/
plugins {
    id("maven-publish")
}

configure<PublishingExtension> {

    /*
     * We have to configure the repository we want to publish our artifact to
     * Usually most companies are using private repositories, which requiring authentication
     *
     * Insert the following into your gradle.properties.kts (e.g. `idiomatic-gradle/gradle.properties`) or
     * to your global gradle.properties.kts (e.g. `~/.gradle/gradle.properties`).
     *
     * For further information see [https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:handling_credentials]
     */
    repositories {
        maven {
            name = "MavenRepository"
            url = uri("my.maven.org")
            credentials(PasswordCredentials::class)
        }
    }

    /*
     * TODO()
     */
    publications {
        create<MavenPublication>("artifact") {
            project.pluginManager.withPlugin("java-platform") {
                from(components["java-platform"])
            }
            project.pluginManager.withPlugin("java") {
                // Clarify if springboot not applied
                //if (project.pluginManager.hasPlugin("springboot-string"))
                from(components["java"])
            }
            project.pluginManager.withPlugin("distribution") {
                if (project.pluginManager.hasPlugin("org.springframework.boot")) {
                    artifact(project.tasks.named("bootDistZip").get())
                    artifact(project.tasks.named("bootDistTar").get())
                } else {
                    artifact(project.tasks.named("distZip").get())
                    artifact(project.tasks.named("distTar").get())
                }
            }
        }
    }
}
