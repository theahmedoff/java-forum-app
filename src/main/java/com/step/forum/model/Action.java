package com.step.forum.model;

import lombok.Data;

import java.util.List;

@Data
public class Action {
    private int id;
    private String actionType;
    private List<Role> roleList;


}
