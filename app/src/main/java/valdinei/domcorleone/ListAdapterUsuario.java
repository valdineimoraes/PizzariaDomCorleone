package valdinei.domcorleone;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.LayoutRes;
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

        TextView txtCodigo = (TextView) convertView.findViewById(R.id.txtCodigoLista);
        txtCodigo.setText(String.valueOf(itemPosicao.getId()));

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNomeLista);
        txtNome.setText(itemPosicao.getNome());

        TextView txtTelefone = (TextView) convertView.findViewById(R.id.txtTelefoneLista);
        txtTelefone.setText(itemPosicao.getTelefone());

        TextView txtCargo = (TextView) convertView.findViewById(R.id.txtCargoLista);
        txtCargo.setText(itemPosicao.getCargo());


        return convertView;
    }


}
