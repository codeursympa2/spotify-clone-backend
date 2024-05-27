package sn.codeur269.clonespotifybackend.usercontext.application;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sn.codeur269.clonespotifybackend.usercontext.domain.User;
import sn.codeur269.clonespotifybackend.usercontext.dto.ReadUserDto;
import sn.codeur269.clonespotifybackend.usercontext.mapper.UserMapper;
import sn.codeur269.clonespotifybackend.usercontext.repository.UserRepository;


@Service

public class UserService {

  
    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //Si la date de modifiation est récente par rapport à l'ancienne on met à jour l'utilisateur
    //Sinon on le crée
    public void syncWithIdp(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        User user = mapOauth2AttributesToUser(attributes);
        Optional<User> existingUser = userRepository.findOneByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (attributes.get("updated_at") != null) {
                Instant dbLastModifiedDate = existingUser.orElseThrow().getLastModifiedDate();
                Instant idpModifiedDate;
                if(attributes.get("updated_at") instanceof Instant) {
                    idpModifiedDate = (Instant) attributes.get("updated_at");
                } else {
                    idpModifiedDate = Instant.ofEpochSecond((Integer) attributes.get("updated_at"));
                }
                if(idpModifiedDate.isAfter(dbLastModifiedDate)) {
                    updateUser(user);
                }
            }
        } else {
            userRepository.saveAndFlush(user);
        }
    }
    private User mapOauth2AttributesToUser(Map<String, Object> attributes){
        User user=new User();
        String sub=String.valueOf(attributes.get("sub"));

        String username=null;

        if(attributes.get("preferred_username") != null){
            username=((String) attributes.get("preferred_username")).toLowerCase();
        }

        
        if(attributes.get("given_name") != null){
            user.setFirstName(((String) attributes.get("given_name")));
        }else if(attributes.get("name") != null){
            user.setFirstName(((String) attributes.get("name")));
        }

        if(attributes.get("family_name") != null){
            user.setLastName(((String) attributes.get("family_name")));
        }

        if(attributes.get("email") != null){
            user.setEmail(((String) attributes.get("email")));
        }else if(sub.contains("|") && (username != null && username.contains("@"))){
            user.setEmail(username);
        }else{
            user.setEmail(sub);
        }

        if(attributes.get("picture") != null){
            user.setImageUrl(((String) attributes.get("picture")));
        }

        return user;


    }


    public ReadUserDto  getAuthenticateUserFromSecurityContext(){
        OAuth2User principal= (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=this.mapOauth2AttributesToUser(principal.getAttributes());

        return userMapper.readUserDTOToUser(user);
    }

    
    public void updateUser(User user){
        Optional<User> userOptional=this.userRepository.findOneByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            User userUpdate=userOptional.get();

            userUpdate.setEmail(user.getEmail());
            userUpdate.setFirstName(user.getFirstName());
            userUpdate.setLastName(user.getLastName());
            userUpdate.setImageUrl(user.getImageUrl());
            
            this.userRepository.saveAndFlush(userUpdate);
        }

    }
}
