# feature-toggle-lib

[![Build Status](https://travis-ci.org/FunThomas424242/feature-toggle-lib.svg?branch=master)](https://travis-ci.org/FunThomas424242/feature-toggle-lib)

Java lib to enable and disable feature toggle in an application and test.

Features:
* annotation based @Feature(ENABLED|DISABLED) @IntegrationToggle
* toggle enabled by annotation
* you can override the toggle state by command line via systemProperties
* you can override the toggle state of @IntegrationToggle in the test by a @Rule