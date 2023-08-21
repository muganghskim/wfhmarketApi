//package com.wfhmarket.api.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "Milestone_Payment")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Milestone_Payment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long payment_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "milestone_id")
//    private Milestone milestone_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "payer_id")
//    private Member payer_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "recipient_id")
//    private Member recipient_id;
//
//    private String amount;
//
//    private String payment_status;
//
//    private String payment_date;
//
//    private String created_at;
//
//    private String updated_at;
//
//    @Builder
//    public Milestone_Payment(Long payment_id,Milestone milestone_id, Member payer_id,Member recipient_id,String amount,String payment_status,String payment_date, String created_at, String updated_at) {
//        this.payment_id = payment_id;
//        this.milestone_id = milestone_id;
//        this.payer_id = payer_id;
//        this.recipient_id = recipient_id;
//        this.amount = amount;
//        this.payment_status = payment_status;
//        this.payment_date = payment_date;
//        this.created_at = created_at;
//        this.updated_at = updated_at;
//    }
//}

package com.wfhmarket.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("Milestone_Payment")
public class Milestone_Payment {
    @Id
    private Long payment_id;

    @Column("milestone_id")
    private Long milestone_id;

    @Column("payer_id")
    private Long payer_id;

    @Column("recipient_id")
    private Long recipient_id;

    private String amount;

    private String payment_status;

    private String payment_date;

    private String created_at;

    private String updated_at;
}

