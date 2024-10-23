package com.likobehruz.flaggame

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.likobehruz.flaggame.models.Flag
import org.w3c.dom.Text
import java.util.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var flagArrayList: ArrayList<Flag>
    var count = 0
    var countryname = ""
    lateinit var buttonArrayList: ArrayList<Button>

    lateinit var linerMatn:LinearLayout
    lateinit var linerbtn1:LinearLayout
    lateinit var linerbtn2:LinearLayout
    lateinit var image :ImageView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonArrayList = ArrayList()

        linerMatn=findViewById(R.id.linear_1_matn)
        linerbtn1=findViewById(R.id.linear_2_matn)
        linerbtn2=findViewById(R.id.linear_3_matn)
        image=findViewById(R.id.image_1)

        obyektYaratish()
        btnJoylaCount()

    }

    private fun obyektYaratish() {
        flagArrayList= ArrayList()
        flagArrayList.add(Flag("Uzbekistan",R.drawable.img))
        flagArrayList.add(Flag("China",R.drawable.img_1))
        flagArrayList.add(Flag("USA",R.drawable.img_2))
        flagArrayList.add(Flag("Britain",R.drawable.img_3))
        flagArrayList.add(Flag("Mexico",R.drawable.img_4))
        flagArrayList.add(Flag("Spain",R.drawable.img_5))
        flagArrayList.add(Flag("Russia",R.drawable.img_6))
        flagArrayList.add(Flag("Australia",R.drawable.img_7))
        flagArrayList.add(Flag("India",R.drawable.img_8))
        flagArrayList.add(Flag("Turkey",R.drawable.img_9))
        flagArrayList.add(Flag("England",R.drawable.img_10))
        flagArrayList.add(Flag("Korea",R.drawable.img_11))
        flagArrayList.add(Flag("France",R.drawable.img_12))
        flagArrayList.add(Flag("Palestine",R.drawable.img_13))
        flagArrayList.add(Flag("Italia",R.drawable.img_14))
        flagArrayList.add(Flag("Kazakhstan",R.drawable.img_15))
        flagArrayList.add(Flag("Kyrgyzstan",R.drawable.img_16))
        flagArrayList.add(Flag("Turkmenistan",R.drawable.img_17))
        flagArrayList.add(Flag("Azerbaijan",R.drawable.img_18))
        flagArrayList.add(Flag("Saudi Arabia",R.drawable.img_19))
        flagArrayList.add(Flag("Nigeria",R.drawable.img_20))
        flagArrayList.add(Flag("Ghana",R.drawable.img_21))
        flagArrayList.add(Flag("Canada",R.drawable.img_22))
        flagArrayList.add(Flag("Pakistan",R.drawable.img_23))
        flagArrayList.add(Flag("Malaysia",R.drawable.img_24))
        flagArrayList.add(Flag("Argentina",R.drawable.img_25))
        flagArrayList.add(Flag("Afghanistan",R.drawable.img_26))
        flagArrayList.add(Flag("Belgium",R.drawable.img_27))
        flagArrayList.add(Flag("Colombia",R.drawable.img_28))
        flagArrayList.add(Flag("Croatia",R.drawable.img_29))
        flagArrayList.add(Flag("Philippine",R.drawable.img_30))
        flagArrayList.add(Flag("Morocco",R.drawable.img_31))

    }
    fun btnJoylaCount(){

        image.setImageResource(flagArrayList[count].image!!)
        linerMatn.removeAllViews()
        linerbtn1.removeAllViews()
        linerbtn2.removeAllViews()
        countryname=""
        btnJoyla(flagArrayList[count].name)
        
    }

    private fun btnJoyla(countryname: String?) {
        val btnArray:ArrayList<Button> = randombtn(countryname)
        for (i in 0 ..5){
            linerbtn1.addView(btnArray[i])
        }
        for (i in 6..11){
            linerbtn2.addView(btnArray[i])
        }

    }

    private fun randombtn(countryname: String?): ArrayList<Button> {
        val array=ArrayList<Button>()
        val arrayText=ArrayList<String>()


        for ( c in countryname!!){
            arrayText.add(c.toString())
        }
        if (arrayText.size < 12) {
            val str = "ABCDEFGHIJKLMNOPQRSTUVXYZ"
            while (arrayText.size < 12) {
                val random = Random().nextInt(str.length)
                arrayText.add(str[random].toString())
            }
        }


        arrayText.shuffle()

        for (i in 0 until  arrayText.size){
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
            button.text = arrayText[i]
            button.setOnClickListener(this)
            array.add(button)
        }
        return array
    }

    override fun onClick(v: View?) {
        val button1 = v as Button
        if (buttonArrayList.contains(button1)) {
            linerMatn.removeView(button1)
            buttonArrayList.remove(button1)
            var hasC = false

            // Check in both button lists
            for (button in linerbtn1.children) {
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    if (countryname.isNotEmpty()) {
                        countryname = countryname.substring(0, countryname.length - 1)
                    }
                    hasC = true
                    break
                }
            }

            for (button in linerbtn2.children) {
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    if (!hasC && countryname.isNotEmpty()) {
                        countryname = countryname.substring(0, countryname.length - 1)
                    }
                    break
                }
            }

        } else {
            button1.visibility = View.INVISIBLE
            countryname += button1.text.toString().uppercase()
            val button2 = Button(this)
            button2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
            button2.text = button1.text
            button2.setOnClickListener(this)
            buttonArrayList.add(button2)
            linerMatn.addView(button2)
            matnTogri()
        }
    }

    private fun matnTogri() {
        if (countryname.uppercase() == flagArrayList[count].name?.uppercase()){
            Log.d("FlagGame", "Correct answer for: ${flagArrayList[count].name}")
            Toast.makeText(this, "Correct ✅ ", Toast.LENGTH_SHORT).show()
            if (count == flagArrayList.size -1){
                count=0
            }else{
                count++
            }
            btnJoylaCount()
        }else{
            if (countryname.length == flagArrayList[count].name?.length){
                Log.d("FlagGame", "Wrong answer for: ${flagArrayList[count].name}")
                Toast.makeText(this, "Wrong ❌ ", Toast.LENGTH_SHORT).show()
                linerMatn.removeAllViews()
                linerbtn1.removeAllViews()
                linerbtn2.removeAllViews()
                btnJoyla(flagArrayList[count].name)
                countryname = ""

            }
        }
    }

}