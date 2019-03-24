package com.matias.ma1;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button danishButton;
    Button englishButton;
    Button brazilButton;
    Button spanishButton;

    TextView danishGradeTextView;
    TextView brazilGradeTextView;
    TextView englishGradeTextView;
    TextView spanishGradeTextView;

    CourseModel danishCourse = new CourseModel();
    CourseModel englishCourse = new CourseModel();
    CourseModel brazilCourse = new CourseModel();
    CourseModel spanishCourse = new CourseModel();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        danishButton = findViewById(R.id.danishButton);
        englishButton = findViewById(R.id.englishButton);
        brazilButton = findViewById(R.id.brazilButton);
        spanishButton = findViewById(R.id.spanishButton);

        danishGradeTextView = findViewById(R.id.danishGradeTextView);
        brazilGradeTextView = findViewById(R.id.brazilGradeTextView);
        englishGradeTextView = findViewById(R.id.englishGradeTextView);
        spanishGradeTextView = findViewById(R.id.spanishGradeTextView);

        danishCourse.setName(getString(R.string.DANISH));
        englishCourse.setName(getString(R.string.ENGLISH));
        spanishCourse.setName(getString(R.string.SPANISH));
        brazilCourse.setName(getString(R.string.BRAZIL));

        danishButton.setText(danishCourse.getName());
        englishButton.setText(englishCourse.getName());
        brazilButton.setText(brazilCourse.getName());
        spanishButton.setText(spanishCourse.getName());

        giveGrades();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void sendMailToToTeacher(String subject, int result)
    {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        // had to hardcode this, cuz i cant do "@" in values/strings.
        i.setData(Uri.parse(getString(R.string.MAILTO) + subject + "@kea.dk"));
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.RESULTS_FOR) + getString(R.string.SPACE) + subject + getString(R.string.SPACE) + getString(R.string.TEACHER));
        i.putExtra(Intent.EXTRA_TEXT   , getString(R.string.TESTSCORE) + result);


        try
        {
            startActivity(Intent.createChooser(i, getString(R.string.SEND_MAIL)));
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(this, getString(R.string.There_are_no_email_clients_installed), Toast.LENGTH_SHORT).show();
        }


    }
    public void giveGrades()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String lastChoice = extras.getString(getString(R.string.Choice));
            int totalValue = extras.getInt(getString(R.string.TotalValue));
            if (lastChoice != null)
            {
                if (lastChoice.equals(getString(R.string.Danish_Button)))
                {
                    danishCourse.setGrade(totalValue);
                    ratingsSchema(danishCourse.getGrade(), danishGradeTextView);
                    sendMailToToTeacher(lastChoice,totalValue);
                }
                else if (lastChoice.equals(getString(R.string.Brazil_Button)))
                {
                    brazilCourse.setGrade(totalValue);
                    ratingsSchema(brazilCourse.getGrade(), brazilGradeTextView);
                    sendMailToToTeacher(lastChoice,totalValue);
                }
                else if (lastChoice.equals(getString(R.string.English_Button)))
                {
                    englishCourse.setGrade(totalValue);
                    ratingsSchema(englishCourse.getGrade(), englishGradeTextView);
                    sendMailToToTeacher(lastChoice,totalValue);
                }
                else if (lastChoice.equals(getString(R.string.Spanish_Button)))
                {
                    spanishCourse.setGrade(totalValue);
                    ratingsSchema(spanishCourse.getGrade(), spanishGradeTextView);
                    sendMailToToTeacher(lastChoice,totalValue);
                }
            }
        }
    }
    public void ratingsSchema(int totalValue, TextView textView)
    {
        if (totalValue > 90)
        {
            textView.setText(getString(R.string.A));
            textView.setTextColor(Color.RED);

        }
        else if (totalValue > 80) {
            textView.setText(getString(R.string.B));
            textView.setTextColor(Color.BLUE);
        }

        else if (totalValue > 70) {
            textView.setText(getString(R.string.C));
            textView.setTextColor(Color.GREEN);
        }

        else if (totalValue > 60) {
            textView.setText(getString(R.string.D));
            textView.setTextColor(Color.YELLOW);
        }

        else if (totalValue > 49) {
            textView.setText(getString(R.string.E));
            textView.setTextColor(Color.CYAN);
        }

        else if( totalValue < 49) {
            textView.setText(getString(R.string.NEW));
            textView.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View view)
    {
        String courseName = "";
        switch (view.getId())
        {
            case R.id.danishButton:
                courseName = danishCourse.getName();
                break;
            case R.id.brazilButton:
                courseName = brazilCourse.getName();
                break;
            case R.id.englishButton:
                courseName = englishCourse.getName();
                break;
            case R.id.spanishButton:
                courseName = spanishCourse.getName();
                break;
        }
        rateCurrentCourse(courseName);
    }
    public void rateCurrentCourse(String courseName)
    {
        Intent intent = new Intent(this,RateActivity.class);
        intent.putExtra(getString(R.string.Message), courseName);
        startActivity(intent);
    }
}
