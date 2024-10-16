package br.com.judev.dockermanager.services;

import com.github.dockerjava.api.DockerClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DockerService {

    @Autowired
    public DockerClient dockerClient;
}
