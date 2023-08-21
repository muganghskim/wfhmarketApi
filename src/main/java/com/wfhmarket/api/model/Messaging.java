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
@Table("Messaging")
public class Messaging {
    @Id
    private Long message_id;

    @Column("sender_id")
    private Long sender_id;

    @Column("reciver_id")
    private Long reciver_id;

    private String content;

    private String created_at;

    private String updated_at;
}
