package tech.faiz.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_CORRECT_ANSWER = "tech.faiz.geoquiz.correct_answer";
    private static final String EXTRA_ANSWER_SHOWN = "tech.faiz.geoquiz.answer_shown";

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    private Boolean mCorrectAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mCorrectAnswer = getIntent().getBooleanExtra(EXTRA_CORRECT_ANSWER, false);

        mAnswerTextView = findViewById(R.id.answer_text_view);

        mShowAnswerButton = findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(mCorrectAnswer ? R.string.true_button : R.string.false_button);
                setAnswerShowResult(true);
            }
        });

    }

    private void setAnswerShowResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }




    public static Intent newIntent(Context packageContext, boolean correctAnswer) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_CORRECT_ANSWER, correctAnswer);
        return intent;
    }
}
