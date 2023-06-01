package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class IntersectionQuizActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var answer1Button: Button
    private lateinit var answer2Button: Button
    private lateinit var answer3Button: Button
    private lateinit var answer4Button: Button

    private var currentQuestionIndex = 0
    private var numCorrectAnswers = 0
    data class Question(
        val imageResId: Int,
        val correctAnswer: String,
        val incorrectAnswers: List<String>
    )

    private val questions = listOf(
        Question(R.drawable.a, "تمر السيارة الحمراء ثم الصفراء", listOf("تمر السيارة االصفراء و الحمراء مع بعض", "تمر السيارة الصفراء ثم الحمراء", "تمر السيارة الصفراء فقط")),
        Question(R.drawable.b, "تمر السيارتان الزرقاء و الصفراء في نفس الوقت ثم تمر السيارة الحمراء", listOf("تمر السيارة الحمراء ثم تمر السيارتان الزرقاء و الصفراء في نفس الوقت", "تمر الصفراء ثم الزرقاء ثم الحمراء", "تمر الحمراء ثم الزرقاء ثم الصفراء")),
        Question(R.drawable.c, "تمر السيارة الزرقاء ثم الحمراء ثم الصفراء", listOf("تمر السيارة الزرقاء ثم الصفراء ثم الحمراء", "تمر السيارة الحمراء ثم الزرقاء ثم الصفراء", "تمر السيارة الصفراء ثم الحمراء ثم الزرقاء")),
        Question(R.drawable.d, "الصفراء والراجل في نفس الوقت ثم تمر الزرقاء أما الحمراء فتنتظر الضوء الخضر", listOf("الصفراء ثم الراجل في نفس الوقت ثم تمر الزرقاء أما الحمراء فتنتظر الضوء الخضر", "الراجل ثم الصفراء في نفس الوقت ثم تمر الزرقاء أما الحمراء فتنتظر الضوء الخضر", "الزرقاء ثم الصفراء والراجل في نفس الوقت أما الحمراء فتنتظر الضوء الخضر")),
        Question(R.drawable.e, "الحمراء و الزرقاء ثم تمر الصفراء", listOf("تمر الزرقاء ثم تمر الحمراء و الصفراء", "تمر الصفراء ثم تمر الحمراء و الزرقاء", "تمر الحمراء ثم تمر الزرقاء و الصفراء")),
        Question(R.drawable.f, "تمر الحمراء اما الزرقاء و الصفراء ينتضران الضوء الاخضر", listOf("الزرقاء و الصفراء ثم تمر الحمراء", "تمر الحمراء ثم الزرقاء اما الصفراء تنتضر الضوء الاخضر", "تمر الحمراء ثم الصفراء اما الزرقاء فتنتضر الضوء الاخضر")),
        Question(R.drawable.g, "تمر السيارة الصفراء ثم الحمراء ثم الزرقاء", listOf("تمر السيارة الحمراء ثم الصفراء ثم الزرقاء", "تمر السيارة الصفراء ثم الزرقاء ثم الحمراء", "تمر السيارة الصفراء ثم الحمراء ثم الزرقاء")),
        Question(R.drawable.h, "الخضراء تستطيع تجاوز اما الصفراء فلا تستطيع", listOf("لا احد يستطيع التجاوز", "الخضراء و الصفراء تستطيعان التجاوز", "االصفراء تستطيع تجاوز اما الخضراء فلا تستطيع")),
        Question(R.drawable.i, "تمر السيارة الزرقاء ثم البيضاء", listOf("تمر السيارة البيضاء و الزرقاء مع بعض", "تمر السيارة الزرقاء ثم البيضاء", "لا احد تستطيع المرور")),
        Question(R.drawable.j, "تمر الخضراء و الصفراء في نفس الوقت ثم تمر الحمراء", listOf("تمر االحمراء ثم الصفراء ثم تمر الخضراء", "تمر الحمراء ثم الخضراء و الصفراء في نفس الوقت", "تمر الخضراء ثم الصفراء ثم تمر الحمراء")),
        Question(R.drawable.k, "تمر الصفراء و الزرقاء ثم تمر الحمراء", listOf("تمر الحمراء ثم الصفراء و الزرقاء", "تمر الصفراء ثم الزرقاء ثم تمر الحمراء", "تمر الزرقاء ثم الصفراء ثم تمر الحمراء")),
        Question(R.drawable.l, "تمر الزرقاء و الصفراء ثم الحمراء", listOf("تمر الزرقاء ثم الصفراء ثم الحمراء", "تمر الحمراء ثم تمر الزرقاء و الصفراء", "تمر الحمراء ثم تمر الزرقاء ثم الصفراء")),
        Question(R.drawable.m, "تمر السيارتان الحمراء و الزرقاء ثم الصفراء", listOf("تمر الحمراء ثم الزرقاء ثم الصفراء", "تمر الصفراء ثم تمر السيارتان الحمراء و الزرقاء", "تمر الصفراء ثم الحمراء ثم الزرقاء")),
        Question(R.drawable.n, "الصفراء تستطيع المرور أما الحمراء و الزرقاء فتنتظران الضوء الخضر", listOf("الصفراء و الزرقاء تستطيع المرور أما الحمراء فتنتظر الضوء الخضر", "الزرقاء تستطيع المرور أما الحمراء و الصفراء فتنتظران الضوء الخضر", "الحمراء و الزرقاء تستطيع المرور أما الصفراء فتنتظر الضوء الخضر")),
        Question(R.drawable.o, "الزرقاء لا يمكنها التجاوز", listOf("الزرقاء يمكنها التجاوز", "الصفراء تتنحى جانبا", "الصفراء تمنع الزرقاء ")),
        Question(R.drawable.p, "تمر السيارة الزرقاء ثم الحمراء", listOf("تمر السيارة الحمراء و الزرقاء تتوقف", "تمر السيارة الزرقاء و الحمراء معا", "تمر السيارة الحمراء ثم الزرقاء")),
        Question(R.drawable.q, "تمر السيارة الصفراء ثم الزرقاء ثم الحمراء", listOf("تمر السيارة الحمراء ثم الصفراء ثم الزرقاء", "تمر السيارة الزرقاء ثم الصفراء ثم الحمراء", "تمر السيارة الحمراء ثم الزرقاء ثم الصفراء")),
        Question(R.drawable.r, "قف علىّ 150م", listOf("السرعة القصوى 30", "ممنوع التجاوز", "الزامي السير في اليمين")),
        Question(R.drawable.s, "تمر السيارة الصفراء ثم الحمراء ثم الزرقاء", listOf("تمر السيارة الحمراء ثم الزرقاء ثم الصفراء", "تمر السيارة الحمراء ثم االصفراء ثم الزرقاء", "تمر السيارة الزرقاء ثم االصفراء ثم الحمراء")),
        Question(R.drawable.t, "تمر السيارة الزرقاء ثم الحمراء ثم الصفراء", listOf("تمر السيارة الحمراء ثم الصفراء ثم الزرقاء", "تمر السيارة الزرقاء ثم الصفراء ثم الحمراء", "تمر السيارة االحمراء ثم الزرقاء ثم الصفراء")),
        Question(R.drawable.u, "تمر السيارة الحمراء ثم الزرقاء و الصفراء", listOf("تمر السيارة الزرقاء ثم الصفراء ثم الحمراء", "تمر السيارة الصفراء ثم الحمراء ثم الزرقاء", "تمر السيارة الصفراء ثم الزرقاء ثم الحمراء")),
        Question(R.drawable.w, "ترك المرور", listOf("قف", "قف على 150 م", "طريق ذو اولوية")),
        Question(R.drawable.x, "الصفراء والراجل في نفس الوقت ثم تمر الزرقاء أما الحمراء فتنتظر الضوء الخضر", listOf("الصفراء ثم الراجل في نفس الوقت ثم تمر الزرقاء أما الحمراء فتنتظر الضوء الخضر", "الراجل ثم الصفراء في نفس الوقت ثم تمر الزرقاء أما الحمراء فتنتظر الضوء الخضر", "الزرقاء ثم الصفراء والراجل في نفس الوقت أما الحمراء فتنتظر الضوء الخضر")),
        Question(R.drawable.y, "تمر الزرقاء أما الصفراء و الخضراء فتنتظران الضوء الخضر", listOf("تمر الصفراء أما الزرقاء و الخضراء فتنتظران الضوء الخضر", "تمر الخضراء أما الصفراء و الزرقاء فتنتظران الضوء الخضر", "تمر الزرقاء و الصفراء ام الخضراء فتنتظر الضوء الخضر")),
        Question(R.drawable.z, "ترك السيارة الحمراء تمر أولا", listOf("امر انا اولا", "ترك السيارة الحمراء تمر أولا", "التوقف بجانب الطريق")),
        Question(R.drawable.aa, "السيارة الزرقاء تستطيع التجاوز", listOf("السيارة الزرقاء لا تستطيع التجاوز", "السيارة الحمراء تمنع الزرقاء", "السيارة الحمراء تتوقف")),
        Question(R.drawable.bb, "تمر الصفراء و الخضراء أما الزرقاء و الحمراء فتنتظران إشارة الشرطي", listOf("تمر الزرقاء و الحمراء أما الصفراء و الخضراء فتنتظران إشارة الشرطي", "تمر الصفراء و الحمراء أما الزرقاء و الخضراء فتنتظران إشارة الشرطي", "تمر الزرقاء و الخضراء أما الصفراء و الحمراء فتنتظران إشارة الشرطي")),
        Question(R.drawable.cc, "تمر السيارة الزرقاء ثم الصفراء", listOf("تمر السيارة الصفراء و الزرقاء مع بعض", "تمر السيارة الصفراء ثم الزرقاء", "تمر السيارة الزرقاء و الصفراء تنتضر")),
        Question(R.drawable.dd, "تتوقف الزرقاء و تمر الحمراء", listOf("تمران معا", "تتوقف الحمراء و تمر الزرقاء", "تتوقفان معا")),
        Question(R.drawable.ff, "السيارة التي تجرر مقطورة تتوقف لتفسح الطريق الحمراء", listOf(" الحمراء تتوقف لتفسح الطريق السيارة التي تجرر مقطورة", "يتوقفان معا", "يمران معا")),
        Question(R.drawable.gg, "السيارة الزرقاء تستطيع التجاوز", listOf("السيارة الزرقاء لا تستطيع التجاوز", "السيارة االحمراء تستطيع التجاوز", "السيارة الحمراء لا تستطيع التجاوز")),


        )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intersection_quiz)



        questionTextView = findViewById(R.id.question_text_view)
        imageView = findViewById(R.id.image_view)
        answer1Button = findViewById(R.id.answer1_button)
        answer2Button = findViewById(R.id.answer2_button)
        answer3Button = findViewById(R.id.answer3_button)
        answer4Button = findViewById(R.id.answer4_button)

        answer1Button.setOnClickListener { checkAnswer(answer1Button.text.toString()) }
        answer2Button.setOnClickListener { checkAnswer(answer2Button.text.toString()) }
        answer3Button.setOnClickListener { checkAnswer(answer3Button.text.toString()) }
        answer4Button.setOnClickListener { checkAnswer(answer4Button.text.toString()) }

        showNextQuestion()
    }

    private fun showNextQuestion() {
        val question = questions[currentQuestionIndex]
        imageView.setImageResource(question.imageResId)
        questionTextView.text = "اختر الاجابة الصحيحة"
        val answerOptions = question.incorrectAnswers.toMutableList()
        answerOptions.add(question.correctAnswer)
        answerOptions.shuffle()
        answer1Button.text = answerOptions[0]
        answer2Button.text = answerOptions[1]
        answer3Button.text = answerOptions[2]
        answer4Button.text = answerOptions[3]
    }

    private fun checkAnswer(selectedAnswer: String) {
        val question = questions[currentQuestionIndex]
        if (selectedAnswer == question.correctAnswer) {
            numCorrectAnswers++
            Toast.makeText(this, "اجابة صحيحة", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "اجابة خاطئة", Toast.LENGTH_SHORT).show()
        }
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            showNextQuestion()
        } else {
            val resultText = "تحصلت على $numCorrectAnswers من ${questions.size}"
            AlertDialog.Builder(this)
                .setTitle("النتيجة")
                .setMessage(resultText)
                .setPositiveButton("حسنا") { _, _ -> finish() }
                .setCancelable(false)
                .show()
        }
    }
}