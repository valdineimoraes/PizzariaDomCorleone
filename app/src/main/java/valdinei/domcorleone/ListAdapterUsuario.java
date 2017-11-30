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

import Model.Usuario;

/**
 * Created by valdinei on 18/11/17.
 */

public class ListAdapterUsuario extends ArrayAdapter<Usuario> {

    private Context context;
    private ArrayList<Usuario> listaUsuario;

    public ListAdapterUsuario(@NonNull Context context, ArrayList<Usuario> lista) {
        super(context, 0, lista);
        this.context = context;
        this.listaUsuario = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Usuario itemPosicao = this.getItem(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista_usuarios, null);

        TextView txtCodigo = (TextView) convertView.findViewById(R.id.txtCodigoListaUser);
        txtCodigo.setText(String.valueOf(itemPosicao.getId()));

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNomeListaUser);
        txtNome.setText(itemPosicao.getNome());

        TextView txtUsuario = (TextView) convertView.findViewById(R.id.txtUsuarioListaUser);
        txtUsuario.setText(itemPosicao.getUsuario());

        TextView txtSenha = (TextView) convertView.findViewById(R.id.txtSenhaListaUser);
        txtSenha.setText(itemPosicao.getCargo());

        TextView txtCargo = (TextView) convertView.findViewById(R.id.txtCargoListaUser);
        txtCargo.setText(itemPosicao.getCargo());

        TextView txtTelefone = (TextView) convertView.findViewById(R.id.txtTelefoneListaUser);
        txtTelefone.setText(itemPosicao.getTelefone());

        return convertView;
    }


}
