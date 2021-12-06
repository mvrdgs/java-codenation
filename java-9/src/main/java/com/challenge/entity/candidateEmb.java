package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class candidateEmb {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "acceleration_id")
    private acceleration acceleration;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "company_id")
    private company company;
}
