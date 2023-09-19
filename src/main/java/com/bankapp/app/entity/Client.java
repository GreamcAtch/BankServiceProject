package com.bankapp.app.entity;

import com.bankapp.app.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "client")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")

    @Column(name = "id")
    private UUID id;

    @Column(name = "client_status")
    private ClientStatus status;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "e-mail")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_numer")
    private String phone;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "accounts")
    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Account> accounts;

    @Column(name = "manager")
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY,
    orphanRemoval = true, cascade = {MERGE,PERSIST,REFRESH})
    private List<Manager> manager;

    public Client(UUID id, ClientStatus status, String taxCode, String firstName,
                  String lastName, String email, String address, String phone,
                  Timestamp createdAt, Timestamp updatedAt, List<Account> accounts, List<Manager> manager) {
        this.id = id;
        this.status = status;
        this.taxCode = taxCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accounts = accounts;
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(taxCode, client.taxCode) && Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxCode, phone);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", status=" + status +
                ", taxCode=" + taxCode +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", accounts=" + accounts +
                ", manager=" + manager +
                '}';
    }
}
