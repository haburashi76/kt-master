import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.0.0"
    id("com.gradleup.shadow") version "8.3.0"
    id("com.vanniktech.maven.publish") version "0.31.0"
    id("org.jetbrains.dokka") version "2.0.0"
}

group = "io.github.haburashi76"
version = "0.0.0"

allprojects {
    repositories {
        mavenCentral()
    }
}

rootProject.apply(plugin = "org.jetbrains.dokka")

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
    }
    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}

project(":${rootProject.name}") {

    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "com.vanniktech.maven.publish")

    tasks {
        create<Jar>("sourcesJar") {
            archiveClassifier.set("sources")
            from(sourceSets["main"].allSource)
        }

        create<Jar>("dokkaJar") {
            archiveClassifier.set("javadoc")
            dependsOn("dokkaHtml")

            @Suppress("DEPRECATION")
            from("$buildDir/dokka/html/") {
                include("**")
            }
        }
    }
    mavenPublishing {
        coordinates(
            groupId = "io.github.haburashi76",
            artifactId = this@project.name,
            version = "0.0.0"
        )

        pom {
            name.set(this@project.name)
            description.set("Kotlin Extension Library With Added Utility Functions")
            url.set("https://github.com/haburashi76/${rootProject.name}")

            licenses {
                license {
                    name.set("GNU General Public License version 3")
                    url.set("https://opensource.org/licenses/GPL-3.0")
                }
            }

            developers {
                developer {
                    id.set("haburashi76")
                    name.set("Haburashi76")
                    email.set("haburashi76@gmail.com")
                    url.set("https://github.com/haburashi76")
                    roles.addAll("developer")
                    timezone.set("Asia/Seoul")
                }
            }


            scm {
                connection.set("scm:git:git://github.com/haburashi76/${rootProject.name}.git")
                developerConnection.set("scm:git:ssh://github.com:haburashi76/${rootProject.name}.git")
                url.set("https://github.com/haburashi76/${rootProject.name}")
            }
        }
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
        signAllPublications()
    }
}

kotlin {
    jvmToolchain(21)
}