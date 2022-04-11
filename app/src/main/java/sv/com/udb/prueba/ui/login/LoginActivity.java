package sv.com.udb.prueba.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.data.model.Marcas;
import sv.com.udb.prueba.databinding.ActivityLoginBinding;
import sv.com.udb.prueba.db.LoginRepositiory;
import sv.com.udb.prueba.db.MarcasRepository;

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
            marcasRepository.insert(new Marcas(1,"BMW"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}