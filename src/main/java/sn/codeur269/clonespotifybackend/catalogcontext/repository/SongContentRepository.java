package sn.codeur269.clonespotifybackend.catalogcontext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.codeur269.clonespotifybackend.catalogcontext.domain.SongContent;

public interface SongContentRepository extends JpaRepository<SongContent, Long > {

}
