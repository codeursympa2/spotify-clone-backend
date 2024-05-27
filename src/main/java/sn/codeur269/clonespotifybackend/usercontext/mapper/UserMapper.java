package sn.codeur269.clonespotifybackend.usercontext.mapper;

import org.mapstruct.Mapper;

import sn.codeur269.clonespotifybackend.usercontext.domain.User;
import sn.codeur269.clonespotifybackend.usercontext.dto.ReadUserDto;

// Cette annotation indique à MapStruct de générer une implémentation 
// pour cette interface de mappage et de l'intégrer dans le contexte de
//  Spring. Cela signifie que vous pourrez injecter et utiliser cette
//   interface comme un bean Spring dans d'autres composants de votre
//    application Spring.

@Mapper(componentModel = "spring")
public interface UserMapper {
    ReadUserDto readUserDTOToUser(User user);
}
