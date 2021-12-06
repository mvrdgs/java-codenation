package com.challenge.entity;

import com.challenge.SpringChallengeApplication;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@EntityListeners(SpringChallengeApplication.class)
public class submission {

    @EmbeddedId
    private submissionEmb submissionEmb;

    @NotNull
    private double score;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    public com.challenge.entity.submissionEmb getSubmissionEmb() {
        return submissionEmb;
    }

    public void setSubmissionEmb(com.challenge.entity.submissionEmb submissionEmb) {
        this.submissionEmb = submissionEmb;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(Date created_at) {
        this.createdAt = created_at;
    }
}
