package sn.codeur269.clonespotifybackend.catalogcontext.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "song")
@Getter
public class Song implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="songSequenceGenerator")
    @SequenceGenerator(name="songSequenceGenerator", sequenceName="song_generator", allocationSize=1)
    private Long id;

    @Column(name = "public_id",unique = true,nullable = false)
    private UUID publicId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "cover",nullable = false)
    private byte[] cover;

    @Column(name = "cover_content_type",nullable = false)
    private String coverContentType;
    
}