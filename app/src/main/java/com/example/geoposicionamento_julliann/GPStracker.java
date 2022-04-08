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

public class GPStracker implements LocationListener {//foi criada a classe GPStracker
    Context context;
    //Instancia o metodo da classe GPStracker
    public GPStracker(Context c){
        context=c;
    }
    //foi criado um metodo getLocation que retonará um valor do tipo location
    public Location getLocation(){
        // Será verificado a permissão de acesso a localização do dispositivo
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();//se nao foi permitido, aparecerá o texto "nao foi permitido"
            return  null;//retorna valor nulo que irá confirmar se o gps está funcionando.

        }
        LocationManager lm= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //se o GPS estiver ativado, será pedido a atualização de localização do dispositivo
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }
        else {
            //Irá aparecer um texto "por favor, habilitar o GPS!", se nao estiver habilitado
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();

        }
        return null;//retorna valor nulo
    }
    @Override

    public  void  onProviderDisabled(@NonNull String provider){ }//transcreve metodo que registra o provedor
    @Override
    public void onLocationChanged(@NonNull Location location){ }//transcreve metodo chamado quando a localização muda
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){ }//transcreve o metodo quando há mudanças no status
}