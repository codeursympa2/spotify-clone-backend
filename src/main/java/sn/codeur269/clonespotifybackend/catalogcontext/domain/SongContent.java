package sn.codeur269.clonespotifybackend.catalogcontext.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "song_content")
public class SongContent implements Serializable {


    @Id
    @Column(name = "song_id")
    private Long songId;

    @MapsId
    /*
     * Cette annotation est utilisée pour mapper l'identifiant d'une entité parente
     *  sur une entité enfant dans une relation OneToOne. Cela signifie que l'identifiant
     *  de l'entité parente sera utilisé comme clé étrangère dans la table de l'entité
     *  enfant.
     */
    @OneToOne
    @JoinColumn(name="song_id", referencedColumnName = "id")
    private Song song;

    @Lob
    @Column(name = "file",nullable = false)
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;
}