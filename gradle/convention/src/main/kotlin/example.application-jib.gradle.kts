import com.google.cloud.tools.jib.gradle.JibExtension

plugins {
    id("com.google.cloud.tools.jib")
}

configure<JibExtension> {
    from {
        image = "openjdk:alpine"
    }
    to {
        image = "${project.name}:${project.version}"
        //image = "<MY_DOCKER_REGISTRY>/${project.name}:${project.version}"
    }
}
