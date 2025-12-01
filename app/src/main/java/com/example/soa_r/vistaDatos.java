package com.example.soa_r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class vistaDatos extends AppCompatActivity {

    private FirebaseAuth mAuth;


    List<CarouselItem> list = new ArrayList<>();

    private Button _btn_link;
    String _url = "https://therapeutic-frequent-collision.glitch.me";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_datos);

        mAuth = FirebaseAuth.getInstance();


        ImageCarousel carousel = findViewById(R.id.carousel);
        list.add(new CarouselItem("https://www.ilovepozarica.com/wp-content/uploads/2019/04/624216-N.jpg"));
        list.add(new CarouselItem("https://www.lavoz.com.ar/resizer/RtMILJPilyuk8lsrLhQ4Np9q9z0=/1023x682/smart/cloudfront-us-east-1.images.arcpublishing.com/grupoclarin/YYKCB22QNBAKLB6Q5WJXDQA62M.jpg"));
        list.add(new CarouselItem("https://www.municipiosoledad.gob.mx/sites/default/files/styles/estilo_imagen_1296x624/public/noticias/2019-11/PARTICIPACION%20CIUDADAN%20A%20%20%281%29.jpeg?itok=4NVM820B"));
        list.add(new CarouselItem("https://s3.amazonaws.com/mundo-bucket-s3/wp-content/uploads/2020/11/20224234/IMG_1605920786614.jpg"));
        list.add(new CarouselItem("https://s3.amazonaws.com/mundo-bucket-s3/wp-content/uploads/2019/10/24080013/36-544284bazar_ropa.jpg"));

        carousel.addData(list);


        _btn_link = findViewById(R.id.boton_link);

        _btn_link.setOnClickListener(view -> {
            Uri _link = Uri.parse(_url);
            Intent i = new Intent(Intent.ACTION_VIEW,_link);
            startActivity(i);
            Log.d("click", "ok");
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(vistaDatos.this,registroRopa.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(vistaDatos.this,vistaDatos.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(vistaDatos.this,VerRopa.class));
                return true;
            case R.id.item4:
                cerrar();
            case R.id.item5:
                startActivity(new Intent(vistaDatos.this,perfilUsuario.class));
                return true;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    public void cerrar(){
        mAuth.signOut();
        finish();
        startActivity(new Intent(vistaDatos.this, MainActivity.class));
        Toast.makeText(vistaDatos.this, "Haz cerrado sesión satisfactoriamente", Toast.LENGTH_SHORT).show();
    }
}