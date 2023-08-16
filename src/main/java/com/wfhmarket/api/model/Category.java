package com.wfhmarket.api.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    private String category_name;

    private String created_at;

    private String updated_at;

    @Builder
    public Category(Long category_id, String category_name, String created_at, String updated_at) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
