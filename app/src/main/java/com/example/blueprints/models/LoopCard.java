package com.example.blueprints.models;

import com.example.blueprints.R;

public class LoopCard extends Assignment {

    public LoopCard(int direction, Resource resource) {
        this.direction = direction;
        this.initialize(resource);
    }

    @Override
    public void initialize(Resource resource) {
        if (resource instanceof Brick) {
            switch (resource.No_resources) {
                case 1:
                    this.drawable = getDrawable(R.drawable.instruction_loop_1_brick);
                    break;
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_loop_2_brick);
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_loop_3_brick);
            }
        } else if (resource instanceof Wood) {
            switch (resource.No_resources) {
                case 1:
                    this.drawable = getDrawable(R.drawable.instruction_loop_1_wood);
                    break;
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_loop_2_wood);
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_loop_3_wood);
            }
        } else {
            switch (resource.No_resources) {
                case 1:
                    this.drawable = getDrawable(R.drawable.instruction_loop_1_straw);
                    break;
                case 2:
                    this.drawable = getDrawable(R.drawable.instruction_loop_2_straw);
                    break;
                case 3:
                    this.drawable = getDrawable(R.drawable.instruction_loop_3_straw);
            }
        }
    }
}
