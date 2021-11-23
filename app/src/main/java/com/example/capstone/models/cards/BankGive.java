package com.example.capstone.models.cards;

import com.example.capstone.R;
import com.example.capstone.models.resources.Brick;
import com.example.capstone.models.resources.Resource;
import com.example.capstone.models.resources.Straw;

public class BankGive extends Assignment {

    public BankGive(int direction, Resource resource)
    {
        this.direction=direction;
        this.initialize(resource);
    }
    @Override
    public void initialize(Resource resource)
    {
        if(resource instanceof Brick)
        {
            switch (resource.No_resources)
            {
                case 1:this.drawable=getDrawable(R.drawable.instruction_bank_give_1_brick);
                break;
                case 2:this.drawable=getDrawable(R.drawable.instruction_bank_give_2_brick);
                break;
                case 3:this.drawable=getDrawable(R.drawable.instruction_bank_give_3_brick);
                    break;
            }
        }
        else if(resource instanceof Straw)
        {
            switch (resource.No_resources)
            {
                case 1:this.drawable=getDrawable(R.drawable.instruction_bank_give_1_straw);
                    break;
                case 2:this.drawable=getDrawable(R.drawable.instruction_bank_give_2_straw);
                    break;
                case 3:this.drawable=getDrawable(R.drawable.instruction_bank_give_3_straw);
                    break;
            }
        }
        else
            {
                switch (resource.No_resources)
                {
                    case 1:this.drawable=getDrawable(R.drawable.instruction_bank_give_1_wood);
                        break;
                    case 2:this.drawable=getDrawable(R.drawable.instruction_bank_give_2_wood);
                        break;
                    case 3:this.drawable=getDrawable(R.drawable.instruction_bank_give_3_wood);
                        break;
                }
            }
    }


}
