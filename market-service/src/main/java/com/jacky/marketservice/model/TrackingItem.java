package com.jacky.marketservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrackingItem {

    @Id
    @Column(name = "item_id")
    private String itemId;

    @MapsId
    @OneToOne(mappedBy = "trackingItem")
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    private Item item;

    private String orderType;

    private Integer lowerBound = 0;

    private Integer upperBound = Integer.MAX_VALUE;

}
