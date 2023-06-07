package mendes.botecchia.dara.galeria;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<String> photos;

    public MainAdapter(MainActivity mainActivity, List<String>photos) {
        this.mainActivity = mainActivity; // Referência para a MainActivity
        this.photos = photos;  // Lista de caminhos das fotos
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.list_item,parent,false); // Infla o layout do item da lista
        return new MyViewHolder(v); // Retorna uma instância do MyViewHolder

    }

    @Override
public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ImageView imPhoto = holder.itemView.findViewById(R.id.imItem); // Obtém a referência para a ImageView dentro do ViewHolder
        int w = (int) mainActivity.getResources().getDimension(R.dimen.itemWidth); // Obtém a largura desejada para a imagem
        int h = (int) mainActivity.getResources().getDimension(R.dimen.itemHeight);  // Obtém a altura desejada para a imagem
        Bitmap bitmap = Util.getBitmap(photos.get(position), w, h); // Obtém o bitmap da imagem com as dimensões desejadas
        imPhoto.setImageBitmap(bitmap); // Define o bitmap na ImageView

    }

    @Override
    public int getItemCount() {
        return photos.size(); // Retorna a quantidade de itens na lista (atualmente está retornando 0, deve ser corrigido)
    }
}