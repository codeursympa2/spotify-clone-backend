package sn.codeur269.clonespotifybackend.usercontext.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import sn.codeur269.clonespotifybackend.sharedkernel.domain.AbstractAuditedEntity;



@Entity
@Table(name = "spotify_user")
public class User extends AbstractAuditedEntity<Long>  {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userSequenceGenerator")
    @SequenceGenerator(name="userSequenceGenerator", sequenceName="user_generator", allocationSize=1)
    @Column(name = "id")
    private Long id;
    

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "subscription")
    private Subscription subscription=Subscription.FREE;

    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
   
}