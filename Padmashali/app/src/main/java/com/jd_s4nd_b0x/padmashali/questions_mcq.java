package com.jd_s4nd_b0x.padmashali;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class questions_mcq extends AppCompatActivity {
    private TextView questionTextView;
    private Button option1Button, option2Button, option3Button, option4Button, continueButton;
    RadioGroup optionsRadioGroup;

    private String[] questions = {"What is the capital of France?", "Which planet is known as the Red Planet?", "Who wrote 'Romeo and Juliet'?", "What is the chemical symbol for water?", "What is the tallest mammal on Earth?"};
    private String[][] options = {{"Paris", "London", "Berlin", "Rome"}, {"Mars", "Jupiter", "Venus", "Mercury"}, {"William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"}, {"H2O", "CO2", "NaCl", "NH3"}, {"Giraffe", "Elephant", "Hippo", "Rhino"}};
    private String[] answers = {"Paris", "Mars", "William Shakespeare", "H2O", "Giraffe"};

    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questions_mcq);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        continueButton = findViewById(R.id.continueButton);

        displayQuestion();
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Find the selected radio button
                int selectedRadioButtonId = optionsRadioGroup.getCheckedRadioButtonId();

                // Check if any radio button is selected
                if (selectedRadioButtonId == -1) {
                    // If no radio button is selected, show a toast message
                    Toast.makeText(questions_mcq.this, "Please select an option.", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                // Check the answer
                String selectedOption = selectedRadioButton.getText().toString();
                checkAnswer(selectedOption);

                // Move to the next question or show the result
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    Intent intent = new Intent(questions_mcq.this, Final_Result.class);
                    intent.putExtra("SCORE", correctAnswers);
                    intent.putExtra("TOTAL_QUESTIONS", questions.length);
                    startActivity(intent);
                }
            }
        });

    }
    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);

        RadioButton option1RadioButton = (RadioButton) optionsRadioGroup.getChildAt(0);
        RadioButton option2RadioButton = (RadioButton) optionsRadioGroup.getChildAt(1);
        RadioButton option3RadioButton = (RadioButton) optionsRadioGroup.getChildAt(2);
        RadioButton option4RadioButton = (RadioButton) optionsRadioGroup.getChildAt(3);

        option1RadioButton.setText(options[currentQuestionIndex][0]);
        option2RadioButton.setText(options[currentQuestionIndex][1]);
        option3RadioButton.setText(options[currentQuestionIndex][2]);
        option4RadioButton.setText(options[currentQuestionIndex][3]);

        // Reset checked state of all radio buttons
        optionsRadioGroup.clearCheck();
    }
    private void checkAnswer(String selectedOption) {
        String correctAnswer = answers[currentQuestionIndex];
        if (selectedOption.equals(correctAnswer)) {
            correctAnswers++;
        }
    }
}