package com.bankapp.app.entity;

import com.bankapp.app.enums.ManagerStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "managers")
@NoArgsConstructor
@Getter
@Setter

/**
 * ----- Russian ------
 * <p>
 * Этот класс представляет собой менеджера в банковской системе.
 * <p>
 *  ----- English -------
 * This class represents a manager in the banking system.
 */
public class Manager {

    /**
     * ----- Russian ------
     * <p>
     * Идентификации уникальной записи или объекта в базе данных.
     * <p>
     * ----- English -------
     * <p>
     * Unique identifier of the record or object in the database.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    @SequenceGenerator(name = "manager_entity_generator", sequenceName = "manager_entyti_seq", allocationSize = 1)
    private UUID id;

    /**
     * ----- Russian ------
     * <p>
     * Имя менеджера.
     * <p>
     * ----- English -------
     * <p>
     * First name of the manager.
     */

    @Column(name = "first_name")
    private String firstName;

    /**
     * ----- Russian ------
     * <p>
     * Фамилия менеджера.
     * <p>
     * ----- English -------
     * <p>
     * Last name of the manager.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * ----- Russian ------
     * <p>
     * Статус менеджера (например, активен, неактивен и т. д.).
     * <p>
     * ----- English -------
     * <p>
     * Status of the manager (e.g., active, inactive, etc.).
     */
    @Column(name = "manager_status")
    @Enumerated(EnumType.STRING)
    private ManagerStatus status;

    /**
     * ----- Russian ------
     * <p>
     * Дата и время создания записи о менеджере.
     * <p>
     * ----- English -------
     * <p>
     * Date and time when the manager record was created.
     */
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    /**
     * ----- Russian ------
     * <p>
     * Дата и время последнего обновления записи о менеджере.
     * <p>
     * ----- English -------
     * <p>
     * Date and time of the last update to the manager record.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    /**
     * ----- Russian ------
     * <p>
     * Список клиентов, связанных с данным менеджером.
     * <p>
     * ----- English -------
     * <p>
     * List of clients associated with this manager.
     */

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private List<Client> listClients;

    /**
     * ----- Russian ------
     * <p>
     * Список продуктов, связанных с данным менеджером.
     * <p>
     * ----- English -------
     * <p>
     * List of products associated with this manager.
     */

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private List<Product> listProducts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager manager)) return false;
        return Objects.equals(id, manager.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", clients=" + listClients +
                ", products=" + listProducts +
                '}';
    }
}
