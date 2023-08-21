//package com.wfhmarket.api.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "Skills")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Skills {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long skill_id;
//
//    private String skill_name;
//
//    @Builder
//    public Skills(Long skill_id, String skill_name) {
//        this.skill_id = skill_id;
//        this.skill_name = skill_name;
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
@Table("Skills")
public class Skills {
    @Id
    private Long skill_id;

    private String skill_name;
}
