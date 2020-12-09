package mike.machine.platziconf.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splashscreen.*
import mike.machine.platziconf.R

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion)
        ivLogoPlatziConf.startAnimation(animacion)
        val intent = Intent(this,MainActivity::class.java)

        animacion.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(intent)
                finish() // Es para que se destruya la actividad de SplashScreen
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
    }


}