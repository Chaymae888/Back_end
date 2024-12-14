package org.example.back_end.controller;


import org.example.back_end.model.Project;
import org.example.back_end.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;


    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @PostMapping
    public ResponseEntity<?> addProject(
        @RequestParam("title") String title ,
        @RequestParam("description") String description,
        @RequestParam("numberLikes") int numberLikes,
        @RequestParam("usedTechnologies") String usedTechnologies,
        @RequestParam("category") String category,
        @RequestParam("lienGithub") String lienGithub,
        @RequestParam("image") MultipartFile imageFile) {

            Project project = new Project();
            project.setTitle(title);
            project.setDescription(description);
            project.setNumberLikes(numberLikes);
            project.setUsedTechnologies(usedTechnologies);
            project.setCategory(category);
            project.setLienGithub(lienGithub);

            try {
                Project project1=projectService.saveProject(project, imageFile);
                return new ResponseEntity<>(project1, HttpStatus.CREATED);
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Failed to upload project: " + e.getMessage());
            }
    }
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAll();
    }

    @DeleteMapping("/{ProjectId}")
    public ResponseEntity<Project> deleteProject(@PathVariable("ProjectId") Long id) {
        if (!projectService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{ProjectId}")
    public ResponseEntity<Project> updateProject(@PathVariable("ProjectId") Long id,@RequestBody Project Project) {
        Project updatedProject = projectService.update(id, Project);
        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProject);
    }

    @PutMapping("/{projectId}/likes")
    public ResponseEntity<Project> updateProjectLikes(
            @PathVariable("projectId") Long id,
            @RequestBody Map<String, Integer> payload) {

        Integer newNumberOfLikes = payload.get("numberLikes");

        if (newNumberOfLikes == null) {
            return ResponseEntity.badRequest().build();
        }

        Project updatedProject = projectService.updateLikes(id, newNumberOfLikes);

        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProject);
    }


//    @GetMapping("/{projectId}/image")
//    public ResponseEntity<byte[]>  getImageByProject(@PathVariable Long projectId){
//        Project project=projectService.getProjectByID(projectId);
//        byte[] image=project.getImage();
//        return ResponseEntity.ok().body(image);
//    }

}
