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

import Model.Turma;
import Model.Usuario;

/**
 * Created by valdinei on 18/11/17.
 */

public class ListAdapterTurma extends ArrayAdapter<Turma> {

    private Context context;
    private ArrayList<Turma> listaTurma;

    public ListAdapterTurma(@NonNull Context context, ArrayList<Turma> lista) {
        super(context, 0, lista);
        this.context = context;
        this.listaTurma = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Turma itemPosicao = this.getItem(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista_turmas, null);

        TextView txtCodigo = (TextView) convertView.findViewById(R.id.txtCodigoListaTurma);
        txtCodigo.setText(String.valueOf(itemPosicao.getId()));

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNomeListaTurma);
        txtNome.setText(itemPosicao.getNome());

        TextView txtTotalAlunos = (TextView) convertView.findViewById(R.id.txtTotalAlunosListaTurma);
        txtTotalAlunos.setText(itemPosicao.getTotalAlunos());

        TextView txtSala = (TextView) convertView.findViewById(R.id.txtSalaListaTurma);
        txtSala.setText(itemPosicao.getSala());

        TextView txtProfessor = (TextView) convertView.findViewById(R.id.txtProfessorListaTurma);
        txtCodigo.setText(itemPosicao.getProfessorNome());


        return convertView;
    }


}
