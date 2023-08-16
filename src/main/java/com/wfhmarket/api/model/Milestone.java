package com.wfhmarket.api.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Milestone")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestone_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "project_id")
    private Project project_id;

    private String title;

    private String description;

    private String amount;

    private String due_date;

    private String status;

    private String created_at;

    private String updated_at;

    @Builder
    public Milestone(Long milestone_id,Project project_id, String title,String description,String amount,String due_date,String status, String created_at, String updated_at) {
        this.milestone_id = milestone_id;
        this.project_id = project_id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.due_date = due_date;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}