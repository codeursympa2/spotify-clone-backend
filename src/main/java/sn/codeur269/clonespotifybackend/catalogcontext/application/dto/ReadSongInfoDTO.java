package sn.codeur269.clonespotifybackend.catalogcontext.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sn.codeur269.clonespotifybackend.catalogcontext.application.vo.SongAuthorVO;
import sn.codeur269.clonespotifybackend.catalogcontext.application.vo.SongTitleVO;

@Getter
@Setter
public class ReadSongInfoDTO {
    private SongTitleVO songTitleVO;
    private SongAuthorVO songAuthorVO;
    @NotNull
    private byte[] cover;
    @NotNull
    private String coverContentType;

    @NotNull 
    private Boolean isFavorite;

    @NotNull
    private UUID publicID;
}
