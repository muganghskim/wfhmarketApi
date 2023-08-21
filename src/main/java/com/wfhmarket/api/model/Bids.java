//package com.wfhmarket.api.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "Bids")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Bids {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long bid_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "project_id")
//    private Project project_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "freelancer_id")
//    private Member freelancer_id;
//
//    private String description;
//
//    private int price;
//
//    private String estimated_completion_date;
//
//    private String resume;
//
//    private String created_at;
//
//    private String updated_at;
//
//    @Builder
//    public Bids(Long bid_id, Project project_id, Member freelancer_id, String description, int price, String estimated_completion_date,String resume, String created_at, String updated_at) {
//        this.bid_id = bid_id;
//        this.project_id = project_id;
//        this.freelancer_id = freelancer_id;
//        this.description = description;
//        this.price = price;
//        this.estimated_completion_date = estimated_completion_date;
//        this.resume = resume;
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
@Table("Bids")
public class Bids {

    @Id
    private Long bid_id;

    @Column("project_id")
    private Long project_id;

    @Column("freelancer_id")
    private Long freelancer_id;

    private String description;

    private int price;

    private String estimated_completion_date;

    private String resume;

    private String created_at;

    private String updated_at;
}
