package sn.codeur269.clonespotifybackend.catalogcontext.application.dto;

import java.util.UUID;

import jakarta.persistence.Lob;

public record SongContentDto(UUID publicId,@Lob byte[] file, String fileContentType) {

}
