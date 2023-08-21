//package com.wfhmarket.api.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "User_Skills")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class User_Skills {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long user_skill_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "user_id")
//    private Member user_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "skill_id")
//    private Skills skill_id;
//
//    @Builder
//    public User_Skills(Long user_skill_id, Member user_id, Skills skill_id) {
//        this.user_skill_id = user_skill_id;
//        this.user_id = user_id;
//        this.skill_id = skill_id;
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
@Table("User_Skills")
public class User_Skills {
    @Id
    private Long user_skill_id;

    @Column("user_id")
    private Long user_id;

    @Column("skill_id")
    private Long skill_id;
}

