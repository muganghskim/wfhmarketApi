//package com.wfhmarket.api.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "Review")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long review_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "reviewer_id")
//    private Member reviewer_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "reviewed_id")
//    private Member reviewed_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "project_id")
//    private Project project_id;
//
//    private String rating;
//
//    private String comment;
//
//    private String created_at;
//
//    private String updated_at;
//
//    @Builder
//    public Review(Long review_id, Member reviewer_id, Member reviewed_id, Project project_id, String rating, String comment, String created_at, String updated_at) {
//        this.review_id = review_id;
//        this.reviewer_id = reviewer_id;
//        this.reviewed_id = reviewed_id;
//        this.project_id = project_id;
//        this.rating = rating;
//        this.comment = comment;
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
@Table("Review")
public class Review {
    @Id
    private Long review_id;

    @Column("reviewer_id")
    private Long reviewer_id;

    @Column("reviewed_id")
    private Long reviewed_id;

    @Column("project_id")
    private Long project_id;

    private String rating;

    private String comment;

    private String created_at;

    private String updated_at;
}

