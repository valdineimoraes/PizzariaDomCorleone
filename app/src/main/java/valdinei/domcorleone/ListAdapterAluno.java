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

import Model.Aluno;

/**
 * Created by valdinei on 18/11/17.
 */

public class ListAdapterAluno extends ArrayAdapter<Aluno> {

    private Context context;
    private ArrayList<Aluno> listaAluno;

    public ListAdapterAluno(@NonNull Context context, ArrayList<Aluno> lista) {
        super(context, 0, lista);
        this.context = context;
        this.listaAluno = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Aluno itemPosicao = this.getItem(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista_alunos, null);

        TextView txtCodigo = (TextView) convertView.findViewById(R.id.txtCodigoAlunoLista);
        txtCodigo.setText(String.valueOf(itemPosicao.getId()));

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNomeAlunoLista);
        txtNome.setText(itemPosicao.getNome());

        TextView txtTelefone = (TextView) convertView.findViewById(R.id.txtTelefoneAluno);
        txtTelefone.setText(itemPosicao.getTelefone());

        TextView txtEndereco = (TextView) convertView.findViewById(R.id.txtEnderecoAluno);
        txtEndereco.setText(itemPosicao.getEndereco());

        TextView txtResponsavel = (TextView) convertView.findViewById(R.id.txtResponsavelAlunoLista);
        txtResponsavel.setText(itemPosicao.getResponsavel());

        return convertView;
    }


}
