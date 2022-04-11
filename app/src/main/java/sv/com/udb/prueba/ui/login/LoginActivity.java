package sv.com.udb.prueba.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import sv.com.udb.prueba.databinding.ActivityLoginBinding;
import sv.com.udb.prueba.repositories.LoginRepositiory;
import sv.com.udb.prueba.repositories.MarcasRepository;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final TextInputLayout usernameEditText = (TextInputLayout) binding.username;
        final TextInputLayout passwordEditText = (TextInputLayout) binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                // ignore
            }


        };




        LoginRepositiory repositiory = new LoginRepositiory(getApplicationContext());
        MarcasRepository marcasRepository = new MarcasRepository(getApplicationContext());
        try {
           // marcasRepository.insert(new Marcas(1,"BMW"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}