plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.spotless)
    alias(libs.plugins.ben.manes.versions)
}

dependencies {
    implementation(libs.spring.boot.starter)
}

spotless {
    java {
        target("src/**/*.java")
        googleJavaFormat()
    }
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}

tasks {
    compileJava {
        sourceCompatibility = libs.versions.java.get()
    }

    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    test {
        useJUnitPlatform()
    }

    dependencyUpdates {
        rejectVersionIf {
            candidate.version.isNonStable()
        }

        rejectVersionIf {
            candidate.version.isNonStable() && !currentVersion.isNonStable()
        }

        resolutionStrategy {
            componentSelection {
                all {
                    if (candidate.version.isNonStable() && !currentVersion.isNonStable()) {
                        reject("Release candidate")
                    }
                }
            }
        }

        checkForGradleUpdate = true
        outputFormatter = "json"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}
