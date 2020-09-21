package com.codingfreak.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_vs_computer.*

class VsComputerActivity : AppCompatActivity() {

    private val boardCells = Array(3) { arrayOfNulls<ImageView>(3) }

    private lateinit var handler: Handler
    var board = Board()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_vs_computer)

        handler = Handler()

        loadBoard()

        button_restart.setOnClickListener {
            board = Board()
            mapBoardToUi()
        }
    }

    private fun mapBoardToUi() {
        for (i in board.board.indices) {
            for (j in board.board.indices) {
                when (board.board[i][j]) {
                    Board.PLAYER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false
                    }
                    Board.COMPUTER -> {
                        handler.postDelayed({
                            boardCells[i][j]?.setImageResource(R.drawable.cross)
                            boardCells[i][j]?.isEnabled = false
                        } , 500)
                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }


    private fun loadBoard() {

        for (i in boardCells.indices) {
            for (j in boardCells.indices) {
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 210
                    height = 220
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 2
                    rightMargin = 2
                }
                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                boardCells[i][j]?.setOnClickListener(CellClickListener(i, j))
                layout_board.addView(boardCells[i][j])
            }
        }
    }

    inner class CellClickListener(
        private val i: Int,
        private val j: Int
    ) : View.OnClickListener {

        override fun onClick(p0: View?) {

            if (!board.isGameOver) {
                val cell = Cell(i, j)
                board.placeMove(cell, Board.PLAYER)
                board.minimax(0, Board.COMPUTER)
                board.computersMove?.let {
                    board.placeMove(it, Board.COMPUTER)
                }
                mapBoardToUi()
            }

            when {

                board.hasComputerWon() -> {
                    handler.postDelayed({
                        Toast.makeText(this@VsComputerActivity, "Computer wins!", Toast.LENGTH_SHORT).show()

                        val gameAlert = AlertDialog.Builder(this@VsComputerActivity)

                        gameAlert.setCancelable(false)
                        gameAlert.setTitle("The game is finished")
                        gameAlert.setMessage("Computer is the WINNER")
                        gameAlert.setNegativeButton("Reset the game") { _, _ -> run {
                            board = Board()
                            mapBoardToUi()
                            }
                        }
                        gameAlert.setPositiveButton("Finish the game") { _, _ -> finish() }
                        gameAlert.show()
                        mapBoardToUi()
                    },600)
                }

                board.hasPlayerWon() -> {
                    handler.postDelayed({
                        Toast.makeText(this@VsComputerActivity, "Player wins!", Toast.LENGTH_SHORT).show()

                        val gameAlert = AlertDialog.Builder(this@VsComputerActivity)

                        gameAlert.setCancelable(false)
                        gameAlert.setTitle("The game is finished")
                        gameAlert.setMessage("Player is the WINNER")
                        gameAlert.setNegativeButton("Reset the game") { _, _ -> run {
                            board = Board()
                            mapBoardToUi()
                            }
                        }
                        gameAlert.setPositiveButton("Finish the game") { _, _ -> finish() }
                        gameAlert.show()
                        mapBoardToUi()
                    },600)
                }

                board.isGameOver -> {
                    handler.postDelayed({
                        Toast.makeText(this@VsComputerActivity, "It's a DRAW!", Toast.LENGTH_SHORT).show()
                        val gameAlert = AlertDialog.Builder(this@VsComputerActivity)
                        gameAlert.setCancelable(false)
                        gameAlert.setTitle("The game is finished")
                        gameAlert.setMessage("It's a DRAW!")
                        gameAlert.setNegativeButton("Reset the game") { _, _ -> run {
                            board = Board()
                            mapBoardToUi() } }
                        gameAlert.setPositiveButton("Finish the game") { _, _ -> finish() }
                        gameAlert.show()
                        mapBoardToUi()
                    },600)
                }
            }
        }
    }
}
