package eu.caraus.home24.application.ui.main.selection

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import eu.caraus.home24.R

/**
 *  SelectionAnimations - this class is used for some lame animations int the
 *  [SelectionFragment]
 */

class SelectionAnimations( private val context : Context ) {

    /**
     *  Lame slide image left
     */
    fun slideLeft( view : View , exec : ()->Unit ) {

        val animSlide = AnimationUtils.loadAnimation(context, R.anim.slide_left).apply {
            setAnimationListener( createAnimationListener( execOnEnd = exec ))
        }

        view.startAnimation( animSlide )

    }

    /**
     *  Lame slide image right
     */
    fun slideRight( view : View, exec: ()->Unit ){

        val animSlide = AnimationUtils.loadAnimation(context, R.anim.slide_right).apply {
            setAnimationListener( createAnimationListener( execOnEnd = exec ))
        }

        view.startAnimation( animSlide )
    }

    private fun createAnimationListener( execOnStart : ()->Unit = {} ,
                                         execOnEnd   : ()->Unit = {} ) : Animation.AnimationListener {
        return object : Animation.AnimationListener {
            var str = true
            var end = true
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {
                if( str ) { execOnStart() ; str = false }
            }
            override fun onAnimationEnd(animation: Animation?) {
                if( end ) { execOnEnd() ; end = false }
            }
        }
    }

    /**
     *  pulsating effect for the star
     */
    fun expandStar( view : View, onDone : () -> Unit ) {

        val scaleUpX = ObjectAnimator.ofFloat( view, "scaleX",1.5f)
        val scaleUpY = ObjectAnimator.ofFloat( view, "scaleY",1.5f)

        val scaleUp = AnimatorSet()
        scaleUp.playTogether( scaleUpX, scaleUpY)

        val scaleDnX = ObjectAnimator.ofFloat( view, "scaleX",1f)
        val scaleDnY = ObjectAnimator.ofFloat( view, "scaleY",1f)

        val scaleDw = AnimatorSet()
        scaleDw.playTogether( scaleDnX, scaleDnY)

        val scale = AnimatorSet()

        scale.playSequentially( scaleUp, scaleDw )

        scale.addListener( object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {
                onDone()
            }
        })

        scale.start()
    }

}