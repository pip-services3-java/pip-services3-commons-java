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
if (!(Test-Path "~/.m2/settings.xml")) {
   # Use existing gpg key if env variables set 
   if (($env:GPG_PUBLIC_KEY -ne $null) -and ($env:GPG_PRIVATE_KEY -ne $null)) {
      Set-Content -Path "gpg_public.key" -Value $env:GPG_PUBLIC_KEY
      Set-Content -Path "gpg_private.key" -Value $env:GPG_PRIVATE_KEY

      Write-Host "Before import"
      gpg --list-keys
      gpg --import "gpg_public.key"
      gpg --batch --passphrase "$($env:GPG_PASSPHRASE)" --import "gpg_private.key"
      Write-Host "After import"
      gpg --list-keys

   } else {
      # Generate new gpg key
      $genKey = @"
Key-Type: 1
Key-Length: 2048
Subkey-Type: 1
Subkey-Length: 2048
Name-Real: $($env:GPG_USERNAME)
Name-Email: $($env:GPG_EMAIL)
Passphrase: $($env:GPG_PASSPHRASE)
Expire-Date: 0
"@

      Set-Content -Path "genKey" -Value $genKey
      
      $gpgOut = gpg --batch --gen-key genKey

      # Get gpg keyname
      $gpgKeyname = Read-Host "Enter gpg key id"

      # Register gpg
      gpg --keyserver keyserver.ubuntu.com --send-keys $gpgKeyname
   }

   # Create m2 config
    $m2SetingsContent = @"
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
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

   # Save config
    if (!(Test-Path "~/.m2")) {
        $null = New-Item -Path "~/.m2" -ItemType "directory"
    }
    Set-Content -Path "~/.m2/settings.xml" -Value $m2SetingsContent
}

Write-Host "Getting mvn version..."
mvn --version

# Release package
mvn clean deploy

# Verify release result
if ($LastExitCode -ne 0) {
    Write-Error "Release failed. Watch logs above. If you run script from local machine - try to remove ~/.m2/settings.xml and rerun a script."
}
