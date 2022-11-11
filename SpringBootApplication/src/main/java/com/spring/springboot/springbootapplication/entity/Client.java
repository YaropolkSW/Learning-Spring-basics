package com.spring.springboot.springbootapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "city")
    private String clientCity;

    @OneToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE})
    @JoinColumn(name = "car_id")
    private Car clientCarId;
}
