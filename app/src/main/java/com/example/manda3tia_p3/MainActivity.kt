package com.example.manda3tia_p3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.manda3tia_p3.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Inisialisasi View Binding untuk ActivityMain
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Menerima data Intent dari LoginActivity
        val username = intent.getStringExtra("Username")
        val password = intent.getStringExtra("Password")

        // 3. Tampilkan data ke TextView (Perhatikan huruf U dan P besar pada txtUsername & txtPassword)
        binding.txtUsername.text = "Username: $username"
        binding.txtPassword.text = "Password: $password"

        // 4. Action Tombol SnackBar (ada di dalam CardView)
        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Halo ini snackbar", Snackbar.LENGTH_LONG)
                .setAction("Kembali") {
                    finish() // Kembali ke halaman Login
                }
                .show()
        }

        // 5. Action Tombol Alert (ada di dalam CardView)
        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Hapus") { dialog, _ ->
                    binding.txtUsername.text = "Username:"
                    binding.txtPassword.text = "Password:"
                    Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
    }
}