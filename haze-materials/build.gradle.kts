// Copyright 2024, Christopher Banes and the Haze project contributors
// SPDX-License-Identifier: Apache-2.0


plugins {
  id("dev.chrisbanes.android.library")
  id("dev.chrisbanes.kotlin.multiplatform")
  id("org.jetbrains.kotlin.plugin.compose")
  id("org.jetbrains.compose")
//  id("org.jetbrains.dokka")
  id("maven-publish")
  id("dev.chrisbanes.metalava")
}

android {
  namespace = "dev.chrisbanes.haze.materials"
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(projects.haze)
        implementation(compose.material3)
      }
    }
  }
}

group = "dev.chrisbanes.haze"
version = "1.6.0-beta03-pixelmonaskarion"

publishing {
  repositories {
    maven {

    }
  }
}
