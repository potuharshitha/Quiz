package com.example.pnageswarao.quiz.db;


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pnageswarao.quiz.model.Question5;

/**
 * Created by alfiroj on 5/13/16.
 */
public class DbHelper4 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Quiz444";

    // Table name
    private static final String TABLE_QUESTION = "question";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUESION = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private static final String KEY_OPTD= "optd"; //option d

    private SQLiteDatabase myDatabase;

    public DbHelper4(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        myDatabase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESION
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC +" TEXT, "+KEY_OPTD+" TEXT)";

        db.execSQL(sql);

        addQuestions();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);

        // Create tables again
        onCreate(db);
    }

    public int rowCount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

    public List<Question5> getAllQuestions() {

        List<Question5> quesList = new ArrayList<Question5>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        myDatabase=this.getReadableDatabase();

        Cursor cursor = myDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question5 quest = new Question5();
                quest.setId(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOptionA(cursor.getString(3));
                quest.setOptionB(cursor.getString(4));
                quest.setOptionC(cursor.getString(5));
                quest.setOptionD(cursor.getString(6));

                quesList.add(quest);

            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    private void addQuestions()
    {
        //format is question-option1-option2-option3-option4-answer

        Question5 q1=new Question5("Which of the following is not OOPS concept in Java","Inheritance", "Encapsulation", "Polymorphism", "Compilation","Compilation");
        this.addQuestion(q1);

        Question5 q2=new Question5(" When does method overloading is determined","At run time ", "At compile time", "At coding time", " At execution time","At compile time");
        this.addQuestion(q2);

        Question5 q3=new Question5("Which concept of Java is achieved by combining methods and attribute into a class?","Encapsulation", " Inheritance", "Polymorphism", "Abstration","Encapsulation");
        this.addQuestion(q3);

        Question5 q4=new Question5("What is it called if an object has its own lifecycle and there is no owner","Aggregation", "Composition", "Encapsulation", "Association","Association");
        this.addQuestion(q4);

        Question5 q5=new Question5(" What would be the behaviour if this() and super() used in a method","Runtime error", "Throws exception", "compile time error", "Runs successfully","compile time error");
        this.addQuestion(q5);
        Question5 q6=new Question5(" What is not the use of “this” keyword in Java","Passing itself to another method", "Calling another constructor in constructor chaining", "Referring to the instance variable when local variable has the same name", " Passing itself to method of the same class"," Passing itself to method of the same class");
        this.addQuestion(q6);
        Question5 q7=new Question5("String in Java is a","character array", "class", "object", "variable","class");
        this.addQuestion(q7);
        Question5 q8=new Question5("Which of these method of String class can be used to test to strings for equality"," isequal()", "isequals()", "equals()", "equal()","equals()");
        this.addQuestion(q8);
        Question5 q9=new Question5("Modulus operator, %, can be applied to which of these","Integers", "Floating – point numbers", "Both Integers and floating – point numbers", "None of the mentioned","Both Integers and floating – point numbers");
        this.addQuestion(q9);
        Question5 q10=new Question5("Which component is used to compile, debug and execute java program","JVM", "JDK", "JRE", "JIT","JDK");
        this.addQuestion(q10);
        Question5 q11=new Question5("Which of the below is invalid indentifier with main method","private", "public", "static", "final","private");
        this.addQuestion(q11);
        Question5 q12=new Question5("What is the extension of compiled java classes",".java", ".txt", ".js", ".class",".class");
        this.addQuestion(q12);
        Question5 q13=new Question5(" Generics does not work wit","Array", "Set", "Tree", "List","Array");
        this.addQuestion(q13);
        Question5 q14=new Question5("Where is array stored in memory"," stack space", " heap space", "heap space and stack space", "first generation memory","heap space");
        this.addQuestion(q14);
        Question5 q15=new Question5("Which of these can be returned by the operator &","Integer", "Boolean", "Integer or Boolean", "Character","Integer or Boolean");
        this.addQuestion(q15);



    }


    // Adding new question
    public void addQuestion(Question5 quest) {

        ContentValues values = new ContentValues();
        values.put(KEY_QUESION, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOptionA());
        values.put(KEY_OPTB, quest.getOptionB());
        values.put(KEY_OPTC, quest.getOptionC());
        values.put(KEY_OPTD, quest.getOptionD());

        // Inserting Row
        myDatabase.insert(TABLE_QUESTION, null, values);
    }


}
