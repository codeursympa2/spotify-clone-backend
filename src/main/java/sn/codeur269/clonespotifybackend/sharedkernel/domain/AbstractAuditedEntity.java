package sn.codeur269.clonespotifybackend.sharedkernel.domain;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditedEntity<T> implements Serializable {

    public abstract T getId();
    
    @CreatedDate
    @Column(name="created_date",updatable = false)
    private Instant createdDate=Instant.now();

    @LastModifiedDate
    @Column(name="last_modified_date")
    private Instant lastModifiedDatet=Instant.now();
   
}
