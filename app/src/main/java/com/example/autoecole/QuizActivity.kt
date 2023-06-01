package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.autoecole.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var rightAnswer: String? =null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT=10
    // Liste de questions et réponses du quiz
    private  val quizData = mutableListOf(
        mutableListOf("ما هي الاحتياطات الواجب أخدها عند التجاوز ؟", "التجاوز من الجهة اليسرى", "التجاوز من الجهة اليمنى", "اشعال الأضواء الخلفية", "استعمال المنبه الصوتي"),

        mutableListOf("عرف مسافة ألمان", "مسافة السائق بينه وبين السيارة التي أمامه", "مسافة التي السائق بينه وبين السيارة التي ورائه", "مسافة التي السائق بينه وبين السيارة التي بجانبه", "مسافة التي السائق بينه وبين السيارة التي بعده"),
        mutableListOf("ماذا تفعل عند الاقتراب من ملتقى الطرق؟", "RASVO أطبق قانون", "KASVO أطبق قانون", "MAKSO أطبق قانون", "TAS أطبق قانون"),
        mutableListOf("أي من هذه من المركبات الاولوية", "الشرطة", "شاحنة", "الاسعاف", "سيارة اجرة"),
        mutableListOf("أي من هذه من ممنوعات في الطريق السريع", "الوقوف والتوقف", "التجاوز من اليسار", "ممنوع منبه الصوت", "السياقة في حالة سكر"),
        mutableListOf("أي من هذه من حالت التجاوز على اليمين؟", "لسائق الامامي ينوي الدوران إلى اليسار", "الوقوف والتوقف", "السير في الاتجاه المعاكس", " قص الشريط الخطية "),
        mutableListOf("تعريف الوقوف", "ترك السيارة لمدة طويلة مع إيقاف المحرك", "ترك السيارة لمدة طويلة مع اشغال المحرك", "ترك السيارة لمدة قصيرة مع إيقاف المحرك", "ترك السيارة لمدة قصيرة مع إشغال المحرك"),
        mutableListOf("أي من هذه من أخطار السياقة في الليل؟", "الرؤية الرديئة", "الضباب", "الانزلاق", "خروج حيواتات"),
        mutableListOf("أي من هذه من حالت حق اليمين واليسار؟", " الخروج من الطريق الريفي ", " الخروج من المدينة ", " الخروج من طريق السيار  ", " الخروج من الطريق جبلي "),
        mutableListOf("عرف رخصة السياقة صنف ب", " مفاعد8وزن المركبة 3.5 طن مع ", "وزن المركبة التي أقودها 3.5 طن", "  مقعد12وزن المركبة التي أقودها 3.5 طن مع ", " وزن المركبة التي أقودها 7.5 طن مع 8 مقعد "),
        mutableListOf("ما هي نسبة الكحول التي يعاقب عليها القانون", "0,2", "0,6", "0,7", "0,4"),


        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        quizData.shuffle() // Mélanger les questions du quiz
        showNextQuiz() // Afficher la première question
    }

    // Afficher la prochaine question
    fun showNextQuiz() {
        binding.countlabel.text = getString(R.string.count_label, quizCount)
        val quiz = quizData[0]
        rightAnswer = quiz[1]
        binding.questionlabel.text = quiz[0]
        quiz.removeAt(0)
        quiz.shuffle() // Mélanger les réponses

        // Affecter les réponses aux boutons de réponse
        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]

        quizData.removeAt(0) // Supprimer la question actuelle de la liste des questions
    }

    // Vérifier la réponse sélectionnée par l'utilisateur
    fun checkAnswer(view: View) {
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "اجابة صحيحة"
            rightAnswerCount++
        } else {
            alertTitle = "اجابة خاطئة"
        }

        // Afficher une boîte de dialogue indiquant si la réponse est correcte ou incorrecte
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("الجواب الصحيح:$rightAnswer")
            .setPositiveButton("حسنا") { dialogInterface, _ ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    // Vérifier si le nombre de questions atteint le nombre total de questions
    fun checkQuizCount() {
        if (quizCount == QUIZ_COUNT) {
            val resultText = "تحصلت على $rightAnswerCount من $quizCount"
            // Afficher une boîte de dialogue avec le score final et proposer de terminer l'activité
            AlertDialog.Builder(this)
                .setTitle("النتيجة")
                .setMessage(resultText)
                .setPositiveButton("حسنا") { _, _ -> finish() }
                .setCancelable(false)
                .show()
        } else {
            quizCount++
            showNextQuiz() // Afficher la prochaine question
        }
    }
}