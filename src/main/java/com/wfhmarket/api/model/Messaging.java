package com.wfhmarket.api.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Messaging")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Messaging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sender_id")
    private Member sender_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reciver_id")
    private Member reciver_id;

    private String content;

    private String created_at;

    private String updated_at;

    @Builder
    public Messaging(Long message_id, Member sender_id, Member reciver_id, String content, String created_at, String updated_at) {
        this.message_id = message_id;
        this.sender_id = sender_id;
        this.reciver_id = reciver_id;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
