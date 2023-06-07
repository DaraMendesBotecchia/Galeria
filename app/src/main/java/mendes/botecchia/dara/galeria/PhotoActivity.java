package mendes.botecchia.dara.galeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toolbar;

import java.io.File;

public class PhotoActivity extends AppCompatActivity {

    String photoPath; // Caminho da foto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Toolbar toolbar = findViewById(R.id.tbPhoto);  // Referência para a Toolbar
        setSupportActionBar(toolbar); // Define a Toolbar como a Action Bar da atividade

        Intent i = getIntent();
        String photoPath = i.getStringExtra("photo_path"); // Obtém o caminho da foto passado como extra no Intent

        Bitmap bitmap = Util.getBitmap(photoPath); // Obtém o bitmap da foto
        ImageView imPhoto = findViewById(R.id.imPhoto); // Referência para a ImageView
        imPhoto.setImageBitmap(bitmap);  // Define o bitmap na ImageView

        ActionBar actionBar = getSupportActionBar(); // Obtém a Action Bar
        actionBar.setDisplayHomeAsUpEnabled(true); // Define o botão "Up" para voltar à atividade anterior
    }

    private void setSupportActionBar(Toolbar toolbar) {     // Método vazio para definir a Toolbar como a Action Bar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_tb, menu); // Infla o menu da Action Bar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opShare:
                sharePhoto(); // Chama o método para compartilhar a foto ao selecionar a opção de compartilhamento
                return true;
            default:
                return super.onOptionsItemSelected(item);
             }
         }

    private void sharePhoto() {
        // Codigo para cpmpartiilhar a foto
        Uri photoUri = FileProvider.getUriForFile(PhotoActivity.this, "mendes.botecchia.dara.galeria.fileprovider", new File(photoPath));
        Intent i = new Intent(Intent.ACTION_SEND); // Cria uma Intent de compartilhamento
        i.putExtra(Intent.EXTRA_STREAM, photoUri); // Adiciona a URI da foto como um extra
        i.setType("image/jpeg");  // Define o tipo da imagem
        startActivity(i); // Inicia a atividade de compartilhamento
    }
}