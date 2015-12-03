[![Build Status](https://travis-ci.org/emartynov/android-template-project.svg?branch=develop)
# Android Template Project

Example/Quick starter for a project with consolidated knowledge of Android development.

## Original problem

The idea came from Funda.nl assignment (link is coming). The app is trying to find top ten brokers by properties numbers.

## Project overview

This android application project with two libraries dependencies - core and network.

### Decisions about dependencies

  1. Support library for decent UI across different Android versions
  2. Dagger 2 for dependencies management across app and libraries
  3. RxJava for threads management and data manipulation as for now. As for now I'm trying to use
 functional part of Rx, and I'm planning to start using reactive part as soon as possible
  4. Retrofit as REST client
  5. ButterKnife for view binding sugar syntax
  6. Mockito, Android AssertJ, Robolectric for unit testing  

### Structure

I'm trying to implement onion architecture. The core module is the core of the project, networking and UI are top layers.

The idea was to make core java library instead of Android, but after hour trying to configure android local maven repository as project
repository, I just changed it back to android library type.

### Dependencies management

Dagger provides nice opportunity to define dependencies scopes. This project is an attempt to make it right.

### UI layer

The idea is to have MVP or MVVM (not decided yet) properly implemented in UI.

### Testing

Currently project contains unit tests and some server integration tests. The idea is to add also 
acceptance espresso tests.

## Other interesting repositories for learning

Links are coming!

u2020
qualitymatters

## Contribution

I appreciate any contribution by ideas proposals and pull requests with actual implementations. 