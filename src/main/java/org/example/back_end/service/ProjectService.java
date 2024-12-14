package org.example.back_end.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.back_end.model.Project;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.example.back_end.repository.ContactRepository;
import org.example.back_end.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }




    public Project saveProject(Project project, MultipartFile file) throws IOException {
//        if (file != null && !file.isEmpty()) {
//            project.setImage(file.getBytes());
//        }
        return projectRepository.save(project);
    }


    public List<Project> getAll() {
        List<Project> projects = projectRepository.findAll();

//        // Set the base64 image for each project
//        for (Project project : projects) {
//            project.setBase64Image(project.getBase64Image());
//        }
//
        return projects;
    }

    public Project updateLikes(Long id, Integer newNumberOfLikes) {
        Project project=projectRepository.findById(id).get();

        if (project!=null) {
            project.setNumberLikes(newNumberOfLikes);
            return projectRepository.save(project);
        }

        return null;
    }



    public Project update(long id, Project updatedItem) {
        if(projectRepository.existsById(id)) {
            return projectRepository.save(updatedItem);
        }
        else return null;

    }




    public void delete(long id) {
        if(projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        }
        else{
            System.out.println("Project with ID " + id + " does not exist.");
        }

    }

    public boolean exists(Long id) {
        return projectRepository.existsById(id);
    }


    public Project getProjectByID(Long projectId) {
        return projectRepository.findById(projectId).get();
    }
}
