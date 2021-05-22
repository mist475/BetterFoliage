package mods.betterfoliage.config

import mods.betterfoliage.BetterFoliageMod
import java.util.Random

fun featureEnable(default: Boolean = true) = boolean(default, langKey = recurring)

val Config get() = BetterFoliageMod.config

abstract class PopulationConfigGroup() : DelegatingConfigGroup() {
    abstract val enabled: Boolean
    abstract val population: Int

    fun enabled(random: Random) = random.nextInt(64) < population && enabled
}

class MainConfig : DelegatingConfigGroup() {
    val enabled by boolean(true, langKey = { "betterfoliage.global.enabled" })
    val nVidia by boolean(false)

    val leaves by subNode(::LeavesConfig)
    val shortGrass by subNode(::ShortGrassConfig)
    val connectedGrass by subNode(::ConnectedGrassConfig)
    val roundLogs by subNode(::RoundLogConfig)
    val cactus by subNode(::CactusConfig)
    val lilypad by subNode(::LilypadConfig)
    val reed by subNode(::ReedConfig)
    val algae by subNode(::AlgaeConfig)
    val coral by subNode(::CoralConfig)
    val netherrack by subNode(::NetherrackConfig)
    val fallingLeaves by subNode(::FallingLeavesConfig)
    val risingSoul by subNode(::RisingSoulConfig)
}

class LeavesConfig : DelegatingConfigGroup() {
    val enabled by featureEnable()
    val snowEnabled by boolean(true)
    val hOffset by double(max=0.4, default=0.2, langKey = recurring)
    val vOffset by double(max=0.4, default=0.1, langKey = recurring)
    val size by double(min=0.75, max=2.5, default=1.4, langKey = recurring)
    val dense by boolean(false)
    val hideInternal by boolean(true)
    val saturationThreshold by double(default=0.1, langKey = recurring)
}

class ShortGrassConfig : PopulationConfigGroup() {
    override val enabled by boolean(true)
    val myceliumEnabled by boolean(true)
    val snowEnabled by boolean(true)
    val hOffset by double(max=0.4, default=0.2, langKey = recurring)
    val heightMin by double(min=0.1, max=2.5, default=0.6, langKey = recurring)
    val heightMax by double(min=0.1, max=2.5, default=0.8, langKey = recurring)
    val size by double(min=0.5, max=1.5, default=1.0, langKey = recurring)
    override val population by integer(max=64, default=64)
    val useGenerated by boolean(false)
    val shaderWind by boolean(true, langKey = recurring)
    val saturationThreshold by double(default=0.1, langKey = recurring)
}

class ConnectedGrassConfig() : DelegatingConfigGroup() {
    val enabled by boolean(true)
    val snowEnabled by boolean(false)
}

class RoundLogConfig() : DelegatingConfigGroup() {
    val enabled by featureEnable()
    val plantsOnly by boolean(true)
    val radiusSmall by double(max=0.5, default=0.25)
    val radiusLarge by double(max=0.5, default=0.44)
    val dimming by double(default = 0.7)
    val connectSolids by boolean(false)
    val lenientConnect by boolean(true)
    val connectPerpendicular by boolean(true)
    val connectGrass by boolean(true)
    val defaultY by boolean(false)
    val zProtection by double(min = 0.9, default = 0.99)
}

class CactusConfig() : DelegatingConfigGroup() {
    val enabled by featureEnable()
    val size by double(min=0.5, max=1.5, default=0.8, langKey = recurring)
    val sizeVariation by double(max=0.5, default=0.1)
    val hOffset by double(max=0.5, default=0.1, langKey = recurring)
}

class LilypadConfig() : PopulationConfigGroup() {
    override val enabled by featureEnable()
    val hOffset by double(max=0.25, default=0.1, langKey = recurring)
    override val population by integer(max=64, default=16, min=0)
    val shaderWind by boolean(true, langKey = recurring)
}

class ReedConfig() : PopulationConfigGroup() {
    override val enabled by featureEnable()
    val hOffset by double(max=0.4, default=0.2, langKey = recurring)
    val heightMin by double(min=1.5, max=3.5, default=1.7, langKey = recurring)
    val heightMax by double(min=1.5, max=3.5, default=2.2, langKey = recurring)
    override val population by integer(max=64, default=32, langKey = recurring)
    val minBiomeTemp by double(default=0.4)
    val minBiomeRainfall by double(default=0.4)
    val shaderWind by boolean(true, langKey = recurring)
}

class AlgaeConfig() : PopulationConfigGroup() {
    override val enabled by featureEnable()
    val hOffset by double(max=0.25, default=0.1, langKey = recurring)
    val size by double(min=0.5, max=1.5, default=1.0, langKey = recurring)
    val heightMin by double(min=0.1, max=1.5, default=0.5, langKey = recurring)
    val heightMax by double(min=0.1, max=1.5, default=1.0, langKey = recurring)
    override val population by integer(max=64, default=48, langKey = recurring)
    val shaderWind by boolean(true, langKey = recurring)
}

class CoralConfig() : PopulationConfigGroup() {
    override val enabled by featureEnable()
    val shallowWater by boolean(false)
    val hOffset by double(max=0.4, default=0.2, langKey = recurring)
    val vOffset by double(max=0.4, default=0.1, langKey = recurring)
    val size by double(min=0.5, max=1.5, default=0.7, langKey = recurring)
    val crustSize by double(min=0.5, max=1.5, default=1.4)
    val chance by integer(max=64, default=32)
    override val population by integer(max=64, default=48, langKey = recurring)
}

class NetherrackConfig() : DelegatingConfigGroup() {
    val enabled by featureEnable()
    val hOffset by double(max=0.4, default=0.2, langKey = recurring)
    val heightMin by double(min=0.1, max=1.5, default=0.6, langKey = recurring)
    val heightMax by double(min=0.1, max=1.5, default=0.8, langKey = recurring)
    val size by double(min=0.5, max=1.5, default=1.0, langKey = recurring)
}

class FallingLeavesConfig() : DelegatingConfigGroup() {
    val enabled by featureEnable()
    val speed by double(min=0.01, max=0.15, default=0.05)
    val windStrength by double(min=0.1, max=2.0, default=0.5)
    val stormStrength by double(min=0.1, max=2.0, default=0.8)
    val size by double(min=0.25, max=1.5, default=0.75, langKey = recurring)
    val chance by double(min=0.001, max=1.0, default=0.02)
    val perturb by double(min=0.01, max=1.0, default=0.25)
    val lifetime by double(min=1.0, max=15.0, default=5.0)
    val opacityHack by boolean(true)
}

class RisingSoulConfig() : DelegatingConfigGroup() {
    val enabled by featureEnable()
    val chance by double(min=0.001, max=1.0, default=0.02)
    val perturb by double(min=0.01, max=0.25, default=0.05)
    val headSize by double(min=0.25, max=1.5, default=1.0)
    val trailSize by double(min=0.25, max=1.5, default=0.75)
    val opacity by double(min=0.05, max=1.0, default=0.5)
    val sizeDecay by double(min=0.5, max=1.0, default=0.97)
    val opacityDecay by double(min=0.5, max=1.0, default=0.97)
    val lifetime by double(min=1.0, max=15.0, default=4.0)
    val trailLength by integer(min=2, max=128, default=48)
    val trailDensity by integer(min=1, max=16, default=3)
}