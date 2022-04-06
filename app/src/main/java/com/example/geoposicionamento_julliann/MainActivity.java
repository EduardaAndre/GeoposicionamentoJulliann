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
    //declarou as variaveis
    private ImageView imageViewFoto;
    private Button btnGeo;

    @Override// sobrescrevendo metedo, "savedInstanceState" serve para recuperar um metodo
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//utilizado para setar o layout

        //criando o botão localização
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);//pedindo para acessar a localização do celular

        //chamando o metodo de pressionar o botão
        btnGeo.setOnClickListener(new View.OnClickListener() {
            @Override//sobrescrevendo o metodo onClick
            public void onClick(View view) {
                GPStracker g = new GPStracker(getApplication());//definindo que vou usar a classe GPStracker
                Location l = g.getLocation();// pegando a localização do dispositivo com o getLocation


                if (l != null) { //se a localização for diferente de nulo, irá pegar as variaveis de latitude e longitude, e vai mostrar na tela
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE:" + lat + "\n LONGITUDE:" + lon, Toast.LENGTH_LONG).show();

                }
            }
        });//checando se dei a permissão para o uso da camera, caso não tenha dado, irá pedir uma permissão
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }
        //
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);// ira mostrar a imagem viewFot, e adicionar um clickListener
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
            @Override//sobrescrevendo metodo, e chamando metodo tirarFoto
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }
    private void  tirarFoto()//metodo tirar foto. Declarando a função tirarFoto e disponibilizando a camera para tirar a foto
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //@Override
    protected void OnActivityResult(int requestCode, int resultCode, @Nullable Intent data){// sobrescrevendo o metodo para receber os resultados do metodo tirarFoto
//Pegar a imagem que irá ser tirado pela camera e transformar em bitmap
        if (requestCode==1&&resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imagem=(Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}



