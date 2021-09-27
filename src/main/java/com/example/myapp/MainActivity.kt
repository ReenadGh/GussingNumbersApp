package com.example.myapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var input : EditText
    lateinit var buttonGuess : Button

    val tasks =  mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.taskinput)
        buttonGuess = findViewById(R.id.button3)

        val myRV = findViewById<RecyclerView>(R.id.rvMain)
        myRV.adapter = RecyclerViewAdapter(tasks)
        myRV.layoutManager = LinearLayoutManager(this)
        myRV.adapter!!.notifyDataSetChanged()




        val gessNum = Random.nextInt(0, 10)
      var NumOfGuess = 3 ;
        buttonGuess.setOnClickListener {





            if (input.text.toString().isEmpty()) {
                Snackbar.make(myRV, "Enter a Number !", Snackbar.LENGTH_SHORT).show()
            } else {


                val UserGessNum = input.text.toString()

                if (UserGessNum.toInt() == gessNum) {
                    tasks.add("your Gess it ! ")
                    showAlertDialog( "Play again ? ")

                } else {
                    NumOfGuess-- ;

                    if(NumOfGuess==0){
                        tasks.add("You lose - The correct answer was $gessNum")
                        showAlertDialog( "Play again ? ")

                    }
                    else {
                        tasks.add("Wrong Guess! ")
                        tasks.add("Num of Guess left  " + NumOfGuess)
                    }

                }



            }

            }
        }


    private fun showAlertDialog(title: String) {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage(title)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game Over")
        // show alert dialog
        alert.show()
    }



          }








