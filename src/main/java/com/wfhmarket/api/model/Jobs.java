//package com.wfhmarket.api.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "Jobs")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Jobs {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long job_id;
//
//    private String job_name;
//
//    private String job_grade;
//
//    private String description;
//
//    private int required_exp_points;
//
//    @Builder
//    public Jobs(Long job_id, String job_name, String job_grade, String description, int required_exp_points) {
//        this.job_id = job_id;
//        this.job_name = job_name;
//        this.job_grade = job_grade;
//        this.description = description;
//        this.required_exp_points = required_exp_points;
//    }
//}

package com.wfhmarket.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("Jobs")
public class Jobs {
    @Id
    private Long job_id;

    private String job_name;

    private String job_grade;

    private String description;

    private int required_exp_points;
}
