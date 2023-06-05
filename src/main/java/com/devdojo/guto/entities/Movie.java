package com.devdojo.guto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "FILMES")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String movieName;

    @Column(name = "DESCRICAO")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "director")
    private Director director;
}
