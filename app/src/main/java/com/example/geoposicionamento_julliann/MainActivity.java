package com.example.geoposicionamento_julliann;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //declara variaveis
    private ImageView imageViewFoto;//imagem
    private Button btnGeo;//botão

    @Override//"savedInstanceState" serve para recuperar um metodo
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//seta o layout

        //botão de localização
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);//pede permissão ao acesso da localização do celular

        //chama o metodo de pressionar o botão "SetOnClickListener"
        btnGeo.setOnClickListener(new View.OnClickListener() {
            @Override//transcreve o metodo onClick
            public void onClick(View view) {
                GPStracker g = new GPStracker(getApplication());//será usada a classe GPStracker
                Location l = g.getLocation();// pega a localização do dispositivo com o getLocation


                if (l != null) { //se a localização for diferente de nulo, irá pegar as variaveis de latitude e longitude
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE:" + lat + "\n LONGITUDE:" + lon, Toast.LENGTH_LONG).show();

                }
            }
        });//checando permissão ao uso da camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }
        //
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);// adiciona um clickListener que serve pra cadastrar um objeto do tipo View(ImageViewFoto)
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
            @Override//transcrevendo metodo, e chamando metodo tirarFoto
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }
    private void  tirarFoto()//Declarando a função tirarFoto que irá disponibilizar a camera para tirar foto
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //@Override
    protected void OnActivityResult(int requestCode, int resultCode, @Nullable Intent data){// transcrevendo o metodo para receber os resultados do metodo tirarFoto

        //a imagem tirada pela camera transforma em bitmap
        if (requestCode==1&&resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imagem=(Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}



