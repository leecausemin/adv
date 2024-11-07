import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cuteButton: Button = findViewById(R.id.cuteButton)

        // Button customization for a cute design
        cuteButton.text = "Click Me!"
        cuteButton.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200))
        cuteButton.setTextColor(ContextCompat.getColor(this, R.color.white))
        cuteButton.textSize = 24f

        // Add click listener with a fun message
        cuteButton.setOnClickListener {
            Toast.makeText(this, "Yay! You clicked me! 😊", Toast.LENGTH_SHORT).show()
        }
    }
}
