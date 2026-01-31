import gg.meza.stonecraft.mod

plugins {
    id("gg.meza.stonecraft")
}

modSettings {
    generatedResources = project.layout.projectDirectory.dir("src/main/generated")
    clientOptions {
        fov = 90
        guiScale = 3
        narrator = false
        darkBackground = true
        musicVolume = 0.0
    }
}

repositories {
    mavenCentral()
}

dependencies {
    if (mod.isFabric) {
        modImplementation("net.fabricmc.fabric-api:fabric-api:${mod.prop("fabric_version")}")
    }
}

// Custom models are too large for Groovy's template expansion (65535 char limit)
// We exclude them from template processing and copy them directly after
tasks.withType<ProcessResources>().configureEach {
    // Exclude custom models from template expansion
    exclude("**/models/custom/**")

    // After processing other resources, copy custom models directly
    doLast {
        val sourceDir = rootProject.file("src/main/resources/assets/serene_shrubbery/models/custom")
        val targetDir = destinationDir.resolve("assets/serene_shrubbery/models/custom")

        if (sourceDir.exists() && sourceDir.isDirectory) {
            targetDir.mkdirs()
            sourceDir.listFiles()?.forEach { file ->
                file.copyTo(targetDir.resolve(file.name), overwrite = true)
            }
        }
    }
}
