package br.com.judev.dockermanager.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.dockerjava.api.model.Container;

import java.util.List;

@Service
@AllArgsConstructor
public class DockerService {

    @Autowired
    public DockerClient dockerClient;

    // Método para listar containers
    public List<Container> listContainers(boolean all) {
        // Chama o comando para listar containers no cliente Docker
        // O parâmetro 'all' indica se queremos listar todos os containers, inclusive os que estão parados
        return dockerClient.listContainersCmd().withShowAll(all).exec();
    }

    // Método para listar imagens
    public List<Image> listImages(){
        // Executa o comando para listar todas as imagens Docker no cliente
        return dockerClient.listImagesCmd().exec();
    }

    // Método para filtrar imagens por nome
    public List<Image> filterImages(String filterName){
        // Usa um filtro para listar imagens que contêm o nome especificado
        return dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
    }

    // Método para iniciar um container
    public void startContainer(String containerId) {
        // Inicia um container usando o ID fornecido
        dockerClient.startContainerCmd(containerId).exec();
    }

    // Método para parar um container
    public void stopContainer(String containerId) {
        // Para a execução de um container pelo seu ID
        dockerClient.stopContainerCmd(containerId).exec();
    }

    // Método para remover um container
    public void deleteContainer(String containerId) {
        // Remove um container existente usando o ID
        dockerClient.removeContainerCmd(containerId).exec();
    }

    // Método para criar um container a partir de uma imagem
    public void createContainer(String imageName) {
        // Cria um novo container baseado em uma imagem com o nome fornecido
        dockerClient.createContainerCmd(imageName).exec();
    }

}
