import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.freefair.gradle.plugins.aspectj.AspectjCompile

plugins {
    // apply the application plugin to add support for building a java application
    application

    // shadow plugin for building fat jar (jar that includes dependencies)
    id("com.github.johnrengelman.shadow") version "6.1.0"

    // plugin that enables usage of aspects
    id("io.freefair.aspectj") version "5.3.0"
}

group = "engineer.mathias"
version = "1.0"

repositories {
    jcenter()
}

dependencies {
    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

    // Log4J
    implementation("org.apache.logging.log4j:log4j-api:2.13.3")
    implementation("org.apache.logging.log4j:log4j-core:2.13.3")

    // Aspect J
    implementation("org.aspectj:aspectjrt:1.9.6")
}

// configure the java version
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    // define the main class for the application - need to use deprecated way because of shadow plugin
    mainClassName = "App"
}

tasks {

    withType<AspectjCompile> {
        source(sourceSets["main"].java.sourceDirectories)
        destinationDir = sourceSets["main"].java.outputDir
    }

    withType<JavaCompile> {
        dependsOn("clean")
    }

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    withType<ShadowJar> {
        // minimise dependencies
        minimize {
            // exclude the following dependencies from minimising
            exclude(dependency("org.apache.logging.log4j:log4j-core:.*"))
        }
    }

    withType<Jar> {
        manifest {
            attributes["Multi-Release"] = "true"
        }
        // include all main source code
        from(sourceSets["main"].allSource).into("/")
        // include all test source code
        from(sourceSets["test"].allSource).into("/")
        // include javadoc
        {
            from(getTasks()["javadoc"]).into("/javadoc")
        }
    }

}
