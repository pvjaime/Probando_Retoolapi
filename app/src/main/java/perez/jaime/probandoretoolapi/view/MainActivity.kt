package perez.jaime.probandoretoolapi.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import perez.jaime.probandoretoolapi.R
import perez.jaime.probandoretoolapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Variable de Binding
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configuraciones de Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Configurar vista
        setContentView(binding.root)
    }
}