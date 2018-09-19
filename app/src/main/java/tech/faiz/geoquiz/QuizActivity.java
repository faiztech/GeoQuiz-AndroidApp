package tech.faiz.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QUIZACTIVITY";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private Button mCheatButton;
    private View mBackgroundView;
    private TextView mQuestionTextView;

    private boolean mIsCheater;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true)
    };



    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate: ");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {

            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById((R.id.question_text_view));

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = findViewById(R.id.false_button);

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
                setDefaultBackground();
            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCurrentIndex = mCurrentIndex == 0 ? (mQuestionBank.length-1) : (mCurrentIndex-1);
               updateQuestion();
               setDefaultBackground();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Boolean correctAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, correctAnswer);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);

            }
        });

        updateQuestion();

        mBackgroundView = (View) findViewById(R.id.background_view);

    }


    private void checkAnswer(boolean userChoice) {
        boolean correctAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if(userChoice == correctAnswer) {
            messageResId = R.string.correct_toast;
            mBackgroundView.setBackgroundColor(Color.argb(255, 0, 128, 0));

        } else {
            messageResId = R.string.incorrect_toast;
            mBackgroundView.setBackgroundColor(Color.argb(255, 255, 0, 0));

        }
        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,300| Gravity.CENTER_HORIZONTAL);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void setDefaultBackground() {
        mBackgroundView.setBackgroundColor(Color.argb(255, 255 , 255, 255));
    }




    public QuizActivity() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    public void onFalse(View v)
    {
        checkAnswer(false);
    }
}
