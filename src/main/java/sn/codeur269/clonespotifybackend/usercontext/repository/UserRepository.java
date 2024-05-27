package sn.codeur269.clonespotifybackend.usercontext.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.codeur269.clonespotifybackend.usercontext.domain.User;

public interface UserRepository extends JpaRepository<User,Long > {

    Optional<User> findOneByEmail(String email);
    
}
