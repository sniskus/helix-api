plugins {
    java
    `java-library`
    `maven-publish`
}

group = "com.sniskus.helix"
version = "1.0.1"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

// Dependencies
// ===========================================================================================

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("com.google.guava:guava:33.1.0-jre")
    implementation("org.jetbrains:annotations:24.1.0")
    implementation("org.spigotmc:spigot-api:1.21.5-R0.1-SNAPSHOT")
}

// Publishing
// ===========================================================================================

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "helix-api"
        }
    }
}