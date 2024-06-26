package com.bankapp.app.entity;

import com.bankapp.app.enums.AccountStatus;
import com.bankapp.app.enums.AccountType;
import com.bankapp.app.enums.CurrencyCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Getter
@Setter

/**
 * ----- Russian ------
 * <p>
 * Это сущность, которая представляет собой аккаунт (счет) в банковской системе.
 * Эта сущность содержит информацию о конкретном счете и связанных с ним данных.
 * <p>
 * ----- English -------
 * <p>
 * This entity represents an account in the banking system.
 * It contains information about a specific account and related data.
 */
public class Account {

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
    private UUID id;

    /**
     * ----- Russian ------
     * <p>
     * Это название счета, которое помогает идентифицировать его и понять его предназначение.
     * <p>
     * ----- English -------
     * <p>
     * The name of the account, helping to identify it and understand its purpose.
     */
    @Column(name = "name")
    private String name;


    /**
     * ---- Russian -------
     * <p>
     * Тип счета, который может указывать на его характеристики,
     * такие как "сберегательный счет", "текущий счет" и т. д.
     * <p>
     * ----- English -------
     * <p>
     * The type of the account, which may indicate its characteristics,
     * such as "savings account," "checking account," etc.
     */
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    /**
     * ---- Russian -------
     * <p>
     * Статус счета, который может указывать на его состояние, такие как "активен", "закрыт" и т. д.
     * <p>
     * ----- English -------
     * <p>
     * The status of the account, which may indicate its state, such as "active," "closed," etc.
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    /**
     * ---- Russian -------
     * <p>
     * Код валюты, в которой ведется учет средств на счете, например, "USD" или "EUR".
     * <p>
     * ----- English -------
     * <p>
     * The currency code in which funds in the account are tracked, e.g., "USD" or "EUR."
     */
    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyCode code;

    /**
     * ---- Russian -------
     * <p>
     * Баланс (остаток) средств на счете.
     * <p>
     * ----- English -------
     * <p>
     * The balance (remaining funds) in the account.
     */
    @Column(name = "balance")
    private BigDecimal balance;

    /**
     * ---- Russian -------
     * <p>
     * Дата и время создания счета.
     * <p>
     * ----- English -------
     * <p>
     * Date and time of account creation.
     */
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    /**
     * ---- Russian -------
     * <p>
     * Дата и временя последнего обновления записи в базе данных.
     * <p>
     * ----- English -------
     * <p>
     * Date and time of the last update to the database record.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    //@LocalDate
    private Timestamp updatedAt;

    /**
     * ---- Russian -------
     * <p>
     * Клиент, связанный с данным счетом.
     * <p>
     * ----- English -------
     * <p>
     * The client associated with this account.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    /**
     * ---- Russian -------
     * <p>
     * Список договоров или соглашений, связанных с этим счетом.
     * <p>
     * ----- English -------
     * <p>
     * The list of agreements associated with this account.
     */
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Agreement> agreementList;

    /**
     * ---- Russian -------
     * <p>
     * Это операции списания средств с банковского счета или карточки.
     * <p>
     * ----- English -------
     * <p>
     * Operations representing withdrawal of funds from the bank account or card.
     */
    @OneToMany(mappedBy = "debitAccount")
    private Set<Transaction> debitTransaction;

    /**
     * ---- Russian -------
     * <p>
     * Это финансовая операция, при которой средства перечисляются на банковский счет или кредитную/дебетовую карту клиента.
     * <p>
     * ----- English -------
     * <p>
     * Financial operation where funds are transferred to the client's bank account or credit/debit card.
     */
    @OneToMany(mappedBy = "creditAccount")
    private Set<Transaction> creditTransaction;

    /**
     * ---- Russian -------
     * <p>
     * Представляет собой класс с различными свойствами, представляющими информацию о банковской карте.
     * <p>
     * ----- English -------
     * <p>
     * Represents a class with various properties representing information about a bank card.
     */

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(name, account.name) && status == account.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + name + '\'' +
                ", accountType=" + type +
                ", accountStatus=" + status +
                ", currencyCode=" + code +
                ", accountBalance=" + balance +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", client=" + client.getLastName() +
                '}';
    }
}
