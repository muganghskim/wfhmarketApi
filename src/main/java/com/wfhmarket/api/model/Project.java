package com.wfhmarket.api.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id")
    private Member client_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id")
    private Category category_id;

    private String title;

    private String description;

    private String budget;

    private String deadline;

    private String status;

    private String created_at;

    private String updated_at;

    @Builder
    public Project(Long project_id, Member client_id, Category category_id, String title, String description, String budget, String deadline, String status, String created_at, String updated_at) {
        this.project_id = project_id;
        this.client_id = client_id;
        this.category_id = category_id;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.deadline = deadline;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}
