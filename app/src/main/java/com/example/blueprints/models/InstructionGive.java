package com.example.blueprints.models;

import com.example.blueprints.R;

public class InstructionGive extends Assignment
{

    public InstructionGive(int direction, Resource resource)
    {
        this.direction=direction;
        this.initialize(resource);
    }
    @Override
    public void initialize(Resource resource)
    {
        //A card is chosen based on direction and the type of resource
           if(resource instanceof Brick){
               switch (resource.No_resources)
               {
                   case 1: if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_1_brick);
                            else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_1_brick);
                            else {this.drawable=getDrawable(R.drawable.instruction_give_right_1_brick);}break;
                   case 2:if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_2_brick);
                   else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_2_brick);
                   else {this.drawable=getDrawable(R.drawable.instruction_give_right_2_brick);}break;
                   case 3:if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_3_brick);
                   else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_3_brick);
                   else {this.drawable=getDrawable(R.drawable.instruction_give_right_3_brick);}break;
               }
           }
           else if(resource instanceof Wood){
               switch (resource.No_resources)
               {
                   case 1: if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_1_wood);
                   else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_1_wood);
                   else {this.drawable=getDrawable(R.drawable.instruction_give_right_1_brick);}break;
                   case 2:if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_2_wood);
                   else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_2_wood);
                   else {this.drawable=getDrawable(R.drawable.instruction_give_right_2_brick);}break;
                   case 3:if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_3_wood);
                   else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_3_wood);
                   else {this.drawable=getDrawable(R.drawable.instruction_give_right_3_wood);}break;
               }
           }
           else{
               switch (resource.No_resources)
               {
                   case 1: if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_1_straw);
                   else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_1_straw);
                   else {this.drawable=getDrawable(R.drawable.instruction_give_right_1_brick);}break;
                   case 2:if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_2_straw);
                   else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_2_straw);
                   else {this.drawable=getDrawable(R.drawable.instruction_give_right_2_brick);}break;
                   case 3:if(this.direction==90)this.drawable=getDrawable(R.drawable.instruction_give_3_straw);
                   else if(this.direction==180)this.drawable=getDrawable(R.drawable.instruction_give_left_3_straw);
                   else {this.drawable=getDrawable(R.drawable.instruction_give_right_3_straw);}break;
               }
           }
    }
}
