package com.example.blueprints.models.cards;

import com.example.blueprints.R;
import com.example.blueprints.models.resources.Brick;
import com.example.blueprints.models.resources.Resource;
import com.example.blueprints.models.resources.Wood;

public class IFCard extends Assignment {

    public IFCard(int direction, Resource resource) {
        this.direction = direction;
        this.initialize(resource);
    }

    @Override
    public void initialize(Resource resource) {

        if (resource instanceof Brick) {
            switch (resource.No_resources) {
                case 1:
                    this.drawable = getDrawable(R.drawable.instruction_has_1_brick);
                    break;
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_has_2_brick);
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_has_3_brick);
                    break;
            }
        } else if (resource instanceof Wood) {
            switch (resource.No_resources) {
                case 1:
                    this.drawable = getDrawable(R.drawable.instruction_has_1_wood);
                    break;
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_has_2_wood);
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_has_3_wood);
                    break;
            }
        } else {
            switch (resource.No_resources) {
                case 1:
                    this.drawable = getDrawable(R.drawable.instruction_has_1_straw);
                    break;
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_has_2_straw);
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_has_3_straw);
                    break;
            }
        }
    }
}
