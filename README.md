[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/elbraulio/neuralnet/blob/master/LICENSE) [![Build Status](https://travis-ci.org/elbraulio/neuralnet.svg?branch=master)](https://travis-ci.org/elbraulio/neuralnet) [![](https://jitpack.io/v/com.elbraulio/neuralnet.svg)](https://jitpack.io/#com.elbraulio/neuralnet) [![](https://img.shields.io/badge/javadocs-ok-green.svg)](https://jitpack.io/com/elbraulio/neuralnet/latest/javadoc/) [![codecov](https://codecov.io/gh/elbraulio/neuralnet/branch/master/graph/badge.svg)](https://codecov.io/gh/elbraulio/neuralnet) [![codebeat badge](https://codebeat.co/badges/270459fc-3a22-4765-80cf-a4f006926a31)](https://codebeat.co/projects/github-com-elbraulio-neuralnet-master)

__neuralnet__ allows to create _neural networks_ and to train them.

# Install

__maven__

```xml
<dependencies>
    <dependency>
        <groupId>com.elbraulio</groupId>
        <artifactId>neuralnet</artifactId>
        <version>{version}</version>
    </dependency>
</dependencies>
<!-- elbraulio.com tools -->
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

```

# How to use

it is simple, just provide __learning rate__, __input size__, __output size__ and each __layer size__.

```java
NeuralNetwork network = new DefaultNetwork(
                0.3, 13, 4,
                10, 5, 8
);
```

in this example we use `learning rate = 0.3`, `input size = 13` and `output size = 4`. Then we include 3 hidden layers with sizes for each layer `10, 5, 8`.

Now it is possible to feed the network. **Important**: the input's length must be the same as the input size declared in the network.

```java
Number[] output = network.feed(inputs);
```

The _output_ 