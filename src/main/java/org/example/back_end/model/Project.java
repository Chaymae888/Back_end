package org.example.back_end.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Base64;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="TITLE",nullable = false)
    private String title;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="NUMBEROFLIKES",nullable = false)
    private int numberLikes;
    @Column(name="USEDTECHNOLOGIES",nullable = false)
    private String usedTechnologies;
    @Column(name="CATEGORY",nullable = false)
    private String category;

    @Column(name = "GITHUB")
    private String lienGithub;

//    @Lob
//    @JsonIgnore
//    @Column(name = "IMAGE", columnDefinition = "BYTEA")
//    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getNumberLikes() {
        return numberLikes;
    }
    public void setNumberLikes(int numberLikes) {
        this.numberLikes = numberLikes;
    }
    public String getUsedTechnologies() {
        return usedTechnologies;
    }
    public void setUsedTechnologies(String usedTechnologies) {
        this.usedTechnologies = usedTechnologies;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getLienGithub() {
        return lienGithub;
    }
    public void setLienGithub(String lienGithub) {
        this.lienGithub = lienGithub;
    }
//    public byte[] getImage() {
//        return image;
//    }
//    public void setImage(byte[] image) {
//        this.image = image;
//    }

//    @Transient  // This field is not persisted in the database
//    private String base64Image;
//
//    // Getter and setter for base64Image
//    public String getBase64Image() {
//        if (image != null) {
//            return Base64.getEncoder().encodeToString(image);
//        }
//        return null;
//    }
//    public void setBase64Image(String base64Image){
//        this.base64Image=base64Image;
//    }

}
