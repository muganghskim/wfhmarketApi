//package com.wfhmarket.api.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "User_Jobs")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class User_Jobs {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long user_job_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "user_id")
//    private Member user_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "job_id")
//    private Jobs job_id;
//
//    private String level;
//
//    private int exp_points;
//
//    private String job_title;
//
//    private String job_title_img;
//
//    @Builder
//    public User_Jobs(Long user_job_id, Member user_id, Jobs job_id, String level, int exp_points, String job_title, String job_title_img) {
//        this.user_job_id = user_job_id;
//        this.user_id = user_id;
//        this.job_id = job_id;
//        this.level = level;
//        this.exp_points = exp_points;
//        this.job_title = job_title;
//        this.job_title_img = job_title_img;
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
@Table("User_Jobs")
public class User_Jobs {
    @Id
    private Long user_job_id;

    @Column("user_id")
    private Long user_id;

    @Column("job_id")
    private Long job_id;

    private String level;

    private int exp_points;

    private String job_title;

    private String job_title_img;
}

