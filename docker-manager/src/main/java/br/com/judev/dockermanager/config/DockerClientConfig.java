package br.com.judev.dockermanager.config;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.RemoteApiVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.dockerjava.api.DockerClient;


@Configuration
public class DockerClientConfig {

    @Value("${docker.socket.path")
    private String dockerSocketPath;

    @Bean
    public DockerClient buildDockerClient() {
        DefaultDockerClientConfig.Builder dockerClientConfigBuilder  = DefaultDockerClientConfig.createDefaultConfigBuilder();

        if(this.dockerSocketPath != null && this.dockerSocketPath.startsWith("unix://")){
            dockerClientConfigBuilder.withDockerHost(dockerSocketPath)
                    .withApiVersion(RemoteApiVersion.VERSION_1_24)
                    .withDockerTlsVerify(false);
        }
        DefaultDockerClientConfig dockerClientConfig = dockerClientConfigBuilder
                .build();

        return null;
    }
}
