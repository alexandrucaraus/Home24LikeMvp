package eu.caraus.home24.application.ui.start


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import eu.caraus.home24.R
import eu.caraus.home24.application.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_start.*

/**
 *
 *  StartActivity - main entry point to the application
 *
 */

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start)

        btStart.setOnClickListener {
            startActivity( Intent( it.context , MainActivity::class.java ) )
        }

    }

}
