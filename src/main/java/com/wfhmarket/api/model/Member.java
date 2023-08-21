//package com.wfhmarket.api.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "Member")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//
//    private String user_type;
//
//    private String email;
//
//    private String password;
//
//    private String name;
//
//    private String profile_img;
//
//    private String resume;
//
//    private String introduction;
//
//    private String created_at;
//
//    private String updated_at;
//
////    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
////    private List<Project> projectList = new ArrayList<>();
////
////    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
////    private List<Wishlist> wishList = new ArrayList<>();
////
////    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
////    private List<Transaction> transactionList = new ArrayList<>();
//    @Builder
//    public Member(Long userId, String user_type, String email, String password, String name, String profile_img, String resume, String introduction, String created_at, String updated_at) {
//        this.userId = userId;
//        this.user_type = user_type;
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.profile_img = profile_img;
//        this.resume = resume;
//        this.introduction = introduction;
//        this.created_at = created_at;
//        this.updated_at = updated_at;
//    }
//}

package com.wfhmarket.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("Member")
public class Member {

    @Id
    private Long userId;

    private String user_type;

    private String email;

    private String password;

    private String name;

    private String profile_img;

    private String resume;

    private String introduction;

    private String created_at;

    private String updated_at;

    public UserDetails toUserDetails() {
        List<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user_type));
        return new User(email, password, authorities);
    }
}

