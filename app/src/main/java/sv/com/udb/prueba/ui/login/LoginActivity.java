package sv.com.udb.prueba.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.HomeActivity;
import sv.com.udb.prueba.databinding.ActivityLoginBinding;
import sv.com.udb.prueba.model.Role;
import sv.com.udb.prueba.model.Usuario;
import sv.com.udb.prueba.repositories.LoginRepositiory;
import sv.com.udb.prueba.repositories.MarcasRepository;
import sv.com.udb.prueba.ui.admin.dashboard;

public class LoginActivity extends AppCompatActivity {

    public static final String ADMIN = "ADMIN";
    public static final String CLIENT = "CLIENT";
    private ActivityLoginBinding binding;
    private LoginRepositiory loginRepositiory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText usernameEditText = (EditText) binding.username;
        final EditText passwordEditText = (EditText) binding.password;
        binding.btnAcceder.setOnClickListener(this::btnAccederListener);
        loginRepositiory = new LoginRepositiory((getApplicationContext()));

        /*try {
            loginRepositiory.create(new Usuario(null, "Matthew", "Gaitan", "mat@gmail.com", "Mat25", "123456", new Role(1, "ADMIN")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

    }

    private void btnAccederListener(View view){
        final String user = binding.username.getText().toString();
        final String password = binding.password.getText().toString();

        try {
            Usuario login = loginRepositiory.acceder(user, password);
            boolean isAdmin = ADMIN.equals(login.getRole().getRole());
            boolean isClient = ADMIN.equals(login.getRole().getRole());
            if (isAdmin){
                Intent i = new Intent(this, dashboard.class);
                startActivity(i);
            }else if(isClient) {
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
            }else {
                throw new RuntimeException("User is not an admin or user or unexpected error");
            }
        }catch (Exception e){
            e.printStackTrace();
            showLoginFailed(e.toString());
        }
    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}