package sn.codeur269.clonespotifybackend.catalogcontext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.codeur269.clonespotifybackend.catalogcontext.domain.Favorite;
import sn.codeur269.clonespotifybackend.catalogcontext.domain.FavoriteId;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId > {

}
