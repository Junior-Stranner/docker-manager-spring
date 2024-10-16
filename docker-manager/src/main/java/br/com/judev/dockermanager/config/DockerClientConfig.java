package br.com.judev.dockermanager.config;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.RemoteApiVersion;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.dockerjava.api.DockerClient;


@Configuration
public class DockerClientConfig {

    @Value("${docker.socket.path}")
// Injeta o valor da propriedade "docker.socket.path" na variável dockerSocketPath.
// Este valor representa o caminho do socket do Docker usado para se conectar ao daemon Docker.
    private String dockerSocketPath;

    @Bean
// Define que este método cria um bean que será gerenciado pelo Spring e pode ser injetado em outras partes da aplicação.
    public DockerClient buildDockerClient() {
        // Cria um builder para configurar o cliente Docker usando configurações padrão.
        DefaultDockerClientConfig.Builder dockerClientConfigBuilder  = DefaultDockerClientConfig.createDefaultConfigBuilder();

        // Verifica se o caminho do socket Docker não é nulo e começa com "unix://",
        // indicando que o Docker está em um ambiente Unix, conectado via socket Unix.
        if(this.dockerSocketPath != null && this.dockerSocketPath.startsWith("unix://")) {
            // Configura o host Docker usando o valor do socket Docker fornecido.
            dockerClientConfigBuilder.withDockerHost(dockerSocketPath)
                    // Define a versão da API do Docker a ser usada.
                    .withApiVersion(RemoteApiVersion.VERSION_1_24)
                    // Desativa a verificação TLS para a conexão (geralmente em ambientes locais).
                    .withDockerTlsVerify(false);
        }

        // Constrói as configurações do cliente Docker usando as opções fornecidas no builder.
        DefaultDockerClientConfig dockerClientConfig = dockerClientConfigBuilder.build();

        // Cria um cliente HTTP Apache para comunicação com o daemon Docker.
        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                // Define o host Docker para o cliente HTTP, com base nas configurações definidas.
                .dockerHost(dockerClientConfig.getDockerHost())
                // Constrói o cliente HTTP.
                .build();

        // Constrói e retorna uma instância do DockerClient usando as configurações e o cliente HTTP.
        return DockerClientBuilder.getInstance(dockerClientConfig)
                .withDockerHttpClient(dockerHttpClient)
                .build();
    }

}
