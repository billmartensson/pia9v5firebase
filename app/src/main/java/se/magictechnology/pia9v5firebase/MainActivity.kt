package se.magictechnology.pia9v5firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val TAG = "pia9debug"

    var todoadapter = TodoAdapter()

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoadapter.mainact = this

        database = Firebase.database.reference

        val todorv = findViewById<RecyclerView>(R.id.todoRV)

        todorv.layoutManager = LinearLayoutManager(this)
        todorv.adapter = todoadapter

        findViewById<Button>(R.id.todoButton).setOnClickListener {
            val todoet = findViewById<EditText>(R.id.todoEdittext)

            var newtodo = Todo(todoet.text.toString())

            database.child("androidtodo").push().setValue(newtodo)

            loadtodo()
        }

        loadtodo()

        /*
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        */

        /*
        val banana = Fruit("Apelsin", "Orange", "Good")
        banana.rating = 3

        database.child("allfruit").push().setValue(banana)
        */

        /*
        val fancyListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue<Fruit>()
                // ...
                Log.d(TAG, post!!.fruitname!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.child("fruitsalad").addValueEventListener(fancyListener)
         */

        /*
        val fancyListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(somefruit in dataSnapshot.children)
                {
                    val thefruit = somefruit.getValue<Fruit>()
                    // ...
                    Log.d(TAG, thefruit!!.fruitname!!)
                }




            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.child("allfruit").addListenerForSingleValueEvent(fancyListener)
        */
    }


    fun loadtodo()
    {
        val todoListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var todoitems = mutableListOf<Todo>()

                for(todoitem in dataSnapshot.children)
                {
                    val thetodo = todoitem.getValue<Todo>()!!
                    thetodo.fbid = todoitem.key!!


                    Log.d(TAG, thetodo!!.todoname)

                    todoitems.add(thetodo)
                }

                todoadapter.todoListUpdate(todoitems)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.child("androidtodo").addListenerForSingleValueEvent(todoListener)
    }

    fun clickRow(clickedtodo : Todo)
    {
        Log.d(TAG, clickedtodo.todoname)

        if(clickedtodo.tododone == true)
        {
            clickedtodo.tododone = false
        } else {
            clickedtodo.tododone = true
        }


        database.child("androidtodo").child(clickedtodo.fbid).setValue(clickedtodo)

        loadtodo()

        //database.child("androidtodo").child(clickedtodo.fbid).child("tododone").setValue(true)
    }

}


data class Todo(var todoname : String = "",
                var tododone : Boolean = false) {
    @Exclude
    var fbid : String = ""
}


data class Fruit(var fruitname : String? = "", var fruitcolor : String? = "", var taste : String? = null) {
    var rating : Int = 0
}