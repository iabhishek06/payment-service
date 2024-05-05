package com.javams.paymentservice.repository;

import com.javams.paymentservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    // TODO : add DB constraints so that the amount does not go below 0
    // Hibernate does not allow to modify the table, so we have to add @Modifying, then it will allow the update

    @Modifying
    @Query("update Account a set a.amount = a.amount + :amount where a.accountId = :senderAccountId")
    void addBalance(Long senderAccountId, Double amount);


    @Modifying
    @Query("update Account a set a.amount = a.amount - :amount where a.accountId = :receiverAccountId")
    void removeBalance(Long receiverAccountID, Double amount);

}
