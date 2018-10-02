[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/elbraulio/neuralnet/blob/master/LICENSE) [![Build Status](https://travis-ci.org/elbraulio/neuralnet.svg?branch=master)](https://travis-ci.org/elbraulio/neuralnet) [![codecov](https://codecov.io/gh/elbraulio/neuralnet/branch/master/graph/badge.svg)](https://codecov.io/gh/elbraulio/neuralnet) [![codebeat badge](https://codebeat.co/badges/270459fc-3a22-4765-80cf-a4f006926a31)](https://codebeat.co/projects/github-com-elbraulio-neuralnet-master)

## Perceptron

Basic use of it:

First receive bias, then all the inputs weights. Also, you can call ```feed``` to get the output value as a [Number](https://docs.oracle.com/javase/8/docs/api/java/lang/Number.html).

~~~java
new Perceptron(-1.5, 1, 1).feed(1, 1).intValue();
~~~

