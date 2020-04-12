package com.bsuir.models;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    private String id;
    private String name;
    private String description;
    private String title;
    private List<String> listOfLinks = new LinkedList<>();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Page> pages = new LinkedList<>();

}
