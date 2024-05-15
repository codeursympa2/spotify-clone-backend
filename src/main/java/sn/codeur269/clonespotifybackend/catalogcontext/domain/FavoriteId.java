package sn.codeur269.clonespotifybackend.catalogcontext.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavoriteId implements Serializable{
    public UUID songPublicId;
    public String userEmail;
}
