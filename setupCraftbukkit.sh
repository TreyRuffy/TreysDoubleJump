#!/usr/bin/env bash
# Created by TreyRuffy for Trey's Double Jump
#
# Feel free to use as you need for any of your projects.
# In order to make this execute commands, read the .semaphore/sempaphore.yml and the
# .github/workflows/codeql-analysis.yml files

# First argument is the version of Spigot (eg. 1.16.2)
# Second argument is if you should force update the Spigot files
#   Set as true for yes
# Run bash setupCraftbukkit.sh 1.16.2 true
if [ $# -eq 0 ]; then
    echo "Missing arguments"
    exit 1
fi

forceUpdate=false;

if [ $# -eq 2 ] && [ "$2" == 'true' ]; then
  forceUpdate=true;
fi

if [ ! -f "$HOME/craftbukkit/BuildTools.jar" ] || [ $forceUpdate == true ]; then

mkdir -p "$HOME"/craftbukkit
echo "Downloading Build Tools for Minecraft version $1"
wget https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar -O "$HOME"/craftbukkit/BuildTools.jar
fi

cd "$HOME"/craftbukkit || exit

chmod +rx BuildTools.jar

# Build spigot
if [ ! -f "$HOME/.m2/repository/org/bukkit/craftbukkit/$1-R0.1-SNAPSHOT/craftbukkit-$1-R0.1-SNAPSHOT.jar" ] || [ $forceUpdate == true ]; then
echo "Building CraftBukkit using Spigot Build Tools for Minecraft version $1"
java -jar BuildTools.jar --rev "$1" --compile CRAFTBUKKIT,SPIGOT

else echo "CraftBukkit for Minecraft version $1 already exists in the repository"

fi