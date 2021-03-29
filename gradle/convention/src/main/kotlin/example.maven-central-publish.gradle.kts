@file:Suppress("UnstableApiUsage")

import gradle.kotlin.dsl.accessors._1e45eae12047fc2ad4d6e1ec9b70a640.clean

plugins {
    signing
    `maven-publish`
}

project.tasks.withType(PublishToMavenRepository::class) { dependsOn(project.tasks.clean) }

/**
 * TODO add description
 */
configure<PublishingExtension> {
    publications {
        repositories {
            maven {
                name = "MavenCentral"
                url = if ((project.rootProject.version as String).endsWith("SNAPSHOT")) {
                    uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                } else {
                    uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                }
                credentials(PasswordCredentials::class)
            }
        }
        create<MavenPublication>("jar") {
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("MyProject - ${project.name}")
                description.set("You should simply describe your project here")
                url.set("https://github.com/example/")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("John Johnson")
                        name.set("Johnson Consulting")
                        email.set("john@example.org")
                    }
                }
                scm {
                    connection.set("https://github.com/example/project.git")
                    developerConnection.set("scm:git:ssh:git@github.com:example/project.git")
                    url.set("https://github.com/example/project")
                }
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

/*
 * TODO Check if the the below line is necessary or not
 */
project.pluginManager.withPlugin("java") {
    configure<JavaPluginExtension> {
        withJavadocJar()
        withSourcesJar()
    }
}

configure<SigningExtension> {
    useGpgCmd()
    sign(the<PublishingExtension>().publications["jar"])
}
