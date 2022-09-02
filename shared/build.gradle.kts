import dependencies.Dependencies

plugins {
    id("commons.shared-library")
    id("com.apollographql.apollo3").version(dependencies.Dependencies.Apollo.VERSION)
}

kotlin {
    cocoapods {
        summary = "Shared"
        homepage = "https://github.com/alexandru-calinoiu/SharedAppSample"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Apollo.RUNTIME)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.AndroidX.Lifecycle.VIEWMODEL)
                implementation(Dependencies.AndroidX.Lifecycle.VIEWMODEL_COMPOSE)
                implementation(Dependencies.AndroidX.Lifecycle.LIVEDATA)
                implementation(Dependencies.AndroidX.Lifecycle.RUNTIME_KTX)
            }
        }
    }
}

apollo {
    packageName.set("com.agilefreaks.sharedappsample.dtos")
    generateApolloMetadata.set(true)
}