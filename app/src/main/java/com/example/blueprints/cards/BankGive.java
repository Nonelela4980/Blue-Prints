package com.example.blueprints.cards;

import android.graphics.drawable.Drawable;

import com.example.blueprints.R;

public class BankGive extends Assignment{

    public BankGive(int direction, Resource resource){

        this.direction=direction;
        setUpCard(resource);
    }

    private void setUpCard(Resource resource)
    {

        if(resource instanceof Brick)
        {
            switch (resource.No_resources)
            {
                case 1:drawable=getDrawable(R.drawable.instruction_bank_give_1_brick);
                break;
                case 2:drawable=getDrawable(R.drawable.instruction_bank_give_2_brick);
                break;
                case 3:drawable=getDrawable(R.drawable.instruction_bank_give_3_brick);
                    break;
            }
        }
        else if(resource instanceof Straw)
        {
            switch (resource.No_resources)
            {
                case 1:drawable=getDrawable(R.drawable.instruction_bank_give_1_straw);
                    break;
                case 2:drawable=getDrawable(R.drawable.instruction_bank_give_2_straw);
                    break;
                case 3:drawable=getDrawable(R.drawable.instruction_bank_give_3_straw);
                    break;
            }
        }
        else
            {
                switch (resource.No_resources)
                {
                    case 1:drawable=getDrawable(R.drawable.instruction_bank_give_1_wood);
                        break;
                    case 2:drawable=getDrawable(R.drawable.instruction_bank_give_2_wood);
                        break;
                    case 3:drawable=getDrawable(R.drawable.instruction_bank_give_3_wood);
                        break;
                }
            }
    }


}
