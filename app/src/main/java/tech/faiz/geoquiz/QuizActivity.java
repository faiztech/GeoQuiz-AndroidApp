package tech.faiz.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {


    Button mTrueButton;
    Button mFalseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //do something

                Toast toast = Toast.makeText(QuizActivity.this, "True Clicked", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,300 |Gravity.CENTER_HORIZONTAL);
                toast.show();
            }
        });




        mFalseButton = findViewById(R.id.false_button);

    }

    public void onFalse(View v)
    {
       Toast toast = Toast.makeText(this, "False Clicked", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,300| Gravity.CENTER_HORIZONTAL);
        toast.show();

    }
}
