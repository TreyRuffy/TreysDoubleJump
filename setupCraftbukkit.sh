#!/usr/bin/env bash
if [ $# -eq 0 ]; then
    echo "Missing arguments"
    exit 1
fi

if [ ! -f "$HOME/craftbukkit/BuildTools.jar" ]; then

mkdir -p "$HOME"/craftbukkit
echo "Downloading Build Tools for Minecraft version $1"
wget https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar -O "$HOME"/craftbukkit/BuildTools.jar
fi

cd "$HOME"/craftbukkit || exit

chmod +rx BuildTools.jar

# Build spigot
if [ ! -f "$HOME/.m2/repository/org/bukkit/craftbukkit/$1-R0.1-SNAPSHOT/craftbukkit-$1-R0.1-SNAPSHOT.jar" ]; then
echo "Building CraftBukkit using Spigot Build Tools for Minecraft version $1"
java -jar BuildTools.jar --rev "$1" --compile CRAFTBUKKIT,SPIGOT

else echo "CraftBukkit for Minecraft version $1 already exists in the repository"

fi

