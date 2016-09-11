#!/bin/sh

echo "Enter new version (major.minor.patch): "

read newVersion

echo "$newVersion"

# bump versions
mvn versions:set -DnewVersion="${newVersion}"
git commit -am "bumped version to ${newVersion}"
git tag -a "${newVersion}" -m "v${newVersion}"
mvn versions:set -DnewVersion="${newVersion}-SNAPSHOT"
git commit -am "set back to snapshot"

echo "Successfully bumped version to ${newVersion}"