# Knit

A utility library for Minecraft modding that provides cross-version compatibility APIs. Knit is a fork of [Deftu's OmniCore](https://github.com/Deftu/OmniCore) library, designed to work across multiple Minecraft versions and mod loaders.

This is a maintained fork by [bllryy](https://github.com/bllryy) updated to support Minecraft 1.21.11.

## Features

- **Command system** - Powerful DSL for building commands using Brigadier
- **Event bus** - Simple and efficient event system
- **Input handling** - Unified keyboard and mouse APIs
- **Text components** - Easy text formatting and manipulation
- **Screen API** - Simplified GUI creation
- **Utility functions** - Number formatting, string operations, and more

## Versions

[![JitPack](https://jitpack.io/v/bllryy/Knit-updated.svg)](https://jitpack.io/#bllryy/Knit-updated)

| Version | Loaders |
|---|---|
| 1.20.1 | Fabric, Forge |
| 1.21.5 | Fabric, Forge, NeoForge |
| 1.21.8 | Fabric, Forge, NeoForge |
| 1.21.9 | Fabric, Forge, NeoForge |
| 1.21.10 | Fabric |
| 1.21.11 | Fabric |

## Installation

Adding Knit to your project:

### Gradle (Kotlin DSL)

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    // Replace TAG with the release version (e.g. 119)
    // Replace MINECRAFT_VERSION-LOADER with your target (e.g. 1.21.11-fabric)
    modImplementation("xyz.meowing:knit-MINECRAFT_VERSION-LOADER:TAG")
}
```

For example, for Minecraft 1.21.11 on Fabric:
```kotlin
modImplementation("xyz.meowing:knit-1.21.11-fabric:119")
```

## Usage Examples

### Commands

Create commands with an intuitive DSL:

```kotlin
object Command : Commodore("mycommand", "alias") {
    init {
        literal("param") {
            runs { name: String ->
                KnitChat.fakeMessage("Hello, $name!")
            }
        }

        literal("param2") {
            runs { x: Float, y: Float, z: Float ->
                println("$x, $y, $z")
            }
        }

        runs {
            println("no parameters passed!")
        }
    }
}

// Register the command
command.register(dispatcher)
```

### Events

Register event handlers with priority support:

```kotlin
val eventBus = EventBus()

class MyCustomEvent : CancellableEvent()

eventBus.register<MyCustomEvent> { event ->
    println("Event fired: $event")
    if (someCondition) {
        event.cancel() // If using CancellableEvent
    }
}

// Post events
eventBus.post(MyCustomEvent())
```

### Text Components

Build formatted chat messages easily:

```kotlin
val message = KnitText
    .literal("Hello world!")
    .onHover("!dlrow olleH")
    .onClick(ClickEvent.RunCommand("/explode"))
    .toVanilla()

KnitChat.fakeMessage(message)

val message = buildText {
    text("Click here") {
        blue()
        bold()
        underlined()
        onClick("https://example.com")
        onHover("Opens example.com")
    }
    text(" to visit our website!") {
        gray()
    }
}

KnitChat.fakeMessage(message.toVanilla())
```

### Screen Creation

Create custom screens:

```kotlin
class MyScreen : KnitScreen("My Custom Screen") {
    override fun onInitGui() {
        // Initialize your screen
    }

    override fun onRender(mouseX: Int, mouseY: Int, deltaTicks: Float) {
        // Render your screen
    }

    override fun onMouseClick(mouseX: Int, mouseY: Int, button: Int) {
        // Handle mouse clicks
    }

    override fun onMouseMove(mouseX: Int, mouseY: Int) {
        // cool stuff
    }

    override fun onKeyType(typedChar: Char, keyCode: Int, scanCode: Int) {
        // Handle keyboard input
    }
}
```

### Utilities

Format numbers and strings:

```kotlin
val abbreviated = 1_500_000.abbreviate() // "1.5M"
val withCommas = 1500000.formatWithCommas() // "1,500,000"
val camelCase = "hello world".toCamelCase() // "helloWorld"
```

### Input Handling

Check keyboard and mouse state:

```kotlin
if (KnitKeys.KEY_SPACE.isPressed) {
    println("Space bar is pressed!")
}

if (KnitMouseButtons.LEFT.isPressed) {
    val x = KnitMouse.Scaled.x
    val y = KnitMouse.Scaled.y
    println("Left mouse button pressed at ($x, $y)")
}
```

## License

Knit is licensed under the GNU General Public License v3.0. See [LICENSE](LICENSE) for more information.

## Credits

- **Deftu** - Original OmniCore library
- **Stivais** - Commodore command system
- **StellariumMC** - Knit fork and prior maintenance
- **bllryy** - 1.21.11 update and current maintenance

The command system (`xyz.meowing.knit.api.command` package) contains modified code from the [Commodore](https://github.com/Stivais/Commodore) library by Stivais.
