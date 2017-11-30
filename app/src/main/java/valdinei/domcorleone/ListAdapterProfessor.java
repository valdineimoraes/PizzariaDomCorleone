package valdinei.domcorleone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Professor;

/**
 * Created by valdinei on 18/11/17.
 */

public class ListAdapterProfessor extends ArrayAdapter<Professor>  {

    private Context context;
    private ArrayList<Professor> listaProf;

    public ListAdapterProfessor(@NonNull Context context, ArrayList<Professor> lista) {
        super(context, 0, lista);
        this.context = context;
        this.listaProf = lista;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Professor itemPosicao = this.getItem(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista_professores, null);

        TextView txtCodigo = (TextView) convertView.findViewById(R.id.txtCodigoListaUser);
        txtCodigo.setText(String.valueOf(itemPosicao.getId()));

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNomeListaUser);
        txtNome.setText(itemPosicao.getNome());

        TextView txtTelefone = (TextView) convertView.findViewById(R.id.txtTelefoneListaProf);
        txtTelefone.setText(itemPosicao.getTelefone());

        TextView txtFormacao = (TextView) convertView.findViewById(R.id.txtFormacaoListaProf);
        txtFormacao.setText(itemPosicao.getFormacao());

        return convertView;
    }

}
