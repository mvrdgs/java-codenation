package com.challenge.entity;

import com.challenge.SpringChallengeApplication;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@EntityListeners(SpringChallengeApplication.class)
public class candidate {

    @EmbeddedId
    private candidateEmb candidateEmb;

    @NotNull
    private int status;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    public com.challenge.entity.candidateEmb getCandidateEmb() {
        return candidateEmb;
    }

    public void setCandidateEmb(com.challenge.entity.candidateEmb candidateEmb) {
        this.candidateEmb = candidateEmb;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(Date created_at) {
        this.createdAt = created_at;
    }
}
