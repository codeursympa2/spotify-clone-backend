package sn.codeur269.clonespotifybackend.catalogcontext.application.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import sn.codeur269.clonespotifybackend.catalogcontext.application.vo.SongAuthorVO;
import sn.codeur269.clonespotifybackend.catalogcontext.application.vo.SongTitleVO;

public record SaveSongDTO(
    @Valid SongAuthorVO songAuthorVO,
    @Valid SongTitleVO songTitleVO,
    @NotNull byte[] cover,
    @NotNull String coverContentType,
    @NotNull byte[] file,
    @NotNull String fileContentType

) {

}
