package org.sid.bankaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.bankaccountservice.enums.AccountType;

import javax.persistence.*;
import java.util.Date;
@Entity@Data @AllArgsConstructor @Builder
@NoArgsConstructor

public class BankAccount {
    @Id
    private  String id;
    private Date creatAt;

    private  Double balance;
    private  String currency;
    @Enumerated(EnumType.STRING)

    private AccountType type;
}
