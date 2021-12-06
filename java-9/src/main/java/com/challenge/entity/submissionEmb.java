package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class submissionEmb {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "challenge_id")
    private challenge challenge;
}
