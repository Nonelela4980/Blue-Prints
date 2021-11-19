package com.example.blueprints.gamelogic;

import com.example.blueprints.models.cards.Assignment;
import com.example.blueprints.models.resources.Resource;

import java.util.List;

public class Player {
    int victoryPoints;
    List<Resource> resourceList;
    List<Assignment> assignmentCards;

    public Player(int victoryPoints, List<Resource> resourceList, List<Assignment> assignmentCards)
    {
        this.victoryPoints = victoryPoints;
        this.resourceList = resourceList;
        this.assignmentCards = assignmentCards;
    }
}
