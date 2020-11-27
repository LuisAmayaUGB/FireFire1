package com.example.firefire1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        var database=FirebaseDatabase.getInstance().reference
        database.setValue("mi base de datos")



        btnguardar.setOnClickListener{

            var empno=edt1.text.toString().toInt()
            var empname =edt2.text.toString()
            var empsal=edt3.text.toString().toInt()

           // database.child(empno.toString()).setValue(empleado(empno,empname,empsal))
            database.child(empno.toString()).setValue(empleado(empname,empsal))

        }

//obteniendo los nodos


        var getdata=object : ValueEventListener{



            override fun onDataChange(snapshot: DataSnapshot) {

                var ab=StringBuilder()
                for(i in snapshot.children){
                    var elnombre=i.child("empname").getValue()
                    var elsal=i.child("empsal").getValue()
                    ab.append("$elnombre $elsal")
            }
                textView.text=ab

            fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        }



        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)


    }
}