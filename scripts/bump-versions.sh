#!/bin/sh

echo "Enter new version (major.minor.patch): "

read newVersion

echo "$newVersion"

# bump versions
mvn versions:set -DnewVersion="${newVersion}"
git commit -am "bumped version to ${newVersion}"
git tag -a "${newVersion}" -m "v${newVersion}"
mvn versions:set -DnewVersion="${newVersion}-SNAPSHOT"
git commit -am "started new version ${newVersion}"

echo "Successfully bumped version to ${newVersion}"