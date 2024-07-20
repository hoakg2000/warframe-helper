package com.jacky.marketservice.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    @Id
    @JsonProperty("id")
    @Column(name = "item_id", nullable = false)
    private String itemId;

    @NotNull
    @JsonProperty("item_name")
    private String itemName;

    @NotNull
    private String thumb;

    @NotNull
    @JsonProperty("url_name")
    private String urlName;

    @JsonProperty("vaulted")
    private Boolean vaulted = false;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private TrackingItem trackingItem;

}
