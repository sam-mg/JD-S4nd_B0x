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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class questions_mcq extends AppCompatActivity {
    private TextView questionTextView;
    private Button continueButton;
    private RadioGroup optionsRadioGroup;
    private String[] questions = {
            "Who invented the Northrop Loom?",
            "What was the key feature of the Northrop Loom that differentiated it from earlier looms?",
            "Who invented the shuttle-changing mechanism for the loom?",
            "What is India’s first cotton cloth mill?",
            "How did the introduction of power looms in India impact the traditional handloom industry?",
            "What were the primary economic factors that influenced the handloom industry?",
            "What technological advancements were made in handloom weaving techniques between 1860 and 1940?",
            "In what ways did the handloom industry contribute to the cultural and artistic heritage of regions where it was prominent?",
            "What were the main markets for handloom products during the period of 1900 to 1960?",
            "How did the Industrial Revolution impact the handloom industry between 1900 and 1940?",
            "Which of the following factors contributed to the unsustainability of traditional looms and dyeing machines in the Indian textile sector?",
            "During the 2010s, what technological advancements did the Indian textile industry begin integrating to enhance efficiency and lower labor expenses?",
            "During the 2000s, what strategies did the Indian textile industry adopt to improve efficiency and reduce environmental impact?",
            "During the 1990s, what was one of the key benefits of introducing CAD and CAM systems in the Indian textile industry?",
            "During the 1980s, what purpose did the textile testing equipment serve in the Indian textile industry?"
    };

    private String[][] options = {
            {"James Hargreaves", "Samuel Crompton", "John Kay", "James Henry Northrop"},
            {"It was manually operated.", "It had an automatic shuttle-changing mechanism.", "It used water power instead of steam power.", "It was made entirely of wood"},
            {"James Henry Northrop", "George Draper and Sons", "Ellias Howe", "James Bullough"},
            {"Surat Textile Park", "Brandix India Apparel City", "Apparel Export Promotion Council", "Bowreah Cotton Mills"},
            {"It led to an increase in the use of handlooms.", "It had no significant impact.", "It resulted in a decline in the handloom industry.", "It promoted handloom products globally."},
            {"Technological advancements and labor costs", "Government subsidies and protectionist policies", "Market demand and availability of raw materials", "Import-export tariffs and international trade agreements"},
            {"Introduction of the power loom", "Development and improvement of shuttle systems", "Invention of synthetic dyes", "All of the above"},
            {"By introducing new weaving techniques from other regions", "By preserving traditional patterns and techniques", "By promoting mass production of textiles", "By eliminating the need for artisanal craftsmanship."},
            {"Local rural markets only", "Urban domestic markets and international colonies", "Exclusive international luxury markets", "Government contracts and military supplies"},
            {"It led to a significant decline due to competition from mechanized mills.", "It had no impact on the handloom industry.", "It boosted the handloom industry by increasing demand for handmade goods.", "It caused the handloom industry to become more mechanized."},
            {"Lack of IoT integration", "High energy consumption", "Limited customization options", "Insufficient water recycling"},
            {"Blockchain and Artificial Intelligence", "Automation and Robotics", "3D Printing and Virtual Reality", "Solar Energy and Wind Power"},
            {"Energy-efficient machinery and blockchain technology", "Energy-efficient machinery and cloud computing", "Energy-efficient machinery, ERP, and SCM systems", "Renewable energy sources and big data analytics"},
            {"Increased usage of renewable energy", "Improved design capabilities and reduced lead times", "Enhanced data storage and retrieval", "Greater integration of social media marketing"},
            {"To enhance marketing strategies", "To improve design and pattern-making", "To check the quality and performance of the material", "To increase energy efficiency"}
    };

    private String[] answers = {
            "James Henry Northrop",
            "It had an automatic shuttle-changing mechanism.",
            "James Henry Northrop",
            "Bowreah Cotton Mills",
            "It resulted in a decline in the handloom industry.",
            "Market demand and availability of raw materials",
            "Development and improvement of shuttle systems",
            "By preserving traditional patterns and techniques",
            "Urban domestic markets and international colonies",
            "It led to a significant decline due to competition from mechanized mills.",
            "High energy consumption",
            "Automation and Robotics",
            "Energy-efficient machinery, ERP, and SCM systems",
            "Improved design capabilities and reduced lead times",
            "To check the quality and performance of the material"
    };

    private List<Integer> questionIndices;
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

        initializeQuestionIndices();

        displayQuestion();
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = optionsRadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId == -1) {
                    Toast.makeText(questions_mcq.this, "Please select an option.", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedOption = selectedRadioButton.getText().toString();
                checkAnswer(selectedOption);

                if (currentQuestionIndex < questionIndices.size() - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    Intent intent = new Intent(questions_mcq.this, Final_Result.class);
                    intent.putExtra("SCORE", correctAnswers);
                    intent.putExtra("TOTAL_QUESTIONS", questionIndices.size());
                    startActivity(intent);
                }
            }
        });
    }

    private void initializeQuestionIndices() {
        questionIndices = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {
            questionIndices.add(i);
        }
        Collections.shuffle(questionIndices);
        questionIndices = questionIndices.subList(0, 5); // Limit to 5 questions
    }

    private void displayQuestion() {
        int questionIndex = questionIndices.get(currentQuestionIndex);
        questionTextView.setText(questions[questionIndex]);

        RadioButton option1RadioButton = (RadioButton) optionsRadioGroup.getChildAt(0);
        RadioButton option2RadioButton = (RadioButton) optionsRadioGroup.getChildAt(1);
        RadioButton option3RadioButton = (RadioButton) optionsRadioGroup.getChildAt(2);
        RadioButton option4RadioButton = (RadioButton) optionsRadioGroup.getChildAt(3);

        option1RadioButton.setText(options[questionIndex][0]);
        option2RadioButton.setText(options[questionIndex][1]);
        option3RadioButton.setText(options[questionIndex][2]);
        option4RadioButton.setText(options[questionIndex][3]);

        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer(String selectedOption) {
        int questionIndex = questionIndices.get(currentQuestionIndex);
        String correctAnswer = answers[questionIndex];
        if (selectedOption.equals(correctAnswer)) {
            correctAnswers++;
        }
    }
}