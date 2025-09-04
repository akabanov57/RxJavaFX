plugins {
    id("java-library")
    id("org.javamodularity.moduleplugin") version "1.8.15"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("maven-publish")
}

group = "io.reactivex.rxjava3"
version = "2.12-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("io.reactivex.rxjava3:rxjava:3.1.11")
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.19.0")
}

java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
    modularity.inferModulePath = false
    withJavadocJar()
    withSourcesJar()
}

tasks.compileJava {
    options.isDebug = true
}

tasks.test {
    useJUnitPlatform()
}

javafx {
    version = "21.0.8"
    modules("javafx.controls", "javafx.fxml")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}