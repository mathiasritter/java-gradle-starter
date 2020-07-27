import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // apply the java plugin to add support for Java
    java

    // apply the application plugin to add support for building an application
    application

    // shadow plugin for building fat jar (jar that includes dependencies)
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "engineer.mathias"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

    // Log4J
    implementation("org.apache.logging.log4j:log4j-api:2.13.3")
    implementation("org.apache.logging.log4j:log4j-core:2.13.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    // define the main class for the application
    mainClassName = "App"
}

tasks.withType<JavaCompile> {
    dependsOn("clean")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<ShadowJar> {
    // minimise dependencies
    minimize {
        // exclude the following dependencies from minimising
        exclude(dependency("org.apache.logging.log4j:log4j-core:.*"))
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Multi-Release"] = "true"
    }
    // include all main source code
    from(sourceSets["main"].allSource).into("/")
    // include all test source code
    from(sourceSets["test"].allSource).into("/")
    // include javadoc
    {
        from(tasks["javadoc"]).into("/javadoc")
    }
}
