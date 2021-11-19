package com.example.blueprints.models.cards;

import com.example.blueprints.R;
import com.example.blueprints.models.cards.Assignment;
import com.example.blueprints.models.resources.Brick;
import com.example.blueprints.models.resources.Resource;
import com.example.blueprints.models.resources.Wood;

public class BluePrints extends Assignment {

    private int victoryPoints;

    public BluePrints(int direction, Resource resource) {
        this.direction = direction;
        this.victoryPoints = 0;
        this.initialize(resource);
    }
    @Override
    public void initialize(Resource resource) {

        //assign victory points based on the number of resources
        if (resource instanceof Brick) {
            switch (resource.No_resources) {
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_blueprint_2_brick);
                    victoryPoints = 1;
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_blueprint_3_brick);
                    victoryPoints = 2;
                    break;
            }
        } else if (resource instanceof Wood) {
            switch (resource.No_resources) {
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_blueprint_2_wood);
                    victoryPoints = 1;
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_blueprint_3_wood);
                    victoryPoints = 2;
                    break;
            }
        } else {
            switch (resource.No_resources) {
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_blueprint_2_straw);
                    victoryPoints = 1;
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_blueprint_3_straw);
                    victoryPoints = 2;
                    break;
            }
        }

    }
}
