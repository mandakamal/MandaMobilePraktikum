package com.example.manda3tia_p3.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.manda3tia_p3.Home.pertemuan5.LimaActivity
import com.example.manda3tia_p3.Home.pertemuan9.NinthActivity
import com.example.manda3tia_p3.Home.pertemuan_6.EnamActivity
import com.example.manda3tia_p3.R
import com.example.manda3tia_p3.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val username =requireActivity(). intent.getStringExtra("Username")
        val password =requireActivity().intent.getStringExtra("Password")

        binding.txtUsername.text = "Username: ${username.orEmpty()}"
        binding.txtPassword.text = "Password: ${password.orEmpty()}"

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Halo ini snackbar", Snackbar.LENGTH_LONG)
                .setAction("Kembali") {
                    requireActivity().finish()
                }
                .show()
        }

        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext(),)
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Hapus") { dialog, _ ->
                    binding.txtUsername.text = "Username:"
                    binding.txtPassword.text = "Password:"
                    Toast.makeText(requireContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }


        // Pertemuan 5: membuka LimaActivity.
        binding.btnToLima.setOnClickListener {
            val intent = Intent(requireContext(), LimaActivity::class.java)
            startActivity(intent)
        }
        binding.btnToEnam.setOnClickListener {
            val intent = Intent(requireContext(), EnamActivity::class.java)
            startActivity(intent)
        }
        binding.btnToSembilan.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        binding.btnToLogout.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }

    }

}