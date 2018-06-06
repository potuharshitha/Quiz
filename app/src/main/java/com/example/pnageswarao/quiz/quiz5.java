package com.example.pnageswarao.quiz;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pnageswarao.quiz.db.DbHelper4;
import com.example.pnageswarao.quiz.model.Question5;

import java.util.ArrayList;
import java.util.List;

public class quiz5 extends AppCompatActivity {

    private List<Question5> questionsList;
    private Question5 currentQuestion5, previousQuestion5;

    private TextView txtQuestion,tvNoOfQs;
    private RadioButton rbtnA, rbtnB, rbtnC,rbtnD;
    private Button btnNext,btnprevious;

    private int obtainedScore=0;
    private int questionId=0;

    private int answeredQsNo=0;

    ArrayList<String> myAnsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);




        //Initialize the view
        init();

        //Initialize the database
        final DbHelper4 dbHelper4 =new DbHelper4(this);
        questionsList= dbHelper4.getAllQuestions();
        currentQuestion5 =questionsList.get(questionId);
        //  previousQuestion5=questionsList.get(questionId);

        //Set question
        setQuestionsView();

        //Check and Next


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());

                Log.e("Answer ID", "Selected Positioned  value - "+grp.getCheckedRadioButtonId());

                if(answer!=null){
                    Log.e("Answer", currentQuestion5.getANSWER() + " -- " + answer.getText());
                    //Add answer to the list
                    myAnsList.add(""+answer.getText());

                    if(currentQuestion5.getANSWER().equals(answer.getText())){
                        obtainedScore++;
                        Log.e("comments", "Correct Answer");
                        Log.d("score", "Obtained score " + obtainedScore);
                    }else{
                        Log.e("comments", "Wrong Answer");
                    }
                    if(questionId< dbHelper4.rowCount()){
                        currentQuestion5 =questionsList.get(questionId);
                        setQuestionsView();
                    }else{
                        Intent intent;
                        intent = new Intent(quiz5.this,ResultActivity5.class);

                        Bundle b = new Bundle();
                        b.putInt("score", obtainedScore);
                        b.putInt("totalQs", questionsList.size());
                        b.putStringArrayList("myAnsList", myAnsList);
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();

                    }

                }else{
                    Log.e("comments", "No Answer");
                }

                //Need to clear the checked item id
                grp.clearCheck();


            }//end onClick Method
        });


    }


    public void init(){
        tvNoOfQs=(TextView)findViewById(R.id.tvNumberOfQuestions);
        txtQuestion=(TextView)findViewById(R.id.tvQuestion);
        rbtnA=(RadioButton)findViewById(R.id.radio0);
        rbtnB=(RadioButton)findViewById(R.id.radio1);
        rbtnC=(RadioButton)findViewById(R.id.radio2);
        rbtnD=(RadioButton)findViewById(R.id.radio3);

        btnNext=(Button)findViewById(R.id.btnNext);

        myAnsList = new ArrayList<String>();
    }


    private void setQuestionsView()
    {
        rbtnA.setChecked(false);
        rbtnB.setChecked(false);
        rbtnC.setChecked(false);
        rbtnD.setChecked(false);

        answeredQsNo=questionId+1;
        tvNoOfQs.setText("Questions "+answeredQsNo+" of "+questionsList.size());

        txtQuestion.setText(currentQuestion5.getQUESTION());
        rbtnA.setText(currentQuestion5.getOptionA());
        rbtnB.setText(currentQuestion5.getOptionB());
        rbtnC.setText(currentQuestion5.getOptionC());
        rbtnD.setText(currentQuestion5.getOptionD());

        questionId++;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
