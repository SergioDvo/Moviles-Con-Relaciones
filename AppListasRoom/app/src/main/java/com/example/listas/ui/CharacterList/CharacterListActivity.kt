package com.example.listas.ui.CharacterList

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulationapp.ui.Constantes
import com.example.listas.R
import com.example.listas.ui.AddCharacter.AddCharacterActivity
import com.example.listas.ui.PersonasAdapter
import com.example.listas.ui.UpdateCharacter.UpdateCharacterActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by viewModels()

    private fun clickUpdate(nombre: String) {
        val intent = Intent(this, UpdateCharacterActivity::class.java)
        intent.putExtra(Constantes.NOMBRE, nombre)
        startActivity(intent)
    }

    private fun clickDelete(nombre: String) {
        viewModel.handleEvent(ListEvent.DeleteCharacter(nombre))
        Snackbar.make(
            findViewById<RecyclerView>(R.id.listaPersonajes), " $nombre", Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle item selection
        return when (item.itemId) {
            R.id.action_action1 -> {
                val uri = Uri.parse(Constantes.URL)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicler)

        val rvPersona = this.findViewById<RecyclerView>(R.id.listaPersonajes)
        val adapter = PersonasAdapter(
            object : Actions {
                override fun onClickEdit(name: String) {
                    clickUpdate(name)
                }
                override fun onClickBorrar(name: String) {
                    clickDelete(name)
                }
            })
        rvPersona.adapter = adapter
        rvPersona.layoutManager = GridLayoutManager(this@CharacterListActivity, 1)
        viewModel.handleEvent(ListEvent.GetCharacters)
        val button = this.findViewById<FloatingActionButton>(R.id.textButton2)
        button.setOnClickListener() {
            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle(Constantes.CONFIRMACION)
                .setMessage(Constantes.QUIERES_AÑADIR)
                .setNegativeButton(Constantes.NO) { view, _ ->
                    view.dismiss()
                }
                .setPositiveButton(Constantes.SI) { view, _ ->
                    val intent = Intent(this, AddCharacterActivity::class.java)
                    startActivity(intent)
                    view.dismiss()
                }
                .setCancelable(false)
                .create()

            dialog.show()

        }
        viewModel.uiState.observe(this@CharacterListActivity) { state ->
            state.characterList.let {
                adapter.submitList(it)
            }
            state.error?.let {
                Snackbar.make(
                    findViewById<RecyclerView>(R.id.listaPersonajes), it, Snackbar.LENGTH_SHORT
                ).show()
            }
        }


    }

}