package com.wfhmarket.api.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Contract")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "freelancer_id")
    private Project project_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "freelancer_id")
    private Member freelancer_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id")
    private Member client_id;

    private String agreed_price;

    private String start_date;

    private String end_date;

    private String status;

    private String created_at;

    private String updated_at;

    @Builder
    public Contract(Long contract_id, Project project_id, Member freelancer_id, Member client_id, String agreed_price, String start_date, String end_date, String status, String created_at, String updated_at) {
        this.contract_id = contract_id;
        this.project_id = project_id;
        this.freelancer_id = freelancer_id;
        this.client_id = client_id;
        this.agreed_price = agreed_price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
