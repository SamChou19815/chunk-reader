# Chunk Reader

[![Build Status](https://travis-ci.com/SamChou19815/chunk-reader.svg?branch=master)](https://travis-ci.com/SamChou19815/chunk-reader)
[![Release](https://jitpack.io/v/SamChou19815/chunk-reader.svg)](https://jitpack.io/#SamChou19815/chunk-reader)
![GitHub](https://img.shields.io/github/license/SamChou19815/chunk-reader.svg)

A hackathon-winning service to extract key information and summary from text.

Currently, the 
[master_legacy branch](https://github.com/SamChou19815/chunk-reader/tree/master_legacy) contains the
original version implemented during Hackathon and the new rewritten one is in the master branch, 
which only contains the backend code.

A reference implementation is hosted on 
[Developer Sam's Website](https://developersam.com/playground/chunk-reader).

Read the docs [here](https://docs.developersam.com/chunk-reader/)

## Gradle Config

Add this to your `build.gradle` to use the artifact.

```groovy
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}
dependencies {
    implementation 'com.github.SamChou19815:chunk-reader:+'
}
```
