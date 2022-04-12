package sv.com.udb.prueba.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityDashboardBinding;
import sv.com.udb.prueba.databinding.ActivityLoginBinding;
import sv.com.udb.prueba.ui.admin.usuarios.UsuariosActivity;

public class dashboard extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUsuario.setOnClickListener(this::btnUsuarioListener);
    }

    private void btnUsuarioListener(View view){
        Intent i = new Intent(this, UsuariosActivity.class);
        startActivity(i);
    }
}