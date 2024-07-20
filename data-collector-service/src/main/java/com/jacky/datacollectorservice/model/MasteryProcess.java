package com.jacky.datacollectorservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class MasteryProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_unique_name", referencedColumnName = "unique_name")
    private Item item;

    private EMasteryStatus eMasteryStatus;

    private Integer priorityLevel;

}
