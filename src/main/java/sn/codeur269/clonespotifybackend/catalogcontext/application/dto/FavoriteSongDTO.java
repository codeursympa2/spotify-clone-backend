package sn.codeur269.clonespotifybackend.catalogcontext.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record FavoriteSongDTO(
    @NotNull boolean favorite,@NotNull UUID publicId
) {}
