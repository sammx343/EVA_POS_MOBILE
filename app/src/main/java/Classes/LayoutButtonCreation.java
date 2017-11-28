package Classes;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.smayor.myapplication.R;

import java.util.ArrayList;

/**
 * Created by smayor on 28/11/2017.
 */

public class LayoutButtonCreation {

    public ArrayList<LinearLayout> layouts = new ArrayList<>();

    public LinearLayout rootView(int rows, int columns, Context context){
        LinearLayout lrl = new LinearLayout(context);
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        rlp.setMargins(0,5,0,0);
        lrl.setLayoutParams(rlp);
        lrl.setOrientation(LinearLayout.VERTICAL);
        lrl.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        lrl.setWeightSum(rows);

        for(int index = 0; index < rows; index++){
            lrl.addView(createLinearLayout(rows, columns, context));
        }
        return lrl;
    }

    public LinearLayout createLinearLayout(int rows, int columns, Context context){
        LinearLayout layout2 = new LinearLayout(context);
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,1);
        rlp.setMargins(0,0,3,0);
        layout2.setLayoutParams(rlp);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setWeightSum(columns);
        for(int index = 0; index < columns; index++){
            layout2.addView(createButton(context));
        }
        return layout2;
    }

    public LinearLayout createButton(Context context){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,  1);
        params.setMargins(0,3,3,0);

        LinearLayout layout = new LinearLayout(context);
        layout.setWeightSum(2);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setLayoutParams(params);

        layout.setBackgroundColor(context.getColor(R.color.colorPrimaryDark));
        layouts.add(layout);
        return layout;
    }
}
