package sv.com.udb.prueba.ui.admin.automovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.adapter.AutoAdapter;
import sv.com.udb.prueba.databinding.ActivityAutoHomeBinding;
import sv.com.udb.prueba.model.Automovil;
import sv.com.udb.prueba.repositories.AutoRepository;

public class AutoHomeActivity extends AppCompatActivity {

    private ActivityAutoHomeBinding binding;
    private AutoRepository autoRepository;
    private List<Automovil> automoviles = new ArrayList<>();
    private AutoAdapter autoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_home);
        binding = ActivityAutoHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        autoAdapter = new AutoAdapter(automoviles,payload -> System.out.println("Soft Click"));
        binding.recyclerView.setAdapter(autoAdapter);
        autoRepository = new AutoRepository(getApplicationContext());
        init();
    }

    private void init(){
        try{
            //autoRepository.create(new Automovil(2,"Elantra","numeroVin","numeroChasis","numeroMotor",5,2017,5,6800.0F,"uri","descripcion",1,1,1));
            automoviles = autoRepository.findAll();
            autoAdapter.update(automoviles);
        }catch (Exception e){
            e.printStackTrace();
            showToast("Failed to recover cars");
        }
    }


    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}