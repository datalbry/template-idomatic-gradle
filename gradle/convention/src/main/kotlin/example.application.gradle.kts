import org.omg.CORBA.portable.ApplicationException

/*
 * The application convention contains any logic for the Spring-Boot application
 *
 */
plugins {
    application
}

configure<JavaApplication> {
    mainClass.set("org.gradle.sample.Main")
    applicationDefaultJvmArgs = listOf("-Dgreeting.language=en")
}
