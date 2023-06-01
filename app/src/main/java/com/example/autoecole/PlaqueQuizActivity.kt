package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class PlaqueQuizActivity : AppCompatActivity() {
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
        Question(R.drawable.trentekm, "السرعة الادنى : 30كم", listOf("اكتظاظ في حركة السير أمامك", "خطر غير معين ", "قف عند حاجز الجمارك")),
        Question(R.drawable.anonymetemp, "خطر غير معين ", listOf("خطر مؤقت طريق زلق", "حظيرة سيارات", "طريق مسدود")),
        Question(R.drawable.attentiontrain, "ممر سكة حديدية محروس بخط مكهرب", listOf("طريق مسدود", "قف عند حاجز الجمارك", "قف")),
        Question(R.drawable.chaaldaw, "اشعال الاضواء", listOf("غابة سهلة الاحتراق", "لافتة إرشاد قبل محول في طريق سريعة", "قف عند حاجز الجمارك")),
        Question(R.drawable.deuxsens, "السرعة محددة على المسلكين", listOf("مسلك اجباري للحافلات", "لافتة إرشاد قبل محول في طريق سريعة", "اشعال الاضواء")),
        Question(R.drawable.douane, "قف عند حاجز الجمارك", listOf("نهاية السبيل الاجباري للخيالة", "خطر تقاطع طريقين ثانويين", "طريق وعرة أو مشوشة")),
        Question(R.drawable.fininterdictiona, "نهاية منع التجاوز على عربات نقل البضائع", listOf("لافتة إرشاد قبل محول في طريق سريعة", "نهاية الطريق المضمون", "اشعال الاضواء")),
        Question(R.drawable.fininterdictionhssan, "نهاية السبيل الاجباري للخيالة", listOf("اشعال الاضواء", "حظيرة سيارات", "غابة سهلة الاحتراق")),
        Question(R.drawable.finpriorite, "نهاية الطريق المضمون", listOf("اجباري استعمال السلاسل الجبلية", "حظيرة سيارات", "طريق وعرة أو مشوشة")),
        Question(R.drawable.finsensintersit, "الخروج من منطقة كان الوقوف فيها ممنوع", listOf("طريق مسدود", "موقف ترامواي", "قف")),
        Question(R.drawable.help, "مسلك الطوارئ", listOf("Incorrect Answer 1", "طريق مسدود", "طريق وعرة أو مشوشة")),
        Question(R.drawable.interditdemitour, "ممنوع القيام بنصف دورة", listOf("اجباري استعمال السلاسل الجبلية", "نهاية الطريق المضمون", "ممنوع دخول الدراجات النارية")),
        Question(R.drawable.interditparking, "ممنوع الوقوف و التوقف", listOf("ميدان تخييم القافلات المجرورة", "اجباري استعمال السلاسل الجبلية", "قف")),
        Question(R.drawable.interdittediwtjib, "افسح المرور للاتجاه المعاكس", listOf("نهاية السبيل الاجباري للخيالة", "نهاية الطريق المضمون", "موقف ترامواي")),
        Question(R.drawable.interdittout, "ممنوع المرور في الاتجاهين", listOf("مسلك اجباري للحافلات", "اتجاه ممنوع", "طريق وعرة أو مشوشة")),
        Question(R.drawable.maktora, "ميدان تخييم القافلات المجرورة", listOf("طريق وعرة أو مشوشة", "آلة ضوئية على بعد 50-150 متر", "اكتظاظ في حركة السير أمامك")),
        Question(R.drawable.parking, "حظيرة سيارات", listOf("اكتظاظ في حركة السير أمامك", "خطر تقاطع طريقين ثانويين", "شارع مختلط")),
        Question(R.drawable.passagepietons, "خطر ممر الراجلين", listOf("ميدان تخييم القافلات المجرورة", "آلة ضوئية على بعد 50-150 متر", "خطر رياح جانبية")),
        Question(R.drawable.pedofile, "طريق للراجلين", listOf("اولوية السير للاتجاه المؤدي", "اكتظاظ في حركة السير أمامك", "نهاية الطريق السريعة")),
        Question(R.drawable.priorite, "طريق مضمون ذو اولوية", listOf("اكتظاظ في حركة السير أمامك", "خطر غير معين ", "قف عند حاجز الجمارك")),
        Question(R.drawable.routedebus, "مسلك اجباري للحافلات", listOf("اولوية السير للاتجاه المؤدي", "لافتة إرشاد قبل محول في طريق سريعة ", "طريق وعرة أو مشوشة")),
        Question(R.drawable.routeglissante, "خطر طريق زلق", listOf("مسلك اجباري للحافلات", "طريق وعرة أو مشوشة", "ممنوع دخول الدراجات النارية")),
        Question(R.drawable.routeglissante, "خطر طريق زلق", listOf("مسلك اجباري للحافلات", "طريق وعرة أو مشوشة", "ممنوع دخول الدراجات النارية")),
        Question(R.drawable.routemassdoud, "طريق مسدود", listOf("اكتظاظ في حركة السير أمامك", "منعطف خطير على اليسار", "ممنوع دخول التراكتورات ومركبات العمل")),
        Question(R.drawable.sensinterdit, "اتجاه ممنوع", listOf("ممنوع دخول الدراجات النارية", "موقف ترامواي", "ممنوع مرور الراجلين")),
        Question(R.drawable.sensla, "اجباري استعمال السلاسل الجبلية", listOf("خطر مؤقت طريق زلق", "فندق", "مسلك اجباري للحافلات")),
        Question(R.drawable.stop, "قف", listOf("طريق ذو اتجاه واحد", "ممنوع دخول الدراجات النارية", "طريق وعرة أو مشوشة")),
        Question(R.drawable.taille, "ممنوع عبور العربات التي يفوق ارتفاعها 3.5 م", listOf("ممنوع الوقوف", "فندق", "خطر تقاطع طريقين ثانويين")),
        Question(R.drawable.tediwtjib, "اولوية السير للاتجاه المؤدي", listOf("ممنوع دخول التراكتورات ومركبات العمل", "آلة ضوئية على بعد 50-150 متر", "اجباري استعمال السلاسل الجبلية")),
        Question(R.drawable.tournerright, "دوران اجباري نحو اليمين عند التقاطع القادم", listOf("طريق وعرة أو مشوشة", "نهاية تحديد السرعة", "ممنوع مرور الراجلين")),
        Question(R.drawable.tramway, "موقف ترامواي", listOf("منعطف خطير على اليسار", "آلة ضوئية على بعد 50-150 متر", "نهاية منع التجاوز ")),
        Question(R.drawable.vent, "خطر رياح جانبية", listOf("خطر رياح جانبية", "ممنوع دخول الدراجات النارية", "ممنوع مرور الراجلين")),
        Question(R.drawable.vheculelents, "مسلك اجباري للمركبات البطيئة", listOf("اكتظاظ في حركة السير أمامك", "لافتة إرشاد قبل محول في طريق سريعة ", "نهاية تحديد السرعة")),
        Question(R.drawable.virage, "منعطف خطير على اليسار", listOf("طريق وعرة أو مشوشة", "ممنوع الوقوف", "نهاية منع التجاوز "))
        )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plaque_quiz)



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
        questions.shuffled()
        showNextQuestion()
    }

    private fun showNextQuestion() {
        val question = questions[currentQuestionIndex]
        imageView.setImageResource(question.imageResId)
        questionTextView.text = "الى ماذا ترمز هذه الاشارة"
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