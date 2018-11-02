package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = 8; // 8 sebab 100 / 8 = 12.5 . we have about 13 question;

    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    int mIndex;
    int mScore; //to add up the correct answer
    ProgressBar mProgressBar;
    TextView mScoreTextView;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, false),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, false),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, false),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, true),
            new TrueFalse(R.string.question_13,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar) ;
        mScoreTextView = (TextView) findViewById(R.id.score);

        mQuestionTextView.setText(mQuestionBank[mIndex].getQuestionID());

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("Quizzler", "True button pressed!");
//                Toast.makeText(getApplicationContext(),"True is pressed",Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("Quizzler","False button pressed!");
//                Toast.makeText(getApplicationContext(),"False is pressed", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
        mIndex = (mIndex + 1) % mQuestionBank.length; //To avoid out of bound, sebab ada 13 question, jadi kalau 12 + 1 % 13 akan jadi 0 balik

        if (mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this); // or (this)
            alert.setTitle("Game Over");
            alert.setCancelable(false); //if user click outside box alertdialog (false), nothing happen
            alert.setMessage("You scored " + mScore + " point(s)!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        mQuestionTextView.setText(mQuestionBank[mIndex].getQuestionID());
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
    }

    private void checkAnswer(boolean userAnswer){
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();

        if(userAnswer == correctAnswer){
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;
            mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
        }

        else{
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
