package com.example.data

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.UserData
import com.example.data.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recy:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        userList = ArrayList()
        addsBtn = findViewById(R.id.addingBtn)
        recy = findViewById(R.id.mRecycler)
        userAdapter = UserAdapter(this,userList)
        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = userAdapter
        addsBtn.setOnClickListener { addInfo() }
    }

    private fun addInfo() {
       val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_icon,null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Tambahkan") {
            dialog,_->
            val names = userName.text.toString()
            val number = userNo.text.toString()
           userList.add(UserData("Nama: $names","NIM : $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Tambah Data Informasi",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Batal") {
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Batal",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }


}