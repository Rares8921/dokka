@file:Suppress("PackageDirectoryMismatch")

package org.jetbrains.dokka.gradle

import org.jetbrains.dokka.DokkaConfigurationImpl
import org.jetbrains.dokka.build
import org.gradle.api.tasks.*

@CacheableTask
abstract class DokkaTask : AbstractDokkaLeafTask() {
    override fun buildDokkaConfiguration(): DokkaConfigurationImpl =
        DokkaConfigurationImpl(
            moduleName = moduleName.get(),
            moduleVersion = moduleVersion.getValidVersionOrNull(),
            outputDir = outputDirectory.get(),
            cacheRoot = cacheRoot.orNull,
            offlineMode = offlineMode.get(),
            failOnWarning = failOnWarning.get(),
            sourceSets = unsuppressedSourceSets.build(),
            pluginsConfiguration = buildPluginsConfiguration(),
            pluginsClasspath = plugins.resolve().toList(),
            suppressObviousFunctions = suppressObviousFunctions.get(),
            suppressInheritedMembers = suppressInheritedMembers.get(),
        )
}
