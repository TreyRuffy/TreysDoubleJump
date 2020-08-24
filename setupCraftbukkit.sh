#!/usr/bin/env bash
if [ $# -eq 0 ]; then
    echo "Missing arguments"
    exit 1
fi

if [ ! -f "$HOME/craftbukkit/BuildTools.jar" ]; then

mkdir -p "$HOME"/craftbukkit
echo "Downloading Build Tools for Minecraft version $1"
wget https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar -O "$HOME"/craftbukkit/BuildTools.jar

# Build spigot
echo "Building Spigot using Spigot Build Tools for minecraft version $1"
java -jar BuildTools.jar --rev "$1" --compile CRAFTBUKKIT,SPIGOT
fi