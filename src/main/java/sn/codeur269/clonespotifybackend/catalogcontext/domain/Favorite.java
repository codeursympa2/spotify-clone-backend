package sn.codeur269.clonespotifybackend.catalogcontext.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favorite_song")
@Getter
@Setter
@IdClass(FavoriteId.class)
public class Favorite implements Serializable {

    @Id
    private UUID songPublicId;

    @Id
    @Column(name = "user_email")
    private String userEmail;
    
}