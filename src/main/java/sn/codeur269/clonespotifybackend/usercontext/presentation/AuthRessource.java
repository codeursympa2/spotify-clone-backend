package sn.codeur269.clonespotifybackend.usercontext.presentation;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Map;

import sn.codeur269.clonespotifybackend.usercontext.application.UserService;
import sn.codeur269.clonespotifybackend.usercontext.dto.ReadUserDto;

@RestController
@RequestMapping("/api")
public class AuthRessource {

    private final UserService userService;
    private final ClientRegistration registrations;

    public AuthRessource(UserService service,ClientRegistrationRepository registrations){
        this.userService=service;
        this.registrations=registrations.findByRegistrationId("okta");
    }


    @GetMapping("/get-authenticated-user")
    public ResponseEntity<ReadUserDto> getAuthenticatedUser(@AuthenticationPrincipal OAuth2User user){ 
        if(user == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            userService.syncWithIdp(user);
            ReadUserDto userFromAuthentication=userService.getAuthenticateUserFromSecurityContext();
            
            return ResponseEntity.ok().body(userFromAuthentication);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String issueUrl=registrations.getProviderDetails().getIssuerUri();
        String originUrl=request.getHeader(HttpHeaders.ORIGIN);
        Object[] parms={issueUrl, registrations.getClientId(),originUrl};

        String logoutUrl=MessageFormat.format("{0}v2/logout?client_id={1}&returnTo={2}",parms);

        request.getSession().invalidate();
        return ResponseEntity.ok().body(Map.of("logoutUrl",logoutUrl));
    }
    
}
