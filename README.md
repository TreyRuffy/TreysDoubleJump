# Trey's Double Jump
[![Latest Version](https://img.shields.io/badge/dynamic/json?color=ed37aa&label=Latest%20Version&query=name&url=https%3A%2F%2Fapi.spiget.org%2Fv2%2Fresources%2F19630%2Fversions%2Flatest)](https://www.spigotmc.org/resources/treys-double-jump-api.19630/)
![Minecraft Version Support](https://img.shields.io/badge/Minecraft%20Versions-1.8--1.16.2-9450cc)
[![Downloads](https://img.shields.io/badge/dynamic/json?color=2230f2&label=Downloads&query=downloads&url=https%3A%2F%2Fapi.spiget.org%2Fv2%2Fresources%2F19630)](https://www.spigotmc.org/resources/treys-double-jump-api.19630/)


 This plugin allows players to double jump on Spigot and Bukkit servers and has tons of customization options.
 
 ## Installation
 Just drop the plugin from [here](https://www.spigotmc.org/resources/treys-double-jump-api.19630/) into your server
  plugins folder.

This plugin only works on Bukkit and Spigot servers including forks (PaperMC).

 ## Usage
`/tdj` - Toggles double jumping

`/fly` - Allows you to fly

`/groundpound` - Allows you to toggle ground pounding

`/djreload` - Allows you to reload the configuration files

## CraftBukkit Script
In order to get the required CraftBukkit libraries for CI programs, this plugin uses a script in order to
 automatically download them. If you would like to include the file in your own project, read how it works in the
  .semaphore/semaphore.yml and .github/workflows/codeql-analysis.yml files.
  
The basic command is
```bash
bash setupCraftbukkit.sh 1.16.2
```
Just replace 1.16.2 with the version you need and build tools will download the required CraftBukkit libraries into
 your Maven repository.

## Contributing
All pull requests are welcome. If you found a bug or would like a new feature added, contact me through the [issues
 tab](https://github.com/TreyRuffy/TreysDoubleJump/issues).
 
## License
[GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
 
 
