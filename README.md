# CapstoneProjectAndroid

Master: [![Build Status](https://travis-ci.org/alexmichon/CapstoneProjectAndroid.svg?branch=master)](https://travis-ci.org/alexmichon/CapstoneProjectAndroid)
[![codecov.io](https://codecov.io/github/alexmichon/CapstoneProjectAndroid/branch/master/graph/badge.svg)](https://codecov.io/github/alexmichon/CapstoneProjectAndroid)

Develop: [![Build Status](https://travis-ci.org/alexmichon/CapstoneProjectAndroid.svg?branch=develop)](https://travis-ci.org/alexmichon/CapstoneProjectAndroid)
[![codecov.io](https://codecov.io/github/alexmichon/CapstoneProjectAndroid/branch/develop/graph/badge.svg)](https://codecov.io/github/alexmichon/CapstoneProjectAndroid)


## Prerequisite
This application requires Bluetooth Low Energy support (API 18 or higher).

## Code architecure
Code architecture is inspired from: https://github.com/MindorksOpenSource/android-mvp-architecture
It is based on RxJava2 and Dagger2.

## Build variants
There are 4 build variants to simulate or not the Bluetooth device and the Webserver:
* Fake (Device and Webserver are simulated)
* Fake Bluetooth (Bluetooth is simulated)
* Fake Network (Webserver is simulated)
* Full