package com.codingfreak.tictactoe

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*

class KidsActivity : AppCompatActivity() {
    enum class Player {
        One, Two, NoOne
    }

    private var currentPlayer = Player.One
    private var player1Turn = true
    private var playerChoices = arrayOfNulls<Player>(9)
    private var roundCount = 0
    private var gameOver = false
    private var gridLayout: GridLayout? = null
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_kids)

        playerChoices[0] = Player.NoOne
        playerChoices[1] = Player.NoOne
        playerChoices[2] = Player.NoOne
        playerChoices[3] = Player.NoOne
        playerChoices[4] = Player.NoOne
        playerChoices[5] = Player.NoOne
        playerChoices[6] = Player.NoOne
        playerChoices[7] = Player.NoOne
        playerChoices[8] = Player.NoOne

        handler = Handler()

        gridLayout = findViewById(R.id.gridLayout)

        val resetBtn = findViewById<Button>(R.id.button)
        resetBtn.setOnClickListener { resetGame() }
    }

    fun imageViewIsTapped(imageView: View) {
        val tappedImageView = imageView as ImageView
        val tiTag = tappedImageView.tag.toString().toInt()
        if (playerChoices[tiTag] == Player.NoOne && !gameOver) {
            tappedImageView.translationX = -2000f
            playerChoices[tiTag] = currentPlayer
            if (currentPlayer == Player.One) {
                tappedImageView.setImageResource(R.drawable.tiger)
                currentPlayer = Player.Two
            } else if (currentPlayer == Player.Two) {
                tappedImageView.setImageResource(R.drawable.lion)
                currentPlayer = Player.One
            }
            tappedImageView.animate().translationXBy(2000f).alpha(1f).rotation(3600f).duration =
                1000
        } else if (playerChoices[tiTag] != Player.NoOne && !gameOver) {
            return
        }
        roundCount++
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin(): Boolean {
        if (playerChoices[0] == playerChoices[1] && playerChoices[0] == playerChoices[2] && playerChoices[0] != Player.NoOne) {
            return true
        }
        if (playerChoices[3] == playerChoices[4] && playerChoices[3] == playerChoices[5] && playerChoices[3] != Player.NoOne) {
            return true
        }
        if (playerChoices[6] == playerChoices[7] && playerChoices[6] == playerChoices[8] && playerChoices[6] != Player.NoOne) {
            return true
        }
        if (playerChoices[0] == playerChoices[3] && playerChoices[0] == playerChoices[6] && playerChoices[0] != Player.NoOne) {
            return true
        }
        if (playerChoices[1] == playerChoices[4] && playerChoices[1] == playerChoices[7] && playerChoices[1] != Player.NoOne) {
            return true
        }
        if (playerChoices[2] == playerChoices[5] && playerChoices[2] == playerChoices[8] && playerChoices[2] != Player.NoOne) {
            return true
        }
        if (playerChoices[0] == playerChoices[4] && playerChoices[0] == playerChoices[8] && playerChoices[0] != Player.NoOne) {
            return true
        }
        if (playerChoices[2] == playerChoices[4] && playerChoices[2] == playerChoices[6] && playerChoices[2] != Player.NoOne) {
           return true
        }
        return false
    }

    private fun player1Wins() {
        gameOver = true

        handler.postDelayed({

            Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()

            val gameAlert = AlertDialog.Builder(this)
            gameAlert.setCancelable(false)
            gameAlert.setTitle("The game is finished")
            gameAlert.setMessage("Player 1 is the WINNER")
            gameAlert.setNegativeButton("Reset the game") { _, _ -> resetGame() }
            gameAlert.setPositiveButton("Finish the game") { _, _ -> finish() }
            gameAlert.show()

        },1000)
    }

    private fun player2Wins() {
        gameOver = true

        handler.postDelayed({

            Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show()

            val gameAlert = AlertDialog.Builder(this)
            gameAlert.setCancelable(false)
            gameAlert.setTitle("The game is finished")
            gameAlert.setMessage("Player 2 is the WINNER")
            gameAlert.setNegativeButton("Reset the game") { _, _ -> resetGame() }
            gameAlert.setPositiveButton("Finish the game") { _, _ -> finish() }
            gameAlert.show()

        },1000)
    }

    private fun draw() {
        gameOver = true

        handler.postDelayed({

            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()

            val gameAlert1 = AlertDialog.Builder(this)
            gameAlert1.setCancelable(false)
            gameAlert1.setTitle("The game is finished")
            gameAlert1.setMessage("It's a TIE")
            gameAlert1.setNegativeButton("Reset the game") { _, _ ->
                roundCount = 0
                resetGame()
            }
            gameAlert1.setPositiveButton("Finish the game") { _, _ -> finish() }
            gameAlert1.show()

        },1000)
    }

    private fun resetGame() {
        roundCount = 0
        for (index in 0 until gridLayout!!.childCount) {
            val imageView = gridLayout!!.getChildAt(index) as ImageView
            imageView.setImageDrawable(null)
        }
        currentPlayer = Player.One

        Arrays.fill(playerChoices, Player.NoOne)
        player1Turn = true
        gameOver = false
    }
}