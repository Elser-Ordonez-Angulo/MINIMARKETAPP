package com.cibertec.minimarketapp.beneficio

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibertec.minimarketapp.MinimarketApp
import com.cibertec.minimarketapp.R
import com.cibertec.minimarketapp.categorias.CategoriasActivity
import com.cibertec.minimarketapp.detalleoferta.DetalleofActivity
import com.cibertec.minimarketapp.maps.MapsActivity
import com.cibertec.minimarketapp.ofertas.Oferta
import com.cibertec.minimarketapp.ofertas.OfertasAdapter


import com.cibertec.minimarketapp.pedidos.PedidosActivity

import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class BeneficioActivity: AppCompatActivity(), OfertasAdapter.OfertasListener {

    // recycler beneficio + recycler ofertas + agregar imagenes + menu

    private val PICK_IMAGE_REQUEST = 1
    //imagenes
    private lateinit var imgProfile: ImageView
    private lateinit var storageReference: StorageReference
    private lateinit var firestore: FirebaseFirestore
    private lateinit var drawerLayout: DrawerLayout
    //lista
    var listBeneficio = arrayListOf<Beneficio>()
    var listOfertas = arrayListOf<Oferta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Cabecera
        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Inicio"

        val drawerLayaut = findViewById<DrawerLayout>(R.id.drawerLayaut)
        val imgMneu = findViewById<ImageView>(R.id.imgMenu)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawerLayaut)

        //carga de datos de acuerdo al que se registra

        val headerView = nav_view.getHeaderView(0)
        val textMenuName = headerView.findViewById<TextView>(R.id.textName)
        val textMenuEmail = headerView.findViewById<TextView>(R.id.textMenuEmail)

        val name = MinimarketApp.prefs?.getString("name")
        val email = MinimarketApp.prefs?.getString("email")

        name?.let {
            textMenuName.text = it
        }

        email?.let {
            textMenuEmail.text = it
        }



        imgMneu.setOnClickListener {
            drawerLayaut.openDrawer(GravityCompat.START)
        }

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemCategorias -> {
                    startActivity(Intent(this, CategoriasActivity::class.java))
                    true
                }

                else -> false
            }
            when (it.itemId) {
                R.id.itemPedidos -> {
                    startActivity(Intent(this, PedidosActivity::class.java))
                    true
                }

                else -> false
            }

            when (it.itemId) {
                R.id.itemUbicanos -> {
                    startActivity(Intent(this, MapsActivity::class.java))
                    true
                }

                else -> false
            }
        }


        val recyclerBeneficio = findViewById<RecyclerView>(R.id.recyclerBeneficio)





        val adaptere = BeneficioAdapter()
        recyclerBeneficio.adapter = adaptere
        recyclerBeneficio.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )


        val db = Firebase.firestore
        db.collection("beneficios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documents = document.data

                    val titulo = documents["titulo"] as String
                    val subtitulo = documents["subtitulo"] as String
                    val enproducto = documents["enproducto"] as String
                    val recordatorio = documents["recordatorio"] as String

                    val imagen = documents["imagen"] as String

                    val beneficio = Beneficio(titulo, subtitulo, enproducto, imagen, recordatorio)
                    listBeneficio.add(beneficio)

                }

                adaptere.loadData(listBeneficio)
            }
            .addOnFailureListener { exception ->
                Log.w("CIBERTEC", "Error getting documents.", exception)
            }


        val headerView2 = nav_view.getHeaderView(0)
        imgProfile = headerView2.findViewById(R.id.imgProfile)
        storageReference = FirebaseStorage.getInstance().reference
        firestore = FirebaseFirestore.getInstance()

        imgProfile.setOnClickListener {
            selectImageFromGallery()
        }

        // Cargar la URL de la imagen desde Firestore al iniciar la actividad
        loadProfileImage()
    }

    //abre la galeria

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }


    //seleccion de la imagen de la url

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val selectedImageUri: Uri? = data.data
            if (selectedImageUri != null) {
                uploadImageToFirebase(selectedImageUri)
            }
        }
    }
//en caso de que tu imagen no se carge
    private fun uploadImageToFirebase(fileUri: Uri) {
        val fileName = "profile_images/" + System.currentTimeMillis() + ".jpg"
        val fileRef = storageReference.child(fileName)
        fileRef.putFile(fileUri)
            .addOnSuccessListener {
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    saveImageUrlToFirestore(uri.toString())
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to upload image: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
//guarda la imagen
    private fun saveImageUrlToFirestore(url: String) {
        val user = hashMapOf(
            "profileImageUrl" to url
        )
        firestore.collection("users")
            .document("user_id") // Reemplaza "user_id" con el ID real del usuario
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Image URL saved successfully", Toast.LENGTH_SHORT).show()
                // Cargar la nueva imagen en el ImageView
                loadProfileImage()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to save image URL: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun loadProfileImage() {
        firestore.collection("users")
            .document("user_id") // Reemplaza "user_id" con el ID real del usuario
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val profileImageUrl = document.getString("profileImageUrl")
                    if (!profileImageUrl.isNullOrEmpty()) {
                        Glide.with(this)
                            .load(profileImageUrl)
                            .into(imgProfile)
                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Failed to load profile image: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        val recyclerOfertas = findViewById<RecyclerView>(R.id.recyclerOfertas)

        val adapter = OfertasAdapter(this)
        recyclerOfertas.adapter = adapter
        recyclerOfertas.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)



        //firebase
        val db = Firebase.firestore
        db.collection("ofertas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documents = document.data

                    val id = documents["id"] as String
                    val titulo = documents["titulo"] as String
                    val subtitulo = documents["subtitulo"] as String
                    val precio = documents["precio"] as String
                    val regular = documents["regular"] as String
                    val oferte = documents["oferte"] as String
                    val cantidad = documents["cantidad"] as String
                    val imagen = documents["imagen"] as String
                    val valido = documents["valido"] as String


                    val oferta = Oferta(
                        id, titulo, subtitulo, precio, regular, oferte, cantidad, imagen, valido
                    )
                    listOfertas.add(oferta)

                }

                adapter.loadData(listOfertas)
            }
            .addOnFailureListener { exception ->
                Log.w("CIBERTEC", "Error getting documents.", exception)
            }
    }



        override fun getOfertaSelected(oferta: Oferta) {
            val id = oferta.id
            val intent = Intent(this, DetalleofActivity::class.java)
            intent.putExtra("idOferta", id)
            startActivity(intent)
        }
    }










