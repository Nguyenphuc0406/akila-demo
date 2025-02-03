package com.akila.demo.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "account")
public class Account extends InitializationInfo implements Serializable {

    private static final long serialVersionUID = -2394258836408474013L;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

//    @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    private List<Transaction> sentTransactions;
//
//    @OneToMany(mappedBy = "toAccount", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    private List<Transaction> receivedTransactions;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<Wallet> wallets;

}
