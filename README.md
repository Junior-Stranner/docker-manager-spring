# docker-manager-spring
The objective of this project is to develop a Docker container management application, using Spring Boot as the main framework, with integration with the Docker API to manage containers, images and volumes in an automated and centralized manner. The system will allow starting, stopping, listing, removing containers



# Getting Started

## Dependencies

To start using `docker-java` , you need to add at least two dependencies:
1. `com.github.docker-java:docker-java-core` for the `DockerClient`
1. one of `com.github.docker-java:docker-java-transport-*` to communicate with the Docker daemon. See [Available Transports](./transports.md) for more info.

The latest available version: 
[![Maven Central](https://img.shields.io/maven-central/v/com.github.docker-java/docker-java.svg)](https://mvnrepository.com/artifact/com.github.docker-java/docker-java)


## Instantiating a `DockerClientConfig`

You will need an instance of `DockerClientConfig` to tell the library how to access Docker, which credentials to use to pull from Docker registries, etc etc.

The builder is available and allows you to configure every property of the client:
```java
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DefaultDockerClientConfig
DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
```

```java
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DefaultDockerClientConfig

DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
    .withDockerHost("tcp://docker.somewhere.tld:2376")
    .withDockerTlsVerify(true)
    .withDockerCertPath("/home/user/.docker")
    .withRegistryUsername(registryUser)
    .withRegistryPassword(registryPass)
    .withRegistryEmail(registryMail)
    .withRegistryUrl(registryUrl)
    .build();
```

Here you can tune registry auth, DOCKER_HOST and other options.

There are a couple of configuration items, all of which have sensible defaults:

* `DOCKER_HOST` The Docker Host URL, e.g. `tcp://localhost:2376` or `unix:///var/run/docker.sock`
* `DOCKER_TLS_VERIFY` enable/disable TLS verification (switch between `http` and `https` protocol)
* `DOCKER_CERT_PATH` Path to the certificates needed for TLS verification
* `DOCKER_CONFIG` Path for additional docker configuration files (like `.dockercfg`)
* `api.version` The API version, e.g. `1.23`.
* `registry.url` Your registry's address.
* `registry.username` Your registry username (required to push containers).
* `registry.password` Your registry password.
* `registry.email` Your registry email.

There are three ways to configure, in descending order of precedence:

##### Properties (docker-java.properties)

    DOCKER_HOST=tcp://localhost:2376
    DOCKER_TLS_VERIFY=1
    DOCKER_CERT_PATH=/home/user/.docker/certs
    DOCKER_CONFIG=/home/user/.docker
    api.version=1.23
