package com.jacky.datacollectorservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_unique_name", referencedColumnName = "unique_name")
    @JsonBackReference
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_component_unique_name", referencedColumnName = "unique_name")
    private Item itemComponent;

    @Column(name = "item_count")
    private Long itemCount;

    public Item getItemComponent(){
        Item removedField = this.itemComponent;
        removedField.removeNotImportantField();
        return removedField;
    }
}
