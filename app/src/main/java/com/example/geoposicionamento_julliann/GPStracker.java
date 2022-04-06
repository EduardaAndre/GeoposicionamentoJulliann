package com.example.geoposicionamento_julliann;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {//criando o metodo da classe GPStracker
    Context context;
    //Instanciando o metodo da classe GPStracker
    public GPStracker(Context c){
        context=c;
    }
    //Criei o metodo getLocation que requer um retorno de um valor do tipo location
    public Location getLocation(){
        //checando a permissão para poder acessar a localiação do dispositivo
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();//caso não tenha permissão, irá aparecer a mensagem que não foi permitido
            return  null;// retornaremos o valor nulo. Apos retornar o valor nulo, irá verificar se o serviço de gps está ativo.
        }
        LocationManager lm= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //Se o GPS estiver ativado, solicitação da atualização de localização do dispositivo
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }
        else {
            //Irá aparecer na tela para habilitar o gps, caso nao esteja habilitado
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();

        }
        return null;//retornando o valor nulo
    }
    @Override

    public  void  onProviderDisabled(@NonNull String provider){ }//sobrescrevendo metodo que registra o provedor
    @Override
    public void onLocationChanged(@NonNull Location location){ }//sobrescrevendo metodo chamado quando a localização muda
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){ }//sobrescrevendo o metodo quando há mudanças no status
}