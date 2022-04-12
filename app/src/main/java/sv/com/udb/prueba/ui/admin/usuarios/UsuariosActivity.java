package sv.com.udb.prueba.ui.admin.usuarios;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityUsuariosBinding;
import sv.com.udb.prueba.model.Role;
import sv.com.udb.prueba.repositories.LoginRepositiory;

public class UsuariosActivity extends AppCompatActivity {

    private List<Role> tipoRole = new ArrayList<>();
    private SpinnerRolAdapter spinnerRolAdapter;
    private ActivityUsuariosBinding binding;
    private LoginRepositiory usuarioRepository;
    private Role actualRole = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        binding = ActivityUsuariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spinnerRolAdapter = new SpinnerRolAdapter(this, android.R.layout.simple_spinner_dropdown_item,tipoRole.toArray(new Role[tipoRole.size()]));
        binding.slRol.setAdapter(spinnerRolAdapter);

        usuarioRepository = new LoginRepositiory(getApplicationContext());
    }
}