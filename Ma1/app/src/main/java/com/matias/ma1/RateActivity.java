package com.matias.ma1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RateActivity extends AppCompatActivity
{
    TextView rateTextView, seekBarValue, questionTextView;
    SeekBar rateSeekBar;
    Button nextQuestionButton, previousQuestionButton;
    int totalValue, questionIndex = 0, value0,value1,value2,value3,value4,value5;
    List<String> list = new ArrayList<String>();
    String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.rate_layout);
        super.onCreate(savedInstanceState);

        rateTextView = findViewById(R.id.rateTextView);
        rateSeekBar = findViewById(R.id.rateSeekBar);
        seekBarValue = findViewById(R.id.seekBarValue);
        questionTextView = findViewById(R.id.questionTextView);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);
        previousQuestionButton = findViewById(R.id.previousQuestionButton);

        Intent intent = getIntent();
        choice = intent.getStringExtra(getString(R.string.Message));

        rateTextView.setText(getString(R.string.Rate) + getString(R.string.SPACE) + choice + getString(R.string.SPACE) + getString(R.string.below_please));
        previousQuestionButton.setVisibility(View.INVISIBLE);

        questionHandler();
        seekBarHandler();
    }
    public void nextQuestion(View view)
    {
        switch (questionIndex)
        {
            case 0:
                value0 = rateSeekBar.getProgress();
                rateSeekBar.setProgress(50);
                break;
            case 1:
                value1 = rateSeekBar.getProgress();
                rateSeekBar.setProgress(50);
                break;
            case 2:
                value2 = rateSeekBar.getProgress();
                rateSeekBar.setProgress(50);
                break;
            case 3:
                value3 = rateSeekBar.getProgress();
                rateSeekBar.setProgress(50);
                break;
            case 4:
                value4 = rateSeekBar.getProgress();
                rateSeekBar.setProgress(50);
                break;
            case 5:
                value5 = rateSeekBar.getProgress();
                rateSeekBar.setProgress(50);
                break;
        }
        questionIndex++;
        if (nextQuestionButton.getText() == getString(R.string.Send_To_Teacher))
        {
            questionIndex--;
            totalValue = (value0 + value1 + value2 + value3 + value4 + value5)/6;

            Intent intent = new Intent(this,MainActivity.class);
            Bundle extras = new Bundle();

            extras.putString(getString(R.string.Choice),choice);
            extras.putInt(getString(R.string.TotalValue), totalValue);
            intent.putExtras(extras);
            startActivity(intent);
        }
        else if (questionIndex == list.size()-1)
        {
            nextQuestionButton.setText(getString(R.string.Send_To_Teacher));
            questionTextView.setText(list.get(questionIndex));
        }
        else
        {
            questionTextView.setText(list.get(questionIndex));
            previousQuestionButton.setVisibility(View.VISIBLE);
        }
    }
    public void previousQuestion(View view)
    {
        questionIndex--;

        switch (questionIndex)
        {
            case 0:
                value0 = 0;
                rateSeekBar.setProgress(50);
                break;
            case 1:
                value1 = 0;
                rateSeekBar.setProgress(50);
                break;
            case 2:
                value2 = 0;
                rateSeekBar.setProgress(50);
                break;
            case 3:
                value3 = 0;
                rateSeekBar.setProgress(50);
                break;
            case 4:
                value4 = 0;
                rateSeekBar.setProgress(50);
                break;
            case 5:
                value5 = 0;
                rateSeekBar.setProgress(50);
                break;
        }
        if (questionIndex == 0)
        {
            previousQuestionButton.setVisibility(View.INVISIBLE);
            questionTextView.setText(list.get(questionIndex));
        }
        else if (questionIndex == list.size()-2)
        {
            nextQuestionButton.setText(getString(R.string.Next_Question));
            questionTextView.setText(list.get(questionIndex));
        }
        else
        {
            questionTextView.setText(list.get(questionIndex));
        }
    }

    public void questionHandler()
    {
        list.add(getString(R.string.subject_relavans));
        list.add(getString(R.string.Teacher_overall_performance));
        list.add(getString(R.string.Teacher_preparation));
        list.add(getString(R.string.Amount_of_feedback));
        list.add(getString(R.string.How_good_their_examples_are));
        list.add(getString(R.string.Job_opportunities));
    }

    public void seekBarHandler()
    {
        rateSeekBar.setMax(100);
        rateSeekBar.setProgress(50);
        seekBarValue.setText(String.valueOf(rateSeekBar.getProgress()));

        rateSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                seekBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

    }
}