package ru.webapp.notaryoffice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="deals")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "deals_services",
            joinColumns = @JoinColumn(name = "deal_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false))
    private List<Service> services = new ArrayList<>();;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long commission_fees;

    @Column()
    private String description;
}
