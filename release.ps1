#!/usr/bin/env pwsh

Set-StrictMode -Version latest
$ErrorActionPreference = "Stop"

$component = Get-Content -Path "component.json" | ConvertFrom-Json
$version = ([xml](Get-Content -Path "pom.xml")).project.version

# Verify versions in component.json and pom.xml
if ($component.version -ne $version) {
    throw "Versions in component.json and pom.xml do not match"
}

# Create ~/.m2/settings.xml if not exists
if (-not (Test-Path "~/.m2/settings.xml")) {
    $m2SetingsContent = @"
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
<settings>
   <servers>
      <server>
         <id>ossrh</id>
         <username>$($env:M2_USER)</username>
         <password>$($env:M2_PASS)</password>
      </server>
      <server>
         <id>sonatype-nexus-snapshots</id>
         <username>$($env:M2_USER)</username>
         <password>$($env:M2_PASS)</password>
      </server>
      <server>
         <id>nexus-releases</id>
         <username>$($env:M2_USER)</username>
         <password>$($env:M2_PASS)</password>
      </server>
   </servers>
   <profiles>
      <profile>
         <id>ossrh</id>
         <activation>
            <activeByDefault>true</activeByDefault>
         </activation>
         <properties>
            <gpg.keyname>$($env:GPG_KEYNAME)</gpg.keyname>
            <gpg.executable>gpg</gpg.executable>
            <gpg.passphrase>$($env:GPG_PASSPHRASE)</gpg.passphrase>
         </properties>
      </profile>
   </profiles>
</settings>
"@

    if (-not (Test-Path "~/.m2")) {
        $null = New-Item -Path "~/.m2" -ItemType "directory"
    }

    Set-Content -Path "~/.m2/settings.xml" -Value $m2SetingsContent
}

# Release package
mvn clean deploy

# Verify release result
if ($LastExitCode -ne 0) {
    Write-Error "Release failed. Watch logs above. If you run script from local machine - try to remove ~/.m2/settings.xml and rerun a script."
}
