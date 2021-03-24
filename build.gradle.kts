import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // apply the application plugin to add support for building a java application
    application

    // shadow plugin for building fat jar (jar that includes dependencies)
    id("com.github.johnrengelman.shadow") version "6.1.0"

    // plugin used to include javafx
    id("org.openjfx.javafxplugin") version "0.0.9"
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
}

// configure the java version
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

javafx {
    modules("javafx.controls", "javafx.fxml")
}

application {
    // define the main class for the application - need to use deprecated way because of shadow plugin
    mainClassName = "Main"
}

tasks {

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
            // exclude log4j and javafx from minimising (won't work otherwise)
            exclude(dependency("org.apache.logging.log4j:log4j-core:.*"))
            exclude(dependency("org.openjfx:.*:.*"))
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
