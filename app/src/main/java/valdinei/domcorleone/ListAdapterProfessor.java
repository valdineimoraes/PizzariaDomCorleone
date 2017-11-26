package valdinei.domcorleone;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import Model.Professor;

/**
 * Created by valdinei on 18/11/17.
 */

public class ListAdapterProfessor extends ArrayAdapter<Professor> {


    public ListAdapterProfessor(@NonNull Context context, ArrayList<Professor> professores) {
        super(context, R.layout.spinner_professores);
    }

    //public View getDropDownView(int position, View convert)
}
